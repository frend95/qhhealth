package com.hfkd.qhhealth.comment.controller;


import com.hfkd.qhhealth.comment.mapper.CommentMapper;
import com.hfkd.qhhealth.comment.model.ChildComment;
import com.hfkd.qhhealth.comment.model.Comment;
import com.hfkd.qhhealth.comment.service.CommentService;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 评论 Controller
 * @author hexq
 * @date 2018/7/4 18:21
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @LogOut("查询评论")
    @RequestMapping("/getComment")
    public Map<String, Object> getComment(String type, Integer page, Integer size, Integer contentId) {
        size = size == null ? 10 : size;
        List<Comment> comments = commentService.getFullCmt(type, page, size, contentId);
        return RspUtil.ok(comments);
    }

    @LogOut("查询评论回复")
    @RequestMapping("/getReply")
    public Map<String, Object> getReply(String type, Integer page, Integer size, Integer cmtId) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        List<ChildComment> comments = commentMapper.getChildCmt(type, page, size, cmtId);
        return RspUtil.ok(comments);
    }

    @LogOut("评论")
    @Verify
    @RequestMapping("/post")
    public Map<String, Object> post(String type, Integer contentId, String content) {
        Integer currId = session.getCurrId();
        Comment comment = new Comment(currId, contentId, content, type);
        commentMapper.addCmt(comment);
        // 评论数加一
        commentMapper.cmtCntPlusOne(type, contentId);
        // 查询出刚刚插入的评论
        Comment cmtById = commentMapper.getCmtById(type, comment.getId());
        return RspUtil.ok(cmtById);
    }

    @LogOut("回复评论")
    @Verify
    @RequestMapping("/reply")
    public Map<String, Object> reply(String type, Integer cmtId, String content) {
        Integer currId = session.getCurrId();
        // 根据父评论id查询内容id
        Integer contentId = commentMapper.getContentIdByCmtId(type, cmtId);
        ChildComment childCmt = new ChildComment(currId, cmtId, content, null, null, type);
        commentMapper.addChildCmt(childCmt);
        // 回复数加一
        commentMapper.replyCntPlusOne(type, cmtId);
        // 评论数加一
        commentMapper.cmtCntPlusOne(type, contentId);
        // 查询出刚刚插入的评论
        ChildComment cmtById = commentMapper.getChildCmtById(type, childCmt.getId());
        return RspUtil.ok(cmtById);
    }
}
