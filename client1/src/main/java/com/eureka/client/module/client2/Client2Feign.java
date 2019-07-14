package com.eureka.client.module.client2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "LOGSERVICE2",path = "logService2")
public interface Client2Feign {

    @RequestMapping(method = RequestMethod.GET,value = "/getName")
    String getName();
}
