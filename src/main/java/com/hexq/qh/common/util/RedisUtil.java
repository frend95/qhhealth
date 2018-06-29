package com.hexq.qh.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Redis工具类
 * @author hexq
 * @date 2017/11/14  10:13
 */
@Component
public class RedisUtil {
    private static RedisUtil redisUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CacheManager cacheManager;
    @PostConstruct
    public void init() {
        redisUtil = this;
    }


    /**
     * 数据插入redis
     * @param key 键
     * @param value 值
     */
    public static void set(String key, Object value) {
        RedisConnection connection = null;
        try {
            connection = getConnection();
            connection.set(key.getBytes(), SerializeUtil.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 数据插入redis, 并设置存活时间
     * @param key  键
     * @param value 值
     * @param ttl 存活时间
     */
    public static void setEx(String key, Object value, long ttl) {
        RedisConnection connection = null;
        try {
            connection = getConnection();
            connection.setEx(key.getBytes(), ttl, SerializeUtil.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * redis中取数据
     * @param key 键
     */
    public static Object get(String key) {
        RedisConnection connection = null;
        Object value = null;
        try {
            connection = getConnection();
            byte[] bytes = connection.get(key.getBytes());
            if (ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            value = SerializeUtil.deserialization(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * redis中取数据并设置ttl
     * @param key 键
     */
    public static Object getEx(String key, long ttl) {
        RedisConnection connection = null;
        Object value = null;
        try {
            connection = getConnection();
            byte[] keyBytes = key.getBytes();
            byte[] bytes = connection.get(keyBytes);
            if (ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            value = SerializeUtil.deserialization(bytes);
            connection.expire(keyBytes, ttl);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 从redis删除数据
     * @param key 键
     */
    public static void del(String key) {
        RedisConnection connection = null;
        try {
            connection = getConnection();
            connection.del(key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * redis中取出数据然后删除数据
     * @param key 键
     */
    public static Object getAndDel(String key) {
        byte[] keyBytes = key.getBytes();
        RedisConnection connection = null;
        Object value = null;
        try {
            connection = getConnection();
            //取出数据
            byte[] bytes = connection.get(keyBytes);
            //如果redis 有数据则删除
            if (ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            connection.del(keyBytes);
            value = SerializeUtil.deserialization(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 获取连接
     * @return RedisConnection
     */
    public static RedisConnection getConnection() {
        return redisUtil.redisTemplate.getConnectionFactory().getConnection();
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
        RedisConnection connection = null;
        try {
            connection = getConnection();
            for (String str : prefix) {
                str = str + "*";
                Set<byte[]> keys = connection.keys(str.getBytes());
                for (byte[] keyStr : keys) {
                    connection.del(keyStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
