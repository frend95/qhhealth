package com.hfkd.qhhealth.health.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 饮食计划食物项 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("health_plan_item")
public class HealthPlanItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**食物名称*/
    private String name;
    /**千卡每100克*/
    @TableField("kcal_per_100g")
    private Integer kcalPer100g;
    /**0:五谷杂粮,1:鱼肉蛋奶,2:果蔬菌藻,3:菜肴,4:饮料,5:油脂,6:零食,7:其他*/
    private String sort;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKcalPer100g() {
        return kcalPer100g;
    }

    public void setKcalPer100g(Integer kcalPer100g) {
        this.kcalPer100g = kcalPer100g;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "HealthPlanItem{" +
            "id=" + id +
            ", name=" + name +
            ", kcalPer100g=" + kcalPer100g +
            ", sort=" + sort +
            "}";
    }
}
