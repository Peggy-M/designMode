package com.peggy.service;

import com.peggy.dto.DiscountResult;

/**
 * @Author Peggy
 * @Date 2023-05-17 16:03
 **/
public class DiscountService {
    public DiscountResult sendDiscount(String uid, String number) {
        System.out.println("向用户发放打折券一张: " + uid + " , " + number);
        return new DiscountResult("200", "发放打折券成功");
    }
}
