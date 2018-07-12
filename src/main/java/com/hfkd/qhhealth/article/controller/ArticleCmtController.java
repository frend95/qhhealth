package com.hfkd.qhhealth.article.controller;


import com.hfkd.qhhealth.article.mapper.ArticleCmtChildMapper;
import com.hfkd.qhhealth.article.mapper.ArticleCmtMapper;
import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.article.model.ArticleCmt;
import com.hfkd.qhhealth.article.model.ArticleCmtChild;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 文章评论 Controller
 * @author hexq
 * @date 2018/7/4 18:21
 */
@RestController
@RequestMapping("/article")
public class ArticleCmtController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private ArticleCmtMapper articleCmtMapper;
    @Autowired
    private ArticleCmtChildMapper articleCmtChildMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @LogOut("查询文章评论")
    @RequestMapping("/getComment")
    public Map<String, Object> getComment(Integer page, Integer size, Integer articleId) {
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> comments = articleCmtMapper.getComments(page, size, articleId);
        if (comments.size() > 0) {
            for (Map<String, Object> comment : comments) {
                Integer replyCnt = (Integer) comment.get("replyCnt");
                // 如果子评论数为零则继续查询下一条评论的子评论, 最大为4条
                if (replyCnt == null || replyCnt == 0) {
                    comment.put("childCmt", Collections.EMPTY_LIST);
                    continue;
                }
                Integer cmtId = Integer.valueOf(comment.get("id").toString());
                List<Map<String, Object>> childCmt = articleCmtChildMapper.getChildCmt(0, 4, cmtId);
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
        List<Map<String, Object>> comments = articleCmtChildMapper.getChildCmt(page, size, cmtId);
        Map<String, Object> resultMap = RspUtil.ok();
        resultMap.put("result", comments);
        return resultMap;
    }

    @LogOut("评论文章")
    @Verify
    @RequestMapping("/comment")
    public Map<String, Object> comment(Integer articleId, String content) {
        Integer currId = session.getCurrId();
        ArticleCmt articleCmt = new ArticleCmt();
        articleCmt.setAuthorId(currId);
        articleCmt.setContent(content);
        articleCmt.setContentId(articleId);
        articleCmtMapper.insert(articleCmt);
        // 评论数加一
        articleMapper.cmtCntPlusOne(articleId);
        return RspUtil.ok();
    }

    @LogOut("回复文章评论")
    @Verify
    @RequestMapping("/reply")
    public Map<String, Object> reply(Integer cmtId, String content, Integer replyToId, String replyToName) {
        Integer currId = session.getCurrId();
        ArticleCmtChild cmtChild = new ArticleCmtChild();
        cmtChild.setParentCmtId(cmtId);
        cmtChild.setContent(content);
        cmtChild.setAuthorId(currId);
        cmtChild.setReplyToId(replyToId);
        cmtChild.setReplyToName(replyToName);
        articleCmtChildMapper.insert(cmtChild);
        // 回复数加一
        articleCmtMapper.replyCntPlusOne(cmtId);
        return RspUtil.ok();
    }
}
