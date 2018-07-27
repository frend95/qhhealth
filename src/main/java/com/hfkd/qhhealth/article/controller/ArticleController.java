package com.hfkd.qhhealth.article.controller;


import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.comment.service.CommentService;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.user.mapper.UserArticleCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 文章 Controller
 * @author hexq
 * @date 2018/7/4 18:21
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserArticleCollectionMapper articleCollectionMapper;

    @LogOut("查询文章列表")
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page, Integer size, String tag) {
        String title = null;
        if (tag != null) {
            switch (tag) {
                case ConstVal.ARTICLE_TAG_DIET:
                    title = ConstEnum.ARTICLE_TAG_DIET.ename();
                    break;
                case ConstVal.ARTICLE_TAG_SPORT:
                    title = ConstEnum.ARTICLE_TAG_SPORT.ename();
                    break;
                case ConstVal.ARTICLE_TAG_METHOD:
                    title = ConstEnum.ARTICLE_TAG_METHOD.ename();
                    break;
                case ConstVal.ARTICLE_TAG_RECIPE:
                    title = ConstEnum.ARTICLE_TAG_RECIPE.ename();
                    break;
                default:
            }
        }
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;

        List<Map<String, Object>> articles = articleMapper.getArticles(page, size, tag);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", articles);
        resultMap.put("title", title);
        return resultMap;
    }

    @LogOut("查询文章详情")
    @Verify(hasSession = true)
    @RequestMapping("/detail")
    public Map<String, Object> detail(Integer id) {
        Integer currId = session.getCurrId();
        String url = articleMapper.getResourceById(id);
        // 查询是否收藏
        Boolean isCollect = currId != null && articleCollectionMapper.getClctId(currId, id) != null;
        // 观看数加一
        articleMapper.watchedCntPlusOne(id);

        Map<String, Object> rspMap = RspUtil.ok();
        rspMap.put("isCollect", isCollect);
        rspMap.put("resource", url);
        return rspMap;
    }

}
