package com.eureka.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello")
    public String sayHello1() {
        logger.info("hello");
        return "Hello -- 1--";
    }
}
