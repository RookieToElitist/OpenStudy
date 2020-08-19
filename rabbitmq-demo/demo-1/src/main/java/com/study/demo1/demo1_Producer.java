package com.study.demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
消费者消费消息
 */
public class demo1_Producer {


    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            //创建链接实例
            Connection connection = factory.newConnection();
            //创建通道实例
            Channel channel = connection.createChannel();
            //添加交换器
            channel.exchangeDeclare("demo1_myExchangeName","direct",true,false,null);
            //添加队列
            channel.queueDeclare("demo1_myQueneName",true,false,false,null);
            //使用路由键将交换器与队列链接
            channel.queueBind("demo1_myQueneName","demo1_myExchangeName","demo1_myRoutingKey");
            //向通道内发送消息:消息格式为纯文本
            channel.basicPublish("demo1_myExchangeName","demo1_myRoutingKey", MessageProperties.TEXT_PLAIN,"hello World".getBytes());
            //通道关闭
            channel.close();
            //连接关闭
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
