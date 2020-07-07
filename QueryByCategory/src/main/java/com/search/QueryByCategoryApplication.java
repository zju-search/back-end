package com.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.search.mapper")
public class QueryByCategoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryByCategoryApplication.class, args);
    }

}
