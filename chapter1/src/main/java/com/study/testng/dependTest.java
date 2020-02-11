package com.study.testng;

import org.testng.annotations.Test;

public class dependTest {

    @Test
    public void test1() {
        System.out.println("test1运行");
        throw new RuntimeException();
    }

    //test1异常时，忽略执行；
    // 场景：买东西要先登陆
    @Test(dependsOnMethods = "test1")
    public void test2() {
        System.out.println("test2运行");
    }
}
