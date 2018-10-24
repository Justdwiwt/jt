package com.jt.cart.service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    public List<Cart> queryCarts(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        List<Cart> cartList = cartMapper.select(cart);
        return cartList;
    }

    //保存购物车商品
    //1 已经存在的商品，需要将历史num和新增num添加后，更新商品数量
    //2 无商品，直接insert添加
    public void saveCart(Cart cart) {
        // TODO Auto-generated method stub
        Cart _cart = new Cart();
        _cart.setUserId(cart.getUserId());
        _cart.setItemId(cart.getItemId());
        Cart exists = cartMapper.selectOne(_cart);
        if (null == exists) {//为空，表示新增
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cartMapper.insert(cart);
        } else {//非空表示更新
            //exists存在id，可以利用id更新数据
            Cart cartn = new Cart();
            cartn.setNum(exists.getNum() + cart.getNum());
            cartn.setId(exists.getId());
            cartn.setUpdated(new Date());
            cartMapper.updateByPrimaryKeySelective(cartn);
            //根据非空字段拼接set后字段
        }
    }

    public void updateCart(Long userId, Long itemId, Integer num) {
        //自定义一个sql语句
        //update tb_cart set num=#{num},updated=#{updated} where user_id=#{userId}
        //and item_id=#{itemId}
        Cart cart = new Cart();
        cart.setUpdated(new Date());
        cart.setNum(num);
        cart.setUserId(userId);
        cart.setItemId(itemId);
        cartMapper.updateNum(cart);

    }

    public void deleteCart(Long userId, Long itemId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemId(itemId);
        cartMapper.delete(cart);
    }

}
