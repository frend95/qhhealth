package com.hfkd.qhhealth.video.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.hfkd.qhhealth.comment.model.Comment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 视频 Model
 * @author hexq
 * @date 2018/7/4 18:21
 */
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;        //
    @TableField("author_id")
    private Integer authorId;        //所有者id
    private String title;        //标题
    private String thumb;        //略缩图
    private String resource;        //资源位置
    private String desc;        //描述
    private String type;        //0:案例, 1:教程
    private String tag;        //0:中年发福, 1:产后肥胖, 2:青春型肥胖, 3:单纯性肥胖, 4:遗传性肥胖
    @TableField("cmt_cnt")
    private Integer cmtCnt;        //评论数
    @TableField("watched_cnt")
    private Integer watchedCnt;        //点击数
    @JSONField(format="yyyy-MM-dd HH:mm")
    @TableField("create_time")
    private Date createTime;        //创建时间
    @JSONField(format="yyyy-MM-dd HH:mm")
    @TableField("update_time")
    private Date updateTime;        //更新时间
    @TableField(exist = false)
    private List<Comment> comments;        //评论
    @TableField(exist = false)
    private Boolean isCollect;        //是否收藏

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
        return "Video{" +
            "id=" + id +
            ", authorId=" + authorId +
            ", title=" + title +
            ", thumb=" + thumb +
            ", resource=" + resource +
            ", desc=" + desc +
            ", type=" + type +
            ", tag=" + tag +
            ", cmtCnt=" + cmtCnt +
            ", watchedCnt=" + watchedCnt +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
