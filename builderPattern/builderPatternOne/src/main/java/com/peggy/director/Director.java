package com.peggy.director;

import com.peggy.bo.Bike;
import com.peggy.builder.Builder;

/**
 * @Author Peggy
 * @Date 2023-05-18 10:17
 * 指挥者
 **/
public class Director {
    private Builder mBuilder;

    public Director(Builder mBuilder) {
        this.mBuilder = mBuilder;
    }

    public Bike construct(){
        mBuilder.buildFrame();
        mBuilder.buildSeat();
        return mBuilder.createBike();
    }
}
