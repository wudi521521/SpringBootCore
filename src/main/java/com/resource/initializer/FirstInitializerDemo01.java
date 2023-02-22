package com.resource.initializer;

import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@Order(1)
//初始化器
public class FirstInitializerDemo01 implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        //获取环境
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        MapPropertySource firstInitailzer = new MapPropertySource("firstInitailzer", map);
        environment.getPropertySources().addLast(firstInitailzer);
        //放置到容器中
        System.out.println("==========run firstInitializer");
    }
}
