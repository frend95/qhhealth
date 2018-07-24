package com.hfkd.qhhealth.video.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频子评论 Model
 * @author hexq
 * @date 2018/7/4 18:21
 */
@TableName("video_cmt_child")
public class VideoCmtChild implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**作者id*/
    @TableField("author_id")
    private Integer authorId;
    /**父评论id*/
    @TableField("parent_cmt_id")
    private Integer parentCmtId;
    /**评论内容*/
    private String content;
    /**被回复人id*/
    @TableField("reply_to_id")
    private Integer replyToId;
    /**被回复人名称*/
    @TableField("reply_to_name")
    private String replyToName;
    /**创建时间*/
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getParentCmtId() {
        return parentCmtId;
    }

    public void setParentCmtId(Integer parentCmtId) {
        this.parentCmtId = parentCmtId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(Integer replyToId) {
        this.replyToId = replyToId;
    }

    public String getReplyToName() {
        return replyToName;
    }

    public void setReplyToName(String replyToName) {
        this.replyToName = replyToName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VideoCmtChild{" +
            "id=" + id +
            ", authorId=" + authorId +
            ", parentCmtId=" + parentCmtId +
            ", content=" + content +
            ", replyToId=" + replyToId +
            ", createTime=" + createTime +
            "}";
    }
}
