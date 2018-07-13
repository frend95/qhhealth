package com.hfkd.qhhealth.user.mapper;

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
public interface UserCollectionMapper {

    List<Map<String, Object>> getCollection(@Param("type") String type,
                                            @Param("page") Integer page,
                                            @Param("size") Integer size,
                                            @Param("userId") Integer userId);

    Integer getClctId(@Param("type") String type,
                      @Param("userId") Integer userId,
                      @Param("contentId") Integer contentId);

    void addCollection(@Param("type") String type,
                       @Param("userId") Integer userId,
                       @Param("contentId") Integer contentId,
                       @Param("title") String title,
                       @Param("thumb") String thumb);

    void delCollection(@Param("type") String type,
                       @Param("clctId") Integer clctId);
}