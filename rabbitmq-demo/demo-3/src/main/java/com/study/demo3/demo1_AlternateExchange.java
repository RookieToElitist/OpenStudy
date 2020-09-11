package com.study.demo3;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/*

备份交换器：当消息不能路由时，该消息进入备份交换器指定的队列。


 */
public class demo1_AlternateExchange {

    public static void main(String[] args) {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = null;

        try {
            connection = factory.newConnection();


            Channel channelForAlternateExchange = connection.createChannel();

            //定义备份交换器(类型为fanout)、队列及绑定关系（可以是不同的Channel）
            channelForAlternateExchange.exchangeDeclare("demo1_unNormalExchangeName","fanout",true,true,null);
            channelForAlternateExchange.queueDeclare("demo1_unNormalQueueName",true,false,false,null);
            channelForAlternateExchange.queueBind("demo1_unNormalQueueName","demo1_unNormalExchangeName","");

            //设定运行时exchange参数
            Map<String,Object> params=new HashMap<>();
            params.put("alternate-exchange","demo1_unNormalExchangeName");

            Channel channelForNormal = connection.createChannel();

            //定义正常交换器、队列及绑定关系,
            channelForNormal.exchangeDeclare("demo1_NormalExchangeName","direct",true,true,params);
            channelForNormal.queueDeclare("demo1_NormalQueueName",true,false,false,null);
            channelForNormal.queueBind("demo1_NormalQueueName","demo1_NormalExchangeName","normal_rk");


            //发送消息
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();

            basicProperties.builder().
                    deliveryMode(2).
                    contentType("text/plain")
                    .build();

            //（1）正常测试：normal_rk
//            channel.basicPublish("demo1_NormalExchangeName", "normal_rk", basicProperties, "test_AlternateExchange".getBytes());
            //（2）异常测试
            channelForNormal.basicPublish("demo1_NormalExchangeName", "error_rk", basicProperties, "test_AlternateExchange".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
