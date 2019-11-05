package com.wudi.spring.springbootstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class SpringbootstartApplication {

    //Spring应用启动起来
    public static void main(String[] args) {
        SpringApplication.run(SpringbootstartApplication.class, args);
    }

}
