package com.jt.manage.manage.service;

import com.jt.manage.manage.mapper.ItemCatMapper;
import com.jt.manage.manage.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    //业务层获取数据返回controller商品分类
    public List<ItemCat> queryItemCat(Long parentId) {
        //如果自行定义xml映射文件
        //select * from jt_item_cat where parent_id=#{parentId};
        //select(T),拼接的sql语句就是select * from tb_item_cat where
        //T类型的对象中非空属性的拼接 condiction1=#{} and condiction2=#{}
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);
        List<ItemCat> itemCatList = itemCatMapper.select(itemCat);
        return itemCatList;
    }

    public List<ItemCat> queryAll() {
        return itemCatMapper.selectAll();
    }

}
