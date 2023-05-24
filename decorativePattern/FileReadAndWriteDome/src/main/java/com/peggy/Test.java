package com.peggy;

/**
 * @Projectname: designMode
 * @Filename: Test
 * @Author: peggy
 * @Data:2023/5/24 15:38
 * @Description: 测试类
 */

public class Test {
    public static void main(String[] args) {
        String info = "name:tom,age:15";
        DataLoaderDecorator decorator = new EncryptionDataDecorator(new BaseFileDataLoader("demo.txt"));
        decorator.write(info);
        String data = decorator.redFile();
        System.out.println(data);
    }
}
