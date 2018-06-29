package com.hexq.qh.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Redis工具类
 * @author hexq
 * @date 2017/11/14  10:13
 */
//@Component
public class SimpleRedisUtil {
    private static SimpleRedisUtil redisUtil;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private CacheManager cacheManager;
    @PostConstruct
    public void init() {
        redisUtil = this;
    }

    private RedisConnection connection = null;

    /**
     * 数据插入redis
     * @param key 键
     * @param value 值
     */
    public static void set(String key, Object value) {
        try {
            getConnection();
            redisUtil.connection.set(key.getBytes(), SerializeUtil.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据插入redis, 并设置存活时间
     * @param key  键
     * @param value 值
     * @param ttl 存活时间
     */
    public static void expire(String key, Object value, long ttl) {
        try {
            getConnection();
            redisUtil.connection.setEx(key.getBytes(), ttl, SerializeUtil.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * redis中取数据
     * @param key 键
     */
    public static Object get(String key) {
        Object value = null;
        try {
            getConnection();
            byte[] bytes = redisUtil.connection.get(key.getBytes());
            if (ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            value = SerializeUtil.deserialization(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 从redis删除数据
     * @param key 键
     */
    public static void del(String key) {
        try {
            getConnection();
            redisUtil.connection.del(key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * redis中取出数据然后删除数据
     * @param key 键
     */
    public static Object getAndDel(String key) {
        byte[] keyBytes = key.getBytes();
        Object value = null;
        try {
            getConnection();
            //取出数据
            byte[] bytes = redisUtil.connection.get(keyBytes);
            //如果redis 有数据则删除
            if (ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            redisUtil.connection.del(keyBytes);
            value = SerializeUtil.deserialization(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取连接
     */
    private static void getConnection() {
        if (redisUtil.connection == null) {
            redisUtil.connection = redisUtil.redisTemplate.getConnectionFactory().getConnection();
        }
    }

    /**
     * 关闭连接
     */
    public static void close() {
        if (redisUtil.connection != null) {
            try {
                redisUtil.connection.close();
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 清除缓存
     * @param cacheName 缓存名称
     */
    public static void clearCache(String cacheName) {
        Cache cache = redisUtil.cacheManager.getCache(cacheName);
        cache.clear();
    }

    /**
     * 清理所有缓存
     */
    public static void clearAllCache() {
        bathDelByPref("CACHE");
    }

    /**
     * 批量删除前缀为 prefix的key值
     * @param prefix 缓存前缀
     */
    public static void bathDelByPref(String... prefix) {
        if (ArrayUtils.isEmpty(prefix)) {
            return;
        }
        try {
            getConnection();
            for (String str : prefix) {
                str = str + "*";
                Set<byte[]> keys = redisUtil.connection.keys(str.getBytes());
                for (byte[] keyStr : keys) {
                    redisUtil.connection.del(keyStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
