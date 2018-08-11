package com.hfkd.qhhealth.sys.controller;


import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.nutritionist.mapper.NutritionistCaseMapper;
import com.hfkd.qhhealth.nutritionist.service.NutritionistService;
import com.hfkd.qhhealth.sys.mapper.SysEntranceMapper;
import com.hfkd.qhhealth.sys.model.SysDisplayImg;
import com.hfkd.qhhealth.sys.service.SysDisplayImgService;
import com.hfkd.qhhealth.video.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 内容入口配置 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@RestController
@RequestMapping("/sys")
public class SysEntranceController {

    @Autowired
    private SysDisplayImgService displayImgService;
    @Autowired
    private NutritionistService yysService;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private NutritionistCaseMapper caseMapper;
    @Autowired
    private SysEntranceMapper entranceMapper;

    @LogOut("查询首页")
    @Verify
    @Cacheable("API_CACHE")
    @RequestMapping("/homepage")
    public Map<String, Object> homepage() {
        // 查询6条banner
        List<SysDisplayImg> banner = displayImgService.getDisplayImg(ConstVal.IMG_PAGE_TUTORIAL, null, 6);
        // 推荐营养师
        List<Map<String, Object>> recommendYys = yysService.recommendYys();
        // 查询4条教学视频
        List<Map<String, Object>> video = videoMapper.getVideoLs(0, 4, ConstVal.VIDEO_TYPE_TUTORIAL,
                null, null);
        // 查询3条食谱文章
        List<Map<String, Object>> recipe = articleMapper.getArticles(0, 3, ConstVal.ARTICLE_TAG_RECIPE);
        // 查询10条客户案例
        List<Map<String, Object>> cases = videoMapper.getVideoLs(0, 10, ConstVal.VIDEO_TYPE_CASE,
                null, null);
        // 查询减肥小窍门标签
        List<Map<String, Object>> articleTags = entranceMapper.getEntrance(ConstVal.IMG_PAGE_TUTORIAL);

        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("banner", banner);
        resultMap.put("recommendYys", recommendYys);
        resultMap.put("video", video);
        resultMap.put("recipe", recipe);
        resultMap.put("cases", cases);
        resultMap.put("articleTags", articleTags);
        return resultMap;
    }

    @LogOut("查询启动页")
    @RequestMapping("/startPage")
    public Map<String, Object> startPage() {
        List<SysDisplayImg> splash = displayImgService.getDisplayImg(ConstVal.IMG_PAGE_SPLASH, null, null);
        return RspUtil.ok(splash.get(0));
    }

    @LogOut("查询引导页")
    @RequestMapping("/onboardingPage")
    public Map<String, Object> onboardingPage() {
        List<SysDisplayImg> onboarding = displayImgService.getDisplayImg(ConstVal.IMG_PAGE_ONBOARDING, null, null);
        return RspUtil.ok(onboarding);
    }

    @LogOut("查询案例页")
    @Verify
    @Cacheable("API_CACHE")
    @RequestMapping("/casePage")
    public Map<String, Object> casePage() {
        // 查询3张banner
        List<SysDisplayImg> banner = displayImgService.getDisplayImg(ConstVal.IMG_PAGE_CASE, null, 3);
        // 查询1条客户案例视频
        List<Map<String, Object>> video = videoMapper.getVideoLs(0, 1, ConstVal.VIDEO_TYPE_CASE,
                null, null);
        // 查询10条案例文章
        List<Map<String, Object>> cases = caseMapper.getCases(0, 10, null);
        // 查询案例分类标签
        List<Map<String, Object>> videoTags = entranceMapper.getEntrance(ConstVal.IMG_PAGE_CASE);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("banner", banner);
        resultMap.put("video", video.get(0));
        resultMap.put("cases", cases);
        resultMap.put("videoTags", videoTags);
        return resultMap;
    }

}
