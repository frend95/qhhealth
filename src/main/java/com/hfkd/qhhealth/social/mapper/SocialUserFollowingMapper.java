package com.hfkd.qhhealth.social.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.common.Model.PageVo;
import com.hfkd.qhhealth.social.model.FollowVo;
import com.hfkd.qhhealth.social.model.SocialUserFollowing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户关注 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface SocialUserFollowingMapper extends BaseMapper<SocialUserFollowing> {

    /**
     * 获取关注列表id
     * @param followingType 关注类型
     * @param userId 用户id
     * @param followingId 被关注者id
     * @return 关注列表id
     */
    Integer getFollowLsId(@Param("followingType") String followingType,
                          @Param("userId") Integer userId,
                          @Param("followingId") Integer followingId);

    /**
     * 获取关注列表
     * @param pageVo 分页对象
     * @param userId 用户id
     * @return List<FollowVo>
     */
    List<FollowVo> getFollowingLs(@Param("pageVo") PageVo pageVo,
                                  @Param("userId") Integer userId,
                                  @Param("currId") Integer currId);

    List<FollowVo> getFollowers(@Param("pageVo") PageVo pageVo,
                                          @Param("id") Integer id,
                                          @Param("currId") Integer currId,
                                          @Param("type") String type);

    void followerPlusOne(@Param("id") Integer id,
                         @Param("type") String type);

    void followerMinusOne(@Param("id") Integer id,
                          @Param("type") String type);

    void followingPlusOne(@Param("id") Integer userId);

    void followingMinusOne(@Param("id") Integer userId);
}