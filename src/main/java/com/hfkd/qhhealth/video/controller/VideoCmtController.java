package com.hfkd.qhhealth.video.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.video.mapper.VideoCmtChildMapper;
import com.hfkd.qhhealth.video.mapper.VideoCmtMapper;
import com.hfkd.qhhealth.video.mapper.VideoMapper;
import com.hfkd.qhhealth.video.model.VideoCmt;
import com.hfkd.qhhealth.video.model.VideoCmtChild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 视频评论 Controller
 * @author hexq
 * @date 2018/7/4 18:21
 */
@RestController
@RequestMapping("/video")
public class VideoCmtController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private VideoCmtMapper videoCmtMapper;
    @Autowired
    private VideoCmtChildMapper videoCmtChildMapper;
    @Autowired
    private VideoMapper videoMapper;

    @LogOut("查询文章评论")
    @RequestMapping("/getComment")
    public Map<String, Object> getComment(Integer page, Integer size, Integer videoId) {
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> comments = videoCmtMapper.getComments(page, size, videoId);
        if (comments.size() > 0) {
            for (Map<String, Object> comment : comments) {
                Integer replyCnt = (Integer) comment.get("replyCnt");
                // 如果子评论数为零则继续查询下一条评论的子评论, 最大为4条
                if (replyCnt == null || replyCnt == 0) {
                    comment.put("childCmt", Collections.EMPTY_LIST);
                    continue;
                }
                Integer cmtId = Integer.valueOf(comment.get("id").toString());
                List<Map<String, Object>> childCmt = videoCmtChildMapper.getChildCmt(0, 4, cmtId);
                comment.put("childCmt", childCmt);
            }
        }
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", comments);
        return resultMap;
    }

    @LogOut("查询文章评论回复")
    @RequestMapping("/getReply")
    public Map<String, Object> getReply(Integer page, Integer size, Integer cmtId) {
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> comments = videoCmtChildMapper.getChildCmt(page, size, cmtId);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", comments);
        return resultMap;
    }

    @LogOut("评论视频")
    @Verify
    @RequestMapping("/comment")
    public Map<String, Object> comment(Integer videoId, String content) {
        Integer currId = session.getCurrId();
        VideoCmt videoCmt = new VideoCmt();
        videoCmt.setAuthorId(currId);
        videoCmt.setContent(content);
        videoCmt.setContentId(videoId);
        videoCmtMapper.insert(videoCmt);
        // 评论数加一
        videoMapper.cmtCntPlusOne(videoId);
        return RspUtil.ok();
    }

    @LogOut("回复视频评论")
    @Verify
    @RequestMapping("/reply")
    public Map<String, Object> reply(Integer cmtId, String content, Integer replyToId, String replyToName) {
        Integer currId = session.getCurrId();
        VideoCmtChild cmtChild = new VideoCmtChild();
        cmtChild.setParentCmtId(cmtId);
        cmtChild.setContent(content);
        cmtChild.setAuthorId(currId);
        cmtChild.setReplyToId(replyToId);
        cmtChild.setReplyToName(replyToName);
        videoCmtChildMapper.insert(cmtChild);
        // 回复数加一
        videoCmtMapper.replyCntPlusOne(cmtId);
        return RspUtil.ok();
    }

}
