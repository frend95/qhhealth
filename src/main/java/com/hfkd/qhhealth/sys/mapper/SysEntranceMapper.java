package com.hfkd.qhhealth.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.sys.model.SysEntrance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 内容入口配置 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface SysEntranceMapper extends BaseMapper<SysEntrance> {

    /**
     * 获取入口列表
     * @param page 页面类别
     * @return List<Map{ name,tag,img }>
     */
    List<Map<String, Object>> getEntrance(@Param("page") String page);

}