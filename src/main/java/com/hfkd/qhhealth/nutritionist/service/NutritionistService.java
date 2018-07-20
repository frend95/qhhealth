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

    /**
     * 获取推荐营养师
     * @return List<Map{ id,name,bio,title,avatar,serviceCnt,followers,feedCnt,tutorialCnt }>
     */
    List<Map<String, Object>> recommendYys();

    /**
     * 获取随机营养师id
     * @return 随机营养师id
     */
    Integer getRandomYysId();
}
