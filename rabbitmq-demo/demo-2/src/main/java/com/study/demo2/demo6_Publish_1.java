package com.study.demo2;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo6_Publish_1 {

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
            //模拟发送大量数据，测试消费者加限流控制与不加控制，是否会出问题。
            for (int i=0;i<3000000;i++) {
                channel.basicPublish("demo6_myExchangeName", "demo6_rk", basicProperties, "hello World".getBytes());
            }

//            channel.close();
//            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
