package com.study.httpclientstudy.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class cookiesForPost {
    private String url;
    private ResourceBundle bundle;

    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA); // properties后缀不用写，自动找到result下的文件
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;

        HttpGet get = new HttpGet(testUrl);

        DefaultHttpClient client = new DefaultHttpClient();
        //HttpClient client = HttpClientBuilder.create().build();//获取DefaultHttpClient请求

        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();

        for(Cookie cookie : cookieList) {
            String key = cookie.getName();
            String value = cookie.getValue();

            System.out.println("cookie name = " + key + ";" + "cookie value = " + value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"}) //依赖testGetCookies()方法
    public void testPostWithCookies() throws IOException {
        String uri = bundle.getString("test.post.with.cookies");
        String testUrl = this.url + uri;

        //声明一个方法，Post方法
        HttpPost post = new HttpPost(testUrl);

        //声明一个client方法，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();

        //添加参，pom中加入json
        JSONObject paramjson = new JSONObject();
        paramjson.put("name","liuxiaoxi");
        paramjson.put("age","2");

        //设置请求头信息，设置header
        post.setHeader("content-type","application/json");

        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(paramjson.toString(),"utf-8");
        post.setEntity(entity);

        /*
        //设置cookies信息 -- client方法
        client.setCookieStore(this.store);

        HttpResponse response = client.execute(get);


        //获取响应的状态码
        int responseStatus = response.getStatusLine().getStatusCode();

        System.out.println(responseStatus);

        if(responseStatus == 200) {
            //get返回的hi文本信息
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }
        else {
            System.out.println("false");
        }

         */

        //声明i 个对象来进行响应结果的存储
        String result;

        //设置cookies信息 -- client方法
        client.setCookieStore(this.store);

        //执行post请求
        HttpResponse response = client.execute(post);

        //获取响应结果，post返回的是json信息
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("result: " + result);


        //处理结果，判断返回结果是否符合预期
        //将返回的响应结果字符串转换为json对象
        /*
        解决方法：

            int i = resuStr.indexOf("{");
            resuStr = resuStr.substring(i);
            JSONObject json = new JSONObject(resuStr.trim()); 
            System.out.println(json.toString(4));  



            原因是：

            json文件头里带有编码字符(如UTF－8等)，读取字符串时json串是正常的，但是解析就有异常
         */

        //int i = result.indexOf("{");
        //result = result.substring(i);
       /*
            String substring(int begin,int end)提取begin和end之间的字符串部分；
            String substring(int index)提取从位置索引开始的字符串部分；
            char charAt(int index)返回指定索引处的char值
        */

        JSONObject resultJson = new JSONObject(result);

        //具体判断返回结果的值
        //获取结果值
        String name = (String) resultJson.get("liuxiaoxi");
        String status = (String) resultJson.get("status");

        Assert.assertEquals(name,"这是一个需要cookies信息才能访问的post请求");
        Assert.assertEquals("1",status);
    }
}
