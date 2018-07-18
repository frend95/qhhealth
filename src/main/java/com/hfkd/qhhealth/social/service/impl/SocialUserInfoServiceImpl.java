package com.hfkd.qhhealth.social.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.social.mapper.SocialUserInfoMapper;
import com.hfkd.qhhealth.social.model.SocialUserInfo;
import com.hfkd.qhhealth.social.service.SocialUserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户社交信息 ServiceImpl
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class SocialUserInfoServiceImpl extends ServiceImpl<SocialUserInfoMapper, SocialUserInfo> implements SocialUserInfoService {

    @Override
    public void updSocialName(Integer userId, String name, String avatar) {
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setUserId(userId);
        userInfo.setName(name);
        userInfo.setAvatar(avatar);
        updateById(userInfo);
    }
}
