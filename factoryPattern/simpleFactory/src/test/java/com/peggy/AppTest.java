package com.peggy;

import com.peggy.response.AwardInfo;
import com.peggy.response.ResponseResult;
import com.peggy.service.controller.DeliverController;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂
 * 优点：
 * 封装了创建对象的过程，可以通过参数直接获取对象。把对象的创建和业务逻
 * 辑层分开，这样以后就避免了修改客户代码，如果要实现新产品直接修改工厂
 * 类，而不需要在原代码中修改，这样就降低了客户代码修改的可能性，更加容
 * 易扩展。
 *
 * 缺点:
 * 增加新产品时还是需要修改工厂类的代码，违背了“开闭原则”。
 *
 */
public class AppTest {
    DeliverController deliverController = new DeliverController();

    @Test
    public void test1() {
        //1. 发放打折券优惠
        AwardInfo info1 = new AwardInfo();
        info1.setUid("1001");
        info1.setAwardTypes(1);
        info1.setAwardNumber("DEL12345");
        ResponseResult result = deliverController.awardToUser(info1);
        System.out.println(result);
    }

    @Test
    public void test2() {
        //2. 发放优酷会员
        AwardInfo info2 = new AwardInfo();
        info2.setUid("1002");
        info2.setAwardTypes(2);
        info2.setAwardNumber("DW12345");
        Map<String, String> map = new HashMap<>();
        map.put("phone", "13512341234");
        info2.setExtMap(map);
        ResponseResult result1 = deliverController.awardToUser(info2);
        System.out.println(result1);
    }

    @Test
    public void test3() {
//3. 发放小礼品
        AwardInfo info3 = new AwardInfo();
        info3.setUid("1003");
        info3.setAwardTypes(3);
        info3.setAwardNumber("SM12345");
        Map<String, String> map2 = new HashMap<>();
        map2.put("username", "大远");
        map2.put("phone", "13512341234");
        map2.put("address", "北京天安门");
        info3.setExtMap(map2);
        ResponseResult result2 = deliverController.awardToUser(info3);
        System.out.println(result2);
    }
}
