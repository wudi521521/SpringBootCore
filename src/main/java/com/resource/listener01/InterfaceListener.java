package com.resource.listener01;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@EnableAsync
@Component
public class InterfaceListener implements ApplicationListener {


    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        try{
        Thread.sleep(3000);
        System.out.println("--"+event.getSource());
        }catch (Exception e){}
    }
}
