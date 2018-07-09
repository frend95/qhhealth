package com.hfkd.qhhealth.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.health.model.HealthGoal;
import com.hfkd.qhhealth.health.service.HealthGoalService;
import com.hfkd.qhhealth.social.model.SocialUserInfo;
import com.hfkd.qhhealth.social.service.SocialUserInfoService;
import com.hfkd.qhhealth.user.mapper.UserMapper;
import com.hfkd.qhhealth.user.model.User;
import com.hfkd.qhhealth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户信息 ServiceImpl
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private HealthGoalService healthGoalService;
    @Autowired
    private SocialUserInfoService socialUserInfoService;
    
    @Override
    public User getByAccount(String account) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("account", account);
        return selectOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void completeInfo(User user, HealthGoal healthGoal, SocialUserInfo socialUserInfo) {
        insert(user);
        healthGoalService.insert(healthGoal);
        socialUserInfoService.insert(socialUserInfo);
    }
}
