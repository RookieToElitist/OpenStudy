package com.study.demo3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者确认消息是否成功发送到服务端之事务机制
 */
public class demo5_TX {

    public static void main(String[] args) throws IOException {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare("demo5_TXExchangeName", "direct", true, false, null);
            channel.queueDeclare("demo5_TXQueneName", true, false, false, null);
            channel.queueBind("demo5_TXQueneName", "demo5_TXExchangeName", "demo5_TXRoutingKey");

            //开启事务
            channel.txSelect();
            channel.basicPublish("demo5_TXExchangeName", "demo5_TXRoutingKey", MessageProperties.TEXT_PLAIN, "hello World".getBytes());

            //模拟业务处理出现异常，验证是否进行消息回滚
//            int a =1/0;
            //提交事务：提交成功，则一定将消息发到了服务端,若不提交，则消息就不会发到服务端。
            channel.txCommit();


        } catch (IOException e) {
            e.printStackTrace();
            //回滚事务
            channel.txRollback();

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
