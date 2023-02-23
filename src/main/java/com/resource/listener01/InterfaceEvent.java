package com.resource.listener01;

import org.springframework.context.ApplicationEvent;

import java.awt.event.ActionEvent;

public class InterfaceEvent extends ApplicationEvent {

    public InterfaceEvent(Object source) {

        super(source);
        try{
            Thread.sleep(1000);
            System.out.println(source);
        }catch (Exception e){}

    }
}
