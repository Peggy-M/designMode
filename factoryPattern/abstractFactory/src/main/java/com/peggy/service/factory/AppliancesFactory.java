package com.peggy.service.factory;

/**
 * @Author Peggy
 * @Date 2023-05-17 21:21
 * 抽象工厂: 在一个抽象工厂中可以声明多个工厂方法,用于创建不同类型的产品
 **/
public interface AppliancesFactory {
    AbstractTV createTV();

    AbstractFreezer createFreezer();
}
