package com.peggy.client;

import com.peggy.bo.Bike;
import com.peggy.builder.Builder;
import com.peggy.builder.HelloBuilder;
import com.peggy.builder.MobikeBuilder;
import com.peggy.director.Director;

/**
 * @Author Peggy
 * @Date 2023-05-18 10:21
 * 客户端
 **/
public class Client {
    public static void main(String[]  args){
        showBike(new HelloBuilder());
        showBike(new MobikeBuilder());
    }

    public static void showBike(Builder builder){
        Director director=new Director(builder);
        Bike bike=director.construct();
        System.out.println(bike.getFrame());
        System.out.println(bike.getSeat());
    }
}
