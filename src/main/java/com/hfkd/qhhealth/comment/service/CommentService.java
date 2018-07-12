package com.hfkd.qhhealth.comment.service;

import com.hfkd.qhhealth.comment.model.Comment;

import java.util.List;

/**
 * @author hexq
 * @date 2018/7/11 17:40
 */
public interface CommentService {

    List<Comment> getFullCmt(String type, Integer page, Integer size, Integer contentId);
}
