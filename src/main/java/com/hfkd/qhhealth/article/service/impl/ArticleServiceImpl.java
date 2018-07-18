package com.hfkd.qhhealth.article.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.article.model.Article;
import com.hfkd.qhhealth.article.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章 ServiceImpl
 * @author hexq
 * @date 2018/7/4 18:21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
