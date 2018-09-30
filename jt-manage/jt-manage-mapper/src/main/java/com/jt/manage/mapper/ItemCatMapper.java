package com.jt.manage.mapper;

import com.jt.manage.mapper.base.mapper.SysMapper;
import com.jt.manage.pojo.ItemCat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface ItemCatMapper extends SysMapper<ItemCat> {

    /**
     * 根据ID查询商品分类数据
     *
     * @param id parentId
     * @return List
     */
    List<ItemCat> queryListById(@Param("id") Long id);

}