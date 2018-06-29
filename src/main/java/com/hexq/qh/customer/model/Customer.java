package com.hexq.qh.customer.model;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户 Model
 * @author hexq
 * @date 2018-06-06
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;        //openid
    @TableField("user_id")
    private String userId;        //营养师id
    private String mobile;        //手机号码
    private String location;        //位置
    private String name;        //名称
    private String desc;        //描述
    private String avatar;        //头像
    private Integer star;        //星级
    private String status;        //状态：0无效，1未购买，2已购买
    @TableField("is_vip")
    private String isVip;        //是否vip：0否，1是
    @TableField("create_time")
    private Date createTime;        //创建时间
    @TableField("update_time")
    private Date updateTime;        //修改时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
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
        return "Customer{" +
            "id=" + id +
            ", userId=" + userId +
            ", mobile=" + mobile +
            ", location=" + location +
            ", name=" + name +
            ", desc=" + desc +
            ", avatar=" + avatar +
            ", star=" + star +
            ", status=" + status +
            ", isVip=" + isVip +
            "}";
    }
}
