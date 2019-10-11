package com.example.service.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class TestListenerService {

    @RabbitListener(queues = "temp")
    public void processMessage(Channel channel, String content,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception
    {
        System.out.println("channel=="+channel);
        System.out.println("获得的数据为"+content);
        System.out.println("tag==="+tag);

        Thread.sleep(10000L);

        System.out.println("等待完10秒了");

        channel.basicAck(tag, false); //确认消息成功消费


    }

    //不用确认
    /*
    @RabbitListener(queues = "temp")
    public void processMessage(Channel channel, String content) throws Exception
    {
        System.out.println("channel=="+channel);
        System.out.println("获得的数据为"+content);
    }

     */

}
