package com.study.config;

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
public class SwggerUiConfig {

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
                .title("UserManager Service API")
                .contact(new Contact("程茜茜","","838837654@qq.com"))
                .description("这是用户管理服务API")
                .version("1.0.0.0")
                .build();

    }

}
