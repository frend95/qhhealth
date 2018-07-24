package com.hfkd.qhhealth.nutritionist.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 营养师用户信 Model
 * @author hexq
 * @date 2018/7/5 10:12
 */
@TableName("nutritionist_letter")
public class NutritionistLetter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**营养师id*/
    @TableId("nutritionist_id")
    private Integer nutritionistId;
    /**内容*/
    private String content;
    private String url;


    public Integer getNutritionistId() {
        return nutritionistId;
    }

    public void setNutritionistId(Integer nutritionistId) {
        this.nutritionistId = nutritionistId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NutritionistLetter{" +
                "nutritionistId=" + nutritionistId +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
