package com.peggy.service;

import com.peggy.dto.User;

import java.util.Collections;
import java.util.List;

/**
 * @Projectname: designMode
 * @Filename: UserServiceImpl
 * @Author: peggy
 * @Data:2023/5/22 21:49
 * @Description: 目标类
 */

public class UserServiceImpl {
    public List<User> findUserList(){
        return Collections.singletonList(new User("tom",18));
    }
}
