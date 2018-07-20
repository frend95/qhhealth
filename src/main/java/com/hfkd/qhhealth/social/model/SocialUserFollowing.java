package com.hfkd.qhhealth.social.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户关注 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("social_user_following")
public class SocialUserFollowing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**用户id*/
    @TableField("user_id")
    private Integer userId;
    /**被关注者名称*/
    @TableField(exist = false)
    private String followingName;
    /**被关注者id*/
    @TableField("following_id")
    private Integer followingId;
    /**被关注者类型：0用户，1营养师*/
    @TableField("following_type")
    private String followingType;
    /**创建时间*/
    @JSONField(serialize = false)
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

    public String getFollowingName() {
        return followingName;
    }

    public void setFollowingName(String followingName) {
        this.followingName = followingName;
    }

    public Integer getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Integer followingId) {
        this.followingId = followingId;
    }

    public String getFollowingType() {
        return followingType;
    }

    public void setFollowingType(String followingType) {
        this.followingType = followingType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SocialUserFollowing(Integer userId, Integer followingId, String followingType) {
        this.userId = userId;
        this.followingId = followingId;
        this.followingType = followingType;
    }

    public SocialUserFollowing() {
    }

    @Override
    public String toString() {
        return "SocialUserFollowing{" +
                "id=" + id +
                ", userId=" + userId +
                ", followingName='" + followingName + '\'' +
                ", followingId=" + followingId +
                ", followingType='" + followingType + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
