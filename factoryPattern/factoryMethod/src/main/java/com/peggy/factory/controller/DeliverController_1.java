package com.peggy.factory.controller;

import com.peggy.factory.service.IFreeGoodsFactory;
import com.peggy.factory.service.impl.DiscoutFreeGoodsFactory;
import com.peggy.factory.service.impl.SamllGiftFreeGoodsFactory;
import com.peggy.response.AwardInfo;
import com.peggy.response.ResponseResult;
import com.peggy.service.IFreeGoods;

/**
 * @Author Peggy
 * @Date 2023-05-17 20:29
 **/
public class DeliverController_1 {

    private IFreeGoodsFactory freeGoodsFactory = null;


    /**
     * 按照类型的不同发放商品
     */
    public ResponseResult awardToUser(AwardInfo awardInfo) {

        /*************************工厂类对象的创建**************************/
        //从上面的代码实现来看，工厂类对象的创建逻辑又耦合进了 awardToUser() 方
        //法中，跟我们最初的代码版本非常相似,引入工厂方法非但没有解决问题，反倒
        //让设计变得更加复杂了。

        if (awardInfo.getAwardTypes() == 1) {
            freeGoodsFactory = new DiscoutFreeGoodsFactory();
        } else if (awardInfo.getAwardTypes() == 2) {
            freeGoodsFactory = new SamllGiftFreeGoodsFactory();
        }

        /***************************************************************/
        //为了解决这样的问题,我们可以创建一个工厂的工厂,去包装我们的工厂模式

        IFreeGoods freeGoods = freeGoodsFactory.getInstance();
        System.out.println("===== 工厂方法模式 ========");
        ResponseResult result = freeGoods.sendFreeGoods(awardInfo);
        return result;
    }
}
