package com.hexq.qh.sys.controller;

import com.hexq.qh.common.annotation.LogOut;
import com.hexq.qh.common.util.DigestUtil;
import com.hexq.qh.common.util.RedisUtil;
import com.hexq.qh.common.util.RspUtil;
import com.hexq.qh.common.util.UUIDUtil;
import com.hexq.qh.user.model.User;
import com.hexq.qh.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录模块
 * @author hexq
 * @date 2017/11/14  10:13
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController {

    @Autowired
    private UserService userService;

    @Value("${login.prefix}")
    private String prefix;
    @Value("${login.timeout}")
    private long timeout;

    @LogOut("登陆")
	@RequestMapping("/login")
	public Map<String, Object> login(@RequestBody Map<String, String> params) {
		Map<String, Object> resultMap = RspUtil.ok();
        Map<String, Object> errorMsg = RspUtil.unauthorized("登陆信息错误");
        String account = params.get("account");
		String pwdMd5 = params.get("password");
        if (StringUtils.isBlank(account) || StringUtils.isBlank(pwdMd5)) {
            return errorMsg;
        }
        User user = userService.getByAccount(account);
        if (user == null) {
            return errorMsg;
        }
        String pwdSaltDb = user.getPassword();
        String salt = user.getSalt();
        String pwdSalt = DigestUtil.pwdSalt(pwdMd5, salt);
        if (!pwdSaltDb.equals(pwdSalt)) {
            return errorMsg;
        }
        user.setPassword(null);
        user.setSalt(null);
        // 将用户信息放入redis
        String token = UUIDUtil.getUUid();
        String sessionKey = prefix + token;
        RedisUtil.setEx(sessionKey, user, timeout);
        resultMap.put("result", user);
        resultMap.put("token", token);
        return resultMap;
	}

    @LogOut("验证登陆状态")
    @RequestMapping("/verify")
    public Map<String, Object> verify(@RequestBody Map<String, String> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        Map<String, Object> errorMsg = RspUtil.unauthorized("登陆状态过期，请重新登陆");
        String token = params.get("token");
        if (StringUtils.isBlank(token)) {
            return errorMsg;
        }
        String sessionKey = prefix + token;
        // 取出并重新设置session ttl
        User user = (User) RedisUtil.getEx(sessionKey, timeout);
        if (user == null) {
            return errorMsg;
        }
        resultMap.put("result", user);
        return resultMap;
    }
}
