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
    
    Map<String, Object> getUserDetail(@Param("id") Integer id);

    void updWeight(@Param("id") Integer id,
                   @Param("weight") BigDecimal weight);

    UserBodyInfo getBodyInfo(@Param("id") Integer id);
}