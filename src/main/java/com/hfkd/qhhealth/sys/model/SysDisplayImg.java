package com.hfkd.qhhealth.sys.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 展示图片 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("sys_display_img")
public class SysDisplayImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**名称*/
    private String name;
    /**图片链接*/
    @TableField("img_url")
    private String imgUrl;
    /**指向链接*/
    @TableField("to_url")
    private String toUrl;
    /**类型：0非广告，1广告*/
    private String type;
    /**分组*/
    private Integer group;
    /**页面：0 splash，1 onboarding，2 tutorial，3 case，4 social*/
    private String page;
    /**序号*/
    private Integer seq;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
