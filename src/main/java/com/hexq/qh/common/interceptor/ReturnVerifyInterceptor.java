package com.hexq.qh.common.interceptor;

import com.hexq.qh.common.util.RedisUtil;
import com.hexq.qh.common.util.SerializeUtil;
import com.hexq.qh.config.CacheAopConfig;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 检查从切面{@link CacheAopConfig#saveCacheName}
 * 返回信息并删除被spring缓存的错误数据
 * todo 考虑至全局异常管理里处理错误数据{@link com.hexq.qh.config.GlobalExceptionHandler}
 * @author hexq
 * @date 2017/11/20  17:02
 */
//@Component
public class ReturnVerifyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //当前线程缓存的Key
        String key = "IncorrectResult_" + Thread.currentThread().getId();
        RedisConnection connection = null;
        try {
            connection = RedisUtil.getConnection();
            byte[] keyBytes = key.getBytes();
            byte[] bytes = connection.get(keyBytes);
            //如果Redis里有值，说明缓存了错误的返回信息
            if (ArrayUtils.isNotEmpty(bytes)) {
                connection.del(keyBytes);
                String cacheName = (String) SerializeUtil.deserialization(bytes);
                //删除缓存
                String prefix = cacheName + "*";
                Set<byte[]> keys = connection.keys(prefix.getBytes());
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
        super.afterCompletion(request, response, handler, ex);
    }
}