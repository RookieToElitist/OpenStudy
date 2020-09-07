package com.study.responsejsondemo;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/*
step-3:自定义注解，将返回值转换成json格式。使用@ResponseBody实现

 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})//可用于类、方法上
@Documented
@ResponseBody
public @interface ResponseResultBody {
}
