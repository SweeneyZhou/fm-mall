package com.sweeney.mall.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sweeney
 * @since 2021/06/29 14:07 created.
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("锋迷商城在线开发文档")
                        .description("fm-mall Restful APIs Doc")
                        .version("1.0-SNAPSHOT")
                        .contact(new Contact("sweeney","https://www.sweeneyzhou.top","sweeneyzhou@outlook.com"))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sweeney.mall.web"))
                .apis(RequestHandlerSelectors.any())
                .build();
    }

}
