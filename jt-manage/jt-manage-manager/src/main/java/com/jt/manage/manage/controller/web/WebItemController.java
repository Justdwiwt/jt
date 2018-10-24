package com.jt.manage.manage.controller.web;

import com.jt.manage.manage.pojo.Item;
import com.jt.manage.manage.pojo.ItemDesc;
import com.jt.manage.manage.service.ItemDescService;
import com.jt.manage.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;

    //前台获取的数据全在这里做,包括desc
    //前台一个请求是获取商品,一个请求是获取商品详情
    @RequestMapping("/items/{itemId}")
    @ResponseBody
    public Item queryItemById(@PathVariable Long itemId) {
        Item item = itemService.queryItemById(itemId);
        return item;
    }

    //商品详情
    @RequestMapping("itemDesc/{itemId}")
    @ResponseBody
    public ItemDesc queryItemDescById(@PathVariable Long itemId) {
        ItemDesc itemDesc = itemDescService.queryDescById(itemId);
        return itemDesc;
    }


}
