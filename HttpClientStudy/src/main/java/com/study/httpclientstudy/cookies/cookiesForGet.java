package com.study.httpclientstudy.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class cookiesForGet {

    private String url;
    private ResourceBundle bundle;

    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application",Locale.CHINA); // properties后缀不用写，自动找到result下的文件
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
    public void testGetWithCookies() throws IOException {
        String uri = bundle.getString("test.get.with.cookies");
        String testUrl = this.url + uri;

        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookies信息 -- client方法
        client.setCookieStore(this.store);

        HttpResponse response = client.execute(get);


        //获取响应的状态码
        int responseStatus = response.getStatusLine().getStatusCode();

        System.out.println(responseStatus);

        if(responseStatus == 200) {
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }
        else {
            System.out.println("false");
        }
    }
}
