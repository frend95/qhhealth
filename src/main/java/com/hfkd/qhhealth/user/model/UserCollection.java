package com.hfkd.qhhealth.user.model;

import java.io.Serializable;

/**
 * 用户收藏 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
public class UserCollection implements Serializable {
    
    /**收藏内容id*/
    private Integer id;
    /**标题*/
    private String title;
    /**缩略图*/
    private String thumb;
    /**资源位置*/
    private String resource;
    /**创建时间*/
    private Integer watchedCnt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getWatchedCnt() {
        return watchedCnt;
    }

    public void setWatchedCnt(Integer watchedCnt) {
        this.watchedCnt = watchedCnt;
    }

    @Override
    public String toString() {
        return "UserCollection{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                ", resource='" + resource + '\'' +
                ", watchedCnt=" + watchedCnt +
                '}';
    }
}
