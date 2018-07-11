package com.hfkd.qhhealth.sys.controller;


import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.nutritionist.service.NutritionistService;
import com.hfkd.qhhealth.sys.model.SysDisplayImg;
import com.hfkd.qhhealth.sys.service.SysDisplayImgService;
import com.hfkd.qhhealth.video.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @LogOut("查询首页")
    @RequestMapping("/homepage")
    public Map<String, Object> homepage() {
        // 查询6条banner
        List<SysDisplayImg> banner = displayImgService.getDisplayImg(ConstEnum.IMG_PAGE_TUTORIAL, null, 6);
        // 推荐营养师
        List<Map<String, Object>> recommendYys = yysService.recommendYys();
        // 查询4条教学视频
        List<Map<String, Object>> video = videoMapper.getVideoLs(0, 4, ConstEnum.VIDEO_TYPE_TUTORIAL.getValue(), null);
        // 查询3条食谱文章
        List<Map<String, Object>> recipe = articleMapper.getArticles(0, 3, ConstEnum.ARTICLE_TAG_RECIPE.getValue());
        // 查询5条客户案例
        List<Map<String, Object>> cases = videoMapper.getVideoLs(0, 5, ConstEnum.VIDEO_TYPE_CASE.getValue(), null);

        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("banner", banner);
        resultMap.put("recommendYys", recommendYys);
        resultMap.put("video", video);
        resultMap.put("recipe", recipe);
        resultMap.put("cases", cases);
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


}
