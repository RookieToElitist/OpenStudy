package com.study.springmvcdemo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
使用ThreadLocal解决单例模式下，成员变量线程安全性问题
 */
@Controller
public class SingletonController_2 {

    //https://www.jianshu.com/p/3c5d7f09dfbd
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    private Integer num=10;



    @RequestMapping(value = "/add5",method = RequestMethod.GET)
    @ResponseBody
    public int addNum5(){
        threadLocal.set(num);//为每个线程一份数据
        Integer integer = threadLocal.get();

        return ++integer;
    }

    @RequestMapping(value = "/add6",method = RequestMethod.GET)
    @ResponseBody
    public int addNum6(){
        threadLocal.set(num);
        Integer integer = threadLocal.get();
        return ++integer;
    }

}
