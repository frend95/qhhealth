package com.hfkd.qhhealth.social.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 动态 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("social_feed")
public class SocialFeed implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**作者id*/
    @TableField("author_id")
    private Integer authorId;
    /**作者类型：0用户，1营养师*/
    @TableField("author_type")
    private String authorType;
    /**标题*/
    private String title;
    /**内容*/
    private String content;
    /**图片*/
    private String img;
    /**图片缩略图*/
    @TableField("img_thumb")
    private String imgThumb;
    /**评论数*/
    @TableField("cmt_cnt")
    private Integer cmtCnt;
    /**点赞数*/
    @TableField("like_cnt")
    private Integer likeCnt;
    /**是否私密：0否，1是*/
    @TableField("is_private")
    private String isPrivate;
    /**序号*/
    private Integer seq;
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

    public String getAuthorType() {
        return authorType;
    }

    public void setAuthorType(String authorType) {
        this.authorType = authorType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgThumb() {
        return imgThumb;
    }

    public void setImgThumb(String imgThumb) {
        this.imgThumb = imgThumb;
    }

    public Integer getCmtCnt() {
        return cmtCnt;
    }

    public void setCmtCnt(Integer cmtCnt) {
        this.cmtCnt = cmtCnt;
    }

    public Integer getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(Integer likeCnt) {
        this.likeCnt = likeCnt;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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
        return "SocialFeed{" +
            "id=" + id +
            ", authorId=" + authorId +
            ", authorType=" + authorType +
            ", title=" + title +
            ", content=" + content +
            ", img=" + img +
            ", imgThumb=" + imgThumb +
            ", cmtCnt=" + cmtCnt +
            ", likeCnt=" + likeCnt +
            ", isPrivate=" + isPrivate +
            ", seq=" + seq +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
