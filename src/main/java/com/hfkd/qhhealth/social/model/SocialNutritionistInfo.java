package com.hfkd.qhhealth.social.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 营养师社交信息 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("social_nutritionist_info")
public class SocialNutritionistInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**营养师id*/
    @TableId("nutritionist_id")
    private Integer nutritionistId;
    /**名称*/
    private String name;
    /**个人简介*/
    private String bio;
    /**头像*/
    private String avatar;
    /**头像略缩图*/
    @TableField("avatar_thumb")
    private String avatarThumb;
    /**服务人数*/
    @TableField("service_cnt")
    private Integer serviceCnt;
    /**动态数*/
    @TableField("feed_cnt")
    private Integer feedCnt;
    /**课程数*/
    @TableField("tutorial_cnt")
    private Integer tutorialCnt;
    /**粉丝数*/
    private Integer followers;


    public Integer getNutritionistId() {
        return nutritionistId;
    }

    public void setNutritionistId(Integer nutritionistId) {
        this.nutritionistId = nutritionistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public Integer getServiceCnt() {
        return serviceCnt;
    }

    public void setServiceCnt(Integer serviceCnt) {
        this.serviceCnt = serviceCnt;
    }

    public Integer getFeedCnt() {
        return feedCnt;
    }

    public void setFeedCnt(Integer feedCnt) {
        this.feedCnt = feedCnt;
    }

    public Integer getTutorialCnt() {
        return tutorialCnt;
    }

    public void setTutorialCnt(Integer tutorialCnt) {
        this.tutorialCnt = tutorialCnt;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "SocialNutritionistInfo{" +
            "nutritionistId=" + nutritionistId +
            ", name=" + name +
            ", bio=" + bio +
            ", avatar=" + avatar +
            ", avatarThumb=" + avatarThumb +
            ", serviceCnt=" + serviceCnt +
            ", feedCnt=" + feedCnt +
            ", tutorialCnt=" + tutorialCnt +
            ", followers=" + followers +
            "}";
    }
}
