package com.peggy.service.client;

import com.peggy.service.IUserDao;
import com.peggy.service.impl.UserDaoImpl;
import com.peggy.service.proxy.UserDaoProxy;

/**
 * @Projectname: designMode
 * @Filename: Client
 * @Author: peggy
 * @Data:2023/5/22 19:35
 * @Description: TODO
 */

public class Client {

    public static void main(String[] args) {

        IUserDao userDaoImpl=new UserDaoImpl();

        IUserDao proxy=new UserDaoProxy(userDaoImpl);

        proxy.save();
    }
}
