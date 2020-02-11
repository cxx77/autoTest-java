package com.study.model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String username;
    private String password;
    private int age;
    private int sex;//0,1
    private int permission;//0,1
    private int isDelete;

    @Override
    //复写toString方法
    public String toString() {
        return (
                "{id: "+id+", "+
                        "username: "+username+", "+
                        "password: "+password+", "+
                        "age: "+age+", "+
                        "sex: "+sex+", "+
                        "permission: "+permission+", "+
                        "isDelete: "+isDelete+"}"
                );
    }
}
