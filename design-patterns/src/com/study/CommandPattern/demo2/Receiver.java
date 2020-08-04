package com.study.CommandPattern.demo2;

/**
 * 动作执行者/接收者，如厨师
 */
public  class Receiver {

     public void on(){
          System.out.println("灯开了");
     }

     public void off(){
          System.out.println("灯关了");
     }
}
