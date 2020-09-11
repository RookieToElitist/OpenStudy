package com.study.springmvcdemo2.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
使用@Scope(value = "prototype")将本bean变成多例模式

 */
@Controller
//@Scope(value = "singleton"),默认就是单例
@Scope(value = "prototype")
public class PrototypeController {

    private Integer num=10;

    @RequestMapping(value = "/add7",method = RequestMethod.GET)
    @ResponseBody
    public Integer addNum7(){
        return ++num;
    }

    @RequestMapping(value = "/add8",method = RequestMethod.GET)
    @ResponseBody
    public Integer addNum8(){
        return ++num;
    }
}
