package com.hfkd.qhhealth.health.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.health.mapper.HealthPlanIntakeMapper;
import com.hfkd.qhhealth.health.model.HealthPlanIntake;
import com.hfkd.qhhealth.health.service.IHealthPlanIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 饮食计划已选食物 ServiceImpl
 * @author hexq
 * @date 2018-07-18
 */
@Service
public class HealthPlanIntakeServiceImpl extends ServiceImpl<HealthPlanIntakeMapper, HealthPlanIntake> implements IHealthPlanIntakeService {

    @Autowired
    private HealthPlanIntakeMapper intakeMapper;

    @Override
    public List<Map<String, Object>> getTotalIntakeSortByType(Integer userId) {
        List<Map<String, Object>> sortByType = intakeMapper.getTotalIntakeSortByType(userId);
        if (sortByType.size() == 0) {
            Map<String, Object> bfMap = new HashMap<>(4);
            bfMap.put("kcal", 0);
            bfMap.put("type", ConstVal.INTAKE_BREAKFAST);
            sortByType.add(bfMap);

            Map<String, Object> lcMap = new HashMap<>(4);
            lcMap.put("kcal", 0);
            lcMap.put("type", ConstVal.INTAKE_LUNCH);
            sortByType.add(lcMap);

            Map<String, Object> dnMap = new HashMap<>(4);
            dnMap.put("kcal", 0);
            dnMap.put("type", ConstVal.INTAKE_DINNER);
            sortByType.add(dnMap);

            Map<String, Object> mlMap = new HashMap<>(4);
            mlMap.put("kcal", 0);
            mlMap.put("type", ConstVal.INTAKE_MEAL);
            sortByType.add(mlMap);
        }
        return sortByType;
    }
}
