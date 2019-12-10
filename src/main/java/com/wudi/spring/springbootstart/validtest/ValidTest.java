package com.wudi.spring.springbootstart.validtest;

import com.wudi.spring.springbootstart.testnetty.seri_netty.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Dillon Wu
 * @Title: ValidTest
 * @Description: TODO
 * @date 2019/12/10 11:19
 */
@Controller
@RequestMapping("/test")
public class ValidTest {

    @PostMapping("valid")
    public void Test(@RequestBody UserInfo userInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
    }
}
