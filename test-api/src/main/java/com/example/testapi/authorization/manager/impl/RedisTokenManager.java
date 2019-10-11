package com.example.testapi.authorization.manager.impl;

import com.example.common.entry.user.UserInfo;
import com.example.testapi.authorization.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过Redis存储和验证token的实现类
 * 
 * @see com.example.testapi.authorization.manager.impl.RedisTokenManager
 * @author hfx
 * @date 2019年10月11日14:58:58
 */
@Component
public class RedisTokenManager implements TokenManager {

    private static final String ACCESS = "access:";
    private static final String USER = "user:";
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        // 泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @SuppressWarnings("unused")
    public Long findTokenByUser(UserInfo userInfo) {
        String token = (String) redisTemplate.boundValueOps(USER + userInfo.getUserAccount()).get();
        Long time = redisTemplate.boundValueOps(ACCESS + userInfo.getToken()).getExpire();
        return time;
    }

    /**
     * 校验登录
     */
    public boolean checkLoginToken(String token) {
        //截取token
        String[] arr = token.split("_");
        String tmpToken = (String) redisTemplate.boundValueOps(arr[1]).get();
        if(!StringUtils.isEmpty(tmpToken)&&token.equals(tmpToken)){
            return true;
        }else{
            return false;
        }
    }

    public void createToken(UserInfo userInfo) throws Exception {

        //redisTemplate.boundValueOps(ACCESS + userInfo.getToken()).set(userInfo, 2, TimeUnit.HOURS);
        redisTemplate.boundValueOps(ACCESS + userInfo.getToken()).set(userInfo);

    }

    public UserInfo getToken(String token) {
        if (token == null || token.length() == 0) {
            return null;
        }

        UserInfo userInfo = (UserInfo) redisTemplate.boundValueOps(ACCESS + token).get();
        return userInfo;
    }

    /**
     * 根据本次的request获取token 只能用于网页端的接口,app端并没有token
     * @return
     */
    public UserInfo getToken(){
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        return getToken(token);// 从redis获取
    }

    public boolean checkToken(UserInfo userInfo) {
        if (userInfo == null) {
            return false;
        }
        // 如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        //redisTemplate.boundValueOps(ACCESS + userInfo.getToken()).expire(2, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(String token) {
        redisTemplate.delete(ACCESS + token);
    }


}
