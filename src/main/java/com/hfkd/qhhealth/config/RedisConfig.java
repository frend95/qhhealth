package com.hfkd.qhhealth.config;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Map;

/**
 * redis缓存配置
 * @author hexq
 * @date 2018/6/5 17:51
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 用spring标记接口时的key值设置
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getSimpleName());
            sb.append(".");
            sb.append(method.getName());
            for (Object obj : params) {
                if (ArrayUtils.isNotEmpty(params)) {
                    Map map = (Map) params[0];
                    map.remove("token");
                }
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /*@Bean
    public boolean initCache() {
        RedisUtil.bathDelByPref("CACHE_");
        log.info("缓存与登陆信息初始化...");
        return true;
    }*/

    /**
     * 缓存管理设置(缓存刷新时间)
     * @return CacheManager
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        CustomJsonRedisSerializer fastJsonRedisSerializer = new CustomJsonRedisSerializer();
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer))
                .entryTtl(Duration.ofDays(7));
    }

    //    @Bean
    //    public RedisCacheConfiguration redisCacheConfiguration() {
    //        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
    //        ObjectMapper objectMapper = new ObjectMapper();
    //        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    //        serializer.setObjectMapper(objectMapper);
    //        return RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
    //    }

    @SuppressWarnings("unchecked")
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        //jackson
        /*StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();*/

        //fastJson
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        //GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        CustomJsonRedisSerializer fastJsonRedisSerializer = new CustomJsonRedisSerializer();
        //设置默认的Serialize，包含 keySerializer & valueSerializer
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        //单独设置keySerializer
        //redisTemplate.setKeySerializer(fastJsonRedisSerializer);
        //单独设置valueSerializer
        //redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        return redisTemplate;
    }

}