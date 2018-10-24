package com.jt.manage.manage.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ItemCatResult {
    //一级菜单的list,返回的是分类中parent_id=0的所有分类
    @JsonProperty("data")
    private List<ItemCatData> itemCats;

    public List<ItemCatData> getItemCats() {
        return itemCats;
    }

    public void setItemCats(List<ItemCatData> itemCats) {
        this.itemCats = itemCats;
    }


}
