package com.hexq.qh.healthlog.mapper;

import com.hexq.qh.healthlog.model.HealthLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客户健康信息记录 Mapper
 * @author hexq
 * @date 2018-06-09
 */
public interface HealthLogMapper extends BaseMapper<HealthLog> {

    /**
     * 获取用户体重记录
     * @param id 客户id
     * @param startTime 开始时间yyyyMMdd
     * @param endTime 结束时间yyyyMMdd
     * @return weight, date
     */
    List<Map<String, Object>> getWeightLog(@Param("id") String id,
                                           @Param("startTime") String startTime,
                                           @Param("endTime") String endTime);

    /**
     * 获取该客户累计减重
     * @param id 客户id
     * @return 累计减重
     */
    Integer getWeightLoss(@Param("id") String id);

    /**
     * 添加或更新累计减重记录
     * @param customerId 客户id
     * @param userId 营养师id
     * @param weightLoss 减重记录
     * @return boolean
     */
    boolean addWeightLoss(@Param("customerId") String customerId,
                          @Param("userId") String userId,
                          @Param("weightLoss") Integer weightLoss);

    /**
     * 获取最近的体重
     * @param id 客户id
     * @return 体重
     */
    Integer getRecentWeight(@Param("id") String id);

    /**
     * 获取最初的体重
     * @param id 客户id
     * @return 体重
     */
    Integer getInitialWeight(@Param("id") String id);

    /**
     * 添加或更新累计减重记录
     * @param customerId 客户id
     * @param userId 营养师id
     * @param initialWeight 初始体重
     * @return boolean
     */
    boolean addInitialWeight(@Param("customerId") String customerId,
                          @Param("userId") String userId,
                          @Param("initialWeight") Integer initialWeight);
}