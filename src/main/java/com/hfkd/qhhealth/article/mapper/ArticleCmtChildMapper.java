package com.hfkd.qhhealth.article.mapper;

import com.hfkd.qhhealth.article.model.ArticleCmtChild;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章子评论 Mapper
 * @author hexq
 * @date 2018/7/4 18:21
 */
@Mapper
public interface ArticleCmtChildMapper extends BaseMapper<ArticleCmtChild> {

    List<Map<String,Object>> getChildCmt(@Param("page") Integer page,
                                         @Param("size") Integer size,
                                         @Param("cmtId") Integer cmtId);
}