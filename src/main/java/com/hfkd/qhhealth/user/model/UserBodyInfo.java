package com.hfkd.qhhealth.user.model;

import com.hfkd.qhhealth.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hexq
 * @date 2018/7/16 11:37
 */
public class UserBodyInfo implements Serializable{
    /**性别：0女，1男*/
    private String gender;
    /**身高(cm)*/
    private Integer height;
    /**体重(kg)*/
    private BigDecimal weight;
    /**生日*/
    private String birthday;
    /**年龄*/
    private Integer age;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        if (StringUtils.isBlank(birthday)) {
            return null;
        }
        long ageL = DateUtil.divideDate(DateUtil.yyyyMMddHasLine(), birthday, "yyyy-MM-dd") / 365;
        return Math.toIntExact(ageL);
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserBodyInfo{" +
                "gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", birthday='" + birthday + '\'' +
                ", age=" + age +
                '}';
    }
}
