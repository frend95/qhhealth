package com.hfkd.qhhealth.social.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

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
    /**被关注者id*/
    @TableField("following_id")
    private Integer followingId;
    /**被关注者类型：0用户，1营养师*/
    @TableField("following_type")
    private String followingType;
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

    @Override
    public String toString() {
        return "SocialUserFollowing{" +
            "id=" + id +
            ", userId=" + userId +
            ", followingId=" + followingId +
            ", followingType=" + followingType +
            ", createTime=" + createTime +
            "}";
    }
}
