package com.study.CommandPattern.demo1;

import com.study.CommandPattern.demo1.BeefCookerReceiver;

/**
 * Test就等价于顾客
 */
public class ClientTest {

    public static void main(String[] args) {
        //动作接收者：真正处理具体的业务逻辑
        BeefCookerReceiver bcp=new BeefCookerReceiver();
        //具体命令对象：绑定命令操作与接收者
        BeefCommand beefCommand=new BeefCommand(bcp);
        //调用者：持有命令对象并执行请求
        Invoker invoker=new Invoker();

        //调用者添加具体命令对象
        invoker.setCommand(beefCommand);
        //调用者执行命令对象
        invoker.action();
    }
}
