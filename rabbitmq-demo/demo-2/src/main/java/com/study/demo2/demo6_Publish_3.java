package com.study.demo2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo6_Publish_3 {

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
            //验证 mandatory 参数值：将路由规则写错进行模拟
            channel.basicPublish("demo6_myExchangeName", "demo6_rk_error",true, basicProperties, "test mandatory".getBytes());
//            channel.basicPublish("demo6_myExchangeName", "demo6_rk",false, basicProperties, "hello World".getBytes());

            //添加监听器
            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("退回的消息是==>"+new String(body));
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
