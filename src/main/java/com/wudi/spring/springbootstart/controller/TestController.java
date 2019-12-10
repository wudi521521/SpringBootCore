package com.wudi.spring.springbootstart.controller;

import com.wudi.spring.springbootstart.testnetty.seri_netty.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Dillon Wu
 * @Title: TestController
 * @Description: TODO
 * @date 2019/11/13 16:43
 */
@Controller
@RequestMapping("web")
public class TestController {

    /**
     * 测试thymeleaf的配置模板
     * @return
     */
    @GetMapping("test-success")
    public  String directSuccess(Map<String,Object> map){

        System.err.println("===进入页面调用的方法====");
        //classpath:/templates/success.html
        map.put("hello","<h1> 你好<h1>");
        map.put("user", Arrays.asList("zhangsan","lisi","kaisi"));
        return "success";
    }

    @PostMapping("valid")
    public void Test(@Valid @RequestBody UserInfo userInfo, BindingResult bindingResult){
        System.out.println("=========");
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
    }
}
