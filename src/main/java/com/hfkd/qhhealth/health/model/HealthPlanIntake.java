package com.hfkd.qhhealth.health.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 饮食计划已选食物 Model
 * @author hexq
 * @date 2018-07-18
 */
@TableName("health_plan_intake")
public class HealthPlanIntake implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**用户id*/
    @JSONField(serialize = false)
    @TableField("user_id")
    private Integer userId;
    /**食物id*/
    @TableField("food_id")
    private Integer foodId;
    /**类型：0早餐，1午餐，2晚餐，3加餐*/
    private Integer type;
    /**食物名称*/
    @TableField(exist = false)
    private String foodName;
    /**数量*/
    private Integer amount;
    /**重量g*/
    @TableField(exist = false)
    private Integer weight;
    /**单个重量g*/
    @TableField(exist = false)
    private Integer singleWeight = 100;
    /**总卡路里kCal*/
    private Integer kcal;
    /**千卡每100克*/
    @TableField(exist = false)
    private Integer kcalPer100g;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        if (amount != null) {
            if (this.kcalPer100g != null) {
                this.kcal = this.kcalPer100g * amount;
            }
            if (this.singleWeight != null) {
                this.weight = this.singleWeight * amount;
            }
        }
        this.amount = amount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSingleWeight() {
        return singleWeight;
    }

    public void setSingleWeight(Integer singleWeight) {
            if (this.amount != null && singleWeight != null) {
                this.weight = this.amount * singleWeight;
            } else {
                this.weight = singleWeight;
            }
        this.singleWeight = singleWeight;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public Integer getKcalPer100g() {
        return kcalPer100g;
    }

    public void setKcalPer100g(Integer kcalPer100g) {
        if (this.amount != null && kcalPer100g != null) {
            this.kcal = this.amount * kcalPer100g;
        } else {
            this.kcal = kcalPer100g;
        }
        this.kcalPer100g = kcalPer100g;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HealthPlanIntake{" +
                "id=" + id +
                ", userId=" + userId +
                ", foodId=" + foodId +
                ", type=" + type +
                ", foodName='" + foodName + '\'' +
                ", amount=" + amount +
                ", weight=" + weight +
                ", singleWeight=" + singleWeight +
                ", kcal=" + kcal +
                ", kcalPer100g=" + kcalPer100g +
                '}';
    }

    public HealthPlanIntake(Integer userId, Integer foodId, Integer amount, Integer type, Integer kcal) {
        this.userId = userId;
        this.foodId = foodId;
        this.amount = amount;
        this.type = type;
        this.kcal = kcal;
        if (amount != null) {
            this.weight = this.singleWeight * amount;
        } else {
            this.weight = this.singleWeight;
        }
    }

    public HealthPlanIntake(Integer id, Integer kcal, Integer amount) {
        this.id = id;
        this.kcal = kcal;
        this.amount = amount;
    }

    public HealthPlanIntake() {
        this.weight = this.singleWeight;
    }
}
