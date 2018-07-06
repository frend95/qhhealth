package com.hfkd.qhhealth.health.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 饮食计划常量 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("health_plan_const")
public class HealthPlanConst implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**用户id（预留，目前所有用户公用one row）*/
    @TableField("user_id")
    private Integer userId;
    /**卡路里总预算（Kcal）*/
    @TableField("total_budget")
    private Integer totalBudget;
    /**早餐建议卡路里*/
    @TableField("breakfast_threshold")
    private Integer breakfastThreshold;
    /**午餐建议卡路里*/
    @TableField("lunch_threshold")
    private Integer lunchThreshold;
    /**晚餐建议卡路里*/
    @TableField("dinner_threshold")
    private Integer dinnerThreshold;
    /**加餐建议卡路里*/
    @TableField("meal_threshold")
    private Integer mealThreshold;
    /**创建时间*/
    @TableField("create_time")
    private Date createTime;


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

    public Integer getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Integer totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Integer getBreakfastThreshold() {
        return breakfastThreshold;
    }

    public void setBreakfastThreshold(Integer breakfastThreshold) {
        this.breakfastThreshold = breakfastThreshold;
    }

    public Integer getLunchThreshold() {
        return lunchThreshold;
    }

    public void setLunchThreshold(Integer lunchThreshold) {
        this.lunchThreshold = lunchThreshold;
    }

    public Integer getDinnerThreshold() {
        return dinnerThreshold;
    }

    public void setDinnerThreshold(Integer dinnerThreshold) {
        this.dinnerThreshold = dinnerThreshold;
    }

    public Integer getMealThreshold() {
        return mealThreshold;
    }

    public void setMealThreshold(Integer mealThreshold) {
        this.mealThreshold = mealThreshold;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "HealthPlanConst{" +
            "id=" + id +
            ", userId=" + userId +
            ", totalBudget=" + totalBudget +
            ", breakfastThreshold=" + breakfastThreshold +
            ", lunchThreshold=" + lunchThreshold +
            ", dinnerThreshold=" + dinnerThreshold +
            ", mealThreshold=" + mealThreshold +
            ", createTime=" + createTime +
            "}";
    }
}
