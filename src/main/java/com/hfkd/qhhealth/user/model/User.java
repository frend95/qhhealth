package com.hfkd.qhhealth.user.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**名称*/
    private String name;
    /**头像*/
    private String avatar;
    /**头像略缩图*/
    @TableField("avatar_thumb")
    private String avatarThumb;
    /**账号（手机号）*/
    private String account;
    /**密码*/
    private String password;
    /**盐*/
    private String salt;
    /**营养师id*/
    @TableField("nutritionist_id")
    private Integer nutritionistId;
    /**性别：0女，1男*/
    private String gender;
    /**身高(cm)*/
    private Integer height;
    /**体重(kg)*/
    private BigDecimal weight;
    /**生日*/
    private String birthday;
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

    public Integer getNutritionistId() {
        return nutritionistId;
    }

    public void setNutritionistId(Integer nutritionistId) {
        this.nutritionistId = nutritionistId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", avatarThumb='" + avatarThumb + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", nutritionistId=" + nutritionistId +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", birthday='" + birthday + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
