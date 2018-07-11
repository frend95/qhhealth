package com.hfkd.qhhealth.health.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 健康目标 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("health_goal")
public class HealthGoal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**用户id*/
    @TableId("user_id")
    private Integer userId;
    /**目标体重*/
    @TableField("goal_weight")
    private BigDecimal goalWeight;
    /**目标类型：0减肥，1塑形*/
    @TableField("goal_type")
    private String goalType;
    /**时间期限*/
    private Integer period;
    /**开始时间*/
    @TableField("start_time")
    private String startTime;
    /**创建时间*/
    @TableField("create_time")
    private Date createTime;
    /**更新时间*/
    @TableField("update_time")
    private Date updateTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(BigDecimal goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "HealthGoal{" +
            "userId=" + userId +
            ", goalWeight=" + goalWeight +
            ", goalType=" + goalType +
            ", period=" + period +
            ", startTime=" + startTime +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
