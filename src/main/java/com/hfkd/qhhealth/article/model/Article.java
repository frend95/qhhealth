package com.hfkd.qhhealth.article.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.hfkd.qhhealth.comment.model.Comment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章 Model
 * @author hexq
 * @date 2018/7/4 18:21
 */
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**作者id*/
    @TableField("author_id")
    private Integer authorId;
    /**标题*/
    private String title;
    /**缩略图*/
    private String thumb;
    /**资源位置*/
    private String resource;
    /**0:饮食，1:运动，2:减肥方法，3:食谱*/
    private String tag;
    /**内容*/
    private String content;
    /**描述*/
    private String desc;
    /**评论数*/
    @TableField("cmt_cnt")
    private Integer cmtCnt;
    /**点击数*/
    @TableField("watched_cnt")
    private Integer watchedCnt;
    /**创建时间*/
    @TableField("create_time")
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date createTime;
    /**更新时间*/
    @JSONField(format="yyyy-MM-dd HH:mm")
    @TableField("update_time")
    private Date updateTime;
    /**评论*/
    @TableField(exist = false)
    private List<Comment> comments;
    /**是否收藏*/
    @TableField(exist = false)
    private Boolean isCollect;

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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCmtCnt() {
        return cmtCnt;
    }

    public void setCmtCnt(Integer cmtCnt) {
        this.cmtCnt = cmtCnt;
    }

    public Integer getWatchedCnt() {
        return watchedCnt;
    }

    public void setWatchedCnt(Integer watchedCnt) {
        this.watchedCnt = watchedCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getCollect() {
        return isCollect;
    }

    public void setCollect(Boolean collect) {
        isCollect = collect;
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + id +
            ", authorId=" + authorId +
            ", title=" + title +
            ", content=" + content +
            ", cmtCnt=" + cmtCnt +
            ", watchedCnt=" + watchedCnt +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
