package com.study.CommandPattern.demo1;

/**
 * 具体的厨师，如专门做牛肉的厨师
 */
public class BeefCookerReceiver extends CookerReceiver {

    @Override
    void cooking() {
        System.out.println("牛肉类厨师正在准备食物...");
    }
}
