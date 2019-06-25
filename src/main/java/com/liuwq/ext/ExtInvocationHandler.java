package com.liuwq.ext;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 下午 4:55
 * @version: V1.0
 */
public interface ExtInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
