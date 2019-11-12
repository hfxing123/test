package com.example.testapi.service;

import com.example.common.entry.user.User;
import com.example.testapi.rpc.UserClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO 这里设置数据
@Service
public class LoginShiroService {

    @Autowired
    private UserClient userClient;

    public void login(User user)
    {
        //TODO
        user=userClient.getUserByUserName(user);

        if(user!=null)
        {
            loginShiro(user.getUserId(),user.getPassword());
        }

        //TODO 报错进入了doGetAuthenticationInfo

    }

    /**
     * 使用userId和密码来登录shiro，用作后续权限的判断
     * @param userId
     * @param password
     */
    private void loginShiro(Integer userId,String password)
    {

        //TDOO

        //1. Acquire submitted principals and credentials:
        AuthenticationToken token =
                new UsernamePasswordToken(userId.toString(), password);

        //2. Get the current Subject:
        Subject currentUser = SecurityUtils.getSubject();

        //3. Login:
        currentUser.login(token);

        System.out.println(token);

        //3. Login:
		/*
		try {

			currentUser.login(token);

		} catch (IncorrectCredentialsException ice) {

		} catch (LockedAccountException lae) {

		}
		catch (AuthenticationException ae) {

		}
		*/

    }

    public void loginOut()
    {
        //退出shiro，TODO 退出Redis
        loginOutShiro();
    }

    private void loginOutShiro()
    {
        //2. Get the current Subject:
        Subject currentUser = SecurityUtils.getSubject();

        //3. Login:
        currentUser.logout(); //removes all identifying information and invalidates their session too.
    }

}
