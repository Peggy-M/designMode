package com.peggy.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Projectname: designMode
 * @Filename: AdvTemplate
 * @Author: peggy
 * @Data:2023/5/20 0:16
 * @Description: 广告信模板代码
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvTemplate {
    //广告信名称
    private String advSubject = "xx银行本月还款达标,可抽iPhone13等好礼!";
    //广告信内容
    private String advContext = "达标用户请在2022年3月1日到2022年3月30参与抽奖......";

}
