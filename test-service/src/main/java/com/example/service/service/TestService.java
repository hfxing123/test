package com.example.service.service;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /*
    @Autowired
    public MyBean(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
    }
    */

    public boolean sendData()
    {

        //amqpTemplate.convertAndSend("123456");

        //amqpTemplate.send("temp",new Message("temp123456".getBytes(), new MessageProperties()));

        amqpTemplate.convertAndSend("temp","中文123456ごじゅうおんず");

        //amqpTemplate.convertAndSend("","");

        return true;
    }

    public String getData()
    {
        /*
        Message m= amqpTemplate.receive("temp");
        //amqpTemplate.receiveAndReply()
        System.out.println("获得的数据为="+new String(m.getBody()));;
        System.out.println("直接的数据="+m.getBody().toString());
        System.out.println(JSON.toJSON(m).toString());

        return m.toString();
         */

        //amqpTemplate.reply

        return (String)amqpTemplate.receiveAndConvert("temp");

    }

}
