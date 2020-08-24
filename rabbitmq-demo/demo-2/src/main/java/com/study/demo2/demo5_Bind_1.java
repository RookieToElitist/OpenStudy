package com.study.demo2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo5_Bind_1 {
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
            //绑定交换器
            channel.exchangeBind("demo5_myExchangeName","demo1_myExchangeName","demo5_rk_1");




        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
