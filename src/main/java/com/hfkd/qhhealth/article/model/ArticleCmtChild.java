package com.hfkd.qhhealth.article.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 文章子评论 Model
 * @author hexq
 * @date 2018/7/4 18:21
 */
@TableName("article_cmt_child")
public class ArticleCmtChild implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;        //
    @TableField("author_id")
    private Integer authorId;        //作者id
    @TableField("parent_cmt_id")
    private Integer parentCmtId;        //父评论id
    private String content;        //评论内容
    @TableField("reply_to_id")
    private Integer replyToId;        //被回复人id
    @TableField("create_time")
    private Date createTime;        //创建时间


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ArticleCmtChild{" +
            "id=" + id +
            ", authorId=" + authorId +
            ", parentCmtId=" + parentCmtId +
            ", content=" + content +
            ", replyToId=" + replyToId +
            ", createTime=" + createTime +
            "}";
    }
}
