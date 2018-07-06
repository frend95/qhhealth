package com.hfkd.qhhealth.video.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频评论 Model
 * @author hexq
 * @date 2018/7/4 18:21
 */
@TableName("video_cmt")
public class VideoCmt implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;        //
    @TableField("video_id")
    private Integer videoId;        //视频id
    @TableField("author_id")
    private Integer authorId;        //作者id
    private String content;        //评论内容
    @TableField("reply_cnt")
    private Integer replyCnt;        //回复数
    @TableField("create_time")
    private Date createTime;        //创建时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyCnt() {
        return replyCnt;
    }

    public void setReplyCnt(Integer replyCnt) {
        this.replyCnt = replyCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VideoCmt{" +
            "id=" + id +
            ", videoId=" + videoId +
            ", authorId=" + authorId +
            ", content=" + content +
            ", replyCnt=" + replyCnt +
            ", createTime=" + createTime +
            "}";
    }
}
