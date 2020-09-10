package com.study.javademo.javax.annotation.study_annotation;


import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE_PARAMETER,TYPE_USE})
public @interface MyCustomerAnnotation_2 {
    String value();
}
