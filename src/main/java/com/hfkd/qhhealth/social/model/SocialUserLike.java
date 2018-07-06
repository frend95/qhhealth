package com.hfkd.qhhealth.social.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 用户点赞 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("social_user_like")
public class SocialUserLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**用户id*/
    @TableField("user_id")
    private Integer userId;
    /**赞过的动态id*/
    @TableField("feed_id")
    private Integer feedId;
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

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SocialUserLike{" +
            "id=" + id +
            ", userId=" + userId +
            ", feedId=" + feedId +
            ", createTime=" + createTime +
            "}";
    }
}
