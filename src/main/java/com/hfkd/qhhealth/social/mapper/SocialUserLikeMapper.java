package com.hfkd.qhhealth.social.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.social.model.SocialUserLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户点赞 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface SocialUserLikeMapper extends BaseMapper<SocialUserLike> {

    Integer getLikeId(@Param("userId") Integer userId,
                      @Param("feedId") Integer feedId);

    void delLike(@Param("id") Integer id);

    void delLikeByFeed(@Param("feedId") Integer feedId);
}