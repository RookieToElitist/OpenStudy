package com.study.demo3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo5_PublisherConfirm {

    public static void main(String[] args) throws IOException {
        //创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection=null;
        Channel channel =null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare("demo5_PCExchangeName","direct",true,false,null);
            channel.queueDeclare("demo5_PCQueneName",true,false,false,null);
            channel.queueBind("demo5_PCQueneName","demo5_PCExchangeName","demo5_PCRoutingKey");

            //开启发送方确认消息机制，
            channel.confirmSelect();
            channel.basicPublish("demo5_PCExchangeName","demo5_PCRoutingKey", MessageProperties.TEXT_PLAIN,"hello World".getBytes());
            //
            if(channel.waitForConfirms()){
                System.out.println("消息确认发送成功");
            }else {
                System.out.println("消息确认发送失败");
            }




        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {

            try {
                channel.close();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            connection.close();
        }
    }

}