package com.hfkd.qhhealth.nutritionist.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.social.mapper.SocialNutritionistInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 营养师信息 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@RestController
@RequestMapping("/yys")
public class NutritionistController {
    @Autowired
    private SocialNutritionistInfoMapper socialYysInfoMapper;

    @LogOut("查询营养师列表")
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page, Integer size, String name) {
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> yysList = socialYysInfoMapper.getYysList(page, size, name);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", yysList);
        return resultMap;
    }


}
