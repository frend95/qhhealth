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
public interface IHealthPlanIntakeService extends IService<HealthPlanIntake> {

    List<Map<String,Object>> getTotalIntakeSortByType(Integer userId);
}
