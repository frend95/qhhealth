package com.hfkd.qhhealth.social.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hfkd.qhhealth.comment.model.Comment;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 动态 VO
 * @author hexq
 * @date 2018/7/5 10:12
 */
public class SocialFeedVo implements Serializable {

    private Integer id;
    /**作者id*/
    private Integer authorId;
    /**作者名称*/
    private String name;
    /**头像*/
    private String avatar;
    /**作者类型：0用户，1营养师*/
    private String authorType;
    /**内容*/
    private String content;
    /**图片*/
    @JSONField(serialize = false)
    private String img;
    /**图片列表*/
    private String[] imgs = ArrayUtils.EMPTY_STRING_ARRAY;
    /**评论数*/
    private Integer cmtCnt;
    /**点赞数*/
    private Integer likeCnt;
    /**创建时间*/
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**评论*/
    private List<Comment> comments = Collections.EMPTY_LIST;
    /**是否点赞*/
    private Boolean isLike;
    /**是否关注*/
    private Boolean isFollow;


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

    public String getAuthorType() {
        return authorType;
    }

    public void setAuthorType(String authorType) {
        this.authorType = authorType;
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
        if (StringUtils.isNotBlank(img)) {
            this.imgs = img.split(",");
        }
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean like) {
        isLike = like;
    }

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }

    @Override
    public String toString() {
        return "SocialFeedVo{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", authorType='" + authorType + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", imgs=" + Arrays.toString(imgs) +
                ", cmtCnt=" + cmtCnt +
                ", likeCnt=" + likeCnt +
                ", createTime=" + createTime +
                '}';
    }
}
