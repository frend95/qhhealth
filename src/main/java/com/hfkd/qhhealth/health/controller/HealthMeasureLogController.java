package com.hfkd.qhhealth.health.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.health.mapper.HealthMeasureLogMapper;
import com.hfkd.qhhealth.health.model.HealthMeasureLog;
import com.hfkd.qhhealth.health.service.HealthMeasureLogService;
import com.hfkd.qhhealth.user.mapper.UserMapper;
import com.hfkd.qhhealth.user.model.UserBodyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 健康数据记录 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@RestController
@Verify
@RequestMapping("/health")
public class HealthMeasureLogController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private HealthMeasureLogMapper measureLogMapper;
    @Autowired
    private HealthMeasureLogService measureLogService;
    @Autowired
    private UserMapper userMapper;

    @LogOut("查询体脂秤记录")
    @RequestMapping("/log")
    public Map<String, Object> log() {
        Integer currId = session.getCurrId();
        HealthMeasureLog recentLog = measureLogMapper.getRecentLog(currId);
        boolean hasData = recentLog != null;

        UserBodyInfo bodyInfo = userMapper.getBodyInfo(currId);
        String gender = bodyInfo.getGender();
        Integer age = bodyInfo.getAge();
        gender = gender == null ? ConstVal.USER_GENDER_FEMALE : gender;
        age = age == null ? 30 : age;
        List<Map<String, Object>> list = measureLogService.buildLog(recentLog, gender, age);

        Map<String, Object> resultMap = RspUtil.ok(list);
        resultMap.put("hasData", hasData);
        return resultMap;
    }

    @LogOut("添加健康数据记录")
    @RequestMapping("/addLog")
    public Map<String, Object> addLog(HealthMeasureLog measureLog) {
        BigDecimal weight = measureLog.getWeight();
        if (weight == null) {
            return RspUtil.error("体重不能为空");
        }
        Integer currId = session.getCurrId();
        measureLog.setUserId(currId);
        // 获取性别年龄计算各项数据标准
        UserBodyInfo bodyInfo = userMapper.getBodyInfo(currId);
        String gender = bodyInfo.getGender();
        Integer age = bodyInfo.getAge();
        gender = gender == null ? ConstVal.USER_GENDER_FEMALE : gender;
        age = age == null ? 30 : age;
        // 体脂秤数据标尺
        List<Map<String, Object>> list = measureLogService.buildLog(measureLog, gender, age);
        // 更改用户表体重(两处体重记录，user与health_measure_log)
        userMapper.updWeight(currId, weight);
        measureLogMapper.insert(measureLog);
        return RspUtil.ok(list);
    }
}
