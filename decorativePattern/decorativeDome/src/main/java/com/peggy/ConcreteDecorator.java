package com.peggy;

/**
 * @Projectname: designMode
 * @Filename: ConcreteDecorator
 * @Author: peggy
 * @Data:2023/5/24 15:10
 * @Description: 具体装饰类
 */

public class ConcreteDecorator extends Decorator{

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation(); //调用原有的业务的方法
        addedBehavior();
    }

    //新增业务方法
    public void addedBehavior(){
        System.out.println("新增业务方法");
    }
}
