package com.jt.manage.manage.controller.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jt.common.util.ObjectUtil;
import com.jt.manage.manage.pojo.ItemCat;
import com.jt.manage.manage.pojo.ItemCatResult;
import com.jt.manage.manage.service.ItemCatService;
import com.jt.manage.manage.util.ItemCatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WebItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("web/itemcat/all")
    @ResponseBody
    public String getItemCatAll(String callback) throws JsonProcessingException {
        //获取所有的分类List
        List<ItemCat> itemCatList = itemCatService.queryAll();
        //获取的list具有1181条所有的分类集合
        ItemCatResult result =
                ItemCatUtil.allItemCat(itemCatList);
        String jsonData = ObjectUtil.mapper.
                writeValueAsString(result);
        String jsonpData = callback + "(" + jsonData + ")";
        return jsonpData;
    }
}
