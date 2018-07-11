package com.hfkd.qhhealth.nutritionist.service;

import com.hfkd.qhhealth.nutritionist.model.Nutritionist;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 营养师信息 Service
 * @author hexq
 * @date 2018/7/5 10:12
 */
public interface NutritionistService extends IService<Nutritionist> {

    List<Map<String, Object>> recommendYys();
}
