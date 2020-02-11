package com.study.testng.goupstest;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class groupsOnMethod {

    @Test(groups = "server")
    public void test1() {
        System.out.println("这是服务端测试组1");
    }

    @Test(groups = "server")
    public void test2() {
        System.out.println("这是服务端测试组2");
    }

    @Test(groups = "client")
    public void test3() {
        System.out.println("这是客户端测试组3");
    }

    @Test(groups = "client")
    public void test4() {
        System.out.println("这是客户端测试组4");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer() {
        System.out.println("这是服务端组运行之前的方法");
    }

    @AfterGroups("server")
    public void afterGroupsOnServer() {
        System.out.println("这是服务端组运行之后的方法");
    }

    @BeforeGroups("client")
    public void beforeGroupsOnClient() {
        System.out.println("这是客户端组运行之前的方法");
    }

    @AfterGroups("client")
    public void afterGroupsOnClient() {
        System.out.println("这是客户端组运行之后的方法");
    }
}
