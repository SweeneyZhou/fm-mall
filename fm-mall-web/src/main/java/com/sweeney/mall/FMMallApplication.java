package com.sweeney.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author sweeney
 * @since 2021/06/29 22:55 created.
 */
@SpringBootApplication
//TODO WARN: No MyBatis mapper was found in '[com.sweeney.mall]' package.
@MapperScan("com.sweeney.mall.dao")
public class FMMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(FMMallApplication.class,args);
    }
}
