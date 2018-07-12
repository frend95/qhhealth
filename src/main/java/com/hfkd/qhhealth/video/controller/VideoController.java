package com.hfkd.qhhealth.video.controller;


import com.hfkd.qhhealth.comment.model.Comment;
import com.hfkd.qhhealth.comment.service.CommentService;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.user.mapper.UserVideoCollectionMapper;
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
    private SessionUtil session;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoService videoService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserVideoCollectionMapper videoCollectionMapper;
    
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
    @Verify(hasSession = true)
    @RequestMapping("/detail")
    public Map<String, Object> detail(Integer id) {
        Integer currId = session.getCurrId();
        Video video = videoService.selectById(id);
        // 查询最近的10条评论
        List<Comment> comments = commentService.getFullCmt(ConstEnum.CONTENT_TYPE_VIDEO.getValue(), 0, 10, id);
        video.setComments(comments);
        // 查询是否收藏
        Boolean isCollect = currId != null && videoCollectionMapper.getClctId(currId, id) != null;
        video.setCollect(isCollect);
        // 观看数加一
        videoMapper.watchedCntPlusOne(id);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", video);
        return resultMap;
    }
}
