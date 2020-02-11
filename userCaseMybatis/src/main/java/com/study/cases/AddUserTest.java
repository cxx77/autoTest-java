package com.study.cases;

import com.study.config.TestConfig;
import com.study.model.AddUserCase;
import com.study.model.User;
import com.study.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    //必须依赖登陆成功的组
    @Test(dependsOnGroups = "loginTrue", description = "增加新用户测试")
    public void addUser() throws IOException, InterruptedException {

        SqlSession sqlSession = DatabaseUtil.getSqlSession();

        AddUserCase addUser = sqlSession.selectOne("addUserTest",1);

        System.out.println(addUser.toString());
        System.out.println(TestConfig.addUserUrl);

        //发请求，获取结果
        String result = getResult(addUser);

        Thread.sleep(3000);

        //验证返回结果
        User user = sqlSession.selectOne("addUser", addUser);
        Assert.assertEquals(addUser.getExpected(), result);

    }

    private String getResult(AddUserCase addUser) throws IOException {

        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();

        param.put("username", addUser.getUsername());
        param.put("password", addUser.getPassword());
        param.put("age",addUser.getAge());
        param.put("sex",addUser.getSex());
        param.put("permission",addUser.getPermission());
        param.put("isDelete",addUser.getIsDelete());

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

        return result;

    }

}
