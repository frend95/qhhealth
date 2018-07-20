package com.hfkd.qhhealth.comment.service;

import com.hfkd.qhhealth.comment.model.Comment;

import java.util.List;

/**
 * @author hexq
 * @date 2018/7/11 17:40
 */
public interface CommentService {

    /**
     * 查询包含子评论的完整评论列表
     * @param type 内容类型
     * @param page 页码
     * @param size limit
     * @param contentId 内容id
     * @return List<Comment>
     */
    List<Comment> getFullCmt(String type, Integer page, Integer size, Integer contentId);
}
