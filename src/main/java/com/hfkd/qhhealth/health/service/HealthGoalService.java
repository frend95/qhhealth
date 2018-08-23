package com.hfkd.qhhealth.health.service;

import com.hfkd.qhhealth.health.model.HealthGoal;
import com.baomidou.mybatisplus.service.IService;

/**
 * 健康目标 Service
 * @author hexq
 * @date 2018/7/5 10:12
 */
public interface HealthGoalService extends IService<HealthGoal> {

    Long computeRemainDays(String startTime, Integer period);
}
