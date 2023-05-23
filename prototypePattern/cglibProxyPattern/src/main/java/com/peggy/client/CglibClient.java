package com.peggy.client;

import com.peggy.dto.User;
import com.peggy.service.UserLogProxy;
import com.peggy.service.UserServiceImpl;

import java.util.List;

/**
 * @Projectname: designMode
 * @Filename: CglibClient
 * @Author: peggy
 * @Data:2023/5/22 22:11
 * @Description: TODO
 */

public class CglibClient {
    public static void main(String[] args) {
        //目标对象
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.getClass());
        //代理对象
        UserServiceImpl proxy = (UserServiceImpl) new UserLogProxy(userService).getLogProxy();
//        System.out.println(proxy.getClass());
//        List<User> userList = proxy.findUserList();
//        System.out.println("用户信息: "+userList);

        System.out.println("=========================");
        System.out.println(proxy.getUserName());
        System.out.println("=========================");
    }
}
