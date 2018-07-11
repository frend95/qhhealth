package com.hfkd.qhhealth.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.user.model.UserArticleCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户文章收藏 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface UserArticleCollectionMapper extends BaseMapper<UserArticleCollection> {

    List<Map<String, Object>> getCollection(@Param("page") Integer page,
                                            @Param("size") Integer size,
                                            @Param("userId") Integer userId);
}