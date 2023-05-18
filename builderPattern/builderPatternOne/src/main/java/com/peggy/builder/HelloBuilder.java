package com.peggy.builder;

import com.peggy.bo.Bike;

/**
 * @Author Peggy
 * @Date 2023-05-18 10:13
 * 碳纤维自行车构建
 **/
public class HelloBuilder extends Builder {
    @Override
    public void buildFrame() {
        mBike.setFrame("碳纤维骨架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("真皮坐垫");
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}
