package com.liuwq.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 下午 5:02
 * @version: V1.0
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">>>before...");
        Object result = method.invoke(target, args);
        System.out.println(">>>after...");
        return result;
    }

    public <T> T getProxyInstance() {
        Class<?> type = target.getClass();
        return (T) Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), this);
    }

}
