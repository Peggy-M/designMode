package com.peggy.service.impl;

import com.peggy.response.AwardInfo;
import com.peggy.response.ResponseResult;
import com.peggy.service.IFreeGoods;

/**
 * @Author Peggy
 * @Date 2023-05-17 17:36
 * 优酷 会员服务
 **/
public class YouKuMemberFreeGoods implements IFreeGoods {

    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {
        String phone = awardInfo.getExtMap().get("phone");
        System.out.println("发放优酷会员成功,绑定手机号: " + phone);
        return new ResponseResult(200, "优酷会员发放成功!", null);
    }
}
