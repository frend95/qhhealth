package com.hfkd.qhhealth.video.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.video.mapper.VideoMapper;
import com.hfkd.qhhealth.video.model.Video;
import com.hfkd.qhhealth.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 视频 Controller
 * @author hexq
 * @date 2018/7/4 18:21
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoService videoService;
    
    @LogOut("查询视频列表")
    @RequestMapping("/list")
    public Map<String, Object> list(Integer page, Integer size, String type, String tag) {
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> articles = videoMapper.getVideoLs(page, size, type, tag);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", articles);
        return resultMap;
    }

    @LogOut("查询视频详情")
    @RequestMapping("/detail")
    public Map<String, Object> detail(Integer id) {
        Video video = videoService.selectById(id);
        // 观看数加一
        videoMapper.watchedCntPlusOne(id);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", video);
        return resultMap;
    }
}
