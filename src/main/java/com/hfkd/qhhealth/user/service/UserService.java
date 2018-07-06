package com.hfkd.qhhealth.user.service;

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
}
