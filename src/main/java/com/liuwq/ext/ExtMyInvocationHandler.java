package com.liuwq.ext;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 下午 5:02
 * @version: V1.0
 */
public class ExtMyInvocationHandler implements ExtInvocationHandler {

    private Object target;

    public ExtMyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">>>before...");
        Object result = method.invoke(target, args);
        System.out.println(">>>after...");
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("aa", "aa");
        return result;
    }

}
