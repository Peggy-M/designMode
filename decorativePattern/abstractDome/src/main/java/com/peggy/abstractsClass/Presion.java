package com.peggy.abstractsClass;

import lombok.Data;

/**
 * @Projectname: designMode
 * @Filename: Presion
 * @Author: peggy
 * @Data:2023/5/24 14:40
 * @Description: TODO
 */

public class Presion {
    private String name;

    public Presion(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println(name+"开始装扮");
    }
}
