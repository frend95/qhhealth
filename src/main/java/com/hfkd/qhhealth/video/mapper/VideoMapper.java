package com.hfkd.qhhealth.video.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.video.model.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 视频 Mapper
 * @author hexq
 * @date 2018/7/4 18:21
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 获取视频列表
     * @param page 页码
     * @param size limit
     * @param type 视频类型
     * @param tag 视频标签
     * @param authorId 作者id
     * @return List<Map{ id,title,thumb,resource,watchedCnt,createTime }>
     */
    List<Map<String,Object>> getVideoLs(@Param("page") Integer page,
                                      @Param("size") Integer size,
                                      @Param("type") String type,
                                      @Param("tag") String tag,
                                      @Param("authorId") Integer authorId);

    /**
     * 获取视频简要信息
     * @param id 视频id
     * @return Map{ title,thumb }
     */
    Map<String, Object> getVideoBrief(@Param("id") Integer id);

    /**
     * 观看次数加一
     * @param id 视频id
     */
    void watchedCntPlusOne(@Param("id") Integer id);
}