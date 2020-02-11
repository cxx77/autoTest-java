package com.study.testng;

import org.testng.annotations.Test;

public class ignoreTest {

    @Test
    public void ignoreTest1() {
        System.out.println("忽略测试1");
    }

    @Test(enabled = false)
    public void ignoreTest2() {
        System.out.println("忽略测试2");
    }

    @Test(enabled = true)
    public void ignoreTest3() {
        System.out.println("忽略测试3");
    }
}
