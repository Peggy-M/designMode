package com.peggy.service.factory.impl;

import com.peggy.service.factory.AbstractFreezer;
import com.peggy.service.factory.AbstractTV;
import com.peggy.service.factory.dto.HisenseTV;
import com.peggy.service.factory.AppliancesFactory;
import com.peggy.service.factory.dto.HissenseFreezer;

/**
 * @Author Peggy
 * @Date 2023-05-17 21:26
 **/
public class HisenseFactory implements AppliancesFactory {
    @Override
    public AbstractTV createTV() {
        return new HisenseTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HissenseFreezer();
    }
}
