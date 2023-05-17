package com.peggy.factory.service.impl;

import com.peggy.factory.service.IFreeGoodsFactory;
import com.peggy.service.IFreeGoods;
import com.peggy.service.impl.DiscountFreeGoods;

/**
 * @Author Peggy
 * @Date 2023-05-17 20:26
 * 打折服务实现
 **/
public class DiscoutFreeGoodsFactory implements IFreeGoodsFactory {
    @Override
    public IFreeGoods getInstance() {
        return new DiscountFreeGoods();
    }
}
