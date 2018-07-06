package com.hfkd.qhhealth.sys.controller;

import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.util.*;
import com.hfkd.qhhealth.user.model.User;
import com.hfkd.qhhealth.user.model.UserSession;
import com.hfkd.qhhealth.user.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录模块
 * @author hexq
 * @date 2017/11/14 10:13
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController {

    @Autowired
    private SessionUtil sessionUtil;
    @Autowired
    private StringRedisTemplate redis;
    @Autowired
    private UserService userService;

    @Value("${login.prefix}")
    private String prefix;
    @Value("${login.timeout}")
    private long timeout;
    @Value("${user.home}${file.path.avatar}")
//    @Value("${file.path.avatar}")
    private String avatarPath;
    @Value("${domain.avatar}")
    private String avatarDomain;
    @Value("${sms.timeout}")
    private long smsTimeout;

    @LogOut("登陆")
	@RequestMapping("/login")
	public Map<String, Object> login(@RequestBody Map<String, String> params) {
        Map<String, Object> errorMsg = RspUtil.error("登陆信息错误");
        String account = params.get("account");
		String pwdMd5 = params.get("password");
        if (StringUtils.isBlank(account) || StringUtils.isBlank(pwdMd5)) {
            return errorMsg;
        }
        User user = userService.getByAccount(account);
        if (user == null) {
            return errorMsg;
        }
        Integer id = user.getId();
        String pwdSaltDb = user.getPassword();
        String salt = user.getSalt();
        // 将前端传来的密码加盐后与数据库相比较
        String pwdSalt = DigestUtil.pwdSalt(pwdMd5, salt);
        if (!pwdSaltDb.equals(pwdSalt)) {
            return errorMsg;
        }
        // 生成uuid的session key（改为单设备登陆）
        /*String token = UUIDUtil.getUUid();
        String sessionKey = prefix + token;*/
        String sessionKey = prefix + id;
        // 设置session
        UserSession userSession = new UserSession();
        userSession.setId(id);
        userSession.setAccount(user.getAccount());
        userSession.setName(user.getName());
        userSession.setNutritionistId(user.getNutritionistId());
        // 将用户session放入redis并设置ttl
        String token = sessionUtil.setUniqueSession(userSession, sessionKey, timeout);

        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", userSession);
        resultMap.put("token", token);
        return resultMap;
	}

    @LogOut("验证登陆状态")
    @RequestMapping("/verify")
    public Map<String, Object> verify(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            return RspUtil.error();
        }
        String[] split = token.split("_");
        String sessionKey = prefix + split[0];
        String oriToken = split[1];
        // 取出并重新设置session ttl（改为单设备登陆）
        //String sessionStr = RedisUtil.getEx(sessionKey, timeout);
        String oriTokenDb = (String) redis.opsForHash().get(sessionKey, "token");
        if (StringUtils.isBlank(oriTokenDb) || !oriToken.equals(oriTokenDb)) {
            return RspUtil.unauthorized("登陆状态过期，请重新登陆");
        }
        redis.expire(sessionKey, timeout, TimeUnit.SECONDS);
        return RspUtil.ok();
    }

    @LogOut("用户注册")
    @RequestMapping("/register")
    public Map<String, Object> register(@RequestParam("avatar")MultipartFile avatar,
                                        @RequestParam("phone")String phone,
                                        @RequestParam("password")String password,
                                        @RequestParam("code")String code) throws IOException {
        // 验证短信验证码
        if (!redis.hasKey(phone + code)) {
            return RspUtil.error("验证码错误");
        }
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
            return RspUtil.error("信息不完整");
        }
        if (password.length() < 6) {
            return RspUtil.error("密码不能小于6位");
        }
        // 查询手机号是否已注册
        User userDb = userService.getByAccount(phone);
        if (userDb != null) {
            return RspUtil.error("该号码已被注册");
        }

        String pwdMd5 = DigestUtil.md5(password);
        String salt = UUIDUtil.getUUid();
        String pwdSalt = DigestUtil.pwdSalt(pwdMd5, salt);
        String filename = FileUtil.upload(avatar, avatarPath, true, false);
        String avatarUrl = avatarDomain + filename;

        User user = new User();
        user.setAccount(phone);
        user.setPassword(pwdSalt);
        user.setSalt(salt);
        user.setAvatar(avatarUrl);
        user.setName("Qh_" + RandomStringUtils.randomAlphanumeric(4));
        userService.insert(user);

        Integer id = user.getId();
        UserSession userSession = new UserSession();
        userSession.setId(id);
        userSession.setAccount(phone);
        // 生成uuid的session key（改为单设备登陆）
        /*String token = UUIDUtil.getUUid();
        String sessionKey = prefix + token;*/
        String sessionKey = prefix + id;
        String token = sessionUtil.setUniqueSession(userSession, sessionKey, timeout);

        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("token", token);
        return resultMap;
    }

    @LogOut("获取验证码")
    @RequestMapping("/getCode")
    public Map<String, Object> getCode(@RequestBody Map<String, String> params) {
        String phone = params.get("phone");
        if (StringUtils.isBlank(phone)) {
            return RspUtil.error();
        }
        String code = RandomUtil.getRandom();
        String resp = SmsUtil.send(phone, code);
        if (!"0".equals(resp)) {
            return RspUtil.error("发送失败，错误码：" + resp);
        }
        // 放入redis并设置ttl
        String codeKey = phone + code;
        redis.opsForValue().set(codeKey, "", smsTimeout, TimeUnit.MINUTES);
        return RspUtil.ok();
    }

}
