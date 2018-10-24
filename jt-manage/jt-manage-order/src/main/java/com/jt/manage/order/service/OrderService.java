package com.jt.manage.order.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jt.common.util.ObjectUtil;
import com.jt.manage.order.mapper.OrderMapper;
import com.jt.manage.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public String createOrder(String orderJson) throws JsonParseException, JsonMappingException, IOException {
        //将json转化成order对象
        Order order = ObjectUtil.
                mapper.readValue(orderJson, Order.class);
        //orderId，userId+currenTime;
        String orderId = order.getUserId() + "" +
                System.currentTimeMillis();
        order.setOrderId(orderId);
        orderMapper.saveOrder(order);
        return orderId;
    }

    public Order queryOrder(String orderId) {
        Order order = orderMapper.queryOrder(orderId);
        return order;
    }

}
