package com.study.testng.TestSuite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

//测试套件的配置类
public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite: 前测试套件");
    }

    @AfterSuite
    public void AfterSuite() {
        System.out.println("AfterSuite: 后测试套件");
    }

    @BeforeTest
    public void BeforeTest() {
        System.out.println("BeforeTest：测试前");
    }

    @AfterTest
    public void AfterTest() {
        System.out.println("AfterTest：测试后");
    }
}
