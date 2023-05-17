package com.peggy.service.factory;

import com.peggy.service.IFreeGoods;
import com.peggy.service.impl.DiscountFreeGoods;
import com.peggy.service.impl.SmallGiftFreeGoods;
import com.peggy.service.impl.YouKuMemberFreeGoods;

/**
 * @Author Peggy
 * @Date 2023-05-17 17:46
 * 生成免费商品
 **/
public class FreeGoodsFactory {

    //通过商品请求类型返回指定商品
    public static IFreeGoods getInstance(Integer awardType) {
        IFreeGoods iFreeGoods = null;
        if (awardType == 1) { //打折券
            iFreeGoods = new DiscountFreeGoods();
        }
        if (awardType == 2) { //优酷会员
            iFreeGoods = new SmallGiftFreeGoods();
        }
        if (awardType == 3) { //小礼品
            iFreeGoods = new YouKuMemberFreeGoods();
        }
        return iFreeGoods;
    }
}
