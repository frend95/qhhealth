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

    List<Map<String,Object>> getVideoLs(@Param("page") Integer page,
                                      @Param("size") Integer size,
                                      @Param("type") String type,
                                      @Param("tag") String tag);

    Map<String, Object> getVideoBrief(@Param("id") Integer id);

    void watchedCntPlusOne(@Param("id") Integer id);

    void cmtCntPlusOne(@Param("id") Integer id);
}