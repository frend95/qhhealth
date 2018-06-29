package com.hexq.qh.customer.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.hexq.qh.customer.model.Customer;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客户 Mapper
 * @author hexq
 * @date 2018-06-06
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 获取简要用户列表
     * @param name 用户姓名
     * @param status 状态：0无效，1未购买，2已购买
     * @param userId 营养师id
     * @param page 分页
     * @return id，avatar, star
     */
    List<Map<String, Object>> getConciseList(@Param("name") String name,
                                             @Param("status") String status,
                                             @Param("userId") String userId,
                                             Page<Customer> page);

    /**
     * 获取客户的媒体信息
     * @param id 客户id
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
     * 添加客户媒体信息
     * @param id 客户id
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
}