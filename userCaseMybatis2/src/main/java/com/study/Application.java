//package com.study;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
////入口类
//@EnableScheduling
//@SpringBootApplication
//public class Application {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//
//}


package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@EnableScheduling
@SpringBootApplication
public class Application {

    private  static ConfigurableApplicationContext context;

    public static void main(String[] args) {

        Application.context = SpringApplication.run(Application.class,args);
    }

    @PreDestroy
    public void close(){
        Application.context.close();
    }

}

