package com.study.cases;

import com.google.common.base.Objects;
import com.study.config.TestConfig;
import com.study.model.GetUserInfoCase;
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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取userId为1的用户信息")
    public void getUserInfo() throws IOException {

        SqlSession sqlSession = DatabaseUtil.getSqlSession();

        GetUserInfoCase getUserInfoTest = sqlSession.selectOne("getUserInfoTest", 1);

        System.out.println(getUserInfoTest.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        JSONArray resultJson = getResultJson(getUserInfoTest);

        // userTep = sqlSession.selectList(getUserInfoTest.getExpected(), getUserInfoTest);
        User userTep = sqlSession.selectOne(getUserInfoTest.getExpected(), getUserInfoTest);
        List userList = new ArrayList();
        userList.add(userTep);

        JSONArray userListJson = new JSONArray(userList);

        //数据顺序不一致，两种不相等
       // Assert.assertEquals(resultJson, userListJson); //只有一个userId数据比较，所以只有一个即可

        JSONArray resultJsonSeq = new JSONArray(resultJson.getString(0));

        //Assert.assertEquals(userListJson, resultJsonSeq);//仍然报错
        Assert.assertEquals(userListJson.toString(), resultJsonSeq.toString());

    }

    private JSONArray getResultJson(GetUserInfoCase getUserInfoTest) throws IOException {

        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();

        param.put("id", getUserInfoTest.getUserid());
        //param.put("expected", getUserInfoTest.getExpected());

        post.setHeader("content-type", "application/json");
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        StringEntity stringEntity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(stringEntity);

        //响应
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        String result = EntityUtils.toString(response.getEntity(), "utf-8");

        //JSONArray resultJson = new JSONArray(result);

        List resultList = Arrays.asList(result);
        JSONArray resultJson = new JSONArray(resultList);

        return resultJson;

    }

}
