package com.hfkd.qhhealth.health.service;

import com.hfkd.qhhealth.health.model.HealthPlanConst;
import com.baomidou.mybatisplus.service.IService;

/**
 * 饮食计划常量 Service
 * @author hexq
 * @date 2018/7/5 10:12
 */
public interface HealthPlanConstService extends IService<HealthPlanConst> {
    HealthPlanConst getPlanConst();
}
