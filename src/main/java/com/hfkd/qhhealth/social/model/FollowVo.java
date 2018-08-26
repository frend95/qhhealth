package com.hfkd.qhhealth.social.model;

import java.io.Serializable;

/**
 * @author 关注项VO hexq
 * @date 2018/8/26 10:10
 */
public class FollowVo implements Serializable{
    private Integer followingId;
    private String followingName;
    private String avatar;
    private String followingType;
    private Boolean isFollow;

    public Integer getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Integer followingId) {
        this.followingId = followingId;
    }

    public String getFollowingName() {
        return followingName;
    }

    public void setFollowingName(String followingName) {
        this.followingName = followingName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFollowingType() {
        return followingType;
    }

    public void setFollowingType(String followingType) {
        this.followingType = followingType;
    }

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }

    @Override
    public String toString() {
        return "FollowVo{" +
                "followingId=" + followingId +
                ", followingName='" + followingName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", followingType='" + followingType + '\'' +
                ", isFollow=" + isFollow +
                '}';
    }
}
