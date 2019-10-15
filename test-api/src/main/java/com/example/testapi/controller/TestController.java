package com.example.testapi.controller;


import com.example.testapi.authorization.annotation.CustomAuthorization;
import com.example.testapi.rpc.HystrixClient;
import com.example.testapi.rpc.Test1Client;
import io.swagger.annotations.Api;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api("用户信息管理")
@RestController
public class TestController {

    //@Autowired
    //private TestClient testClient;

    @Autowired
    private HystrixClient hystrixClient;

    @Autowired
    private Test1Client test1Client;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    //@Autowired
    //private TestHystrixClient testHystrixClient;

    @RequestMapping("/")
    public String home()
    {
        return "testFeign";
    }

    @RequestMapping("/getData")
    public String getData()
    {
        //没有回退的请求
        //System.out.println("获取的数据为:"+testClient.getData1());
        //有回退的请求
        System.out.println("新获取到的数据为："+hystrixClient.iFailSometimes());

        System.out.println("第二获得的============"+test1Client.iFailSometimes());

        //拥有原因能回退的请求
        //System.out.println("能回退的函数返回======"+testHystrixClient.iFailSometimes());

        return "获得的结果查看print";

    }

    @RequestMapping("/sendData")
    public String sendData()
    {

        //amqpAdmin.

        Queue queue=new Queue("temp1");
        amqpAdmin.declareQueue(queue);

        amqpTemplate.convertAndSend("temp", "中文123456ごじゅうおんず");

        amqpTemplate.convertAndSend("temp1", "中文123456ごじゅうおんず");
        return "已发送数据";
    }

    @GetMapping("/test")
    @CustomAuthorization
    public String test()
    {
        return "测试成功"+ UUID.randomUUID().toString();
    }

}
