package com.study.springmvcdemo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
验证@Controller所代表的bean是单列么？
    1）测试：先访问接口addNum1，再访问addNum2，
    2）结果：每次请求都自增，说明不是单例的，存在线程安全性问题
 */
@Controller
public class SingletonController {

    private Integer num=10;//非静态成员变量

    private static Integer static_num=10;//静态成员变量

    @RequestMapping(value = "/add1",method = RequestMethod.GET)
    @ResponseBody
    public Integer addNum1(){
        return ++num;
    }

    @RequestMapping(value = "/add2",method = RequestMethod.GET)
    @ResponseBody
    public Integer addNum2(){
        return ++num;
    }

    @RequestMapping(value = "/add3",method = RequestMethod.GET)
    @ResponseBody
    public Integer addNum3(){
        return ++static_num;
    }

    @RequestMapping(value = "/add4",method = RequestMethod.GET)
    @ResponseBody
    public Integer addNum4(){
        return ++static_num;
    }
}
