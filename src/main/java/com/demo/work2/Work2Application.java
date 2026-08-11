package com.demo.work2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.demo.work2.Mapper")
@SpringBootApplication
public class Work2Application {

    public static void main(String[] args) {
        SpringApplication.run(Work2Application.class, args);
    }

}
