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
            Channel channel = connection.createChannel();

            //定义备份交换器、队列及绑定关系
            channel.exchangeDeclare("demo1_unNormalExchangeName","fanout",true,true,null);
            channel.queueDeclare("demo1_unNormalQueueName",true,false,false,null);
            channel.queueBind("demo1_unNormalQueueName","demo1_unNormalExchangeName","");

            //设定运行时exchange参数
            Map<String,Object> params=new HashMap<>();
            params.put("alternate-exchange","myA_E");

            //定义正常交换器、队列及绑定关系,
            // 注意该交换器只能绑定一个备份交换器，如将myA_E改为myA_E_1，
            // 则报inequivalent arg 'alternate-exchange' for exchange 'demo1_NormalExchangeName' in vhost '/': received 'myA_E_1' but current is 'myA_E',
            //此时需要将之前的备份交换器、对应队列都删除才可以（即重新开始）
            channel.exchangeDeclare("demo1_NormalExchangeName","direct",true,true,params);
            channel.queueDeclare("demo1_NormalQueueName",true,false,false,null);
            channel.queueBind("demo1_NormalQueueName","demo1_NormalExchangeName","normal_rk");


            //发送消息
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();

            basicProperties.builder().
                    deliveryMode(2).
                    contentType("text/plain")
                    .build();

            //（1）正常测试：normal_rk
//            channel.basicPublish("demo1_NormalExchangeName", "normal_rk", basicProperties, "test_AlternateExchange".getBytes());
            //（2）异常测试
            channel.basicPublish("demo1_NormalExchangeName", "error_rk", basicProperties, "test_AlternateExchange".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
