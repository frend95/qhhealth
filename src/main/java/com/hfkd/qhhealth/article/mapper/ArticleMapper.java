package com.hfkd.qhhealth.article.mapper;

import com.hfkd.qhhealth.article.model.Article;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章 Mapper
 * @author hexq
 * @date 2018/7/4 18:21
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Map<String,Object>> getArticles(@Param("page") Integer page,
                                         @Param("size") Integer size,
                                         @Param("tag") String tag);

    Map<String, Object> getArticleBrief(@Param("id") Integer id);

    void watchedCntPlusOne(@Param("id") Integer id);

    void cmtCntPlusOne(@Param("id") Integer id);
}