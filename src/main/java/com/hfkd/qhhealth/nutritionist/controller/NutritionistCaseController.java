package com.hfkd.qhhealth.nutritionist.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.RspEntity;
import com.hfkd.qhhealth.nutritionist.mapper.NutritionistCaseMapper;
import com.hfkd.qhhealth.nutritionist.model.NutritionistCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 营养师服务案例 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/yys")
public class NutritionistCaseController {

    @Autowired
    private NutritionistCaseMapper caseMapper;

    @LogOut("查询服务案例列表")
    @RequestMapping("/caseList")
    public Map caseList(Integer page, Integer size, Integer nutritionistId) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> cases = caseMapper.getCases(page, size, nutritionistId);
        Map<String, Object> resultMap = RspEntity.ok();
        resultMap.put("result", cases);
        return resultMap;
    }

    @LogOut("查询服务案例详情")
    @RequestMapping("/caseDetail")
    public Map caseDetail(Integer id) {
        NutritionistCase aCase = caseMapper.selectById(id);
        return RspEntity.ok(aCase);
    }

}
