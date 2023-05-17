package com.peggy.service.controller;

import com.peggy.response.AwardInfo;
import com.peggy.response.ResponseResult;
import com.peggy.service.IFreeGoods;
import com.peggy.service.factory.FreeGoodsFactory;

/**
 * @Author Peggy
 * @Date 2023-05-17 17:53
 **/
public class DeliverController {
    public ResponseResult awardToUser(AwardInfo awardInfo) {
        try {
            IFreeGoods iFreeGoods = FreeGoodsFactory.getInstance(awardInfo.getAwardTypes());
            ResponseResult responseResult = iFreeGoods.sendFreeGoods(awardInfo);
            return responseResult;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("奖品发放失败");
        }
    }
}
