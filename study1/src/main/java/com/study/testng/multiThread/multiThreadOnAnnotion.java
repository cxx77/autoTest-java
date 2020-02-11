package com.study.testng.multiThread;

import org.testng.annotations.Test;

public class multiThreadOnAnnotion {

    int n = 1;

    //线程个数；线程池
    @Test(invocationCount = 10, threadPoolSize = 3)
    public void test() {
        System.out.println(n++);
        System.out.printf("Thread Id: %s%n", Thread.currentThread().getId());
    }
}

/*
@Test(invocationCount = 10, threadPoolSize = 1)
1
Thread Id: 1
2
Thread Id: 1
3
Thread Id: 1
4
Thread Id: 1
5
Thread Id: 1
6
Thread Id: 1
7
Thread Id: 1
8
Thread Id: 1
9
Thread Id: 1
10
Thread Id: 1
 */

 /*     @Test(invocationCount = 10, threadPoolSize = 3)
2
3
Thread Id: 13
1
Thread Id: 14
Thread Id: 12
4
Thread Id: 14
5
Thread Id: 13
7
Thread Id: 12
6
Thread Id: 14
8
Thread Id: 13
10
Thread Id: 12
9
Thread Id: 14

!!:多线程的线程顺序无法控制，但是可以提高工作效率（各线程之间没有关系）
*/