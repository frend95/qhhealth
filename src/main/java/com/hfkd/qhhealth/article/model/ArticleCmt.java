package com.hfkd.qhhealth.article.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章评论 Model
 * @author hexq
 * @date 2018/7/4 18:21
 */
@TableName("article_cmt")
public class ArticleCmt implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**文章id*/
    @TableField("content_id")
    private Integer contentId;
    /**作者id*/
    @TableField("author_id")
    private Integer authorId;
    /**评论内容*/
    private String content;
    /**回复数*/
    @TableField("reply_cnt")
    private Integer replyCnt;
    /**创建时间*/
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
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
        return "ArticleCmt{" +
            "id=" + id +
            ", contentId=" + contentId +
            ", authorId=" + authorId +
            ", content=" + content +
            ", replyCnt=" + replyCnt +
            ", createTime=" + createTime +
            "}";
    }
}
