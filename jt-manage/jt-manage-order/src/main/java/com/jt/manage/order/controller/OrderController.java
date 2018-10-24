package com.jt.manage.order.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jt.common.vo.SysResult;
import com.jt.manage.order.pojo.Order;
import com.jt.manage.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    //创建订单
    @RequestMapping("/order/create")
    @ResponseBody
    public SysResult createOrder(@RequestBody String orderJson) throws JsonParseException, JsonMappingException, IOException {
        //将参数交给service，
        String orderId = orderService.createOrder(orderJson);
        return SysResult.oK(orderId);
    }

    //查询订单
    @RequestMapping("order/query/{orderId}")
    @ResponseBody
    public Order queryOrder(@PathVariable String orderId) {
        Order order = orderService.queryOrder(orderId);
        return order;
    }
}



























