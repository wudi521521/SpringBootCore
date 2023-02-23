package com.controller;

import com.resource.initializer.TestInitializerComponent;
import com.resource.listener01.InterfaceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InitializerController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private TestInitializerComponent testInitializerComponent;

    @RequestMapping("/initializer")
    public String getInitializerDemo(){
        //发送机制
        applicationEventPublisher.publishEvent(new InterfaceEvent("你好北京"));
        System.out.println("完成");
       return testInitializerComponent.test();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher=applicationEventPublisher;
    }
}
