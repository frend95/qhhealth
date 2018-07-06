package com.hfkd.qhhealth.common.util;

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
 * @date 2017/11/14 10:13
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
    public static void set(String key, String value) {
        RedisConnection connection = null;
        try {
            connection = getConnection();
            connection.set(key.getBytes(), value.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
    public static void setEx(String key, String value, long ttl) {
        RedisConnection connection = null;
        try {
            connection = getConnection();
            connection.setEx(key.getBytes(), ttl, value.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
     * 检查给定 key 是否存在
     * @param key 键
     * @return true/false
     */
    public static boolean exists(String key) {
        RedisConnection connection = null;
        try {
            connection = getConnection();
            return connection.exists(key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
    public static String get(String key) {
        RedisConnection connection = null;
        try {
            connection = getConnection();
            byte[] bytes = connection.get(key.getBytes());
            if (ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
     * redis中取数据并设置ttl
     * @param key 键
     */
    public static String getEx(String key, long ttl) {
        RedisConnection connection = null;
        String value;
        try {
            connection = getConnection();
            byte[] keyBytes = key.getBytes();
            byte[] bytes = connection.get(keyBytes);
            if (ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            value = new String(bytes);
            connection.expire(keyBytes, ttl);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public static void hSetEx(String key1, String key2, String value, long ttl) {
        RedisConnection connection = null;
        try {
            connection = getConnection();
            byte[] keyBytes1 = key1.getBytes();
            byte[] keyBytes2 = key2.getBytes();
            connection.hSet(keyBytes1, keyBytes2, value.getBytes());
            connection.expire(keyBytes1, ttl);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public static String hGetEx(String key1, String key2, long ttl) {
        RedisConnection connection = null;
        String value;
        try {
            connection = getConnection();
            byte[] keyBytes1 = key1.getBytes();
            byte[] keyBytes2 = key2.getBytes();
            byte[] bytes = connection.hGet(keyBytes1, keyBytes2);
            if (ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            value = new String(bytes);
            connection.expire(keyBytes1, ttl);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
            throw e;
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
    public static String getAndDel(String key) {
        byte[] keyBytes = key.getBytes();
        RedisConnection connection = null;
        String value;
        try {
            connection = getConnection();
            //取出数据
            byte[] bytes = connection.get(keyBytes);
            //如果redis 有数据则删除
            if (ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            connection.del(keyBytes);
            value = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
