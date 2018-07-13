package com.hfkd.qhhealth.article.controller;


import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.article.model.Article;
import com.hfkd.qhhealth.article.service.ArticleService;
import com.hfkd.qhhealth.comment.model.Comment;
import com.hfkd.qhhealth.comment.service.CommentService;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstEnum;
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
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserArticleCollectionMapper articleCollectionMapper;

    @LogOut("查询文章列表")
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page, Integer size, String tag) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> articles = articleMapper.getArticles(page, size, tag);
        return RspUtil.ok(articles);
    }

    @LogOut("查询文章详情")
    @Verify(hasSession = true)
    @RequestMapping("/detail")
    public Map<String, Object> detail(Integer id) {
        Integer currId = session.getCurrId();
        Article article = articleService.selectById(id);
        // 查询最近的10条评论
        List<Comment> comments = commentService.getFullCmt(ConstEnum.CONTENT_TYPE_ARTICLE.getValue(), 0, 10, id);
        article.setComments(comments);
        // 查询是否收藏
        Boolean isCollect = currId != null && articleCollectionMapper.getClctId(currId, id) != null;
        article.setCollect(isCollect);
        // 观看数加一
        articleMapper.watchedCntPlusOne(id);
        return RspUtil.ok(article);
    }

}
