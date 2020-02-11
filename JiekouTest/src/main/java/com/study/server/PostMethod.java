package com.study.server;

import com.study.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "所有post方法")

//所有方法前必须要加v1
@RequestMapping("/v1")
public class PostMethod {

    //cookies变量用来装cookies信息
    private static Cookie cookies;

    //用户登陆成功获取到cookies信息，然后再访问其他接口获取到列表

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登陆成功并获取到cookies信息", httpMethod = "POST") // @RequestParam String PassWord :接口文档进行显示；
                                                                           // value = "username", required = true：参数必须传
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username", required = true) String UserName,
                        @RequestParam(value = "password", required = true) String PassWord) {
        if(UserName.equals("chengxixi") && PassWord.equals("admin123")) {
            cookies = new Cookie("login", "true");
            return "POST方法登陆成功并获取到cookies信息";
        }

        return "用户名或者密码错误，登陆失败！";
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "POST方法获取用户列表", httpMethod = "POST")
    //HttpServletRequest带入cookies信息
    public String getUserList(HttpServletRequest request,
                            @RequestBody User user) {
        Cookie[] cookie = request.getCookies();
        User userList;
        for(Cookie c: cookie) {
            if(c.getName().equals("login")
                    && c.getValue().equals("true")
                    && user.getUsername().equals("chengxixi")
                    && user.getPassword().equals("admin123")){
                userList = new User();
                userList.setName("liuheng");
                userList.setAge("28");
                userList.setSex("man");

                return userList.toString();
            }
        }

        return "参数不合法！";
    }
}
