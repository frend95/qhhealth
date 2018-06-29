package com.hexq.qh.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hexq.qh.user.mapper.UserMapper;
import com.hexq.qh.user.model.User;
import com.hexq.qh.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户 ServiceImpl
 * @author hexq
 * @date 2018-06-05
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
