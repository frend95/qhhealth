package com.hfkd.qhhealth.user.model;

import java.io.Serializable;

/**
 * 用户session
 * @author hexq
 * @date 2018/7/5 09:50
 */
public class UserSession implements Serializable{

    private Integer id;
    /**名称*/
    private String name;
    /**账号（手机号）*/
    private String account;
    /**营养师id*/
    private Integer nutritionistId;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getNutritionistId() {
        return nutritionistId;
    }

    public void setNutritionistId(Integer nutritionistId) {
        this.nutritionistId = nutritionistId;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", nutritionistId=" + nutritionistId +
                '}';
    }
}
