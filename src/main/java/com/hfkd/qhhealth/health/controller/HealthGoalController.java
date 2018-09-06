package com.hfkd.qhhealth.health.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.DateUtil;
import com.hfkd.qhhealth.common.util.RspEntity;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.health.mapper.HealthGoalMapper;
import com.hfkd.qhhealth.health.model.HealthGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 健康目标 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/health")
public class HealthGoalController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private HealthGoalMapper healthGoalMapper;

    @LogOut("更改目标体重")
    @RequestMapping("/updWeight")
    public Map updWeight(BigDecimal goalWeight, Integer period) {
        if (goalWeight == null || period == null) {
            return RspEntity.error("体重或时间期限不能为空");
        }
        Integer currId = session.getCurrId();
        String startTime = DateUtil.yyyyMMdd();
        HealthGoal healthGoal = new HealthGoal(currId, goalWeight, period, startTime);
        healthGoalMapper.updateById(healthGoal);
        return RspEntity.ok();
    }
}
