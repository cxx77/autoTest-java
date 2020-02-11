package com.study.httpclientstudy;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class myHttpClient {

    @Test
    public void test1() throws IOException {
        String result;//存放结果
        HttpGet get = new HttpGet("http://www.baidu.com");
        DefaultHttpClient client = new DefaultHttpClient(); //执行get方法

        //DefaultHttpClient clients = new DefaultHttpClient();
        //HttpClient client = HttpClientBuilder.create().build();//获取DefaultHttpClient请求

        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }
}
