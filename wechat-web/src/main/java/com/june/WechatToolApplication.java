package com.june;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.june" })
public class WechatToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatToolApplication.class, args);
    }

}
