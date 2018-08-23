package com.hfkd.qhhealth.social.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.social.model.SocialUserFollowing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * @param page 页码
     * @param size limit
     * @param userId 用户id
     * @param followingType 关注类型
     * @return List<Map{ id,userId,followingId,followingName,followingType }>
     */
    List<Map<String,Object>> getFollowingLs(@Param("page") Integer page,
                                            @Param("size") Integer size,
                                            @Param("userId") Integer userId,
                                            @Param("followingType") String followingType);

    void followerPlusOne(@Param("id") Integer id,
                         @Param("type") String type);

    void followerMinusOne(@Param("id") Integer id,
                          @Param("type") String type);

    void followingPlusOne(@Param("id") Integer userId);

    void followingMinusOne(@Param("id") Integer userId);
}