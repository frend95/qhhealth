package com.hfkd.qhhealth.comment.mapper;

import com.hfkd.qhhealth.comment.model.ChildComment;
import com.hfkd.qhhealth.comment.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论 Mapper
 * @author hexq
 * @date 2018/7/4 18:21
 */
@Mapper
public interface CommentMapper {

    List<Comment> getComments(@Param("type") String type,
                              @Param("page") Integer page,
                              @Param("size") Integer size,
                              @Param("contentId") Integer contentId);

    void replyCntPlusOne(@Param("type") String type,
                         @Param("cmtId") Integer cmtId);

    void cmtCntPlusOne(@Param("type") String type,
                       @Param("contentId") Integer contentId);

    List<ChildComment> getChildCmt(@Param("type") String type,
                                   @Param("page") Integer page,
                                   @Param("size") Integer size,
                                   @Param("cmtId") Integer cmtId);

    void addCmt(Comment comment);

    void addChildCmt(ChildComment childComment);

    Comment getCmtById(@Param("type") String type,
                       @Param("cmtId") Integer cmtId);

    Integer getContentIdByCmtId(@Param("type") String type,
                                @Param("cmtId") Integer cmtId);

    ChildComment getChildCmtById(@Param("type") String type,
                                 @Param("cmtId") Integer cmtId);
}