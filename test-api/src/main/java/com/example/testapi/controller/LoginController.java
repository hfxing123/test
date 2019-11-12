package com.example.testapi.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.entry.user.Permission;
import com.example.common.entry.user.User;
import com.example.common.message.ResponseMessage;
import com.example.testapi.authorization.annotation.CustomAuthorization;
import com.example.testapi.rpc.UserClient;
import com.example.testapi.service.LoginShiroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("login")
@Api(tags = "登录信息管理",description="登录信息管理的API")
public class LoginController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private LoginShiroService loginShiroService;

    @ApiOperation(value = "登录",notes = "用于登录",response = ResponseMessage.class,produces = "application/json")
    @PostMapping("/login")
    public String login(User user)
    {
        System.out.println("到达了api的login");

        loginShiroService.login(user);
        //return JSON.toJSONString();
        return "成功";
    }

    @RequiresPermissions("user:show")
    @PostMapping("/getPermissionListByUserId")
    public List<Permission> getPermissionListByUserId(Integer userId)
    {
        return userClient.getPermissionListByUserId(userId);
    }

    @RequiresPermissions("test:list")
    @PostMapping("/testList")
    public List<Permission> testList(Integer userId)
    {
        return userClient.getPermissionListByUserId(userId);
    }

    @ApiOperation(value = "退出登录",notes = "用于退出登录",response = ResponseMessage.class,produces = "application/json")
    @PostMapping("/loginOut")
    public String loginOut()
    {
        return "测试成功"+ UUID.randomUUID().toString();
    }


}
