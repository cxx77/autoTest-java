package com.study.testng.multiThread;

import org.testng.annotations.Test;

public class multThreadOnAnnotion {

    @Test(invocationCount = 10, threadPoolSize = 3)
    public void test() {
        System.out.println(1);
        System.out.printf("thread id: %s%n", Thread.currentThread().getId());
    }
}
