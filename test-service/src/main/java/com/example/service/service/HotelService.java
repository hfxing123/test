package com.example.service.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.common.entry.hotel.Hotel;
import com.example.common.entry.tw.Tw;
import com.example.service.common.CommonUtils;
import com.example.service.common.RedisCommon;
import com.example.service.dao.HotelMapper;
import com.example.service.dao.TwMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.*;

@Service
@Transactional
public class HotelService implements RabbitTemplate.ConfirmCallback {

    private static Logger log= LoggerFactory.getLogger(HotelService.class);

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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private List<Integer> list=new ArrayList<Integer>();

    private Map<Integer,Integer> countMap=new HashMap<Integer,Integer>();

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

        //amqpTemplate.convertAndSend("temp", "中文123456ごじゅうおんず");

        //amqpTemplate.convertAndSend("","");

        // 发送消息
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend("orderRoutingKey", "中文123456ごじゅうおんず");


        String orderId = UUID.randomUUID().toString();

        Hotel h=new Hotel();
        h.setName("名字999999");
        int orderResult=hotelMapper.insert(h);

        // 1.先下单，创建订单 (往订单数据库中插入一条数据)
        System.out.println("orderResult:" + orderResult);
        if (orderResult <= 0) {
            //return setResultError("下单失败!");
            System.out.println("下单失败");
            return false;
        }
        // 2.订单表插插入完数据后 订单表发送 外卖小哥
        send(orderId);

        return true;
    }

    public String getData() {
        /*
        Message m= amqpTemplate.receive("temp");
        //amqpTemplate.receiveAndReply()
        log.debug("获得的数据为="+new String(m.getBody()));;
        log.debug("直接的数据="+m.getBody().toString());
        log.debug(JSON.toJSON(m).toString());
        return m.toString();
         */

        //amqpTemplate.reply

        return (String) amqpTemplate.receiveAndConvert("temp");

    }

    public void insertHotel()
    {

        //测试能承受多少请求
        if(list.isEmpty())
        {
            for(int i=0;i<100;i++)
            {
                Integer temp= CommonUtils.getRandomInteger(0,100000)+1811;
                if(temp>100000)
                {
                    temp=temp-1811;
                }
                list.add(temp);
            }
            for(Integer i:list)
            {
                countMap.put(i,0);
            }
        }

        Hotel hotel=null;

        Integer key=list.get(CommonUtils.getRandomInteger(0,10));
        countMap.put(key,countMap.get(key)+1);
        Hotel h=hotelMapper.selectByCityId4Update(key);

        h.setCount(h.getCount()+1);
        hotelMapper.updateCount(h);

        String uuid=UUID.randomUUID().toString();
//        log.debug("有随机的uuid========="+uuid);
        stringRedisTemplate.opsForValue().set(uuid, JSON.toJSONString(hotel), Duration.ofSeconds(6000));


//        Queue queue=new Queue("temp1");
//        amqpAdmin.declareQueue(queue);
//
//        amqpTemplate.convertAndSend("temp", "中文123456ごじゅうおんず");
//        amqpTemplate.convertAndSend("temp1", "中文123456ごじゅうおんず");

//        log.debug("完成" );

//        String[] arr=new String[]{"1","2"};
//        String s=arr[3];


    }

    public String getData(String s)
    {
        String data=stringRedisTemplate.opsForValue().get(s);
        data=JSON.toJSONString(redisCommon.getRedisTemplate().opsForValue().get(s));
        return data;
    }

    private void send(String orderId) {
        JSONObject jsonObect = new JSONObject();
        jsonObect.put("orderId", orderId);
        String msg = jsonObect.toJSONString();

        Hotel h=new Hotel();
        h.setName("名字999999");
        msg=JSON.toJSONString(h);

        System.out.println("msg:" + msg);

        // 封装消息
        Message message = MessageBuilder.withBody(msg.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8").setMessageId(orderId).build();

        // 构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(orderId);
        // 发送消息
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);

        //rabbitTemplate.convertAndSend("order_exchange_name", "orderRoutingKey", message, correlationData);
        rabbitTemplate.convertAndSend("temp", message, correlationData);

        //测试
        int temp=1/0;

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String orderId = correlationData.getId(); //id 都是相同的哦  全局ID
        System.out.println("消息id:" + correlationData.getId());
        if (ack) { //消息发送成功
            System.out.println("消息发送确认成功");
        } else {
            //重试机制
            send(orderId);
            System.out.println("消息发送确认失败:" + cause);
        }
    }

}
