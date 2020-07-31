package com.study.sleuthdemo2.controller;

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

    @GetMapping(value = "/sleuth2")
    String sleuth2(){
        System.out.println("请求sleuth2");
        return "请求了sleuth2";
    }

    /**
     * 主要为了验证请求头上含有Span类的属性信息
     * @param request
     * @return
     */
    @GetMapping(value = "/sleuthHeaderInfo")
    String sleuthHeaderInfo(HttpServletRequest request){
        System.out.println("获取所有请求头信息===》》》");
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + "=" + value);
        }

        return "请求了sleuth2";
    }
}
