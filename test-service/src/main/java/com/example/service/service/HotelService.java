package com.example.service.service;

import com.alibaba.fastjson.JSON;
import com.example.common.entry.hotel.Hotel;
import com.example.common.entry.tw.Tw;
import com.example.service.common.RedisCommon;
import com.example.service.dao.HotelMapper;
import com.example.service.dao.TwMapper;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class HotelService {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisCommon redisCommon;

    /*
    @Autowired
    public MyBean(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
    }
    */

    public boolean sendData() {

        //amqpTemplate.convertAndSend("123456");

        //amqpTemplate.send("temp",new Message("temp123456".getBytes(), new MessageProperties()));

        amqpTemplate.convertAndSend("temp", "中文123456ごじゅうおんず");

        //amqpTemplate.convertAndSend("","");

        return true;
    }

    public String getData() {
        /*
        Message m= amqpTemplate.receive("temp");
        //amqpTemplate.receiveAndReply()
        System.out.println("获得的数据为="+new String(m.getBody()));;
        System.out.println("直接的数据="+m.getBody().toString());
        System.out.println(JSON.toJSON(m).toString());

        return m.toString();
         */

        //amqpTemplate.reply

        return (String) amqpTemplate.receiveAndConvert("temp");

    }

    public void insertHotel()
    {
        Hotel hotel=new Hotel();
        hotel.setName("测试");
        hotelMapper.insert(hotel);

        String uuid=UUID.randomUUID().toString();
        System.out.println("有随机的uuid========="+uuid);
        stringRedisTemplate.opsForValue().set(uuid, JSON.toJSONString(hotel), Duration.ofSeconds(6000));

//        String[] arr=new String[]{"1","2"};
//        String s=arr[3];

    }

    public String getData(String s)
    {
        String data=stringRedisTemplate.opsForValue().get(s);
        data=JSON.toJSONString(redisCommon.getRedisTemplate().opsForValue().get(s));
        return data;
    }

}
