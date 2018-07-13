package com.hfkd.qhhealth.nutritionist.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.nutritionist.model.NutritionistCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 营养师服务案例 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface NutritionistCaseMapper extends BaseMapper<NutritionistCase> {

    List<Map<String, Object>> getCases(@Param("page") Integer page,
                                       @Param("size") Integer size,
                                       @Param("nutritionistId") Integer nutritionistId);
}