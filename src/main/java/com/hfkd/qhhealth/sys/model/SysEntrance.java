package com.hfkd.qhhealth.sys.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 内容入口配置 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("sys_entrance")
public class SysEntrance implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**内容id*/
    @TableField("content_id")
    private Integer contentId;
    /**入口类型*/
    private String type;
    /**序号*/
    private Integer order;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "SysEntrance{" +
            "id=" + id +
            ", contentId=" + contentId +
            ", type=" + type +
            ", order=" + order +
            "}";
    }
}
