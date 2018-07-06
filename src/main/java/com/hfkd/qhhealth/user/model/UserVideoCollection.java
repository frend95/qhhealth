package com.hfkd.qhhealth.user.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户视频收藏 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("user_video_collection")
public class UserVideoCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**用户id*/
    @TableField("user_id")
    private Integer userId;
    /**视频id*/
    @TableField("video_id")
    private Integer videoId;
    /**标题*/
    private String title;
    /**缩略图*/
    private String thumb;
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

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserVideoCollection{" +
            "id=" + id +
            ", userId=" + userId +
            ", videoId=" + videoId +
            ", title=" + title +
            ", thumb=" + thumb +
            ", createTime=" + createTime +
            "}";
    }
}
