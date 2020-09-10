package com.study.javademo.javax.annotation.study_annotation;


import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

@Repeatable(MyCustomerAnnotation_repeatable.class)//要求当前注解@Retention和@Target值要大于等于注解MyCustomerAnnotation_repeatable且@Inherited要有都有
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE,FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
public @interface MyCustomerAnnotation_1 {
    String value();
}
