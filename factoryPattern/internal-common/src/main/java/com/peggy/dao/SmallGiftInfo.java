package com.peggy.dao;

import lombok.Data;

/**
 * @Author Peggy
 * @Date 2023-05-17 16:02
 * 小礼物信息
 **/
@Data

public class SmallGiftInfo {

    private String userName; // 用户姓名

    private String userPhone; // 用户手机

    private String orderId; // 订单ID

    private String relAddress; // 收货地址

}
