package com.peggy.service;

/**
 * @Projectname: designMode
 * @Filename: RefinedAbstraction
 * @Author: peggy
 * @Data:2023/5/23 17:06
 * @Description: TODO
 */

public class RefinedAbstraction extends Abstraction{
    @Override
    public void operation() {
        System.out.println("华为手机");
        super.operation();
        System.out.println("中华有为");
    }
}
