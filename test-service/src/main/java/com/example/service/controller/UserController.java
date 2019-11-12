package com.example.service.controller;

import com.example.common.entry.user.Permission;
import com.example.common.entry.user.User;
import com.example.common.message.ResponseMessage;
import com.example.service.common.RedisCommon;
import com.example.service.dao.HotelMapper;
import com.example.service.dao.TwMapper;
import com.example.service.service.*;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("用户信息管理")
@RestController
@RequestMapping("user")
public class UserController {

    public final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private TwMapper twMapper;

    @Autowired
    private TestService testService;

    @Autowired
    private TestCVS testCVS;

    @Autowired
    private TempService tempService;

    @Autowired
    private RedisCommon redisCommon;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Value("${customValue.tempValue}")
    private String tempValue;


    @PostMapping("/checkLogin")
    public ResponseMessage checkLogin(@RequestBody User user) throws Exception
    {
        System.out.println("进入了service的checkLogin");
        return userService.checkLogin(user.getUserName(),user.getPassword());
    }

    @PostMapping("/getUserByUserName")
    public User getUserByUserName(@RequestBody User user) throws Exception
    {
        System.out.println("进入了service的getUserByUserName");
        return userService.getUserByUserName(user);
    }

    @PostMapping("/getPermissionListByUserId")
    public List<Permission> getPermissionListByUserId(@RequestBody Integer userId)
    {
        return userService.getPermissionListByUserId(userId);
    }

    @PostMapping("getData2")
    public String getData2()
    {
        return testService.getData();
    }

    @PostMapping("getData3")
    public String getData3()
    {
        return "获得的tempValue="+tempValue;
    }

}
