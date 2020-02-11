package com.study.extentrepTest;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class testMethodsDemo {

    @Test
    public void test1() {
        Assert.assertEquals(1,2);
    }

    @Test
    public void test2() {
        Assert.assertEquals(1,2);
    }

    @Test
    public void logDemo() {
        Reporter.log("这是关于……的日志记录");
        throw new RuntimeException("运行时异常");
    }

    @Test
    public void test3() {
        Assert.assertEquals("aa","aa");
    }

    @Test
    public void test4(){

    }
}
