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
对队列中所有消息设置过期时间

 */
public class demo2_TTL {

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

                channel.exchangeDeclare("demo2_TTLExchangeName","fanout",true,true,null);

                //设定运行时  queue 参数:对指定队列设置过期时间，此时该队列中所有的消息将拥有一样的过期时间。
                Map<String,Object> params=new HashMap<>();
                params.put("x-message-ttl",10000);//单位毫秒
                channel.queueDeclare("demo2_TTLQueueName",true,false,false,params);
                channel.queueBind("demo2_TTLQueueName","demo2_TTLExchangeName","TTL_rk");


                //发送消息
                AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();

                basicProperties.builder().
                        deliveryMode(2).
                        contentType("text/plain")
                        .build();

                channel.basicPublish("demo2_TTLExchangeName", "TTL_rk", basicProperties, "test_TTL".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }
    }
}
