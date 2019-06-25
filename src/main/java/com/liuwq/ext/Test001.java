package com.liuwq.ext;

import com.liuwq.service.OrderService;
import com.liuwq.service.impl.OrderServiceImpl;

public class Test001 {
    public static void main(String[] args) throws Throwable {
//        OrderService  orderService = new $Proxy0(new MyJdkInvocationHandler(new OrderServiceImpl()));
//        orderService.order();
        OrderService orderService = (OrderService) MyProxy.newProxyInstance(new JavaClassLoader(), OrderService.class, new ExtMyInvocationHandler(new OrderServiceImpl()));
        orderService.add();
    }
}