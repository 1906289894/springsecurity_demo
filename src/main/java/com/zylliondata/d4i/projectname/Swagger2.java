package com.zylliondata.d4i.projectname;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.time.LocalDateTime;

@Configuration
@EnableSwagger2WebMvc
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zylliondata.gdsp.gateway"))
                .paths(PathSelectors.regex("/api.*"))
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("微服务网关")
                .description("代理访问所有微服务。")
                .termsOfServiceUrl("http://www.zylliondata.com/site/aboutUs")
                .license("© 版权所有 2016 - " + LocalDateTime.now().getYear() + " 中云开源数据（上海）有限公司保留一切权利")
                .licenseUrl("http://www.zylliondata.com/site/aboutUs")
                .version("0.1.5")
                .build();
    }
}

