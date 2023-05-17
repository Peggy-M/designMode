package com.peggy.service.factory.impl;

import com.peggy.service.factory.AbstractFreezer;
import com.peggy.service.factory.AbstractTV;
import com.peggy.service.factory.AppliancesFactory;
import com.peggy.service.factory.dto.HairFreezer;
import com.peggy.service.factory.dto.HairTV;

/**
 * @Author Peggy
 * @Date 2023-05-17 21:25
 **/
public class HairFactory implements AppliancesFactory {
    @Override
    public AbstractTV createTV() {
        return new HairTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HairFreezer();
    }
}
