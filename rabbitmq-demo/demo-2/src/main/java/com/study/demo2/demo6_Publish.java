package com.study.demo2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo6_Publish {

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
            channel.exchangeDeclare("demo6_myExchangeName", "direct", true, false, null);
            channel.queueDeclare("demo6_myQueueName", true, false, false, null);
            channel.queueBind("demo6_myQueueName", "demo6_myExchangeName", "demo6_rk", null);
            //发送消息
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();

            basicProperties.builder().
                    deliveryMode(2).    //投递模式
                    contentType("text/plain")
                    .build();

            channel.basicPublish("demo6_myExchangeName", "demo6_rk", basicProperties, "hello World".getBytes());


//            channel.close();
//            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
