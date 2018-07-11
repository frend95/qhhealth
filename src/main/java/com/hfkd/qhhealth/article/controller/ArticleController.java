package com.hfkd.qhhealth.article.controller;


import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.article.model.Article;
import com.hfkd.qhhealth.article.service.ArticleService;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.RspUtil;
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
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleService articleService;

    @LogOut("查询文章列表")
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page, Integer size, String tag) {
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> articles = articleMapper.getArticles(page, size, tag);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", articles);
        return resultMap;
    }

    @LogOut("查询文章详情")
    @RequestMapping("/detail")
    public Map<String, Object> detail(Integer id) {
        Article article = articleService.selectById(id);
        // 观看数加一
        articleMapper.watchedCntPlusOne(id);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", article);
        return resultMap;
    }

}
