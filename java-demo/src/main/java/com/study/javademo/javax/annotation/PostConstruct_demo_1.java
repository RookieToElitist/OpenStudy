package com.study.javademo.javax.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
/*

使用@PostConstruct解决构造方法中调用注入的bean的方法时出现空指针问题
 */
@Component
public class PostConstruct_demo_1 {

    @Autowired
    private Student student;

    public PostConstruct_demo_1() {
        System.out.println("构造方法执行了...");
        //student.getName();//会报空指针异常的，采取使用@PostConstruct

    }

    @PostConstruct
    private void afterInit() {
        System.out.println("@PostConstruct执行并调用==>" + student.getName());
    }

}

@Service
class Student{

    public Student() {
        System.out.println("注入的bean执行了");
    }

    public String getName(){
        return "jake";
    }
}
