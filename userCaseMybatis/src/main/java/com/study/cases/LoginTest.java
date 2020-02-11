package com.study.cases;

import com.study.config.TestConfig;
import com.study.model.InterfaceName;
import com.study.model.LoginCase;
import com.study.utils.ConfigFile;
import com.study.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoginTest {

    @BeforeTest(groups = "loginTrue", description = "测试准备工作，获取HttpClient对象")
    public void beforeTest() {
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();

    }

    @Test(groups = "loginTrue", description = "用户登陆成功接口测试")
    public void loginTrue() throws IOException {

        SqlSession session;
        session = DatabaseUtil.getSqlSession();

        LoginCase loginCase = session.selectOne("loginCase",2);

        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //发送请求
        String result = getResult(loginCase);

        //验证结果
        Assert.assertEquals(loginCase.getExpected(), result);

    }


    @Test(groups = "loginFalse", description = "用户登陆失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();

        LoginCase loginCase = sqlSession.selectOne("loginCase",1);

        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //发送请求
        String result = getResult(loginCase);

        //验证结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }


    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();

        param.put("username", loginCase.getUsername());
        param.put("password", loginCase.getPassword());
//        param.put("age",addUser.getAge());
//        param.put("sex",addUser.getSex());
//        param.put("permission",addUser.getPermission());
//        param.put("isDelete",addUser.getIsDelete());

        //设置头信息
        post.setHeader("content-type", "application/json");

        StringEntity stringEntity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(stringEntity);

        //设置cookies
        //TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
       // TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();

        //返回结果
        String result;

        //响应
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        //重要的一点：
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();

        return result;



    }

}
