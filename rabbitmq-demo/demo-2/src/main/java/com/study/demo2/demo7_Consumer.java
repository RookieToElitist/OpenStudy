package com.study.demo2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo7_Consumer {

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
                //设置客户端最多接收未被ACK的消息个数:prefetchSize=0 不限制消息大小，Global：true\false是否将上面设置应用于Channel；简单来说，就是上面限制是Channel级别的还是Consumer级别
                channel.basicQos(64);

                //推模式获取消息，显式的关闭自动ack(参数2)
                channel.basicConsume("demo6_myQueueName", false,"consumerTag",new DefaultConsumer(channel){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("Msg=" + new String(body));
                        //模拟业务处理耗时
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //消息消费手动确认：非批量
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                });
                //等待回调函数执行完毕后，再关闭资源，
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                //通道关闭
//                channel.close();
//                //连接关闭
//                connection.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
