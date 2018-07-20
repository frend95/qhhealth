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

    /**
     * 获取我的已选食物列表
     * @param page 页码
     * @param size limit
     * @param userId 用户id
     * @param type 饮食类型
     * @return List<HealthPlanIntake>
     */
    List<HealthPlanIntake> getMyFoods(@Param("page") Integer page,
                                      @Param("size") Integer size,
                                      @Param("userId") Integer userId,
                                      @Param("type") Integer type);

    /**
     * 获取根据饮食类型分类的的总摄入能量列表
     * @param userId 用户id
     * @return List<Map{ kcal,type }>
     */
    List<Map<String, Object>> getTotalIntakeSortByType(@Param("userId") Integer userId);

    /**
     * 根据已选食物id获取每100g食物的卡路里
     * @param itemId 已选食物id
     * @return 每100g食物的卡路里（千卡）
     */
    Integer getKcalPer100gByItemId(@Param("itemId") Integer itemId);

    /**
     * 根据食物id获取已选食物
     * @param userId 用户id
     * @param foodId 食物id
     * @param type 饮食类型
     * @return HealthPlanIntake{ id,amount }
     */
    HealthPlanIntake getSelectedFood(@Param("userId") Integer userId,
                                     @Param("foodId") Integer foodId,
                                     @Param("type") Integer type);

    /**
     * 添加食物进已选食物列表
     * @param intake HealthPlanIntake{ userId,foodId,type,kcal,amount}
     */
    void addIntake(HealthPlanIntake intake);

    /**
     * 根据id获取已选食物
     * @param id 已选食物id
     * @return HealthPlanIntake
     */
    HealthPlanIntake getMyFood(@Param("id") Integer id);
}