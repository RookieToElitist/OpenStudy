package com.study.sleuthdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SleuthController {

    @Autowired
    RestTemplate rt;

    @GetMapping
    String sleuth1(){
        System.out.println("请求sleuth1");
        String body = rt.getForEntity("http://SLEUTH-DEMO-2/sleuth2", String.class).getBody();
        return "先请求了sleuth1，再"+body;
    }
}
