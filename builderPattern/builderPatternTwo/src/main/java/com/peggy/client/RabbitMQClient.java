package com.peggy.client;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Author Peggy
 * @Date 2023-05-18 15:25
 * 建造者模式
 **/
public class RabbitMQClient {
    //私有构造方法
    private RabbitMQClient(Builder builder) {}

    @Data
    @Accessors(chain = true)
    public static class Builder {
        //属性密闭性,保证对象不可变
        private String host = "127.0.0.1";
        private int port = 5672;
        private int mode;
        private String exchange;
        private String queue;
        private boolean isDurable = true;
        int connectionTimeout = 1000;

        //返回构建好的复杂对象
        public RabbitMQClient build() {
            //首先进行校验
            if (mode == 1) { //工作队列模式不需要设计交换机,但队列名一定要有
                if (exchange != null) {
                    throw new RuntimeException("工作队列模式不需要交换机");
                }
                if (queue == null || queue.trim().equals("")) {
                    throw new RuntimeException("工作队列模式名不能为空");
                }
                if (isDurable == false) {
                    throw new RuntimeException("工作队列模式必须开启持久化");
                }
            } else if (mode == 2) { //路由模式必须设计交换机,但是不能设计队列
                if (exchange == null) {
                    throw new RuntimeException("路由模式下必须设置交换机");
                }
                if (queue != null) {
                    throw new RuntimeException("路由模式无须设计队列名称");
                }
            }
            return new RabbitMQClient(this);
        }
    }
    public void sendMessage(String msg) {
        System.out.println("发送消息......");
    }
}
