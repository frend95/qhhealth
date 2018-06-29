package com.hexq.qh.healthlog.controller;


import com.hexq.qh.common.annotation.LogOut;
import com.hexq.qh.common.util.RspUtil;
import com.hexq.qh.common.util.UUIDUtil;
import com.hexq.qh.customer.service.CustomerService;
import com.hexq.qh.healthlog.mapper.HealthLogMapper;
import com.hexq.qh.healthlog.model.HealthLog;
import com.hexq.qh.healthlog.service.HealthLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 客户健康信息记录 Controller
 * @author hexq
 * @date 2018-06-09
 */
@RestController
@RequestMapping("/healthLog")
public class HealthLogController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private HealthLogService healthLogService;
    @Autowired
    private HealthLogMapper healthLogMapper;

    @LogOut("添加健康信息记录")
    @RequestMapping("/add")
    public Map<String, Object> add(@RequestBody HealthLog healthLog) {
        Map<String, Object> resultMap = RspUtil.ok();
        String customerId = healthLog.getCustomerId();
        String yysId = customerService.getCustomerYysId(customerId);
        healthLog.setUserId(yysId);
        healthLog.setId(UUIDUtil.getUUid());
        // 查询最初的体重，更新健康信息表的累计减重
        Integer initialWeight = healthLogMapper.getInitialWeight(customerId);
        Integer currWeight = healthLog.getWeight();
        if (initialWeight != null && initialWeight != 0) {
            Integer weightLoss = initialWeight - currWeight;
            healthLogMapper.addWeightLoss(customerId, yysId, weightLoss);
        } else {
            healthLogMapper.addInitialWeight(customerId, yysId, currWeight);
        }
        healthLogService.insert(healthLog);
        return resultMap;
    }

    @LogOut("查询累计减重")
    @RequestMapping("/weightLoss")
    public Map<String, Object> weightLoss(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        Integer weightLoss = healthLogMapper.getWeightLoss(id);
        resultMap.put("result", weightLoss);
        return resultMap;
    }

    @LogOut("查询健康信息记录")
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        List<Map<String, Object>> weightLog = healthLogMapper.getWeightLog(id, startTime, endTime);
        resultMap.put("result", weightLog);
        return resultMap;
    }

}
