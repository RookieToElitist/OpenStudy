package com.study.javademo.javax.annotation.study_annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface MyCustomerAnnotation {

    //成员变量，使用时必须指定属性值，不然编译报错
    String value();

    //成员变量,含默认值
    String param() default "default value";
}
