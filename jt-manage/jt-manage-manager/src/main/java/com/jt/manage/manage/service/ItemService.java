package com.jt.manage.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.manage.mapper.ItemDescMapper;
import com.jt.manage.manage.mapper.ItemMapper;
import com.jt.manage.manage.pojo.Item;
import com.jt.manage.manage.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    public EasyUIResult queryItemList(Integer page, Integer rows) {
        EasyUIResult result = new EasyUIResult();
        //引入pageHelper插件,拦截开启分页查询的第一条查询语句
        PageHelper.startPage(page, rows);
        //拦截第一条查询语句,拼接limit (page-1)*rows,rows
        //继续执行select count(*) from tb_item
        List<Item> itemList = itemMapper.selectAll();
        //由于是page类型的list,包含了查询结果list和totalcount3094
        //利用pageinfo包装一下list,获取total和list封装给EasyUIResult
        PageInfo<Item> pageInfo = new PageInfo<Item>(itemList);
        //获取total和list查询结果,交给result
        result.setTotal((int) pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        return result;
    }

    public void saveItem(Item item, String desc) {
        //item 缺少数据,id不用管,status,created,updated
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        //insertSelective会使用数据库默认值,传递翻译sql时没有空属性
        //insert into (不加空属性的字段)
        //insert使用代码默认值,空属性,String null,Integer null
        //insert into (全字段) values(默认值)
        itemMapper.insertSelective(item);
        //整理desc数据
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(item.getCreated());
        itemDesc.setUpdated(item.getCreated());
        itemDescMapper.insertSelective(itemDesc);
    }

    public void updateItem(Item item, String desc) {
        item.setUpdated(new Date());
        System.out.println(item.getId());
        itemMapper.updateByPrimaryKeySelective(item);
        //封装desc对象
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
    }

    public Item queryItemById(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }
}






