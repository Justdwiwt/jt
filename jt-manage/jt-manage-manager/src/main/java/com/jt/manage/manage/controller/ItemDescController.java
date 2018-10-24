package com.jt.manage.manage.controller;

import com.jt.common.vo.SysResult;
import com.jt.manage.manage.pojo.ItemDesc;
import com.jt.manage.manage.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemDescController {
    @Autowired
    private ItemDescService itemDescService;

    //商品详情的查询,修改的回显
    @RequestMapping("item/query/item/desc/{itemId}")
    @ResponseBody
    public SysResult queryDescById(@PathVariable Long itemId) {
        ItemDesc itemDesc = itemDescService.
                queryDescById(itemId);
        if (itemDesc != null) {
            return SysResult.oK(itemDesc);
        }
        return SysResult.build(201, "无对应详情");
    }
}











