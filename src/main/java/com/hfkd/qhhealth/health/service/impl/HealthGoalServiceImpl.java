package com.hfkd.qhhealth.health.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.common.util.DateUtil;
import com.hfkd.qhhealth.health.mapper.HealthGoalMapper;
import com.hfkd.qhhealth.health.model.HealthGoal;
import com.hfkd.qhhealth.health.service.HealthGoalService;
import org.springframework.stereotype.Service;

/**
 * 健康目标 ServiceImpl
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class HealthGoalServiceImpl extends ServiceImpl<HealthGoalMapper, HealthGoal> implements HealthGoalService {

    @Override
    public Long computeRemainDays(String startTime, Integer period) {
        String reachTime = DateUtil.addDay(startTime, period);
        Long remainDays = DateUtil.divideDate(reachTime, DateUtil.yyyyMMdd());
        return remainDays < 0 ? 0 : remainDays;
    }
}
