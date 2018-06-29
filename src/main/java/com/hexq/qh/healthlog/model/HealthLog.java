package com.hexq.qh.healthlog.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户健康信息记录 Model
 * @author hexq
 * @date 2018-06-09
 */
@TableName("customer_health_log")
public class HealthLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;        //
    @TableField("customer_id")
    private String customerId;        //客户id
    @TableField("user_id")
    private String userId;        //营养师id
    private Integer height;        //身高(cm)
    private Integer weight;        //体重(g)
    private Integer wc;        //腰围(inch)
    @TableField("body_fat")
    private BigDecimal bodyFat;        //体脂率
    @TableField("create_time")
    private Date createTime;        //创建时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWc() {
        return wc;
    }

    public void setWc(Integer wc) {
        this.wc = wc;
    }

    public BigDecimal getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(BigDecimal bodyFat) {
        this.bodyFat = bodyFat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CustomerHealthLog{" +
            "id=" + id +
            ", customerId=" + customerId +
            ", userId=" + userId +
            ", height=" + height +
            ", weight=" + weight +
            ", wc=" + wc +
            ", bodyFat=" + bodyFat +
            ", createTime=" + createTime +
            "}";
    }
}
