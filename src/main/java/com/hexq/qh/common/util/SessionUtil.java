package com.hexq.qh.common.util;

import com.hexq.qh.user.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;

/**
 * @author hexq
 * @date 2018/6/5 12:37
 */
public class SessionUtil {

    /**
     * 获取当前用户
     * todo 建议只用于获取id与账号，用户其他信息可能会改变
     * @return User
     */
    public static User getCurrUser() throws Exception {
        RedisConnection conn = null;
        try {
            conn = RedisUtil.getConnection();
            String tokenKey = "TOKEN_" + Thread.currentThread().getId();
            String token = new String(conn.get(tokenKey.getBytes()));
            String sessionKey = "SESSION_" + token;
            byte[] bytes = conn.get(sessionKey.getBytes());
            return (User) SerializeUtil.deserialization(bytes);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getCurrId() throws Exception {
        return getCurrUser().getId();
    }
}
