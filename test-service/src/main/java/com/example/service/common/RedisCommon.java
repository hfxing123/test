package com.example.service.common;

import com.alibaba.fastjson.JSON;
import com.example.common.entry.hotel.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisCommon {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        // 泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public void setData()
    {
        Hotel h=new Hotel();
        h.setCity(1L);
        h.setName("测试1");
        h.setZip("测试111");
        h.setAddress("测试地址1");
        stringRedisTemplate.opsForValue().set("1", JSON.toJSONString(h));

        Hotel h2=new Hotel();
        h.setCity(2L);
        h.setName("测试2");
        h.setZip("测试222");
        h.setAddress("测试地址2");
        redisTemplate.opsForValue().set("2", JSON.toJSONString(h));

    }

    public String getData()
    {
        System.out.println("redis获取的="+stringRedisTemplate.opsForValue().get("1"));

        System.out.println("redis获取2的="+redisTemplate.opsForValue().get("2"));

        return "成功";
    }

}
