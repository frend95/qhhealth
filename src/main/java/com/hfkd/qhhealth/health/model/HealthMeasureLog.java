package com.hfkd.qhhealth.health.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 健康数据记录 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("health_measure_log")
public class HealthMeasureLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**用户id*/
    @TableField("user_id")
    private Integer userId;
    /**体重(kg)*/
    private BigDecimal weight;
    /**身体质量指数*/
    private BigDecimal bmi;
    /**体脂率*/
    private BigDecimal bfr;
    /**内脏脂肪指数*/
    private Integer uvi;
    /**肌肉率*/
    private BigDecimal mr;
    /**基础代谢率*/
    private BigDecimal bmr;
    /**骨骼质量*/
    private BigDecimal bq;
    /**水含量*/
    private BigDecimal wr;
    /**蛋白质*/
    private BigDecimal pr;
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getBmi() {
        return bmi;
    }

    public void setBmi(BigDecimal bmi) {
        this.bmi = bmi;
    }

    public BigDecimal getBfr() {
        return bfr;
    }

    public void setBfr(BigDecimal bfr) {
        this.bfr = bfr;
    }

    public Integer getUvi() {
        return uvi;
    }

    public void setUvi(Integer uvi) {
        this.uvi = uvi;
    }

    public BigDecimal getMr() {
        return mr;
    }

    public void setMr(BigDecimal mr) {
        this.mr = mr;
    }

    public BigDecimal getBmr() {
        return bmr;
    }

    public void setBmr(BigDecimal bmr) {
        this.bmr = bmr;
    }

    public BigDecimal getBq() {
        return bq;
    }

    public void setBq(BigDecimal bq) {
        this.bq = bq;
    }

    public BigDecimal getWr() {
        return wr;
    }

    public void setWr(BigDecimal wr) {
        this.wr = wr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPr() {
        return pr;
    }

    public void setPr(BigDecimal pr) {
        this.pr = pr;
    }

    @Override
    public String toString() {
        return "HealthMeasureLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", weight=" + weight +
                ", bmi=" + bmi +
                ", bfr=" + bfr +
                ", uvi=" + uvi +
                ", mr=" + mr +
                ", bmr=" + bmr +
                ", bq=" + bq +
                ", wr=" + wr +
                ", pr=" + pr +
                ", createTime=" + createTime +
                '}';
    }
}
