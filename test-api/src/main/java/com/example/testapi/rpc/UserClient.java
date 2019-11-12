package com.example.testapi.rpc;

import com.example.common.entry.user.Permission;
import com.example.common.entry.user.User;
import com.example.common.message.Resp;
import com.example.common.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Primary
@FeignClient(name = "test-service",fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @PostMapping(value = "/user/checkLogin")
    ResponseMessage checkLogin(User user);

    @PostMapping(value = "/user/getUserByUserName")
    User getUserByUserName(User user);

    @PostMapping(value = "/user/getPermissionListByUserId")
    List<Permission> getPermissionListByUserId(Integer userId);

    @Component
    class UserClientFallback implements UserClient {

        Logger log= LoggerFactory.getLogger(UserClientFallback.class);

        @Override
        public ResponseMessage checkLogin(User user) {
            log.info("进入了checkLogin");
            return Resp.FAIL("获取消息失败");
        }

        @Override
        public User getUserByUserName(User user) {
            log.info("getUserByUserName");
            return null;
        }

        @Override
        public List<Permission> getPermissionListByUserId(Integer userId) {
            log.info("进入了getPermissionListByUserId");
            return null;
        }

    }


}
