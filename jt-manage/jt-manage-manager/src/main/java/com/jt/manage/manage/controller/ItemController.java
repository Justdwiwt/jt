package com.jt.manage.manage.controller;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.manage.pojo.Item;
import com.jt.manage.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    //查询商品
    @RequestMapping("/item/query")
    @ResponseBody
    public EasyUIResult queryItemList(Integer page, Integer rows) {
        //service调用方法查询
        EasyUIResult result = itemService.queryItemList(page, rows);
        return result;
    }

    //新增商品
    @RequestMapping("/item/save")
    @ResponseBody
    public SysResult saveItem(Item item, String desc) {
        //插入数据,调用service插入
        itemService.saveItem(item, desc);
        return SysResult.oK();
    }

    //商品修改
    @RequestMapping("/item/update")
    @ResponseBody
    public SysResult updateItem(Item item, String desc) {

        itemService.updateItem(item, desc);
        return SysResult.oK();
    }


}
