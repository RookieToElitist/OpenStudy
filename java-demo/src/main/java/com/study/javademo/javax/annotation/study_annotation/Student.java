package com.study.javademo.javax.annotation.study_annotation;


//测试重复注解
@MyCustomerAnnotation_1(value = "abc")
@MyCustomerAnnotation_1(value = "def")
public class Student {
    private String age;

    private String name;

    public Student(String age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
