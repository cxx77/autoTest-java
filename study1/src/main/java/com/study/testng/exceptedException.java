package com.study.testng;

import org.testng.annotations.Test;

public class exceptedException {
    /**
     * 什么时候会用到异常测试
     * 在我们期望结果为某个异常的时候
     * 比如：我们传入某些不合法的参数，程序抛出异常
     * 也就是我们的预期结果就是这个异常
     */

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed() {
        System.out.println("这是一个异常测试");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess() {
        System.out.println("这是一个异常测试");
        throw new RuntimeException();
    }

}
