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

    List<SysDisplayImg> getDisplayImg(String page, Integer group, Integer limit);
}
