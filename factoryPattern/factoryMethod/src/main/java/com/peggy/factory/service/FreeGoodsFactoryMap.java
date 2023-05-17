package com.peggy.factory.service;


import com.peggy.factory.service.impl.DiscoutFreeGoodsFactory;
import com.peggy.factory.service.impl.SamllGiftFreeGoodsFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Peggy
 * @Date 2023-05-17 20:39
 * <p>
 * 单利初始化好一个工厂直接在使用的时候进行调用即可
 **/
public class FreeGoodsFactoryMap {

    private static final Map<Integer, IFreeGoodsFactory> cachedFactories = new HashMap<>();

    static {
        cachedFactories.put(1, new DiscoutFreeGoodsFactory());
        cachedFactories.put(2, new SamllGiftFreeGoodsFactory());
    }

    public static IFreeGoodsFactory getPareserFactory(Integer type) {
        return cachedFactories.get(type);
    }

}
