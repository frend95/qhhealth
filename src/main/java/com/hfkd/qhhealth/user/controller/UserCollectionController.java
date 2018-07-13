package com.hfkd.qhhealth.user.controller;


import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.user.mapper.UserCollectionMapper;
import com.hfkd.qhhealth.video.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用户视频收藏 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/user")
public class UserCollectionController {
    @Autowired
    private SessionUtil session;
    @Autowired
    private UserCollectionMapper collectionMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @LogOut("收藏视频或文章")
    @RequestMapping("/collect")
    public Map<String, Object> collect(String type, Integer id) {
        Integer currId = session.getCurrId();

        /*if (type.equals(ConstEnum.CONTENT_TYPE_ARTICLE.getValue())) {

            Map<String, Object> articleBrief = articleMapper.getArticleBrief(id);
            String title = (String) articleBrief.get("title");
            String thumb = (String) articleBrief.get("thumb");

            UserArticleCollection articleClct = new UserArticleCollection();
            articleClct.setArticleId(id);
            articleClct.setThumb(thumb);
            articleClct.setTitle(title);
            articleClct.setUserId(currId);

            articleCollectionService.insert(articleClct);

        } else if (type.equals(ConstEnum.CONTENT_TYPE_VIDEO.getValue())) {

            Map<String, Object> videoBrief = videoMapper.getVideoBrief(id);
            String title = (String) videoBrief.get("title");
            String thumb = (String) videoBrief.get("thumb");

            UserVideoCollection videoClct = new UserVideoCollection();
            videoClct.setVideoId(id);
            videoClct.setThumb(thumb);
            videoClct.setTitle(title);
            videoClct.setUserId(currId);

            videoCollectionService.insert(videoClct);

        } else {
            return RspUtil.error("类型错误");
        }*/
        Integer clctId = collectionMapper.getClctId(type, currId, id);
        if (clctId != null) {
            collectionMapper.delCollection(type, clctId);
            return RspUtil.ok("isCollect", false, "取消收藏成功");
        }

        Map<String, Object> brief;
        if (type.equals(ConstEnum.CONTENT_TYPE_ARTICLE.getValue())) {
            brief = articleMapper.getArticleBrief(id);
        } else if (type.equals(ConstEnum.CONTENT_TYPE_VIDEO.getValue())) {
            brief = videoMapper.getVideoBrief(id);
        } else {
            return RspUtil.error("类型错误");
        }
        String title = (String) brief.get("title");
        String thumb = (String) brief.get("thumb");

        collectionMapper.addCollection(type, currId, id, title, thumb);

        return RspUtil.ok("isCollect", true, "收藏成功");
    }

    @LogOut("查询收藏的视频或文章")
    @RequestMapping("/collection")
    public Map<String, Object> collection(Integer page, Integer size, String type) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        Integer currId = session.getCurrId();
        /*List ls;
        if (type.equals(ConstEnum.CONTENT_TYPE_ARTICLE.getValue())) {
            ls = articleCollectionMapper.getCollection(page, size, currId);
        } else if (type.equals(ConstEnum.CONTENT_TYPE_VIDEO.getValue())) {
            ls = videoCollectionMapper.getCollection(page, size, currId);
        } else {
            return RspUtil.error("类型错误");
        }*/
        List<Map<String, Object>> ls = collectionMapper.getCollection(type, page, size, currId);
        return RspUtil.ok(ls);
    }
}