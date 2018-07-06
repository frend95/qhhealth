package com.hfkd.qhhealth.nutritionist.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * 营养师信息 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
public class Nutritionist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**名称*/
    private String name;
    /**性别：0女，1男*/
    private String gender;
    /**头像*/
    private String avatar;
    /**头像略缩图*/
    @TableField("avatar_thumb")
    private String avatarThumb;
    /**账号*/
    private String account;
    /**密码*/
    private String password;
    /**盐*/
    private String salt;
    /**状态：0无效，1有效*/
    private String status;
    /**创建时间*/
    @TableField("create_time")
    private Date createTime;
    /**更新时间*/
    @TableField("update_time")
    private Date updateTime;


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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarThumb() {
        return avatarThumb;
    }

    public void setAvatarThumb(String avatarThumb) {
        this.avatarThumb = avatarThumb;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    @Override
    public String toString() {
        return "Nutritionist{" +
            "id=" + id +
            ", name=" + name +
            ", gender=" + gender +
            ", avatar=" + avatar +
            ", avatarThumb=" + avatarThumb +
            ", account=" + account +
            ", password=" + password +
            ", salt=" + salt +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
