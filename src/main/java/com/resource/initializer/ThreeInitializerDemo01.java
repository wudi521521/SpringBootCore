package com.resource.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@Order(3)
//初始化器
public class ThreeInitializerDemo01 implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        //获取环境
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        Map<String,Object> map = new HashMap<>();
        map.put("key3","value3");
        MapPropertySource firstInitailzer = new MapPropertySource("ThreeInitializerDemo01", map);
        environment.getPropertySources().addLast(firstInitailzer);
        //放置到容器中
        System.out.println("*********run ThreeInitializerDemo01");
    }
}
