package com.hfkd.qhhealth.nutritionist.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.nutritionist.mapper.NutritionistCaseMapper;
import com.hfkd.qhhealth.nutritionist.mapper.NutritionistLetterMapper;
import com.hfkd.qhhealth.nutritionist.mapper.NutritionistMapper;
import com.hfkd.qhhealth.nutritionist.model.NutritionistLetter;
import com.hfkd.qhhealth.nutritionist.service.NutritionistService;
import com.hfkd.qhhealth.social.mapper.SocialNutritionistInfoMapper;
import com.hfkd.qhhealth.social.mapper.SocialUserFollowingMapper;
import com.hfkd.qhhealth.video.mapper.VideoMapper;
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
    private NutritionistCaseMapper caseMapper;
    @Autowired
    private SocialNutritionistInfoMapper socialYysInfoMapper;
    @Autowired
    private NutritionistService yysService;
    @Autowired
    private NutritionistMapper yysMapper;
    @Autowired
    private SocialUserFollowingMapper userFollowingMapper;
    @Autowired
    private SessionUtil session;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private NutritionistLetterMapper letterMapper;

    @LogOut("查询营养师列表")
    @Verify(hasSession = true)
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page, Integer size, String name) {
        Integer currId = session.getCurrId();
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> yysList = socialYysInfoMapper.getYysList(page, size, name, currId);
        return RspUtil.ok(yysList);
    }

    @LogOut("查询推荐营养师")
    @Verify
    @RequestMapping("/recommend")
    public Map<String, Object> recommend() {
        return RspUtil.ok(yysService.recommendYys());
    }

    @LogOut("查询热门营养师")
    @Verify
    @RequestMapping("/popular")
    public Map<String, Object> popular() {
        return RspUtil.ok(yysMapper.popularYys());
    }

    @LogOut("查询营养师详情")
    @Verify(hasSession = true)
    @RequestMapping("/detail")
    public Map<String, Object> detail(Integer id) {
        Integer currId = session.getCurrId();
        // 查询营养师社圈信息
        Map<String, Object> yysMap = socialYysInfoMapper.getById(id);
        // 查询是否关注该营养师
        boolean isFollow = currId != null && userFollowingMapper.getFollowLsId(ConstVal.YYS, currId, id) != null;
        // 查询6个服务案例
        List<Map<String, Object>> cases = caseMapper.getCases(0, 6, id);
        // 查询4个课程
        List<Map<String, Object>> video = videoMapper.getVideoLs(0, 4, ConstVal.VIDEO_TYPE_TUTORIAL, null, id);
        // 查询给营养师的一封信
        NutritionistLetter letter = letterMapper.selectById(id);

        yysMap.put("isFollow", isFollow);
        yysMap.put("cases", cases);
        yysMap.put("video", video);
        yysMap.put("letter", letter == null ? null : letter.getUrl());

        return RspUtil.ok(yysMap);
    }


}
