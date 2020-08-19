package com.study.demo1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 案例1：消费生产者发送的消息
 */
public class demo1_Consumer {

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");


        try {
            //创建链接实例,与生产者不同
            Connection connection = factory.newConnection(new Address[]{new Address("119.45.6.66",5672)});
            //创建通道实例
            Channel channel = connection.createChannel();
            //设置客户端最多接收未被ACK的消息个数
            channel.basicQos(1);
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    System.out.println("Msg="+new String(body));

                    //消息消费手动确认：非批量
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            };


            //显式的关闭自动ack(参数2)
//            channel.basicConsume("demo1_myQueneName",false,consumer);
            channel.basicConsume("demo1_myQueneName",consumer);
            //等待回调函数执行完毕后，再关闭资源，不然将消费不了消息
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
