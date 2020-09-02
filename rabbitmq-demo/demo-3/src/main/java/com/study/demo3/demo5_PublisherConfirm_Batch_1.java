package com.study.demo3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * 批量确认已发送的消息
 */
public class demo5_PublisherConfirm_Batch_1 {

    public static void main(String[] args) throws IOException {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = null;
        Channel channel = null;
        ArrayList<String> cacheMsg = new ArrayList<>();//用于存放已发送但还未收到服务端确认收取成功的
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare("demo5_PCExchangeName_1", "direct", true, false, null);
            channel.queueDeclare("demo5_PCQueneName_1", true, false, false, null);
            channel.queueBind("demo5_PCQueneName_1", "demo5_PCExchangeName_1", "demo5_PCRoutingKey_1");

            //开启发送方确认消息机制，
            channel.confirmSelect();

            String msg = "test batch confirm";
            int count=0;
            while (++count<=10000000){
                channel.basicPublish("demo5_PCExchangeName_1", "demo5_PCRoutingKey_1", MessageProperties.TEXT_PLAIN, msg.getBytes());
            }
            //所有消息发送后才执行，其批量确认当前发送的所有消息，当存在Basic.nack，则会抛出异常
            channel.waitForConfirmsOrDie();


        } catch (IOException e) {
            e.printStackTrace();

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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