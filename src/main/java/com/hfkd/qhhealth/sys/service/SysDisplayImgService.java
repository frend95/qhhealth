package com.hfkd.qhhealth.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.hfkd.qhhealth.sys.model.SysDisplayImg;

import java.util.List;

/**
 * 展示图片 Service
 * @author hexq
 * @date 2018/7/5 10:12
 */
public interface SysDisplayImgService extends IService<SysDisplayImg> {

    /**
     * 获取展示图片
     * @param page 页面类别
     * @param groups 组别
     * @param limit limit
     * @return List<SysDisplayImg>
     */
    List<SysDisplayImg> getDisplayImg(String page, Integer groups, Integer limit);
}
