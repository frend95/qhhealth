package com.hfkd.qhhealth.video.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.video.model.VideoCmt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 视频评论 Mapper
 * @author hexq
 * @date 2018/7/4 18:21
 */
@Mapper
public interface VideoCmtMapper extends BaseMapper<VideoCmt> {

    List<Map<String,Object>> getComments(@Param("page") Integer page,
                                         @Param("size") Integer size,
                                         @Param("videoId") Integer videoId);

    void replyCntPlusOne(@Param("id") Integer id);

}