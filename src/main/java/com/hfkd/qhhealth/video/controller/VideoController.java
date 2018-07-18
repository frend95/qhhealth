package com.hfkd.qhhealth.video.controller;


import com.hfkd.qhhealth.comment.model.Comment;
import com.hfkd.qhhealth.comment.service.CommentService;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.constant.ConstVal;
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
        String title = null;
        if (tag != null) {
            switch (tag) {
                case ConstVal.VIDEO_TAG_MIDAGE:
                    title = ConstEnum.VIDEO_TAG_MIDAGE.ename();
                    break;
                case ConstVal.VIDEO_TAG_PARTUM:
                    title = ConstEnum.VIDEO_TAG_PARTUM.ename();
                    break;
                case ConstVal.VIDEO_TAG_YOUTH:
                    title = ConstEnum.VIDEO_TAG_YOUTH.ename();
                    break;
                case ConstVal.VIDEO_TAG_SIMPLE:
                    title = ConstEnum.VIDEO_TAG_SIMPLE.ename();
                    break;
                case ConstVal.VIDEO_TAG_GENETIC:
                    title = ConstEnum.VIDEO_TAG_GENETIC.ename();
                    break;
                default:
            }
        }

        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;

        List<Map<String, Object>> articles = videoMapper.getVideoLs(page, size, type, tag, null);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", articles);
        resultMap.put("title", title);
        return resultMap;
    }

    @LogOut("查询视频详情")
    @Verify(hasSession = true)
    @RequestMapping("/detail")
    public Map<String, Object> detail(Integer id) {
        Integer currId = session.getCurrId();
        Video video = videoService.selectById(id);
        // 查询最近的10条评论
        List<Comment> comments = commentService.getFullCmt(ConstVal.CONTENT_TYPE_VIDEO, 0, 10, id);
        video.setComments(comments);
        // 查询是否收藏
        Boolean isCollect = currId != null && videoCollectionMapper.getClctId(currId, id) != null;
        video.setCollect(isCollect);
        // 观看数加一
        videoMapper.watchedCntPlusOne(id);
        return RspUtil.ok(video);
    }
}
