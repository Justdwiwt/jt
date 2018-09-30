package com.jt.manage.service;

import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class ItemCatService extends BaseService<ItemCat> {

    @Autowired
    private ItemCatMapper itemCatMapper;

    public List<ItemCat> queryListById(Long id) {
        //return this.itemCatMapper.queryListById(id);
        ItemCat ic = new ItemCat();
        ic.setParentId(id);
        return this.itemCatMapper.select(ic);
    }

}