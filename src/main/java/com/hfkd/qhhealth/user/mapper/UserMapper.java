package com.hfkd.qhhealth.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.user.model.User;
import com.hfkd.qhhealth.user.model.UserBodyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 用户信息 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户详情
     * @param id 用户id
     * @return Map{ id,name,account,avatar,status,birthday,height,weight,gender,goalWeight,startTime,period,goalType,nutritionistId,nutritionistName,nutritionistAvatar }
     */
    Map<String, Object> getUserDetail(@Param("id") Integer id);

    /**
     * 更新用户表体重
     * @param id 用户id
     * @param weight 体重
     */
    void updWeight(@Param("id") Integer id,
                   @Param("weight") BigDecimal weight);

    /**
     * 获取身体信息
     * @param id 用户id
     * @return UserBodyInfo
     */
    UserBodyInfo getBodyInfo(@Param("id") Integer id);
}