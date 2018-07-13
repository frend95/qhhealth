package com.hfkd.qhhealth.social.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.social.model.SocialUserFollowing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户关注 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface SocialUserFollowingMapper extends BaseMapper<SocialUserFollowing> {
    
    Integer getFollowLsId(@Param("followingType") String followingType,
                   @Param("userId") Integer userId,
                   @Param("followingId") Integer followingId);
}