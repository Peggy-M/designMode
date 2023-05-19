package com.peggy;


import com.peggy.dto.User;
import com.peggy.prototype.ConcretePrototype;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 克隆对象测试
 */
public class AppTest {
    @Test
    public void testCloen1() throws CloneNotSupportedException {
        ConcretePrototype c1 = new ConcretePrototype();
        c1.setUser(new User("卡卡罗特", 18));

        ConcretePrototype c2 = c1.clone();
        c2.user.setName("贝吉塔");
        c2.user.setAge(19);

        System.out.println("对象 c1 和 c2 是用一个对象？" + (c1 == c2));

        System.out.println("对象 c1 和 c2 内部的 user 是同一个对象？" + (c1.user == c2.user));
        System.out.println("对象 c1.user "+c1.getUser());
        System.out.println("对象 c2.user "+c2.getUser());
    }

    @Test
    public void testCloen2() throws Exception {

        ConcretePrototype c1 = new ConcretePrototype();

        c1.setUser(new User("卡卡罗特", 18));

        //创建序列化输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c.txt"));
        //将 c1 写入到文件当中
        oos.writeObject(c1);
        oos.close();

        //创建对象序列化输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c.txt"));
        //读取对象
        ConcretePrototype c2 = (ConcretePrototype) ois.readObject();
        c2.user.setName("贝吉塔");
        c2.user.setAge(19);

        System.out.println("对象 c1 和 c2 是用一个对象？" + (c1 == c2));
        System.out.println("对象 c1 和 c2 内部的 user 是同一个对象？" + (c1.user == c2.user));
        System.out.println("对象 c1.user "+c1.getUser());
        System.out.println("对象 c2.user "+c2.getUser());
    }
}
