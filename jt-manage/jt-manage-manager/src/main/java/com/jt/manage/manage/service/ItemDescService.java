package com.jt.manage.manage.service;

import com.jt.manage.manage.mapper.ItemDescMapper;
import com.jt.manage.manage.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescService {
    @Autowired
    private ItemDescMapper itemDescMapper;

    public ItemDesc queryDescById(Long itemId) {
        ItemDesc itemDesc = itemDescMapper.
                selectByPrimaryKey(itemId);
        return itemDesc;
    }

}
