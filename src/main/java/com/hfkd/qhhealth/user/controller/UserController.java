package com.hfkd.qhhealth.user.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.util.*;
import com.hfkd.qhhealth.health.model.HealthGoal;
import com.hfkd.qhhealth.social.model.SocialUserInfo;
import com.hfkd.qhhealth.social.service.SocialUserInfoService;
import com.hfkd.qhhealth.user.model.User;
import com.hfkd.qhhealth.user.model.UserSession;
import com.hfkd.qhhealth.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private SocialUserInfoService socialUserInfoService;
    @Value("${file.path.avatar}")
    private String avatarPath;
    @Value("${domain.avatar}")
    private String avatarDomain;

    @LogOut("更新昵称")
    @RequestMapping("/updName")
    public Map<String, Object> updName(String name) {
        // 检查名称是否合法
        if (StringUtils.isBlank(name)
                || name.length() < 2
                || name.length() > 20
                || name.replaceAll("^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$", "").length() != 0) {
            return RspUtil.error("昵称不符合要求");
        }

        Integer currId = session.getCurrId();
        User user = new User();
        user.setId(currId);
        user.setName(name);
        userService.updateById(user);
        socialUserInfoService.updSocialName(currId, name, null);
        return RspUtil.ok();
    }

    @LogOut("更新头像")
    @RequestMapping("/updAvatar")
    public Map<String, Object> updAvatar(MultipartFile avatar) throws IOException {
        if (avatar == null || avatar.isEmpty()) {
            return RspUtil.error("头像不能为空");
        }
        // 上传并更新头像
        String filename = FileUtil.upload(avatar, avatarPath, true, false);
        String avatarUrl = avatarDomain + filename;

        Integer currId = session.getCurrId();
        User user = new User();
        user.setId(currId);
        user.setAvatar(avatarUrl);
        userService.updateById(user);
        socialUserInfoService.updSocialName(currId, null, avatarUrl);
        return RspUtil.ok(avatarUrl);
    }

    @LogOut("新用户完善信息")
    @RequestMapping("/completeInfo")
    public Map<String, Object> completeInfo(String birthday, BigDecimal weight, Integer height, String gender,
                                            String goalType, BigDecimal goalWeight, Integer period) {
        if (StringUtils.isBlank(birthday)
                || StringUtils.isBlank(gender)
                || StringUtils.isBlank(goalType)
                || weight == null
                || height == null
                || goalWeight == null
                || period == null) {
            return RspUtil.error("信息不完整");
        }

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
        user.setStatus(ConstVal.USER_STATUS_ENABLE);

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
        return RspUtil.ok(userDetail);
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
