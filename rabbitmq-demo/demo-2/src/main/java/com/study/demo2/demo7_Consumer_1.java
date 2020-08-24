package com.study.demo2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo7_Consumer_1 {

    public static void main(String[] args) {

        {
            //创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("guest");
            factory.setPassword("guest");


            try {
                //创建链接实例,与生产者不同
                Connection connection = factory.newConnection(new Address[]{new Address("119.45.6.66", 5672)});
                //创建通道实例
                Channel channel = connection.createChannel();
                //设置客户端最多接收未被ACK的消息个数
                channel.basicQos(1);

                //拉模式获取消息，显式的关闭自动ack(参数2)
                GetResponse response = channel.basicGet("demo6_myQueueName", false);
                System.out.println(new String (response.getBody()));
                channel.basicAck(response.getEnvelope().getDeliveryTag(),false);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
