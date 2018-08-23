package com.hfkd.qhhealth.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.sys.mapper.SysDisplayImgMapper;
import com.hfkd.qhhealth.sys.model.SysDisplayImg;
import com.hfkd.qhhealth.sys.service.SysDisplayImgService;
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
    public List<SysDisplayImg> getDisplayImg(String page, Integer groups, Integer limit) {
        EntityWrapper<SysDisplayImg> wrapper = new EntityWrapper<>();
        wrapper.eq("page", page)
                .eq(groups != null, "groups", groups)
                .orderBy("seq", true);
        if (limit != null) {
            wrapper.last("LIMIT " + limit);
        }
        return selectList(wrapper);
    }
}
