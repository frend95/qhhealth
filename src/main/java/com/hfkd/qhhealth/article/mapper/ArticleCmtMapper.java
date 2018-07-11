package com.hfkd.qhhealth.article.mapper;

import com.hfkd.qhhealth.article.model.ArticleCmt;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章评论 Mapper
 * @author hexq
 * @date 2018/7/4 18:21
 */
@Mapper
public interface ArticleCmtMapper extends BaseMapper<ArticleCmt> {

    List<Map<String,Object>> getComments(@Param("page") Integer page,
                                         @Param("size") Integer size,
                                         @Param("articleId") Integer articleId);

    void replyCntPlusOne(@Param("id") Integer id);
}