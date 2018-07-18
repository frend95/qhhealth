package com.hfkd.qhhealth.health.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.health.model.HealthPlanItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 饮食计划食物项 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface HealthPlanItemMapper extends BaseMapper<HealthPlanItem> {

    List<HealthPlanItem> getFoods(@Param("page") Integer page,
                                  @Param("size") Integer size,
                                  @Param("sort") String sort,
                                  @Param("name") String name);
}