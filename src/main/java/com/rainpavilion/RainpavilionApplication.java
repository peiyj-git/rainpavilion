package com.rainpavilion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rainpavilion.mapper")
public class RainpavilionApplication {
    public static void main(String[] args) {
        SpringApplication.run(RainpavilionApplication.class, args);
    }
}
