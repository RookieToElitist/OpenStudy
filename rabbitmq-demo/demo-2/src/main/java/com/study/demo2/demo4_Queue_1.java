package com.study.demo2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo4_Queue_1 {
    public static void main(String[] args) {
        ConnectionFactory factory=new ConnectionFactory();

        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare("demo4_myExchangeName_1","direct",true,true,null);
            //（1）不管指定队列中是否有消息都删除
//            channel.queueDelete("demo4_myQueneName_1");
            //（2）当指定队列中是空的才删除，若不是空的，则会抛出异常
//            channel.queueDelete("demo4_myQueneName_1",false,true);
            //（3）当指定队列没有被使用情况下可以删除，否则也是抛出异常
//            channel.queueDelete("demo4_myQueneName_1",true,false);

            //（4）清空队列数据
//            channel.queuePurge("demo4_myQueneName_1");

            //（5）声明无返回值的
//            channel.queueDeclareNoWait();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
