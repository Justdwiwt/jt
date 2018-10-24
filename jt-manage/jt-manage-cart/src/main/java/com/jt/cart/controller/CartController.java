package com.jt.cart.controller;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    //查询我的购物车
    @RequestMapping("cart/query/{userId}")
    @ResponseBody
    public SysResult myCart(@PathVariable Long userId) {
        List<Cart> cartList = cartService.queryCarts(userId);
        return SysResult.oK(cartList);
    }

    //保存我的购物车商品
    @RequestMapping("cart/save")
    @ResponseBody
    public SysResult saveCart(Cart cart) {
        cartService.saveCart(cart);
        return SysResult.oK();
    }

    //更新商品数量
    @RequestMapping("cart/update/num/{userId}/{itemId}/{num}")
    @ResponseBody
    public SysResult updateCart(@PathVariable Long userId,
                                @PathVariable Long itemId, @PathVariable Integer num) {
        cartService.updateCart(userId, itemId, num);
        return SysResult.oK();
    }

    //删除购物车
    @RequestMapping("cart/delete/{userId}/{itemId}")
    @ResponseBody
    public SysResult deleteCart(@PathVariable Long userId,
                                @PathVariable Long itemId) {
        cartService.deleteCart(userId, itemId);
        return SysResult.oK();
    }
}














