package com.hexq.qh.sys.controller;

import com.hexq.qh.common.annotation.LogOut;
import com.hexq.qh.common.util.JsonUtils;
import com.hexq.qh.common.util.WsHttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private final Log log = LogFactory.getLog(getClass());

    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.appid}")
    private String appid;


    @LogOut("微信验证")
    @RequestMapping("/auth")
    public HashMap auth(@RequestBody Map<String,Object> requestMap) throws Exception {
        String jsCode = (String) requestMap.get("code");
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
                + "&secret=" + secret
                + "&js_code=" + jsCode
                + "&grant_type=authorization_code";
        WsHttpClient client = new WsHttpClient(url);
        client.sendGet();
        String responseMsg = client.getResponseMsg();
        return JsonUtils.json2Obj(responseMsg, HashMap.class);
    }


}
