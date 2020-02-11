package com.study.model;

import lombok.Data;

@Data
public class AddUserCase {

       // private int id;
        private String username;
        private String password;
        private int age;
        private int sex;//0,1
        private int permission;//0,1
        private int isDelete;
        private String expected;
}
