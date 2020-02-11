package com.study.cases;

import com.study.config.TestConfig;
import com.study.model.UpdateUserInfoCase;
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
import java.util.ArrayList;
import java.util.List;

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "更改用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {

        SqlSession sqlSession = DatabaseUtil.getSqlSession();

        UpdateUserInfoCase updateUserInfo = sqlSession.selectOne("updateUserInfoTest",1);

        System.out.println(updateUserInfo.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        String resultJson = getResultJson(updateUserInfo);

        User user = sqlSession.selectOne(updateUserInfo.getExpected(), updateUserInfo);
        List userList = new ArrayList();
        JSONArray userJson = new JSONArray(userList);

        Thread.sleep(3000);

//        Assert.assertEquals(resultJson.length(), userJson.length());
//
//        for(int i=0;i<userJson.length();i++) {
//            JSONObject expected = (JSONObject) resultJson.get(i);
//            JSONObject actual = (JSONObject) userJson.get(i);
//
//            Assert.assertEquals(expected.toString(), actual.toString());
//        }

        //System.out.println(user.toString());
        Assert.assertNotNull(user);
        Assert.assertNotNull(resultJson);


    }


    @Test(dependsOnGroups = "loginTrue", description = "删除用户")
    public void deleteUserInfo() throws IOException, InterruptedException {

        SqlSession sqlSession = DatabaseUtil.getSqlSession();

        UpdateUserInfoCase updateUserInfo = sqlSession.selectOne("updateUserInfoTest",2);

        System.out.println(updateUserInfo.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        String resultJson = getResultJson(updateUserInfo);

        Thread.sleep(3000);

        User user = sqlSession.selectOne(updateUserInfo.getExpected(), updateUserInfo);
//        List userList = new ArrayList();
//        userList.add(user);

        //List<User> userList = sqlSession.selectList(updateUserInfo.getExpected(), updateUserInfo);

        //JSONArray userJson = new JSONArray(userList);
        //System.out.println(user.toString());

//        Assert.assertEquals(resultJson.length(), userJson.length());
//
//        for(int i=0;i<userJson.length();i++) {
//            JSONObject expected = (JSONObject) resultJson.get(i);
//            JSONObject actual = (JSONObject) userJson.get(i);
//
//            Assert.assertEquals(expected.toString(), actual.toString());
//    }

         Assert.assertNotNull(user);
         Assert.assertNotNull(resultJson);

    }

    private String getResultJson(UpdateUserInfoCase updateUserInfo) throws IOException {

        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();

        param.put("id", updateUserInfo.getUserid());
        param.put("username", updateUserInfo.getUsername());
        param.put("age", updateUserInfo.getAge());
        param.put("sex", updateUserInfo.getSex());
        param.put("permission", updateUserInfo.getPermission());
        param.put("isDelete", updateUserInfo.getIsDelete());

        post.setHeader("content-type", "application/json");
        StringEntity stringEntity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(stringEntity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");

//        List resultList = Arrays.asList(result);
//        JSONArray resultJson = new JSONArray(resultList);
        System.out.println(result);
        //return Integer.parseInt(result);
        return result;

    }

}
