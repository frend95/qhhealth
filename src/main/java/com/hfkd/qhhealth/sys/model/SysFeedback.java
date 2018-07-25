package com.hfkd.qhhealth.sys.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 反馈建议 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("sys_feedback")
public class SysFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**内容*/
    private String content;
    /**图片*/
    private String img;
    /**手机号*/
    private String mobile;
    /**0:suggestion，1:issue*/
    private String type;
    /**创建时间*/
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SysFeedback(String content, String img, String mobile, String type) {
        this.content = content;
        this.img = img;
        this.mobile = mobile;
        this.type = type;
    }

    public SysFeedback() {
    }

    @Override
    public String toString() {
        return "SysFeedback{" +
            "id=" + id +
            ", content=" + content +
            ", img=" + img +
            ", mobile=" + mobile +
            ", type=" + type +
            "}";
    }
}
