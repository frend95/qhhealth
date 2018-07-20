package com.hfkd.qhhealth.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.sys.model.SysInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统信息 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface SysInfoMapper extends BaseMapper<SysInfo> {

    /**
     * 根据id获取系统信息
     * @param id 信息项id
     * @return SysInfo{ name,variable }
     */
    SysInfo getInfo(@Param("id") Integer id);
}