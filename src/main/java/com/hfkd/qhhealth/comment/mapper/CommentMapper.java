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

    void addCmt(@Param("type") String type,
                @Param("authorId") Integer authorId,
                @Param("content") String content,
                @Param("contentId") Integer contentId);

    void addChildCmt(@Param("type") String type,
                     @Param("parentCmtId") Integer parentCmtId,
                     @Param("content") String content,
                     @Param("authorId") Integer authorId,
                     @Param("replyToId") Integer replyToId,
                     @Param("replyToName") String replyToName);
}