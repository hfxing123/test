package com.example.service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestListenerService {

    //@RabbitListener(queues = "temp")
    public void processMessage(String content)
    {
        System.out.println("获得的数据为"+content);
    }

}
