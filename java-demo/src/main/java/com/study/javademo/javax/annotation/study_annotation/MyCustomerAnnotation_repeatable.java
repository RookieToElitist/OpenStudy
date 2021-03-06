package com.study.javademo.javax.annotation.study_annotation;


import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE,FIELD, METHOD})
public @interface MyCustomerAnnotation_repeatable {
    //
    MyCustomerAnnotation_1[] value();
}
