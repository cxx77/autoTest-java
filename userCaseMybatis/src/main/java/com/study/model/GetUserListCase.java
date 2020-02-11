package com.study.model;

import lombok.Data;

@Data
public class GetUserListCase {

    private String username;
    private int age;
    private int sex;
    private String expected;
}
