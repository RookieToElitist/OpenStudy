package com.study.demo2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo5_Bind {
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
            channel.exchangeDeclare("demo5_myExchangeName","direct",true,true,null);
            channel.queueDeclare("demo5_myQueueName",true,true,true,null);
            //绑定
            channel.queueBind("demo5_myQueueName","demo5_myExchangeName","demo5_rk",null);
            //解绑
//            channel.queueUnbind();



        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
