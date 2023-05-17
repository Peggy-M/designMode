package com.peggy.service.factory.client;

import com.peggy.service.factory.AbstractFreezer;
import com.peggy.service.factory.AbstractTV;
import com.peggy.service.factory.AppliancesFactory;
import com.peggy.service.factory.impl.HisenseFactory;
import lombok.Data;

/**
 * @Author Peggy
 * @Date 2023-05-17 21:35
 **/
@Data
public class Client {
    private AbstractTV tv;
    private AbstractFreezer freezer;


    public Client(AppliancesFactory factory) {
        //在客户端看来就是使用抽象工厂来生产家电
        this.tv = factory.createTV();
        this.freezer = factory.createFreezer();
    }
    public static void main(String[] args) {
        Client client = new Client(new HisenseFactory());
        AbstractTV tv = client.getTv();
        System.out.println(tv);
        AbstractFreezer freezer = client.getFreezer();
        System.out.println(freezer);
    }
}
