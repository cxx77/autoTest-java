package com.study.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {

    //增加/删除用户操作

    private int id;
    private int userid;
    private String username;
    private int age;
    private int sex;
    private int permission;
    private int isDelete;
    private String expected;

}
