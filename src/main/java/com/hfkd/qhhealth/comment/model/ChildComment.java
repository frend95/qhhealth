package com.hfkd.qhhealth.comment.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章子评论 Model
 * @author hexq
 * @date 2018/7/11 14:12
 */
public class ChildComment implements Serializable{

    @JSONField(serialize = false)
    private Integer id;
    /**评论人id*/
    private Integer authorId;
    /**父评论id*/
    @JSONField(serialize = false)
    private Integer parentCmtId;
    /**评论人名称*/
    private String name;
    /**评论人头像*/
    private String avatar;
    /**评论内容*/
    private String content;
    /**被回复人id*/
    private Integer replyToId;
    /**被回复人名称*/
    private String replyToName;
    /**创建时间*/
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date createTime;
    /**格式化的评论时间*/
    @JSONField(serialize = false)
    private String dateTime;
    /**内容类型*/
    @JSONField(serialize = false)
    private String type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ChildComment(Integer authorId, Integer parentCmtId, String content, Integer replyToId, String replyToName, String type) {
        this.authorId = authorId;
        this.parentCmtId = parentCmtId;
        this.content = content;
        this.replyToId = replyToId;
        this.replyToName = replyToName;
        this.type = type;
    }

    public ChildComment() {
    }

    @Override
    public String toString() {
        return "ChildComment{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", parentCmtId=" + parentCmtId +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", replyToId=" + replyToId +
                ", replyToName='" + replyToName + '\'' +
                ", createTime=" + createTime +
                ", dateTime='" + dateTime + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
