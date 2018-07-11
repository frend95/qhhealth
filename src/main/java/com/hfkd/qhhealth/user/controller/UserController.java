package com.hfkd.qhhealth.user.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.util.DateUtil;
import com.hfkd.qhhealth.common.util.DigestUtil;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.health.model.HealthGoal;
import com.hfkd.qhhealth.social.model.SocialUserInfo;
import com.hfkd.qhhealth.user.model.User;
import com.hfkd.qhhealth.user.model.UserSession;
import com.hfkd.qhhealth.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 用户信息 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private UserService userService;

    @LogOut("更新用户")
    @RequestMapping("/update")
    public Map<String, Object> update(Integer id, String name, String gender, Integer height, BigDecimal weight,
                                      String birthday) {
        name = "".equals(name) ? null : name;
        gender = "".equals(gender) ? null : gender;
        birthday = "".equals(birthday) ? null : birthday;

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setGender(gender);
        user.setHeight(height);
        user.setWeight(weight);
        user.setBirthday(birthday);
        userService.updateById(user);
        return RspUtil.ok();
    }

    @LogOut("新用户完善信息")
    @RequestMapping("/completeInfo")
    public Map<String, Object> completeInfo(String birthday, BigDecimal weight, Integer height, String gender,
                                            String goalType, BigDecimal goalWeight, Integer period) {
        UserSession currUser = session.getCurrUser();
        Integer id = currUser.getId();
        String name = currUser.getName();

        // 用户基本资料
        User user = new User();
        user.setId(id);
        user.setBirthday(birthday);
        user.setWeight(weight);
        user.setHeight(height);
        user.setGender(gender);
        user.setStatus(ConstEnum.USER_STATUS_ENABLE.getValue());

        // 用户减肥目标
        HealthGoal healthGoal = new HealthGoal();
        healthGoal.setUserId(id);
        healthGoal.setGoalType(goalType);
        healthGoal.setGoalWeight(goalWeight);
        healthGoal.setPeriod(period);
        healthGoal.setStartTime(DateUtil.yyyyMMdd());

        // 初始化用户社圈信息
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        socialUserInfo.setUserId(id);
        socialUserInfo.setName(name);

        userService.completeInfo(user, healthGoal, socialUserInfo);
        // 查询用户详情
        Map<String, Object> userDetail = userService.getUserDetail(id);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", userDetail);
        return resultMap;
    }

    @LogOut("更新密码")
    @RequestMapping("/updatePwd")
    public Map<String, Object> updatePwd(String oldPassword, String newPassword) {
        if (StringUtils.isBlank(newPassword) || StringUtils.isBlank(oldPassword)) {
            return RspUtil.error("信息不完整");
        }
        if (newPassword.length() < 6) {
            return RspUtil.error("密码不能小于6位");
        }
        if (oldPassword.equals(newPassword)) {
            return RspUtil.error("新旧密码不能相同");
        }
        Integer userId = session.getCurrId();
        User user = userService.selectById(userId);
        String oldPwdSaltDb = user.getPassword();
        // 旧密码md5
        String oldPwdMd5 = DigestUtil.md5(oldPassword);
        // 盐为添加用户时随机生成的uuid
        String salt = user.getSalt();
        // 使用自定算法计算加盐后的的旧密码
        String oldPwdSalt = DigestUtil.pwdSalt(oldPwdMd5, salt);
        // 校验旧密码是否正确
        if (!oldPwdSaltDb.equals(oldPwdSalt)) {
            return RspUtil.error("旧密码不正确");
        }
        // 计算新密码md5
        String newPwdMd5 = DigestUtil.md5(newPassword);
        String newPwdSalt = DigestUtil.pwdSalt(newPwdMd5, salt);
        user.setPassword(newPwdSalt);
        userService.updateById(user);
        return RspUtil.ok();
    }

}
