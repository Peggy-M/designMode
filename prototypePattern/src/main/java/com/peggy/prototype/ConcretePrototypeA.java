package com.peggy.prototype;

import com.peggy.User;

/**
 * @Author Peggy
 * @Date 2023-05-19 10:54
 * 克隆对象 A
 **/
public class ConcretePrototypeA implements Cloneable {

    public User user = new User();

    public ConcretePrototypeA() {
        System.out.println("具体原型对象创建完成");
        user.setName("卡卡罗特");
        user.setAge(18);
    }

    @Override
    public ConcretePrototypeA clone() throws CloneNotSupportedException {
        System.out.println("具体原型对象复制成功");
        return (ConcretePrototypeA) super.clone();
    }
}
