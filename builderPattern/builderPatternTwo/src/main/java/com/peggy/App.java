package com.peggy;

import com.peggy.client.RabbitMQClient;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        //使用链式编程设置参数
        RabbitMQClient build  = new RabbitMQClient.Builder().setHost("192.168.52.123").setMode(2).setExchange("text-exchange").setPort(5672).setDurable(true).build();

        build.sendMessage("Test");

    }

}
