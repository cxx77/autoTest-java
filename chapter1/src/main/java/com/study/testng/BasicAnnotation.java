package com.study.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    //最基本的注解，用来把方法标记为测试的一部分
    @Test
    public void testcase1() {
        System.out.println("这是测试用例1");
        
    }

    @Test
    public void  testcase2() {
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod: 这是在测试方法之前运行的");
    }

    @AfterMethod
    public void AfterMethod() {
        System.out.println("AfterMethod：这是在测试方法之后运行的");
    }

    @BeforeClass
    public void BeforeClass() {
        System.out.println("BeforeClass: 这是在类之前运行的方法");
    }

    @AfterClass
    public void AfterClass() {
        System.out.println("AfterClass: 这是在类之后运行的方法");
    }

    //测试套件可以包含多个类；在类运行之间，将类包裹起来
    @BeforeSuite
    public void BeforeSuit() {
        System.out.println("BeforeSuit: 前测试套件");
    }

    @AfterSuite
    public void AfterSuite() {
        System.out.println("AfterSuite: 后测试套件");
    }

}
