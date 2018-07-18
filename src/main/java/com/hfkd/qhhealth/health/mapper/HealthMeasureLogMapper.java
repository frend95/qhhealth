package com.hfkd.qhhealth.health.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.health.model.HealthMeasureLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 健康数据记录 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface HealthMeasureLogMapper extends BaseMapper<HealthMeasureLog> {

    HealthMeasureLog getRecentLog(@Param("userId") Integer userId);

    void updWeight(@Param("logId") Integer logId,
                   @Param("weight") BigDecimal weight);
}