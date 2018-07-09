package com.hfkd.qhhealth.user.service;

import com.hfkd.qhhealth.health.model.HealthGoal;
import com.hfkd.qhhealth.social.model.SocialUserInfo;
import com.hfkd.qhhealth.user.model.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * 用户信息 Service
 * @author hexq
 * @date 2018/7/5 10:12
 */
public interface UserService extends IService<User> {

    /**
     * 根据账户获取用户
     * @param account 账号
     * @return 用户
     */
    User getByAccount(String account);

    /**
     * 完善用户信息
     * @param user 用户
     * @param healthGoal 健康目标
     * @param socialUserInfo 用户社圈信息
     */
    void completeInfo(User user, HealthGoal healthGoal, SocialUserInfo socialUserInfo);
}
