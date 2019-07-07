package com.eureka.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String sayHello1() {
        return "Hello World---2---";
    }
}
