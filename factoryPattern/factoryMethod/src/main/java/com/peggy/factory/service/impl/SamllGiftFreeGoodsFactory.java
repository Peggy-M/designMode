package com.peggy.factory.service.impl;

import com.peggy.factory.service.IFreeGoodsFactory;
import com.peggy.service.IFreeGoods;
import com.peggy.service.impl.SmallGiftFreeGoods;

/**
 * @Author Peggy
 * @Date 2023-05-17 20:27
 * 小礼品发放服务实现
 **/
public class SamllGiftFreeGoodsFactory implements IFreeGoodsFactory {
    @Override
    public IFreeGoods getInstance() {
        return new SmallGiftFreeGoods();
    }
}
