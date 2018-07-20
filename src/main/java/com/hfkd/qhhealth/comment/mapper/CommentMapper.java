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

    /**
     * 获取评论列表
     * @param type 评论类型
     * @param page 页码
     * @param size limit
     * @param contentId 内容id
     * @return List<Comment>
     */
    List<Comment> getComments(@Param("type") String type,
                              @Param("page") Integer page,
                              @Param("size") Integer size,
                              @Param("contentId") Integer contentId);

    /**
     * 回复数加一
     * @param type 内容类型
     * @param cmtId 评论id
     */
    void replyCntPlusOne(@Param("type") String type,
                         @Param("cmtId") Integer cmtId);

    /**
     * 评论数加一
     * @param type 内容类型
     * @param contentId 内容id
     */
    void cmtCntPlusOne(@Param("type") String type,
                       @Param("contentId") Integer contentId);

    /**
     * 获取子评论列表
     * @param type 内容类型
     * @param page 页码
     * @param size limit
     * @param cmtId 评论id
     * @return List<ChildComment>
     */
    List<ChildComment> getChildCmt(@Param("type") String type,
                                   @Param("page") Integer page,
                                   @Param("size") Integer size,
                                   @Param("cmtId") Integer cmtId);

    /**
     * 添加评论
     * @param comment Comment{ authorId,content,contentId }
     */
    void addCmt(Comment comment);

    /**
     * 添加子评论
     * @param childComment ChildComment{ parentCmtId,content,authorId,replyToId,replyToName }
     */
    void addChildCmt(ChildComment childComment);

    /**
     * 根据评论id获取评论
     * @param type 内容类型
     * @param cmtId 评论id
     * @return Comment
     */
    Comment getCmtById(@Param("type") String type,
                       @Param("cmtId") Integer cmtId);

    /**
     * 根据评论id获取内容id
     * @param type 内容类型
     * @param cmtId 评论id
     * @return 内容id contentId
     */
    Integer getContentIdByCmtId(@Param("type") String type,
                                @Param("cmtId") Integer cmtId);

    /**
     * 根据子评论id获取子评论
     * @param type 内容类型
     * @param cmtId 子评论id
     * @return ChildComment
     */
    ChildComment getChildCmtById(@Param("type") String type,
                                 @Param("cmtId") Integer cmtId);
}