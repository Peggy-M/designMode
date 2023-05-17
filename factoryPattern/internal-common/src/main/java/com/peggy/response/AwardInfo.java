package com.peggy.response;

import lombok.Data;

import java.util.Map;

/**
 * @Author Peggy
 * @Date 2023-05-17 15:47
 * 信息对应实体类
 **/
@Data

public class AwardInfo {

    private String uid; //用户唯一ID

    private Integer awardType; //奖品类型: 1 打折券 ,2 优酷会员,3 小礼品

    private String awardNumber; //奖品编号

    Map<String, String> extMap; //额外信息

}
