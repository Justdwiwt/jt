package com.jt.manage.order.mapper;

import com.jt.manage.order.pojo.Order;

public interface OrderMapper {

    void saveOrder(Order order);

    Order queryOrder(String orderId);

}
