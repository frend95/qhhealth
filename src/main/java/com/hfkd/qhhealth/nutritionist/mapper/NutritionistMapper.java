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

    Integer getMaxId();

    List<Integer> getAllId();

    List<Map<String, Object>> popularYys();
}