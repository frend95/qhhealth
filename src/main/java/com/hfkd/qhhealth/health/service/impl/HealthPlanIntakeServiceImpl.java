package com.hfkd.qhhealth.health.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.health.mapper.HealthPlanIntakeMapper;
import com.hfkd.qhhealth.health.model.HealthPlanIntake;
import com.hfkd.qhhealth.health.service.HealthPlanIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 饮食计划已选食物 ServiceImpl
 * @author hexq
 * @date 2018-07-18
 */
@Service
public class HealthPlanIntakeServiceImpl extends ServiceImpl<HealthPlanIntakeMapper, HealthPlanIntake> implements HealthPlanIntakeService {

    @Autowired
    private HealthPlanIntakeMapper intakeMapper;

    @Override
    public List<Map<String, Object>> getTotalIntakeSortByType(Integer userId) {
        List<Map<String, Object>> sortByTypeLS = intakeMapper.getTotalIntakeSortByType(userId);
        if (sortByTypeLS.size() < 4) {
            List<Map<String, Object>> list = new LinkedList<>();
            Map<Object, Object> map = new HashMap<>(4);

            for (Map<String, Object> sortMap : sortByTypeLS) {
                map.put(sortMap.get("type"), sortMap.get("kcal"));
            }

            Map<String, Object> bfMap = new HashMap<>(4);
            Object bfCal = map.get(ConstVal.INTAKE_BREAKFAST);
            bfMap.put("kcal", bfCal == null ? 0 : bfCal);
            bfMap.put("type", ConstVal.INTAKE_BREAKFAST);
            list.add(bfMap);

            Map<String, Object> lcMap = new HashMap<>(4);
            Object lcCal = map.get(ConstVal.INTAKE_LUNCH);
            lcMap.put("kcal", lcCal == null ? 0 : lcCal);
            lcMap.put("type", ConstVal.INTAKE_LUNCH);
            list.add(lcMap);

            Map<String, Object> dnMap = new HashMap<>(4);
            Object dnCal = map.get(ConstVal.INTAKE_DINNER);
            dnMap.put("kcal", dnCal == null ? 0 : dnCal);
            dnMap.put("type", ConstVal.INTAKE_DINNER);
            list.add(dnMap);

            Map<String, Object> mlMap = new HashMap<>(4);
            Object mlCal = map.get(ConstVal.INTAKE_MEAL);
            mlMap.put("kcal", mlCal == null ? 0 : mlCal);
            mlMap.put("type", ConstVal.INTAKE_MEAL);
            list.add(mlMap);

            sortByTypeLS = list;
        }
        return sortByTypeLS;
    }
}
