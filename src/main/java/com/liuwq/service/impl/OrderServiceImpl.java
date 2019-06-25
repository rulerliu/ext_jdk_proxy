package com.liuwq.service.impl;

import com.liuwq.service.OrderService;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 下午 4:33
 * @version: V1.0
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public String add() {
        System.out.println(">>>add order...");
        return "success";
    }

    @Override
    public String update() {
        System.out.println(">>>update order...");
        return "success";
    }
}
