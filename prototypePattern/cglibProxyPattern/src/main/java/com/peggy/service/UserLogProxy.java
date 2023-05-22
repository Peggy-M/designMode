package com.peggy.service;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceptor implements net.sf.cglib.proxy.MethodInterceptor {
    private Object target;

    public UserLogProxy(){}
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}
