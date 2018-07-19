package com.hfkd.qhhealth.health.mapper;

import com.hfkd.qhhealth.health.model.HealthPlanIntake;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 饮食计划已选食物 Mapper
 * @author hexq
 * @date 2018-07-18
 */
public interface HealthPlanIntakeMapper extends BaseMapper<HealthPlanIntake> {

    List<HealthPlanIntake> getMyFoods(@Param("page") Integer page,
                                      @Param("size") Integer size,
                                      @Param("userId") Integer userId,
                                      @Param("type") Integer type);

    List<Map<String, Object>> getTotalIntakeSortByType(@Param("userId") Integer userId);

    Integer getKcalPer100gByItemId(@Param("itemId") Integer itemId);

    HealthPlanIntake getSelectedFood(@Param("userId") Integer userId,
                                     @Param("foodId") Integer foodId,
                                     @Param("type") Integer type);

    void addIntake(HealthPlanIntake intake);

    HealthPlanIntake getMyFood(@Param("id") Integer id);
}