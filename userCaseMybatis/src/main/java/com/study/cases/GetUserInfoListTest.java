package com.study.cases;

import com.study.config.TestConfig;
import com.study.model.GetUserListCase;
import com.study.model.User;
import com.study.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoListTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户信息")
    public void getUserInfoList() throws IOException, InterruptedException {

        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserInfoListCase = session.selectOne("getUserInfoListTest", 1);

        System.out.println(getUserInfoListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //发送请求获取结果
        JSONArray result = getResult(getUserInfoListCase);

        Thread.sleep(3000);
        //验证
        //Assert.assertEquals(getUserInfoListCase.getExpected(), result);
        List<User> userList = session.selectList(getUserInfoListCase.getExpected(),getUserInfoListCase);

        for(User u:userList){
            System.out.println("获取的user："+u.toString());
        }

        JSONArray userListJson = new JSONArray(userList);

        Assert.assertEquals(userListJson.length(), result.length());

        for(int i=0;i<userListJson.length();i++) {
            JSONObject expect = (JSONObject) result.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);

            Assert.assertEquals(expect.toString(), actual.toString());
        }

    }

    private JSONArray getResult(GetUserListCase getUserInfoListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        //HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();

        param.put("username", getUserInfoListCase.getUsername());
        param.put("age", getUserInfoListCase.getAge());
        param.put("sex", getUserInfoListCase.getSex());
        //param.put("sex",1);
       // param.put("expected", getUserInfoListCase.getExpected());

        //设置头信息
        post.setHeader("content-type", "application/json");

        StringEntity stringEntity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(stringEntity);

        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        //返回结果
        String result;

        //响应
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        JSONArray resultJson = new JSONArray(result);

        return resultJson;


    }

}
