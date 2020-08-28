package com.study.demo3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 为 demo3_DLX_1 定义死信交换器、队列以及路由键
 */
public class demo3_DLX {

    public static void main(String[] args) {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //定义一个DLX及其队列和路由
            channel.exchangeDeclare("demo3_DLXExchangeName", "direct", false, false, null);
            channel.queueDeclare("demo3_DLXQueueName", false, false, false, null);
            channel.queueBind("demo3_DLXQueueName", "demo3_DLXExchangeName", "DLX_rk");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}
