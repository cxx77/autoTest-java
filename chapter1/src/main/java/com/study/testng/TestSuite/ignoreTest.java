package com.study.testng.TestSuite;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ignoreTest {

    @Test
    public void ignore1() {
        System.out.println("ignore 忽略测试1");
    }

    @Test(enabled = false)
    public void ignore2() {
        System.out.println("ignore 忽略测试2");
    }

    @Ignore
    public void ignore3() {
        System.out.println("ignore 忽略测试2");
    }
}
