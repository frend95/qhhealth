package com.hfkd.qhhealth.sys.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.sys.mapper.SysInfoMapper;
import com.hfkd.qhhealth.sys.model.SysInfo;
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

    @LogOut("关于我们")
    @RequestMapping("/about")
    public Map<String, Object> about() {
        Map<String, Object> map = new HashMap<>(8);

        SysInfo appnameInfo = infoMapper.getInfo(appnameCode);
        SysInfo versionInfo = infoMapper.getInfo(versionCode);
        SysInfo iconInfo = infoMapper.getInfo(iconCode);
        SysInfo weiboInfo = infoMapper.getInfo(weiboCode);
        SysInfo wechatInfo = infoMapper.getInfo(wechatCode);
        SysInfo contactInfo = infoMapper.getInfo(contactCode);

        map.put("icon", iconInfo.getVariable());
        map.put("appName", appnameInfo.getVariable() + " v" + versionInfo.getVariable());
        map.put("wechat", wechatInfo.getVariable());
        map.put("weibo", weiboInfo.getVariable());
        map.put("contact", contactInfo.getVariable());

        return RspUtil.ok(map);
    }

    @LogOut("用户协议")
    @RequestMapping("/agreement")
    public Map<String, Object> agreement() {
        SysInfo sysInfo = infoMapper.getInfo(agreementCode);
        return RspUtil.ok(sysInfo.getVariable());
    }

    @LogOut("检查更新")
    @RequestMapping("/checkUpdate")
    public Map<String, Object> checkUpdate(String version) {
        Map<String, Object> resultMap = RspUtil.ok();
        String packageUrl = null;
        boolean newVersion = false;
        String msg = "当前为最新版本";
        String versionDb = infoMapper.getInfo(versionCode).getVariable();
        if (!version.equals(versionDb)) {
            packageUrl = infoMapper.getInfo(packageCode).getVariable();
            newVersion = true;
            msg = "有新版本可供更新";
        }
        resultMap.put("package", packageUrl);
        resultMap.put("newVersion", newVersion);
        resultMap.put("msg", msg);
        return resultMap;
    }
}
