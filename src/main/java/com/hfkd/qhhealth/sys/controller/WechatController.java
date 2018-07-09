package com.hfkd.qhhealth.sys.controller;

import com.alibaba.fastjson.JSON;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.util.WsHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信Controller
 * @author hexq
 * @date 2018/5/11 15:17
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.appid}")
    private String appid;


    @LogOut("微信验证")
    @RequestMapping("/auth")
    public HashMap auth(@RequestBody Map<String,Object> requestMap) {
        String jsCode = (String) requestMap.get("code");
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
                + "&secret=" + secret
                + "&js_code=" + jsCode
                + "&grant_type=authorization_code";
        WsHttpClient client = new WsHttpClient(url);
        client.sendGet();
        String responseMsg = client.getResponseMsg();
        return JSON.parseObject(responseMsg, HashMap.class);
    }


}
