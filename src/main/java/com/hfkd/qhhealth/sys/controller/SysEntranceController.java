package com.hfkd.qhhealth.sys.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.sys.model.SysDisplayImg;
import com.hfkd.qhhealth.sys.service.SysDisplayImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 内容入口配置 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@RestController
@RequestMapping("/sys")
public class SysEntranceController {

    @Autowired
    SysDisplayImgService displayImgService;

    @LogOut("查询首页")
    @RequestMapping("/homepage")
    public Map<String, Object> homepage() {
        // TODO 查询banner
        List<SysDisplayImg> banner = displayImgService.getDisplayImg(ConstEnum.IMG_PAGE_CASE, null, 6);
        return RspUtil.ok();
    }




}
