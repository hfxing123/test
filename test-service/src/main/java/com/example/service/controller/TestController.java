package com.example.service.controller;

import com.alibaba.fastjson.JSON;
import com.example.service.dao.HotelMapper;
import com.example.service.model.Hotel;
import com.example.service.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private TestService testService;

    @Value("${customValue.tempValue}")
    private String tempValue;

    @RequestMapping("/getData")
    public String getData()
    {

        testService.sendData();

        return JSON.toJSON(hotelMapper.selectByCityId(1)).toString();
        //return "testRibbon2";
    }


    @RequestMapping("/getData2")
    public String getData2()
    {
        return testService.getData();
    }

    @RequestMapping("/getData3")
    public String getData3()
    {
        return "获得的tempValue="+tempValue;
    }


    /*
    @RequestMapping("/")
    public String home()
    {
        return "testRibbon2";
    }

    @RequestMapping("/getData2")
    public  String getData1()
    {

        //可以传递对象过去

        return "testRibbon2的data2,获取到的数据为:   "+restTemplate.getForObject("http://test-ribbon/getData1",String.class);
    }



    @RequestMapping("/getData3")
    public String getData3()
    {

        ServiceInstance serviceInstance = this.loadBalancerClient.choose("test-ribbon");

        for(int i=0;i<10;i++)
        {
            ServiceInstance si = this.loadBalancerClient.choose("test-ribbon");

            System.out.println("第"+i+"次得到的是="+si.getUri()+","+si.getPort());
        }

        return "testRibbon2的data3,获得的数据为"+serviceInstance.getUri();
    }

    @RequestMapping("/getData4")
    public  String getData4()
    {

        Map<String,Object> temp=new HashMap<String,Object>();
        temp.put("temp",123456);

        System.out.println("返回的值为======="+testIntegration.getTest(temp));

        //可以传递对象过去
        return "调用了getData4";

    }

     */

}
