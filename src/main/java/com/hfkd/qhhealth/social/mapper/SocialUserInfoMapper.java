package com.hfkd.qhhealth.social.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.social.model.SocialUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 用户社交信息 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface SocialUserInfoMapper extends BaseMapper<SocialUserInfo> {
    
    void feedPlusOne(@Param("userId") Integer userId);

    void feedMinusOne(@Param("userId") Integer userId);

    Map<String, Object> getSocialInfo(@Param("id") Integer id);
}