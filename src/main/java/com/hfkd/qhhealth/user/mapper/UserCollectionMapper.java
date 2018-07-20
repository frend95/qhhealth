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

    /**
     * 获取收藏列表
     * @param type 收藏类型
     * @param page 页码
     * @param size limit
     * @param userId 用户id
     * @return List<Map{ contentId,title,thumb,createTime}>
     */
    List<Map<String, Object>> getCollection(@Param("type") String type,
                                            @Param("page") Integer page,
                                            @Param("size") Integer size,
                                            @Param("userId") Integer userId);

    /**
     * 获取收藏项id
     * @param type 收藏类型
     * @param userId 用户id
     * @param contentId 内容id
     * @return 收藏项id
     */
    Integer getClctId(@Param("type") String type,
                      @Param("userId") Integer userId,
                      @Param("contentId") Integer contentId);

    /**
     * 添加收藏
     * @param type 收藏类型
     * @param userId 用户id
     * @param contentId 内容id
     * @param title 标题
     * @param thumb 略缩图
     */
    void addCollection(@Param("type") String type,
                       @Param("userId") Integer userId,
                       @Param("contentId") Integer contentId,
                       @Param("title") String title,
                       @Param("thumb") String thumb);

    /**
     * 删除收藏
     * @param type 收藏类型
     * @param clctId 收藏项id
     */
    void delCollection(@Param("type") String type,
                       @Param("clctId") Integer clctId);
}