package com.study.testng.groupsTest;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass1 {

    public void teacher1() {
        System.out.println("GroupsOnClass 1 中的teacher1运行");
    }

    public void teacher2() {
        System.out.println("GroupsOnClass 1 中的teacher2运行");
    }
}
