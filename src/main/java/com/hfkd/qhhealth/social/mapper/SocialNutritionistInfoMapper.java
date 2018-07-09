package com.hfkd.qhhealth.social.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.social.model.SocialNutritionistInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 营养师社交信息 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface SocialNutritionistInfoMapper extends BaseMapper<SocialNutritionistInfo> {
    
    List<Map<String, Object>> getYysList(@Param("page") Integer page,
                                         @Param("size") Integer size,
                                         @Param("name") String name);
}