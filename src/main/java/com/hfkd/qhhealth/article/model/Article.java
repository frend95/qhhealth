package com.hfkd.qhhealth.article.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

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
    /**0:饮食，1:运动，2:减肥方法，3:食谱*/
    private String tag;
    /**内容*/
    private String content;
    /**评论数*/
    @TableField("cmt_cnt")
    private Integer cmtCnt;
    /**点击数*/
    @TableField("watched_cnt")
    private Integer watchedCnt;
    /**创建时间*/
    @TableField("create_time")
    private Date createTime;
    /**更新时间*/
    @TableField("update_time")
    private Date updateTime;


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
