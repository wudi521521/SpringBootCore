package com.wudi.spring.springbootstart;

import com.resource.initializer.SecondInitializerDemo01;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;

import java.util.Random;

/**
 * @SpringBootApplication来标注一个主程序类，说明这是一个Spring Boot应用
 */
@ComponentScan(basePackages = {"com.**"})
@SpringBootApplication
public class SpringbootstartApplication {

    //Spring应用启动起来
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));



        SpringApplication.run(SpringbootstartApplication.class, args);
        /*SpringApplication application = new SpringApplication(SpringbootstartApplication.class);
        //添加到初始化器
        application.addInitializers(new SecondInitializerDemo01());
        application.run(args);*/
    }


}
