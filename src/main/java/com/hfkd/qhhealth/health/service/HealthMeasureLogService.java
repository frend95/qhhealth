package com.hfkd.qhhealth.health.service;

import com.hfkd.qhhealth.health.model.HealthMeasureLog;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 健康数据记录 Service
 * @author hexq
 * @date 2018/7/5 10:12
 */
public interface HealthMeasureLogService extends IService<HealthMeasureLog> {

     /**
      * 构建饮食记录
      * @param log HealthMeasureLog
      * @param gender 性别
      * @param age 年龄
      * @return List<Map{ name,value,img,standard }>
      */
     List<Map<String, Object>> buildLog(HealthMeasureLog log, String gender, Integer age);
}
