package com.study.javademo.javax.annotation.study_annotation;

//测试1.8新特性：@Target({ TYPE_PARAMETER})
public class Teacher<@MyCustomerAnnotation_2(value = "abc") T> {
    //测试1.8新特性： @Target({ TYPE_USE})
    int num =(@MyCustomerAnnotation_2(value = "long 转int ")int)10L;
}
