package com.study.demo3;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class demo4_QueuePriority {
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

            channel.exchangeDeclare("demo4_QPExchangeName", "direct", false, true, null);

            //设定运行时  queue 参数:对指定队列设置优先级
            Map<String, Object> params = new HashMap<>();
            params.put("x-max-priority",2);
            channel.queueDeclare("demo4_QPQueueName", false, false, true, params);
            channel.queueBind("demo4_QPQueueName", "demo4_QPExchangeName", "QP_rk");


            //发送消息
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
            basicProperties.builder().
                    deliveryMode(2).
                    priority(1).//对消息设置优先级
                    contentType("text/plain")
                    .build();

            channel.basicPublish("demo4_QPExchangeName", "QP_rk", basicProperties, "Test_QP".getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
