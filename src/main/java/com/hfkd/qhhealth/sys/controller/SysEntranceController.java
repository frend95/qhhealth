package com.hfkd.qhhealth.sys.controller;


import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.nutritionist.mapper.NutritionistCaseMapper;
import com.hfkd.qhhealth.nutritionist.service.NutritionistService;
import com.hfkd.qhhealth.sys.model.SysDisplayImg;
import com.hfkd.qhhealth.sys.service.SysDisplayImgService;
import com.hfkd.qhhealth.video.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
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

    private static List<Map> articleTags;
    private static List<Map> videoTags;
    static {
        articleTags = new LinkedList<>();
        Map<String, String> tag1 = new HashMap<>(4);
        tag1.put("name", "饮食减肥");
        tag1.put("img", "https://app.xintianhong888.com/img/ysjf.png");
        tag1.put("tag", ConstEnum.ARTICLE_TAG_DIET.getValue());
        articleTags.add(tag1);
        Map<String, String> tag2 = new HashMap<>(4);
        tag2.put("name", "运动减肥");
        tag2.put("img", "https://app.xintianhong888.com/img/ydjf.png");
        tag2.put("tag", ConstEnum.ARTICLE_TAG_SPORT.getValue());
        articleTags.add(tag2);
        Map<String, String> tag3 = new HashMap<>(4);
        tag3.put("name", "你所不知道的减肥大法");
        tag3.put("img", "https://app.xintianhong888.com/img/jfxqm.png");
        tag3.put("tag", ConstEnum.ARTICLE_TAG_METHOD.getValue());
        articleTags.add(tag3);

        videoTags = new LinkedList<>();
        Map<String, String> tag4 = new HashMap<>(4);
        tag4.put("name", "中年发福");
        tag4.put("img", "https://app.xintianhong888.com/img/znff.jpg");
        tag4.put("tag", ConstEnum.VIDEO_TAG_MIDAGE.getValue());
        videoTags.add(tag4);
        Map<String, String> tag5 = new HashMap<>(4);
        tag5.put("name", "产后肥胖");
        tag5.put("img", "https://app.xintianhong888.com/img/chfp.jpg");
        tag5.put("tag", ConstEnum.VIDEO_TAG_PARTUM.getValue());
        videoTags.add(tag5);
        Map<String, String> tag6 = new HashMap<>(4);
        tag6.put("name", "青春性肥胖");
        tag6.put("img", "https://app.xintianhong888.com/img/qcxfp.jpg");
        tag6.put("tag", ConstEnum.VIDEO_TAG_YOUTH.getValue());
        videoTags.add(tag6);
        Map<String, String> tag7 = new HashMap<>(4);
        tag7.put("name", "单纯性肥胖");
        tag7.put("img", "https://app.xintianhong888.com/img/dcxfp.jpg");
        tag7.put("tag", ConstEnum.VIDEO_TAG_SIMPLE.getValue());
        videoTags.add(tag7);
        Map<String, String> tag8 = new HashMap<>(4);
        tag8.put("name", "遗传性肥胖");
        tag8.put("img", "https://app.xintianhong888.com/img/ycxfp.jpg");
        tag8.put("tag", ConstEnum.VIDEO_TAG_GENETIC.getValue());
        videoTags.add(tag8);

    }

    @LogOut("查询首页")
    @Cacheable("API_CACHE")
    @RequestMapping("/homepage")
    public Map<String, Object> homepage() {
        // 查询6条banner
        List<SysDisplayImg> banner = displayImgService.getDisplayImg(ConstEnum.IMG_PAGE_TUTORIAL, null, 6);
        // 推荐营养师
        List<Map<String, Object>> recommendYys = yysService.recommendYys();
        // 查询4条教学视频
        List<Map<String, Object>> video = videoMapper.getVideoLs(0, 4, ConstEnum.VIDEO_TYPE_TUTORIAL.getValue(),
                null, null);
        // 查询3条食谱文章
        List<Map<String, Object>> recipe = articleMapper.getArticles(0, 3, ConstEnum.ARTICLE_TAG_RECIPE.getValue());
        // 查询5条客户案例
        List<Map<String, Object>> cases = videoMapper.getVideoLs(0, 5, ConstEnum.VIDEO_TYPE_CASE.getValue(),
                null, null);

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
        List<SysDisplayImg> splash = displayImgService.getDisplayImg(ConstEnum.IMG_PAGE_SPLASH, null, null);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", splash.get(0));
        return resultMap;
    }

    @LogOut("查询案例页")
    @Cacheable("API_CACHE")
    @RequestMapping("/casePage")
    public Map<String, Object> casePage() {
        // 查询3张banner
        List<SysDisplayImg> banner = displayImgService.getDisplayImg(ConstEnum.IMG_PAGE_CASE, null, 3);
        // 查询1条客户案例视频
        List<Map<String, Object>> video = videoMapper.getVideoLs(0, 1, ConstEnum.VIDEO_TYPE_CASE.getValue(),
                null, null);
        // 查询10条案例文章
        List<Map<String, Object>> cases = caseMapper.getCases(0, 10, null);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("banner", banner);
        resultMap.put("video", video.get(0));
        resultMap.put("cases", cases);
        resultMap.put("videoTags", videoTags);
        return resultMap;
    }

}
