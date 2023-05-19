package com.peggy.client;

import com.peggy.dto.Mail;
import com.peggy.prototype.AdvTemplate;

import java.util.Random;

/**
 * @Projectname: designMode
 * @Filename: Client
 * @Author: peggy
 * @Data:2023/5/20 0:19
 * @Description: Client
 */

public class Client {
    //发送信息的是数量,这个值可以从数据库获取
    private static int MAX_COUNT = 6;

    //发送邮件
    public static void sendMail(Mail mail) {
        System.out.println("标题: " + mail.getSubject() + "\t 收件人: " + mail.getReceiver() + "\t..发送成功!");
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        //模拟邮件发送
        int i = 0;
        //把模板定义出来,数据是从数据库获取的
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("xxx银行版权所有");
        while (i < MAX_COUNT) {
            //下面是每封邮件不同的地方
            Mail cloneMail = mail.clone();
            cloneMail.setAppellation(" 先生 (女士)");
            Random random = new Random();
            int num = random.nextInt(9999999);
            cloneMail.setReceiver(num + "@" + "liuliuqiu.com");
            //发送 邮件
            sendMail(cloneMail);
            i++;
        }
    }
}
