package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口测试文档")
                .contact(new Contact("程茜茜","","838837654@qq.com"))
                .description("采用SwaggerUI方法生成的接口测试文档")
                .version("1.0.0.0")
                .build();
    }

    /* 访问方法：-- 运行Application；在浏览器中输入url：localhost:8888/swagger-ui.html
                -- 需要在GetMethod方法上加入Api标签，引入所有get方法
                       需要被Swagger-ui的标签
                              1. @Api(value = "/", description = "所有的get方法")
                              2. Applicaon(value = "通过getCookies方法获取cookies信息", httpMethod = "GET")
                -- @ComponentScan("com.study.server")要把swagger-ui类进行拖挂
                        --> @ComponentScan("com.study")
    */
}
