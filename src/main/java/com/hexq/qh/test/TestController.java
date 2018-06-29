package com.hexq.qh.test;

import com.hexq.qh.common.util.RedisUtil;
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

    @RequestMapping("/1")
    public void test1(@RequestBody Map<String,Object> requestMap) {
        RedisUtil.clearAllCache();
    }
}
