package com.controller;

import com.resource.initializer.TestInitializerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitializerController {

    @Autowired
    private TestInitializerComponent testInitializerComponent;

    @RequestMapping("/initializer")
    public String getInitializerDemo(){
       return testInitializerComponent.test();
    }
}
