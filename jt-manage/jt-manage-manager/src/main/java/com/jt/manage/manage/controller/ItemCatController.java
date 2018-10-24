package com.jt.manage.manage.controller;

import com.jt.manage.manage.pojo.ItemCat;
import com.jt.manage.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    //获取后台商品分类树展示,参数是节点id,作为parent_id的值,查询下级列表
    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<ItemCat> queryItemCat(
            @RequestParam(value = "id", defaultValue = "0") Long parentId) {
        //调用业务层代码,返回分类list对象
        List<ItemCat> itemCatList = itemCatService.
                queryItemCat(parentId);
        return itemCatList;
    }

    @RequestMapping("haha")
    public String testHH() {
        int a = 1;
        return "haha";
    }
}
