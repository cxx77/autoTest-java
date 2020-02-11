package com.study.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class dataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name, int age) {
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("--------------");
    }

    @DataProvider(name = "data")
    public Object[][] providerData() {
        Object[][] obj = new Object[][]{
                {"张三",20},
                {"王八", 37},
                {"hsjahjk", 16}
        };
        return obj;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("test1方法： "+"name = "+name);
        System.out.println("test1方法： "+"age = "+age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("test2方法： "+"name = "+name);
        System.out.println("test2方法： "+"age = "+age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataProvider(Method method) {
        Object[][] obj = null;

        if(method.getName().equals("test1")){
            obj = new Object[][]{
                    {"张三", 22},
                    {"王倩", 21}
            };
        } else if(method.getName().equals("test2")){
            obj = new Object[][]{
                    {"里斯", 28},
                    {"诺娃", 18},
                    {"baby",1}
            };
        }

        return obj;
    }
}
