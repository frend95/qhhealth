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

    /**
     * 查询文章列表
     * @param page 页码
     * @param size limit
     * @param tag 标签
     * @return List<Map{ id,title,desc,thumb,watchedCnt,createTime }>
     */
    List<Map<String,Object>> getArticles(@Param("page") Integer page,
                                         @Param("size") Integer size,
                                         @Param("tag") String tag);

    /**
     * 查询简要文章信息
     * @param id 文章id
     * @return Map{ title,thumb }
     */
    Map<String, Object> getArticleBrief(@Param("id") Integer id);

    /**
     * 文章观看次数加一
     * @param id 文章id
     */
    void watchedCntPlusOne(@Param("id") Integer id);
}