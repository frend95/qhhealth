package com.hfkd.qhhealth.social.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 用户社交信息 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("social_user_info")
public class SocialUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**用户id*/
    @TableId("user_id")
    private Integer userId;
    /**名称*/
    private String name;
    /**个人简介*/
    private String bio;
    /**头像*/
    private String avatar;
    /**头像略缩图*/
    @TableField("avatar_thumb")
    private String avatarThumb;
    /**动态数*/
    @TableField("feed_cnt")
    private Integer feedCnt;
    /**关注数*/
    private Integer following;
    /**粉丝数*/
    private Integer followers;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getFeedCnt() {
        return feedCnt;
    }

    public void setFeedCnt(Integer feedCnt) {
        this.feedCnt = feedCnt;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "SocialUserInfo{" +
            "userId=" + userId +
            ", name=" + name +
            ", bio=" + bio +
            ", avatar=" + avatar +
            ", avatarThumb=" + avatarThumb +
            ", feedCnt=" + feedCnt +
            ", following=" + following +
            ", followers=" + followers +
            "}";
    }
}
