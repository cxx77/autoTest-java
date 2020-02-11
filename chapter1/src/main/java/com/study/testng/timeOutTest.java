package com.study.testng;

import org.testng.annotations.Test;

public class timeOutTest {

    @Test(timeOut = 3000) //单位为ms；3s
    public void testSuccess() throws InterruptedException{
        Thread.sleep(2000);
    }

    @Test(timeOut = 3000) //单位为ms；3s
    public void testSFail() throws InterruptedException{
        Thread.sleep(4000);
    }
}
