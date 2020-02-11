package com.study.testng.goupstest;

import org.testng.annotations.Test;

@Test(groups = "techer")
public class groupsOnclass3 {

    public void techer1() {
        System.out.println("groups pn class3 中的trcher1上运行");
    }

    public void techer2() {
        System.out.println("groups pn class3 中的techer2上运行");
    }
}
