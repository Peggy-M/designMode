package com.peggy.service.proxy;

import com.peggy.service.IUserDao;

/**
 * @Projectname: designMode
 * @Filename: UserDaoProxy
 * @Author: peggy
 * @Data:2023/5/22 19:20
 * @Description: 静态代理类
 */

public class UserDaoProxy implements IUserDao {
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开启事务");
        target.save();
        System.out.println("提交事务");
    }
}
