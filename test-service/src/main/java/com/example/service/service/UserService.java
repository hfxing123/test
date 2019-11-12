package com.example.service.service;

import com.example.common.entry.user.Permission;
import com.example.common.entry.user.User;
import com.example.common.exception.BusinessException;
import com.example.common.message.Resp;
import com.example.common.message.ResponseMessage;
import com.example.service.dao.user.PermissionMapper;
import com.example.service.dao.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户id来获取所拥有的权限
     * @param userId
     * @return
     */
    public List<Permission> getPermissionByUserId(Integer userId)
    {
        return permissionMapper.selectPermissionListByUserId(userId);
    }

    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    public List<Permission> getPermissionListByUserId(Integer userId)
    {
        return permissionMapper.selectPermissionListByUserId(userId);
    }

    public User getUserByUserName(User user)
    {
        return userMapper.getUserByUserName(user);
    }

    public ResponseMessage checkLogin(String userName, String password) throws Exception
    {
        //测试回滚
		/*
		boolean b=true;
		if(b)
		{
			throw new BusinessException("后端有错误");
		}
		*/

        User record=new User();
        record.setUserName(userName);

        User u=userMapper.getUserByUserName(record);

        return checkCanLogin(u, password);
    }


    public User login(String userName,String password) throws Exception
    {

        User user=new User();
        user.setUserName(userName);
        User u=userMapper.getUserByUserName(user);

        ResponseMessage r=checkCanLogin(u, password);

        if(r.isSuccess())
        {
            //登录shiro，控制权限
            // TODO
            //loginShiro(u.getUserId(), password);
        }
        else
        {
            throw new BusinessException(r.getMessage());
        }

        return u;
    }

    /**
     * 根据密码和randomCode来加密密码
     * @param password
     * @param randomCode
     * @return
     */
    private String getRandomCodePassword(String password,String randomCode)
    {
        return password;
        //return EncodingOperation.MD5(password+randomCode);
    }

    private ResponseMessage checkCanLogin(User u, String password)
    {
        if(u==null)
        {
            return Resp.FAIL("没有该账号");
        }

        if(!u.getPassword().equals(getRandomCodePassword(password, u.getRandomCode())))
        {
            return Resp.FAIL("密码错误");
        }
        return Resp.SUCCESS(null,"可以登录");
    }



}
