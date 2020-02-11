package com.study.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//需要被Application中ComponentScan扫描的类
@RestController

//需要被Swagger-ui的标签
@Api(value = "/", description = "所有的get方法")
public class GetMethod {

    @RequestMapping(value = "/getcookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过getCookies方法获取cookies信息", httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        //清楚浏览器历史缓存cookies信息：更多工具 -- 清除cookies信息（时间不限）
        //HttpServerletRequest  装请求信息的类
        //HttpServerletResponse 装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "get方法获得cookies信息成功！";
    }

    /**
     * 要求客户端携带cookies访问
     */
    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies信息访问的get请求", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        //if(cookies == null) ：会出现问题
        if (Objects.isNull(cookies)) {
            return "必须携带cookies信息访问的get请求";
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "携带cookies信息访问的get请求成功！";
            }
        }

        return "必须携带cookies信息访问的get请求";//接口可以访问成功，但是没有携带cookies信息
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第1中方式：url：key=value&key=value
     * 模拟获取商品列表
     */
    @RequestMapping(value = "get/with/paramter", method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数访问的get请求的第一种方式", httpMethod = "GET")
    public Map<String, Integer> getGoodList(@RequestParam Integer start,
                                            @RequestParam Integer end) {
        //实际开发中需要获取访问页面的start/get（数据库）
        //http://localhost:8888/get/with/paramter?start=0&end=1

        Map<String, Integer> goodlist = new HashMap<>();

        goodlist.put("Mac pro", 9999);
        goodlist.put("理肤泉套装", 998);
        goodlist.put("三只松鼠大礼包", 198);

        return goodlist;
    }

    /**
     * 第2种携带参数访问的get方法
     * url:ip:port/get/with/paramter/0/1   路径的区别
     */
    @RequestMapping(value = "get/with/paramter/{start}/{end}", method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数访问的get请求的第二种方式", httpMethod = "GET")
    //@RequestMapping(value = "get/with/paramter/{start}", method = RequestMethod.GET)
    public Map<String, Integer> getGoodList1(@PathVariable Integer start,
                                            @PathVariable Integer end) {
        //实际开发中需要获取访问页面的start/get（数据库）
        //http://localhost:8888/get/with/paramter/0/1

        Map<String, Integer> goodlist = new HashMap<>();

        goodlist.put("Mac pro", 9999);
        goodlist.put("理肤泉套装", 998);
        goodlist.put("三只松鼠大礼包", 1988);

        return goodlist;
    }
}
