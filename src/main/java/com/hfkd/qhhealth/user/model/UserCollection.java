package com.hfkd.qhhealth.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户收藏 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
public class UserCollection implements Serializable {
    
    /**收藏内容id*/
    private Integer contentId;
    /**标题*/
    private String title;
    /**缩略图*/
    private String thumb;
    /**创建时间*/
    private Date createTime;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserArticleCollection{" +
            ", contentId=" + contentId +
            ", title=" + title +
            ", thumb=" + thumb +
            ", createTime=" + createTime +
            "}";
    }
}
