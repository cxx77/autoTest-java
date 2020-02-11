package com.study.utils;

import com.study.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

//拼接URL的工具类方法

public class ConfigFile {

    //获取application.properties的url
    //工具类采用静态方法，只有一次
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri="";

        //最终的测试地址
        String testUrl;

        //每句方法控制限制输入的变量
        if(name == InterfaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");
        }

        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }

        if(name == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }

        if(name == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }

        if(name == InterfaceName.ADDUSER){
            uri = bundle.getString("addUser.uri");
        }

        testUrl = address + uri;
        return testUrl;
    }

}

