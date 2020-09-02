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
 *
 * 业务场景:A公司某服务接收用户发起的请求、将其数据存到数据库并告知处理成功。
 * 然后由定时任务拉取数据，要求将其批量、不丢失的发到MQ服务器。
 */
public class demo5_PublisherConfirm_Batch {

    public static void main(String[] args) throws IOException {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = null;
        Channel channel = null;
        ArrayList<String> cacheMsg=new ArrayList<>();//用于存放已发送但还未收到服务端确认收取成功的
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare("demo5_PCExchangeName","direct",true,false,null);
            channel.queueDeclare("demo5_PCQueneName",true,false,false,null);
            channel.queueBind("demo5_PCQueneName","demo5_PCExchangeName","demo5_PCRoutingKey");

            //开启发送方确认消息机制，
            channel.confirmSelect();
            //假设此时向数据库拉取n条数据。
            int MsgCount=0;
            int BatchCount=100;
            while (true){
                String msg="test batch confirm";
                channel.basicPublish("demo5_PCExchangeName","demo5_PCRoutingKey", MessageProperties.TEXT_PLAIN,msg.getBytes());
                cacheMsg.add(msg);

                //当已发送的消息数超过约定的处理数量时，才进行消息确认
                if(++MsgCount>=BatchCount){
                    try {
                        if(channel.waitForConfirms()){
                            System.out.println("消息确认发送成功");
                            cacheMsg.clear();//清空当前所有消息
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                        //将缓存中数据重新发送或则将数据写入数据库或文件中
                    }
                }
            }
        } catch (IOException e) {
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