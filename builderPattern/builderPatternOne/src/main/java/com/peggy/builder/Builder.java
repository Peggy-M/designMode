package com.peggy.builder;

import com.peggy.bo.Bike;

/**
 * @Author Peggy
 * @Date 2023-05-18 10:09
 * 构建者类
 **/
public abstract class Builder {
    protected Bike mBike = new Bike();

    //构建框架
    public abstract void buildFrame();
    //构建座椅
    public abstract void buildSeat();
    //构建自行车
    public abstract Bike createBike();
}
