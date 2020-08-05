package com.study.busrabbitmqdemo1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope:这个注解是为单独的节点刷新配置的，其需要使用spring-boot-starter-actuator依赖
@RestController
public class TestAutoRefreshConfig {

    @Value("${my.name}")
    private String name;

    @Value("${my.age}")
    private String age;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "我叫"+name+",今年"+age;
    }

}