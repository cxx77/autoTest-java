package com.study.testng.goupstest;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class groupsOnClass1 {

    public void stu1() {
        System.out.println("groups pn class1 中的stu1上运行");
    }


    public void stu2() {
        System.out.println("groups pn class1 中的stu2上运行");
    }
}
