package com.study.sleuthdemo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SleuthController {

    @Autowired
    RestTemplate rt;

    @GetMapping
    String sleuth2(){
        System.out.println("请求sleuth2");
        return "请求了sleuth2";
    }
}
