package com.study.testng.goupstest;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class groupsOnclass2 {

    public void stu1() {
        System.out.println("groups pn class2 中的stu1上运行");
    }

    public void stu2() {
        System.out.println("groups pn class2 中的stu1上运行");
    }
}
