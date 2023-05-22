package com.peggy.service.impl;

import com.peggy.service.IUserDao;

/**
 * @Projectname: designMode
 * @Filename: UserDaoImpl
 * @Author: peggy
 * @Data:2023/5/22 19:18
 * @Description: 实现类
 */

public class UserDaoImpl implements IUserDao {
    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
