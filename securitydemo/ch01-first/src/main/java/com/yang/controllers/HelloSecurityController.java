package com.yang.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloSecurityController {

    @RequestMapping("/world")
    public String sayHello(){
        return "Hello Spring Security 安全管理框架";
    }
}
