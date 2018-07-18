package com.hfkd.qhhealth.common.util;

import com.alibaba.fastjson.JSON;
import com.hfkd.qhhealth.user.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author hexq
 * @date 2018/6/5 12:37
 */
@Component
public class SessionUtil {

    @Autowired
    private StringRedisTemplate redis;

    /**
     * 获取当前用户
     * @return UserSession
     */
    public UserSession getCurrUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userInfo = (String) request.getAttribute("user_info");
        return JSON.parseObject(userInfo, UserSession.class);
    }

    /**
     * 获取当前用户id
     * @return 用户id
     */
    public Integer getCurrId() {
        UserSession userSession = getCurrUser();
        return userSession == null ? null : userSession.getId();
    }

    /**
     * 设置session
     * @param userSession userSession
     * @param sessionKey sessionKey
     * @param ttl 过期时间
     */
    @Deprecated
    public void setSession(UserSession userSession, String sessionKey, long ttl) {
        redis.opsForValue().set(sessionKey, JSON.toJSONString(userSession), ttl, TimeUnit.SECONDS);
    }

    public String setUniqueSession(UserSession userSession, String sessionKey, long ttl) {
        String token = DigestUtil.md5(String.valueOf(System.currentTimeMillis()));
        // session_id为key，存入user信息与token，并设置session过期时间，token为当前时间戳的hash
        Map<String, String> map = new HashMap<>(4);
        map.put("token", token);
        map.put("user", JSON.toJSONString(userSession));
        redis.opsForHash().putAll(sessionKey, map);
        redis.expire(sessionKey, ttl, TimeUnit.SECONDS);
        return userSession.getId() + "_" + token;
    }
}
