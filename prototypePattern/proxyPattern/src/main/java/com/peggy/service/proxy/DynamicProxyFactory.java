package com.peggy.service.proxy;

import java.lang.reflect.Proxy;

/**
 * @Projectname: designMode
 * @Filename: ProxyFactory
 * @Author: peggy
 * @Data:2023/5/22 19:52
 * @Description: 代理工厂-动态生成代理对象
 */

public class DynamicProxyFactory {
    private Object target;  //代理目标对象

    public DynamicProxyFactory(Object target) {
        this.target = target;
    }

    //为了目标生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                //指定目标对象的类加载器
                target.getClass().getClassLoader(),
                //目录对象实现接口的类型
                target.getClass().getInterfaces(),

                /**
                 * proxy 代理对象
                 * method 应用是代理对象调用的接口方法 Method 实例
                 * args 代理对象调用接口方法传递的实际参数
                 * return 返回目标方法的返回值,如果目标方法没有返回值,就返回 null
                 */
                (proxy, method, args) -> {
                    System.out.println("开启事务");
                    method.invoke(target,args);
                    System.out.println("提交事务");
                    return null;
                }
        );
    }


}
