package com.hfkd.qhhealth.sys.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 系统信息 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("sys_info")
public class SysInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**名称*/
    private String name;
    /**描述*/
    private String desc;
    /**变量*/
    private String variable;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
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
        return "SysInfo{" +
            "id=" + id +
            ", name=" + name +
            ", desc=" + desc +
            ", variable=" + variable +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
