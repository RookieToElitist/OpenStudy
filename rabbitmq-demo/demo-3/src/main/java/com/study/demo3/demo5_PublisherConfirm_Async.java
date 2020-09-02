package com.study.demo3;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class demo5_PublisherConfirm_Async {

    public static void main(String[] args) throws IOException {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("119.45.6.66");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = null;
        Channel channel = null;
        TreeSet<Long> set=new TreeSet<Long>();

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare("demo5_PCExchangeName_Async","direct",true,false,null);
            channel.queueDeclare("demo5_PCQueneName_Async",true,false,false,null);
            channel.queueBind("demo5_PCQueneName_Async","demo5_PCExchangeName_Async","demo5_PCRoutingKey_Async");

            //开启发送方确认消息机制，
            channel.confirmSelect();

            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {

                    System.out.println("handleAck==》》deliveryTag=="+deliveryTag+";"+"multiple=="+multiple);
                    //服务器可能会批量的返回,multiple=true，那么编号<=deliveryTag的所有消息都确认了。
                    if(multiple){
                        set.headSet(deliveryTag+1).clear();//清除deliveryTag+1前的数据（+1是因为headSet方法是找比deliveryTag小的数据）。如deliveryTag=10，则表示10及之前的都确认了
                    }else {
                        set.remove(deliveryTag);
                    }
                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("handleNack==》》deliveryTag=="+deliveryTag+";"+"multiple=="+multiple);

                    if(multiple){
                        set.headSet(deliveryTag+1).clear();
                    }else {
                        set.remove(deliveryTag);
                    }
                    //失败消息重发或则持久化
                }
            });


            int count=0;
            while (++count<=20){
                long nextPublishSeqNo = channel.getNextPublishSeqNo();
                channel.basicPublish("demo5_PCExchangeName_Async","demo5_PCRoutingKey_Async", MessageProperties.TEXT_PLAIN,"hello World".getBytes());
                set.add(nextPublishSeqNo);
            }

//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Iterator<Long> iterator = set.iterator();
//
//            while (iterator.hasNext()){
//                long l = iterator.next().longValue();
//                System.out.println(l);
//            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}