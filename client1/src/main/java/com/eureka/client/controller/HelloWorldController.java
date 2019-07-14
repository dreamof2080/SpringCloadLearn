package com.eureka.client.controller;

import com.eureka.client.module.client2.Client2Feign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Client2Feign client2Feign;

    @RequestMapping("/hello")
    public String sayHello1() {
        logger.info("hello");
        return "Hello -- 1--";
    }

    @RequestMapping("/getName")
    public String getName(){
        return client2Feign.getName();
    }
}
