package com.hfkd.qhhealth.health.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 饮食日常记录 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("health_plan_log")
public class HealthPlanLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**用户id*/
    @TableField("user_id")
    private Integer userId;
    /**消耗卡路里（Kcal）*/
    private Integer consume;
    /**早餐摄入（Kcal）*/
    @TableField("breakfast_intake")
    private Integer breakfastIntake;
    /**午餐摄入（Kcal）*/
    @TableField("lunch_intake")
    private Integer lunchIntake;
    /**晚餐摄入（Kcal）*/
    @TableField("dinner_intake")
    private Integer dinnerIntake;
    /**加餐摄入（Kcal）*/
    @TableField("meal_intake")
    private Integer mealIntake;
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

    public Integer getConsume() {
        return consume;
    }

    public void setConsume(Integer consume) {
        this.consume = consume;
    }

    public Integer getBreakfastIntake() {
        return breakfastIntake;
    }

    public void setBreakfastIntake(Integer breakfastIntake) {
        this.breakfastIntake = breakfastIntake;
    }

    public Integer getLunchIntake() {
        return lunchIntake;
    }

    public void setLunchIntake(Integer lunchIntake) {
        this.lunchIntake = lunchIntake;
    }

    public Integer getDinnerIntake() {
        return dinnerIntake;
    }

    public void setDinnerIntake(Integer dinnerIntake) {
        this.dinnerIntake = dinnerIntake;
    }

    public Integer getMealIntake() {
        return mealIntake;
    }

    public void setMealIntake(Integer mealIntake) {
        this.mealIntake = mealIntake;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "HealthPlanLog{" +
            "id=" + id +
            ", userId=" + userId +
            ", consume=" + consume +
            ", breakfastIntake=" + breakfastIntake +
            ", lunchIntake=" + lunchIntake +
            ", dinnerIntake=" + dinnerIntake +
            ", mealIntake=" + mealIntake +
            ", createTime=" + createTime +
            "}";
    }
}
