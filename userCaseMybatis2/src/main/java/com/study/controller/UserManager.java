package com.study.controller;

import com.study.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;


@Log4j
@RestController
@Api(value = "v1", description = "用户管理系统")
@RequestMapping("/v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口", httpMethod = "POST")
    public boolean login(HttpServletResponse response, @RequestBody User user) {

        int i = template.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");

        response.addCookie(cookie);

        //日志记录查询结果：i（查询到几条i显示几）
        log.info("查询到的结果是："+i);
        if(i != 0) {
            log.info("登陆的用户是："+user.getUsername()); //lombok插件作用
            return true;
        }

        return false;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户", httpMethod = "POST")
    public boolean addUser(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verifyCookies(request);
        int result = 0;

        //if(x)
        if(x != null) {
            result = template.insert("addUser", user);
        }

        if(result > 0) {
            log.info("新增用户的数量是："+result);
            return true;
        }

        return false;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户信息（列表）接口", httpMethod = "POST")
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verifyCookies(request);
        System.out.println(user.toString());

        if(x != null) {
            List<User> userList = template.selectList("getUserInfo", user);
            log.info("获取到的用户数量是："+userList.size());
            System.out.println("获取到的用户数量是："+userList.size());
            System.out.println("获取成功");
            return userList;
        } else {
            System.out.println("null");
            return null;
        }

    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)//value = "updateUserInfo":http://localhost:8888/va/updateUserInfo
    @ApiOperation(value = "更新/删除用户接口", httpMethod = "POST") //删除是一个字段，用isDelete表示
    public boolean updateUserInfo(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verifyCookies(request);
        int result = 0;

        //System.out.println("x的值："+x);

        if(x != null) {
            result = template.update("updateUserInfo",user);//mysql.xml的数据库的id值
            System.out.println("result值："+result);
        }

        if(result > 0) {
            log.info("更新用户数量："+ result);
            System.out.println("result>0值："+result+"返回："+true);
            return true;
        }

        return false;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)) {
            log.info("cookies为空");
            System.out.println("cookies为空");
            return false;
        }

        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                log.info("cookies验证通过");
                System.out.println("cookies验证通过");
                return true;
            }
        }

        log.info("cookies验证失败");
        System.out.println("cookies验证失败");
        return false;
    }

}
