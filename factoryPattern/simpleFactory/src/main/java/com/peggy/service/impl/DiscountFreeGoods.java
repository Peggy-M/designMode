package com.peggy.service.impl;

import com.peggy.response.AwardInfo;
import com.peggy.response.ResponseResult;
import com.peggy.service.IFreeGoods;

/**
 * @Author Peggy
 * @Date 2023-05-17 17:27
 * 模拟打折服务
 **/
public class DiscountFreeGoods implements IFreeGoods {

    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {
        System.out.println("向用户发放一张打折券: " +
                awardInfo.getUid() + " , " + awardInfo.getAwardNumber());
        return new ResponseResult(200, "打折券发放成功!", null);
    }
}
