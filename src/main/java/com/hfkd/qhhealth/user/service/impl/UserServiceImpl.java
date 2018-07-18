package com.hfkd.qhhealth.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.util.DateUtil;
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

import java.util.Map;

/**
 * 用户信息 ServiceImpl
 *
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private HealthGoalService healthGoalService;
    @Autowired
    private SocialUserInfoService socialUserInfoService;
    @Autowired
    private UserMapper userMapper;

    private static final String FEMALE_NORMAL = "https://app.xintianhong888.com/img/female_1.png";
    private static final String FEMALE_FAT = "https://app.xintianhong888.com/img/female_2.png";
    private static final String FEMALE_VERYFAT = "https://app.xintianhong888.com/img/female_3.png";
    private static final String MALE_NORMAL = "https://app.xintianhong888.com/img/male_1.png";
    private static final String MALE_FAT = "https://app.xintianhong888.com/img/male_2.png";
    private static final String MALE_VERYFAT = "https://app.xintianhong888.com/img/male_3.png";

    @Override
    public User getByAccount(String account) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("account", account);
        return selectOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void completeInfo(User user, HealthGoal healthGoal, SocialUserInfo socialUserInfo) {
        updateById(user);
        HealthGoal goal = healthGoalService.selectById(healthGoal.getUserId());
        if (goal == null) {
            healthGoalService.insert(healthGoal);
            socialUserInfoService.insert(socialUserInfo);
        } else {
            healthGoalService.updateAllColumnById(healthGoal);
        }

    }

    @Override
    public Map<String, Object> getUserDetail(Integer id) {
        Map<String, Object> userDetail = userMapper.getUserDetail(id);
        Long age = null;
        Long remainDays = null;
        String birthday = (String) userDetail.get("birthday");
        String reachTime = null;
        if (birthday != null) {
            // 计算年龄
            age = DateUtil.divideDate(DateUtil.yyyyMMddHasLine(), birthday, "yyyy-MM-dd") / 365;
        }

        String startTime = (String) userDetail.get("startTime");
        if (startTime != null) {
            // 计算减肥剩余天数
            Integer period = (Integer) userDetail.get("period");
            reachTime = DateUtil.addDay(startTime, period);
            remainDays = DateUtil.divideDate(reachTime, DateUtil.yyyyMMdd());
            remainDays = remainDays < 0 ? 0 : remainDays;
        }

        // todo 计算bmi并匹配相应图片
        String bodyImg = null;
        String gender = (String) userDetail.get("gender");
        gender = gender == null ? ConstVal.USER_GENDER_FEMALE : gender;
        switch (gender) {
            case ConstVal.USER_GENDER_MALE:
                bodyImg = MALE_NORMAL;
                break;
            case ConstVal.USER_GENDER_FEMALE:
                bodyImg = FEMALE_NORMAL;
                break;
            default:
        }

        userDetail.put("bodyImg", bodyImg);
        userDetail.put("age", age);
        userDetail.put("remainDays", remainDays);
        userDetail.put("reachTime", reachTime);
        return userDetail;
    }
}
