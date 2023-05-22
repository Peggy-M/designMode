package com.peggy.service.impl;

import com.alibaba.fastjson.JSON;
import com.peggy.service.SmallGiftInfo;
import com.peggy.response.AwardInfo;
import com.peggy.response.ResponseResult;
import com.peggy.service.IFreeGoods;

import java.util.UUID;

/**
 * @Author Peggy
 * @Date 2023-05-17 17:34
 * 小礼品发放服务
 **/
public class SmallGiftFreeGoods implements IFreeGoods {
    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {
        SmallGiftInfo smallGiftInfo = new SmallGiftInfo();
        smallGiftInfo.setUserPhone(awardInfo.getExtMap().get("phone"
        ));
        smallGiftInfo.setUserName(awardInfo.getExtMap().get("username"));
        smallGiftInfo.setRelAddress(awardInfo.getExtMap().get("address"));
        smallGiftInfo.setOrderId(UUID.randomUUID().toString());
        System.out.println("小礼品发放成,请注意查收: " + JSON.toJSON(smallGiftInfo));
        return new ResponseResult(200, "小礼品发送成功", smallGiftInfo);
    }
}
