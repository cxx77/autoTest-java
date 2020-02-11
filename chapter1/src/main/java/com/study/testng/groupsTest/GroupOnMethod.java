package com.study.testng.groupsTest;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {

    @Test(groups = "server")
    public void groupTest1() {
        System.out.println("这是服务端组测试方法1");
    }

    @Test(groups = "server")
    public void groupTest2() {
        System.out.println("这是服务端组测试方法2");
    }

    @Test(groups = "client")
    public void groupTest3() {
        System.out.println("这是客户端组测试方法1");
    }

    @Test(groups = "client")
    public void groupTest4() {
        System.out.println("这是客户端组测试方法2");
    }

    @BeforeGroups("server")
    public void beforeGroupServer() {
        System.out.println("服务器组运行之前的方法");
    }

    @AfterGroups("server")
    public void afterGroupServer() {
        System.out.println("服务器组运行之后的方法");
    }

    @BeforeGroups("client")
    public void beforeGroupClient() {
        System.out.println("服务器组运行之前的方法");
    }

    @AfterGroups("client")
    public void afterGroupClient() {
        System.out.println("服务器组运行之后的方法");
    }
}
