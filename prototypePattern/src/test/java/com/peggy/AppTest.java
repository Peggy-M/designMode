package com.peggy;


import com.peggy.prototype.ConcretePrototypeA;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 克隆对象测试
 */
public class AppTest {
    @Test
    public void testCloen1() throws CloneNotSupportedException {
        ConcretePrototypeA c1 = new ConcretePrototypeA();
        ConcretePrototypeA c2 = c1.clone();
        System.out.println("对象 c1 和 c2 是用一个对象？" + (c1 == c2));
        System.out.println("对象 c1 和 c2 内部的 user 是同一个对象？" + (c1.user == c2.user));
    }

   
}
