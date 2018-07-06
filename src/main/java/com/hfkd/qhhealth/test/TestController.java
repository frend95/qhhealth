package com.hfkd.qhhealth.test;

import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author hexq
 * @date 2018/6/5 17:53
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private StringRedisTemplate redis;
    @Autowired
    private SessionUtil sessionUtil;

    @RequestMapping("/1")
    @Verify
    public void test1(@RequestBody Map<String,Object> requestMap) {
        /*redis.opsForValue().set("test", "100",60*10, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
        redis.boundValueOps("test").increment(-1);//val做-1操作
        redis.opsForValue().get("test");//根据key获取缓存中的val
        redis.boundValueOps("test").increment(1);//val +1
        redis.getExpire("test");//根据key获取过期时间
        redis.getExpire("test",TimeUnit.SECONDS);//根据key获取过期时间并换算成指定单位
        redis.delete("test");//根据key删除缓存
        redis.hasKey("546545");//检查key是否存在，返回boolean值
        redis.opsForSet().add("red_123", "1","2","3");//向指定key中存放set集合
        redis.expire("red_123",1000 , TimeUnit.MILLISECONDS);//设置过期时间
        redis.opsForSet().isMember("red_123", "1");//根据key查看集合中是否存在指定数据
        redis.opsForSet().members("red_123");//根据key获取set集合*/
        System.out.println(sessionUtil.getCurrUser());


    }
}
