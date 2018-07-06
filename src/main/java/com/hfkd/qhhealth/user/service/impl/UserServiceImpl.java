package com.hfkd.qhhealth.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.user.mapper.UserMapper;
import com.hfkd.qhhealth.user.model.User;
import com.hfkd.qhhealth.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息 ServiceImpl
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    @Override
    public User getByAccount(String account) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("account", account);
        return selectOne(wrapper);
    }
}
