package com.hfkd.qhhealth.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hfkd.qhhealth.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息 Mapper
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}