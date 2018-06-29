package com.hexq.qh.user.mapper;

import com.hexq.qh.user.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户 Mapper
 * @author hexq
 * @date 2018-06-05
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户的媒体信息
     * @param id 用户id
     * @param type 媒体类型：0图片，1视频
     * @param page 页码
     * @param size 大小
     * @return media, type, createTime
     */
    List<Map<String, Object>> getMedia(@Param("id") String id,
                                       @Param("type") String type,
                                       @Param("page") Integer page,
                                       @Param("size") Integer size);

    /**
     * 添加用户媒体信息
     * @param id 用户id
     * @param media 媒体文件名
     * @param type 媒体类型：0图片，1视频
     * @param title 标题
     * @param desc 描述
     * @param thumb 小图
     * @return boolean
     */
    boolean addMedia(@Param("id") String id,
                     @Param("media") String media,
                     @Param("type") String type,
                     @Param("title") String title,
                     @Param("desc") String desc,
                     @Param("thumb") String thumb);

    /**
     * 媒体观看次数加一
     * @param id 媒体id
     * @return boolean
     */
    boolean watchAddOne(@Param("id") String id);

    /**
     * 获取改营养师下的客户总计减重
     * @param id 用户id
     * @return 客户总计减重
     */
    Integer getTotalWeightLoss(@Param("id") String id);

    /**
     * 该用户名下VIP客户加一
     * @param id 用户id
     * @return boolean
     */
    boolean vipAddOne(@Param("id") String id);

    /**
     * 该用户名下VIP客户减一
     * @param id 用户id
     * @return boolean
     */
    boolean vipSubOne(@Param("id") String id);

    /**
     * 该用户名下客户加一
     * @param id 用户id
     * @return boolean
     */
    boolean customerAddOne(@Param("id") String id);

    /**
     * 该用户名下客户减一
     * @param id 用户id
     * @return boolean
     */
    boolean customerSubOne(@Param("id") String id);

    /**
     * 获取用户列表
     * @param page 页码
     * @param size 大小
     * @return name, wechat, avatar, desc, customNum
     */
    List<Map<String, Object>> getUserList(@Param("page") Integer page,
                                          @Param("size") Integer size);
    /**
     * 获取用户预览
     * @param id 用户id
     * @return name, wechat, avatar, desc, customNum
     */
    Map<String, Object> getUserPreview(@Param("id") String id);


}