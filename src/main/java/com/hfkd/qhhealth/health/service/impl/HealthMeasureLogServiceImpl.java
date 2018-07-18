package com.hfkd.qhhealth.health.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.health.mapper.HealthMeasureLogMapper;
import com.hfkd.qhhealth.health.model.HealthMeasureLog;
import com.hfkd.qhhealth.health.model.HealthMeasureRule;
import com.hfkd.qhhealth.health.service.HealthMeasureLogService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 健康数据记录 ServiceImpl
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class HealthMeasureLogServiceImpl extends ServiceImpl<HealthMeasureLogMapper, HealthMeasureLog> implements HealthMeasureLogService {

    private static final String WEIGHT = "体重";
    private static final String BFR = "体脂率";
    private static final String BFR_IMG = "https://app.xintianhong888.com/img/bfr.jpg";
    private static final String UVI = "内脏脂肪数";
    private static final String UVI_IMG = "https://app.xintianhong888.com/img/uvi.jpg";
    private static final String BMI = "BMI";
    private static final String BMI_IMG = "https://app.xintianhong888.com/img/bmi.jpg";
    private static final String BMR = "基础代谢率";
    private static final String BMR_IMG = "https://app.xintianhong888.com/img/bmr.jpg";
    private static final String MR = "肌肉率";
    private static final String MR_IMG = "https://app.xintianhong888.com/img/mr.jpg";
    private static final String WR = "水分";
    private static final String WR_IMG = "https://app.xintianhong888.com/img/wr.jpg";
    private static final String BQ = "骨量";
    private static final String BQ_IMG = "https://app.xintianhong888.com/img/bq.jpg";
    private static final String PR = "蛋白质";
    private static final String PR_IMG = "https://app.xintianhong888.com/img/pr.jpg";

    @Override
    public List<Map<String, Object>> buildLog(HealthMeasureLog log, String gender, Integer age) {
        if (log == null) {
            log = new HealthMeasureLog();
        }

        HealthMeasureRule standard = new HealthMeasureRule(log, gender, age);
        List<Map<String, Object>> list =new LinkedList<>();

        Map<String, Object> weightMap = new HashMap<>(8);
        weightMap.put("name", WEIGHT);
        weightMap.put("value", num2Str(log.getWeight()));
        weightMap.put("img", null);
        weightMap.put("standard", null);
        list.add(weightMap);

        Map<String, Object> bfrMap = new HashMap<>(8);
        bfrMap.put("name", BFR);
        bfrMap.put("value", bd2Percent(log.getBfr()));
        bfrMap.put("img", BFR_IMG);
        bfrMap.put("standard", standard.getBfr());
        list.add(bfrMap);

        Map<String, Object> uviMap = new HashMap<>(8);
        uviMap.put("name", UVI);
        uviMap.put("value", num2Str(log.getUvi()));
        uviMap.put("img", UVI_IMG);
        uviMap.put("standard", standard.getUvi());
        list.add(uviMap);

        Map<String, Object> bmiMap = new HashMap<>(8);
        bmiMap.put("name", BMI);
        bmiMap.put("value", num2Str(log.getBmi()));
        bmiMap.put("img", BMI_IMG);
        bmiMap.put("standard", standard.getBmi());
        list.add(bmiMap);

        Map<String, Object> bmrMap = new HashMap<>(8);
        bmrMap.put("name", BMR);
        bmrMap.put("value", num2Str(log.getBmr()));
        bmrMap.put("img", BMR_IMG);
        bmrMap.put("standard", standard.getBmr());
        list.add(bmrMap);

        Map<String, Object> mrMap = new HashMap<>(8);
        mrMap.put("name", MR);
        mrMap.put("value", bd2Percent(log.getMr()));
        mrMap.put("img", MR_IMG);
        mrMap.put("standard", standard.getMr());
        list.add(mrMap);

        Map<String, Object> wrMap = new HashMap<>(8);
        wrMap.put("name", WR);
        wrMap.put("value", bd2Percent(log.getWr()));
        wrMap.put("img", WR_IMG);
        wrMap.put("standard", standard.getWr());
        list.add(wrMap);

        Map<String, Object> bqMap = new HashMap<>(8);
        bqMap.put("name", BQ);
        bqMap.put("value", num2Str(log.getBq()));
        bqMap.put("img", BQ_IMG);
        bqMap.put("standard", standard.getBq());
        list.add(bqMap);

        Map<String, Object> prMap = new HashMap<>(8);
        prMap.put("name", PR);
        prMap.put("value", bd2Percent(log.getPr()));
        prMap.put("img", PR_IMG);
        prMap.put("standard", standard.getPr());
        list.add(prMap);

        return list;

    }

    private String bd2Percent(BigDecimal bd) {
        return bd == null ? "0" : bd.doubleValue() * 100 + "%";
    }

    private String num2Str(Object number) {
        return number == null ? "0" : number.toString();

    }
}
