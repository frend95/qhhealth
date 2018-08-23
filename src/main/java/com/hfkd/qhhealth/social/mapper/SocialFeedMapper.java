package com.hfkd.qhhealth.social.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.social.model.SocialFeed;
import com.hfkd.qhhealth.social.model.SocialFeedVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 动态 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface SocialFeedMapper extends BaseMapper<SocialFeed> {

    List<Map<String, Object>> getRecentYysFeeds(@Param("limit") Integer limit);

    List<Map<String, Object>> getPlazaFeeds(@Param("page") Integer page,
                                            @Param("size") Integer size);

    SocialFeedVo getFeed(@Param("id") Integer id);

    List<SocialFeedVo> getUserFeeds(@Param("authorId") Integer authorId,
                                    @Param("userId") Integer userId,
                                    @Param("page") Integer page,
                                    @Param("size") Integer size);

    List<SocialFeedVo> getYysFeeds(@Param("authorId") Integer authorId,
                                    @Param("userId") Integer userId,
                                    @Param("page") Integer page,
                                    @Param("size") Integer size);

    List<SocialFeedVo> getLikeFeeds(@Param("userId") Integer userId,
                                    @Param("page") Integer page,
                                    @Param("size") Integer size);

    void likePlusOne(@Param("id") Integer feedId);

    void likeMinusOne(@Param("id") Integer feedId);

}