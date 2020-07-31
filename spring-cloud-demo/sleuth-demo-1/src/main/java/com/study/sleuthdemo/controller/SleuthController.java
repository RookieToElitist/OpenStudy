package com.study.sleuthdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
public class SleuthController {

    @Autowired
    RestTemplate rt;

    @GetMapping(value = "/sleuth1")
    String sleuth1(){
        System.out.println("请求sleuth1");
        String body = rt.getForEntity("http://SLEUTH-DEMO-2/sleuth2", String.class).getBody();
        return "先请求了sleuth1，再"+body;
    }

    /**
     * 测试获取Sleuth在请求头添加的信息
     * @return
     */
    @GetMapping(value = "/sleuthHeaderInfo")
    String sleuthHeaderInfo(){
        String body = rt.getForEntity("http://SLEUTH-DEMO-2/sleuthHeaderInfo", String.class).getBody();
        return "先请求了sleuth1，再"+body;
    }
}
