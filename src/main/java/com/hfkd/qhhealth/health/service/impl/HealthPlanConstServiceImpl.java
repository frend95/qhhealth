package com.hfkd.qhhealth.health.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hfkd.qhhealth.health.model.HealthPlanConst;
import com.hfkd.qhhealth.health.mapper.HealthPlanConstMapper;
import com.hfkd.qhhealth.health.service.HealthPlanConstService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 饮食计划常量 ServiceImpl
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class HealthPlanConstServiceImpl extends ServiceImpl<HealthPlanConstMapper, HealthPlanConst> implements HealthPlanConstService {

    @Override
    public HealthPlanConst getPlanConst() {
        EntityWrapper<HealthPlanConst> wrapper = new EntityWrapper<>();
        wrapper.last("LIMIT 1");
        return selectOne(wrapper);
    }
}
