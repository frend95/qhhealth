package com.hfkd.qhhealth.social.service;

import com.hfkd.qhhealth.social.model.SocialUserInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * 用户社交信息 Service
 * @author hexq
 * @date 2018/7/5 10:12
 */
public interface SocialUserInfoService extends IService<SocialUserInfo> {

    /**
     * 更新用户社交信息的用户昵称
     * @param userId 用户id
     * @param name 昵称
     * @param avatar 头像
     */
    void updSocialName(Integer userId, String name, String avatar);
}
