package com.hfkd.qhhealth.video.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.video.model.VideoCmtChild;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 视频子评论 Mapper
 * @author hexq
 * @date 2018/7/4 18:21
 */
@Mapper
public interface VideoCmtChildMapper extends BaseMapper<VideoCmtChild> {


    List<Map<String,Object>> getChildCmt(@Param("page") Integer page,
                                         @Param("size") Integer size,
                                         @Param("cmtId") Integer cmtId);
}