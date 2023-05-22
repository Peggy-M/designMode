package com.peggy.service;

import com.alibaba.fastjson.JSON;

/**
 * @Author Peggy
 * @Date 2023-05-17 16:05
 **/
public class SmallGiftService {
    public Boolean giveSmallGift(SmallGiftInfo smallGiftInfo) {
        System.out.println("小礼品已发货,获奖用户注意查收! " + JSON.toJSON(smallGiftInfo));
        return true;
    }
}
