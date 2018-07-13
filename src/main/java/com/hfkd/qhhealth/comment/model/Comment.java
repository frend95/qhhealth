package com.hfkd.qhhealth.comment.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论 Model
 * @author hexq
 * @date 2018/7/11 14:08
 */
public class Comment implements Serializable{

    private Integer id;
    private String name;
    private String avatar;
    private Integer authorId;
    private String content;
    private Integer replyCnt;
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date createTime;
    private String dateTime;
    private List<ChildComment> childCmt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<ChildComment> getChildCmt() {
        return childCmt;
    }

    public void setChildCmt(List<ChildComment> childCmt) {
        this.childCmt = childCmt;
    }
}