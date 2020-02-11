package com.study.testng.groupsTest;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass3 {

    public void stu1() {
        System.out.println("GroupsOnClass 3 中的stu1运行");
    }

    public void stu2() {
        System.out.println("GroupsOnClass 3 中的stu2运行");
    }
}
