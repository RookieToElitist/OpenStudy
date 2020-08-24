package com.study.demo2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class demo8_close {
    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            Connection connection = factory.newConnection(new Address[]{new Address("119.45.6.66", 5672)});
            //对链接添加监听器
            connection.addShutdownListener(new ShutdownListener() {
                @Override
                public void shutdownCompleted(ShutdownSignalException cause) {
                    //cause.isHardError()=true表示connection错误
                    if(cause.isHardError()){
                        System.out.println("----"+cause.getReference()+"----"+cause.getReason());
                        //cause.isInitiatedByApplication()=true表示异常是由初始化应用所引起的，否则是服务端问题
                        if(!cause.isInitiatedByApplication()){
                            Method reason = cause.getReason();
                            System.out.println("===="+reason);
                        }
                    //cause.isHardError()=false 表示 channel 错误
                    }else{
                        System.out.println(">>>>"+cause.getReference());
                    }
                }
            });

            Channel channel = connection.createChannel();

            channel.basicConsume("demo6_myQueueName", false,"consumerTag",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("Msg=" + new String(body));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
