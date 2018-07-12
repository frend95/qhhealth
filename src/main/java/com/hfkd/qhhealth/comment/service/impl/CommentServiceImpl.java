package com.hfkd.qhhealth.comment.service.impl;

import com.hfkd.qhhealth.comment.mapper.CommentMapper;
import com.hfkd.qhhealth.comment.model.ChildComment;
import com.hfkd.qhhealth.comment.model.Comment;
import com.hfkd.qhhealth.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author hexq
 * @date 2018/7/11 17:40
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> getFullCmt(String type, Integer page, Integer size, Integer contentId) {
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Comment> comments = commentMapper.getComments(type, page, size, contentId);
        if (comments.size() > 0) {
            for (Comment comment : comments) {
                Integer replyCnt = comment.getReplyCnt();
                // 如果子评论数为零则继续查询下一条评论的子评论, 最大为4条
                if (replyCnt == null || replyCnt == 0) {
                    comment.setChildCmt(Collections.EMPTY_LIST);
                    continue;
                }
                Integer cmtId = comment.getId();
                List<ChildComment> childCmt = commentMapper.getChildCmt(type, 0, 4, cmtId);
                comment.setChildCmt(childCmt);
            }
        }
        return comments;
    }
}
