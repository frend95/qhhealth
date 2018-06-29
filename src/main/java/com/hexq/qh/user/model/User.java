package com.hexq.qh.user.model;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户 Model
 * @author hexq
 * @date 2018-06-05
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;        //
    private String account;        //账号
    private String password;        //密码
    private String name;        //姓名
    private String avatar;        //头像
    private String desc;        //描述
    private String wechat;        //微信号
    @TableField("team_size")
    private Integer teamSize;        //团队人数
    @TableField("vip_num")
    private Integer vipNum;        //品牌客户人数
    @TableField("custom_num")
    private Integer customNum;        //总计服务人数
    private String status;        //账号状态
    @TableField("create_time")
    private Date createTime;        //创建时间
    @TableField("update_time")
    private Date updateTime;        //修改时间
    private String salt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public Integer getVipNum() {
        return vipNum;
    }

    public void setVipNum(Integer vipNum) {
        this.vipNum = vipNum;
    }

    public Integer getCustomNum() {
        return customNum;
    }

    public void setCustomNum(Integer customNum) {
        this.customNum = customNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", account=" + account +
            ", password=" + password +
            ", name=" + name +
            ", avatar=" + avatar +
            ", desc=" + desc +
            ", teamSize=" + teamSize +
            ", vipNum=" + vipNum +
            ", customNum=" + customNum +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
