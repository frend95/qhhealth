package com.hfkd.qhhealth.health.service;

import com.baomidou.mybatisplus.service.IService;
import com.hfkd.qhhealth.health.model.HealthPlanIntake;

import java.util.List;
import java.util.Map;

/**
 * 饮食计划已选食物 Service
 * @author hexq
 * @date 2018-07-18
 */
public interface HealthPlanIntakeService extends IService<HealthPlanIntake> {

    /**
     * 获取根据饮食类型分类的的总摄入能量列表（没有数据初始化）
     * @param userId 用户id
     * @return List<Map{ kcal,type }>
     */
    List<Map<String,Object>> getTotalIntakeSortByType(Integer userId);
}
