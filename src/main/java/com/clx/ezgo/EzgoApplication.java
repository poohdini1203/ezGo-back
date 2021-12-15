package com.clx.ezgo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.clx.ezgo.mapper")
public class EzgoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzgoApplication.class, args);
    }

}
