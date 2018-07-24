package com.hfkd.qhhealth.nutritionist.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.nutritionist.model.NutritionistLetter;
import com.hfkd.qhhealth.nutritionist.service.NutritionistLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 营养师用户信 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@RestController
@RequestMapping("/yys")
public class NutritionistLetterController {

    @Autowired
    private NutritionistLetterService letterService;

    @LogOut("查询给客户的信")
    @RequestMapping("/letter")
    public Map<String, Object> letter(Integer id) {
        NutritionistLetter letter = letterService.selectById(id);
        return RspUtil.ok(letter.getUrl());
    }
}
