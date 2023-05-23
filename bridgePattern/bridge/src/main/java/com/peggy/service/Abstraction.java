package com.peggy.service;

/**
 * @Projectname: designMode
 * @Filename: Abstraction
 * @Author: peggy
 * @Data:2023/5/23 17:05
 * @Description: TODO
 */

public class Abstraction {
    protected Implementor implementor;

    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }

    public void operation(){
        implementor.Openration();
    }
}
