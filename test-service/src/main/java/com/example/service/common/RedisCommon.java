package com.example.service.common;

import com.alibaba.fastjson.JSON;
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

    /* */
    public RedisTemplate getRedisTemplate()
    {
        return redisTemplate;
    }

    //支持事务
    public StringRedisTemplate getStringRedisTemplate()
    {
        //stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate;
    }

}
