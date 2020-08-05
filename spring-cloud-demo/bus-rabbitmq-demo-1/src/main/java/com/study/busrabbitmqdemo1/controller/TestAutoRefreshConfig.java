package top.learninghub.userProvider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestAutoRefreshConfig {

//    @Value("${my.name}")
//    private String name;
//
//    @Value("${my.age}")
//    private String age;
//
//    @RequestMapping(value = "/hello",method =RequestMethod.GET)
//    public String hello(){
//        return "我叫"+name+",今年"+age;
//    }

}