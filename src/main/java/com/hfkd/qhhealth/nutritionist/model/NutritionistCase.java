package com.hfkd.qhhealth.nutritionist.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 营养师服务案例 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("nutritionist_case")
public class NutritionistCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**营养师id*/
    @TableField("nutritionist_id")
    private Integer nutritionistId;
    /**缩略图*/
    private String thumb;
    /**姓名*/
    private String name;
    /**年龄*/
    private String age;
    /**身高(cm)*/
    private String height;
    /**减重结果(kg)*/
    private String result;
    /**减重历时(month)*/
    private String period;
    /**内容*/
    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNutritionistId() {
        return nutritionistId;
    }

    public void setNutritionistId(Integer nutritionistId) {
        this.nutritionistId = nutritionistId;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NutritionistCase{" +
            "id=" + id +
            ", nutritionistId=" + nutritionistId +
            ", thumb=" + thumb +
            ", name=" + name +
            ", age=" + age +
            ", height=" + height +
            ", result=" + result +
            ", period=" + period +
            ", content=" + content +
            "}";
    }
}
