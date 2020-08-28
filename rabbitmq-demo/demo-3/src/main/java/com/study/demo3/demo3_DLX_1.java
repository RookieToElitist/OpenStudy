package com.study.demo3;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class demo3_DLX_1 {

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

            channel.exchangeDeclare("demo3_DLXExchangeName_1", "direct", false, true, null);

            //设定运行时  queue 参数:对指定队列设置死信交换器
            Map<String, Object> params = new HashMap<>();
            params.put("x-dead-letter-exchange", "demo3_DLXExchangeName");//使用demo3_DLX定义的DLX
            params.put("x-dead-letter-routing-key","DLX_rk");//为其指定使用demo3_DLX定义的路由键
            params.put("x-message-ttl",20000);//模拟此队列中过期的消息将进入死信队列
            channel.queueDeclare("demo3_DLXQueueName_1", false, false, true, params);
            channel.queueBind("demo3_DLXQueueName_1", "demo3_DLXExchangeName_1", "DLX_rk_1");

            //发送消息
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
            basicProperties.builder().
                    deliveryMode(2).
                    contentType("text/plain")
                    .build();

            channel.basicPublish("demo3_DLXExchangeName_1", "DLX_rk_1", basicProperties, "Test_DLX_1".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}
