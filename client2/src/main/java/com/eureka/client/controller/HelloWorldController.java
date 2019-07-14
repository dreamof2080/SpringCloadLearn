package com.eureka.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String sayHello1() {
        return "Hello World---2---";
    }

    @RequestMapping(value = "/getName", method = RequestMethod.GET)
    public String feign(){
        return "this is client2";
    }

}
