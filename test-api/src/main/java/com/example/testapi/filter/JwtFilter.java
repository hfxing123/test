package com.example.testapi.filter;

import com.example.testapi.authorization.manager.impl.RedisTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截请求,限制多位置登录
 */
//@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    RedisTokenManager redisTokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器!");
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            return false;
        }
        return redisTokenManager.checkLoginToken(token);
    }
}
