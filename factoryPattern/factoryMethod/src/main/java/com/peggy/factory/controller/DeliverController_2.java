package com.peggy.factory.controller;

import com.peggy.factory.service.FreeGoodsFactoryMap;
import com.peggy.factory.service.IFreeGoodsFactory;
import com.peggy.factory.service.impl.DiscoutFreeGoodsFactory;
import com.peggy.factory.service.impl.SamllGiftFreeGoodsFactory;
import com.peggy.response.AwardInfo;
import com.peggy.response.ResponseResult;
import com.peggy.service.IFreeGoods;

/**
 * @Author Peggy
 * @Date 2023-05-17 20:29
 *
 * 现在我们的代码已经基本上符合了开闭原则,当有新增的产品时,我们需要做的事
 * 情包括:
 * 1. 创建新的产品类,并且让该产品实现抽象产品接口
 * 2. 创建产品类对应的具体工厂,并让具体工厂实现抽象工厂
 * 3. 将新的具体工厂对象,添加到FreeGoodsFactoryMap的cachedFactories中
 * 即可,需要改动的代码改动的非常少.
 **/
public class DeliverController_2 {

    private IFreeGoodsFactory freeGoodsFactory = null;


    /**
     * 按照类型的不同发放商品
     */
    public ResponseResult awardToUser(AwardInfo awardInfo) {

        //按照不同的类型获取对应工厂
        freeGoodsFactory = FreeGoodsFactoryMap.getPareserFactory(awardInfo.getAwardTypes());

        IFreeGoods freeGoods = freeGoodsFactory.getInstance();
        System.out.println("===== 工厂方法模式 ========");
        ResponseResult result = freeGoods.sendFreeGoods(awardInfo);
        return result;
    }
}
