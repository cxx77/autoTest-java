package com.study.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    @Test
    public void testCase1() {
        System.out.println("这是测试用例1");
    }

    @Test
    public void testCase2() {
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void  beforeMethod() {
        System.out.println("BeforeMethod是在测试方法之前运行的");
    }

    @AfterMethod
    public void  AfterMethod() {
        System.out.println("AfterMethod是在测试方法之后运行的");
    }

    @BeforeClass
    public void  beforeClass() {
        System.out.println("BeforeClass是在类之前运行的");
    }

    @AfterClass
    public void  AfterClass() {
        System.out.println("AfterClass是在类之后运行的");
    }

    //测试套件可以包含多个类
    @BeforeSuite
    public  void BeforeSuite() {
        System.out.println("BeforeSuite测试前测试套件");
    }

    @AfterSuite
    public  void AfterSuite() {
        System.out.println("AfterSuite测试后测试套件");
    }
}
