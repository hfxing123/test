package com.example.service.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempService {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;


    public String getData() {

        //这里实际上可以不要的，预先创建好的话，暂时使用默认的，不绑定
        amqpAdmin.declareQueue(new Queue("temp1"));

        //amqpTemplate.receiveAndReply()

//        amqpTemplate
        Message m= amqpTemplate.receive("temp1");

        m.getMessageProperties().getCorrelationId();

        return m.getBody().toString();

        //return (String) amqpTemplate.receiveAndConvert("temp1");


    }

}
