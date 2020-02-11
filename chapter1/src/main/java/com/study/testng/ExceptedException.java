package com.study.testng;

import org.testng.annotations.Test;

public class ExceptedException {
    /***
     *      什么时候用到异常测试？
     *      在我们预期结果为某一个异常的时候
     *      比如：传入某些不合法的参数，程序抛出异常
     */

    //失败的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    /*public  void runTimeexceptionFailed() {
        System.out.println("这是一个失败的异常测试");
    }*/

    //成功的异常测试
    public void runTimeExceptionSuccess() {
        System.out.println("这是超时异常测试");
        throw new RuntimeException();
    }
}
