package com.hfkd.qhhealth.sys.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.RspEntity;
import com.hfkd.qhhealth.sys.mapper.SysInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统信息 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/sys")
public class SysInfoController {

    @Autowired
    private SysInfoMapper infoMapper;
    @Value("${sys.code.appname}")
    private Integer appnameCode;
    @Value("${sys.code.version}")
    private Integer versionCode;
    @Value("${sys.code.icon}")
    private Integer iconCode;
    @Value("${sys.code.weibo}")
    private Integer weiboCode;
    @Value("${sys.code.wechat}")
    private Integer wechatCode;
    @Value("${sys.code.contact}")
    private Integer contactCode;
    @Value("${sys.code.agreement}")
    private Integer agreementCode;
    @Value("${sys.code.package}")
    private Integer packageCode;
    @Value("${sys.code.versionno}")
    private Integer versionnoCode;

    @LogOut("关于我们")
    @RequestMapping("/about")
    public Map about() {
        Map<String, Object> map = new HashMap<>(8);

        map.put("icon", infoMapper.getVariable(iconCode));
        map.put("appName", infoMapper.getVariable(appnameCode) + " v" + infoMapper.getVariable(versionCode));
        map.put("wechat", infoMapper.getVariable(wechatCode));
        map.put("weibo", infoMapper.getVariable(weiboCode));
        map.put("contact", infoMapper.getVariable(contactCode));

        return RspEntity.ok(map);
    }

    @LogOut("用户协议")
    @RequestMapping("/agreement")
    public Map agreement() {
        return RspEntity.ok(infoMapper.getVariable(agreementCode));
    }

    @LogOut("检查更新")
    @RequestMapping("/checkUpdate")
    public Map checkUpdate(Integer version) {
        Map<String, Object> resultMap = RspEntity.ok();
        String packageUrl = null;
        boolean newVersion = false;
        String msg = "当前为最新版本";
        String versionDb = infoMapper.getVariable(versionnoCode);
        Integer i = Integer.parseInt(versionDb);
        if (i > version) {
            packageUrl = infoMapper.getVariable(packageCode);
            newVersion = true;
            msg = "有新版本可供更新";
        }
        resultMap.put("resource", packageUrl);
        resultMap.put("newVersion", newVersion);
        resultMap.put("msg", msg);
        return resultMap;
    }
}
