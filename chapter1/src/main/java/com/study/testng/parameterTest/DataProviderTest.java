package com.study.testng.parameterTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProviderTest(String name, int age) {
        System.out.println("name = " + name);
        System.out.println("age = " + age);
    }

    @DataProvider(name="data")
    public Object[][] providerData() {
        Object[][] obj = new Object[][]{
                {"zhangsan",18},
                {"里斯", 28},
                {"王五",36}
        };

        return obj;
    }

    //dataProvider可根据测试方法传递参数
    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("test1 name = " + name);
        System.out.println("test2 age = " + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("test1 name = " + name);
        System.out.println("test2 age = " + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataProvider(Method method){
        Object[][] result = null;
        if(method.getName().equals("test1")){
            result = new Object[][]{
                    {"张三",20},
                    {"里斯",30},
                    {"王五",40}
            };
        }else if(method.getName().equals("test2")){
            result = new Object[][]{
                    {"勤六",56},
                    {"玛奇",66},
                    {"猪八",78}
            };
        }

        return result;
    }
}
