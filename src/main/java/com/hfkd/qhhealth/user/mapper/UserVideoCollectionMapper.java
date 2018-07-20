package com.hfkd.qhhealth.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.user.model.UserVideoCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户视频收藏 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface UserVideoCollectionMapper extends BaseMapper<UserVideoCollection> {

    /**
     * 获取收藏项id
     * @param userId 用户id
     * @param videoId 视频id
     * @return 收藏项id
     */
    Integer getClctId(@Param("userId") Integer userId,
                      @Param("videoId") Integer videoId);
}