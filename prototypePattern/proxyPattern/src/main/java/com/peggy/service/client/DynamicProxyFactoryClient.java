package com.peggy.service.client;

import com.peggy.service.IUserDao;
import com.peggy.service.impl.UserDaoImpl;
import com.peggy.service.proxy.DynamicProxyFactory;
import com.peggy.service.proxy.UserDaoProxy;

/**
 * @Projectname: designMode
 * @Filename: DynamicProxyClient
 * @Author: peggy
 * @Data:2023/5/22 20:04
 * @Description: 动态代理工厂客户端测试
 */

public class DynamicProxyFactoryClient {


    public static void main(String[] args) {
        IUserDao iUserDao=new UserDaoImpl();
        System.out.println(iUserDao.getClass());

        DynamicProxyFactory proxy=new DynamicProxyFactory(iUserDao);
        IUserDao proxyTargetObj = (IUserDao) proxy.getProxyInstance();
        System.out.println(proxyTargetObj.getClass());
        proxyTargetObj.save();
        while (true);
    }
}
