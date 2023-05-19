package com.peggy.prototype;

import com.peggy.dto.User;

import java.io.Serializable;

/**
 * @Author Peggy
 * @Date 2023-05-19 10:54
 * 克隆对象 A
 **/
public class ConcretePrototype implements Cloneable, Serializable {

    public User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConcretePrototype() {
        System.out.println("具体原型对象创建完成");
    }


    @Override
    public ConcretePrototype clone() throws CloneNotSupportedException {
        System.out.println("具体原型对象复制成功");
        return (ConcretePrototype) super.clone();
    }
}
