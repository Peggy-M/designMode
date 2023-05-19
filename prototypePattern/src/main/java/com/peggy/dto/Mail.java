package com.peggy.dto;

import com.peggy.prototype.AdvTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Projectname: designMode
 * @Filename: Mail
 * @Author: peggy
 * @Data:2023/5/20 0:18
 * @Description: 邮件类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail  {
    //收件人
    private String receiver;
    //邮件名称
    private String subject;
    //称谓
    private String appellation;
    //邮件内容
    private String context;
    //邮件尾部, 一般是"xxx版权所有"等信息
    private String tail;

    //构造函数
    public Mail(AdvTemplate advTemplate) {
        this.context = advTemplate.getAdvContext();
        this.subject = advTemplate.getAdvSubject();
    }
}
