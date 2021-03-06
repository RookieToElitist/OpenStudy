package com.study.demo2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo7_Consumer_3 {

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
                channel.basicQos(32);

                //推模式获取消息，显式的关闭自动ack(参数2)
                channel.basicConsume("demo6_myQueueName", false,"consumerTag",new DefaultConsumer(channel){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String s = new String(body);
                        if("hello World".equals(s)){
                            System.out.println(envelope.getDeliveryTag());
                            //重新入队，默认为true,为flase能够保证该消息还是此消费者处理
                            channel.basicRecover(false);
                        }

                        //消息消费手动确认：非批量
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
