package com.jt.manage.pojo;

import com.jt.common.po.BasePojo;

import javax.persistence.*;

/**
 * @author Administrator
 */
@Table(name = "tb_item_cat")
public class ItemCat extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // 主键自增长策略
    private Long id;

    /**
     * 父分类ID=0时，代表的是一级的分类
     */
    @Column(name = "parent_id")
    private Long parentId;

    private String name;

    /**
     * 状态。可选值:1(正常),2(删除)
     */
    private Integer status;

    /**
     * 排列序号，表示同级分类的展现次序，如数值相等则按名称次序排列。
     * 取值范围:大于零的整数
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /**
     * 该分类是否为父分类，1为true，0为false
     */
    @Column(name = "is_parent")
    private Boolean isParent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    /**
     * 扩展get方法，满足EasyUI的tree格式
     *
     * @return String
     */
    public String getText() {
        return getName();
    }

    public String getState() {
        return getIsParent() ? "closed" : "open";
    }

}
