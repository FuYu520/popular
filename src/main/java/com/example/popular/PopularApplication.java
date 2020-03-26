package com.example.popular;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MapperScan指定要扫描的Mapper类包的路径
 */
@MapperScan(basePackages = "com.example.popular.mapper")
@SpringBootApplication
public class PopularApplication {

    public static void main(String[] args) {
        SpringApplication.run(PopularApplication.class, args);
    }

}
