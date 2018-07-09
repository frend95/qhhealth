package com.hfkd.qhhealth.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.sys.model.SysDisplayImg;
import com.hfkd.qhhealth.sys.mapper.SysDisplayImgMapper;
import com.hfkd.qhhealth.sys.service.SysDisplayImgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 展示图片 ServiceImpl
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class SysDisplayImgServiceImpl extends ServiceImpl<SysDisplayImgMapper, SysDisplayImg> implements SysDisplayImgService {

    @Override
    public List<SysDisplayImg> getDisplayImg(ConstEnum page, Integer group, Integer limit) {
        EntityWrapper<SysDisplayImg> wrapper = new EntityWrapper<>();
        wrapper.eq("type", page.getValue())
                .eq(group != null, "group", group)
                .orderBy("order", true)
                .last("LIMIT " + limit);
        return selectList(wrapper);
    }
}
