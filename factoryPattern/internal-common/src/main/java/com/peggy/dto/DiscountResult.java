package com.peggy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Peggy
 * @Date 2023-05-17 16:02
 * 折扣信息返回
 **/
@Data
@AllArgsConstructor
public class DiscountResult {

    private String status; // 状态码

    private String message; // 信息

}
