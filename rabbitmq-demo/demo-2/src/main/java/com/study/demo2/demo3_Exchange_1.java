package com.study.demo2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/*
学习exchangeDeclarePassive()方法

 */
public class demo3_Exchange_1 {

    public static void main(String[] args)  {
        //创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //方式1
        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //验证当前的交换器是否存在，若存在则正常返回，否则抛出IOException
            channel.exchangeDeclarePassive("demo3_myExchangeName_1");

            //无返回值的声明交换器方法
//            channel.exchangeDeclareNoWait();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }




    }
}
