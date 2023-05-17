package com.peggy.service;

import com.peggy.response.AwardInfo;
import com.peggy.response.ResponseResult;

/**
 * 免费商品发放接口
 * @Author Peggy
 * @Date 2023-05-17 17:24
 **/
public interface IFreeGoods {
    ResponseResult sendFreeGoods(AwardInfo awardInfo);
}
