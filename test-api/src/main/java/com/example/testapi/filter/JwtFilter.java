package com.example.testapi.filter;

import com.example.testapi.authorization.manager.impl.RedisTokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截请求,限制多位置登录
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    RedisTokenManager redisTokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.debug("经过了拦截器!");

        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            return false;
        }
        //暂时所有都返回true，不校验token，根据业务再加
        return true;
        //return redisTokenManager.checkLoginToken(token);
    }
}
