package com.wudi.spring.springbootstart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Dillon Wu
 * @Title: Person
 * @Description: 将配置文件中配置的每一属性的值，映射到这个组件中
 * @ConfigurationProperties:告诉Spring Boot将本类中的所有属性和配置文件中相关的配置进行绑定
 * prefix =“person”:配置文件中那个下面 所有属性进行----映射
 * @date 2019/10/29 15:37
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;

    private Integer age;

    private Boolean boss;

    private Date birth;

    private Map<String,Object> maps;

    private List<Object> lists;

}
