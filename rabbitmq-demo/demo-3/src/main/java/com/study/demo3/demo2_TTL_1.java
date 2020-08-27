package com.study.demo3;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/*
对消息设置过期时间

 */
public class demo2_TTL_1 {

    public static void main(String[] args) {
        {
            ConnectionFactory factory=new ConnectionFactory();
            factory.setHost("119.45.6.66");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");

            Connection connection = null;
            try {
                connection = factory.newConnection();
                Channel channel = connection.createChannel();

                channel.exchangeDeclare("demo2_TTLExchangeName_1","fanout",true,true,null);
                channel.queueDeclare("demo2_TTLQueueName_1",true,false,false,null);
                channel.queueBind("demo2_TTLQueueName_1","demo2_TTLExchangeName_1","TTL_rk");

                //发送消息
                AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
                //对指定消息添加过期时间
                basicProperties.builder().
                        deliveryMode(2).
                        expiration("10000").
                        contentType("text/plain")
                        .build();

                channel.basicPublish("demo2_TTLExchangeName_1", "TTL_rk", basicProperties, "test_TTL_1".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }
    }
}
