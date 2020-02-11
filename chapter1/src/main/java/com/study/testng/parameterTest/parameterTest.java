package com.study.testng.parameterTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class parameterTest {

    @Test
    @Parameters({"name", "age"})
    public void parameterTest(String name, int age) {
        System.out.println("name = "+ name);
        System.out.println("age = " + age);
    }
}
