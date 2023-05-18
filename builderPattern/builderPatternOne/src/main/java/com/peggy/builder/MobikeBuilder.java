package com.peggy.builder;

import com.peggy.bo.Bike;

/**
 * @Author Peggy
 * @Date 2023-05-18 10:15
 * 性价比自行车
 **/
public class MobikeBuilder extends Builder {

    @Override
    public void buildFrame() {
        mBike.setFrame("铝合金车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("橡胶坐垫");
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}
