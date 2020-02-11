package com.study.controller;

import com.study.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/v1")
@Api(value = "v1", description = "这是v1版本的demo")
public class Demo {

    //首先获取一个执行sql语句的对象

    //demol类启动起来，template就加载进来(Autowire)
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value = "访问数据库获取用户数", httpMethod = "GET")
    public int getUserCount() {

        return template.selectOne("getUserCount");

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "增加用户", httpMethod = "POST")  //必须携带用户信息访问：post
    public int addUser(@RequestBody User user) {

       int resule = template.insert("addUser", user);

       return resule;

    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户", httpMethod = "POST")
    public int updateUser(@RequestBody User user) {

        return template.update("updateUser", user);

    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    @ApiOperation(value = "删除用户", httpMethod = "GET")
    public int deleteUser(@RequestParam Integer id) {

        return template.delete("deleteUser", id);

    }
}
