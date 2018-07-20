package com.hfkd.qhhealth.nutritionist.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.nutritionist.model.Nutritionist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 营养师信息 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface NutritionistMapper extends BaseMapper<Nutritionist> {

    /**
     * 获取最大营养师id
     * @return 最大营养师id
     */
    Integer getMaxId();

    /**
     * 获取所有营养师id
     * @return 所有营养师id
     */
    List<Integer> getAllId();

    /**
     * 获取热门营养师
     * @return List<Map{ id,name }>
     */
    List<Map<String, Object>> popularYys();
}