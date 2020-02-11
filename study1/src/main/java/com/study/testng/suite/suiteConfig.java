package com.study.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class suiteConfig {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("before suite测试类之前的套件");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("after suite测试类之后的套件");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("before test测试前");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("after tesr测试后");
    }
}
