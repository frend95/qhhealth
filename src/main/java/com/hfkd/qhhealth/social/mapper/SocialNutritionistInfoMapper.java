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

    /**
     * 获取营养师列表
     * @param page 页码
     * @param size limit
     * @param name 营养师名称
     * @param userId 用户id
     * @return List<Map{ id,name,bio,title,avatar,serviceCnt,followers,feedCnt,tutorialCnt }>
     */
    List<Map<String, Object>> getYysList(@Param("page") Integer page,
                                         @Param("size") Integer size,
                                         @Param("name") String name,
                                         @Param("userId") Integer userId);

    /**
     * 根据ids获取所有营养师
     * @param ids id list
     * @return List<Map{ id,name,bio,title,avatar,serviceCnt,followers,feedCnt,tutorialCnt }>
     */
    List<Map<String, Object>> getYysByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据id获取营养师信息
     * @param id 营养师id
     * @return Map{ id,name,bio,title,avatar,serviceCnt,followers,feedCnt,tutorialCnt }
     */
    Map<String, Object> getById(@Param("id") Integer id);
}