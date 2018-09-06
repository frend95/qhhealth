package com.hfkd.qhhealth.user.controller;


import com.hfkd.qhhealth.article.mapper.ArticleMapper;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.util.RspEntity;
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
    public Map collect(String type, Integer id) {
        Integer currId = session.getCurrId();
        Integer clctId = collectionMapper.getClctId(type, currId, id);
        if (clctId != null) {
            collectionMapper.delCollection(type, clctId);
            return RspEntity.ok("isCollect", false, "取消收藏成功");
        }

        Map<String, Object> brief;
        switch (type) {
            case ConstVal.CONTENT_TYPE_ARTICLE:
                brief = articleMapper.getArticleBrief(id);
                break;
            case ConstVal.CONTENT_TYPE_VIDEO:
                brief = videoMapper.getVideoBrief(id);
                break;
            default:
                return RspEntity.error("类型错误");
        }
        String title = (String) brief.get("title");
        String thumb = (String) brief.get("thumb");

        collectionMapper.addCollection(type, currId, id, title, thumb);

        return RspEntity.ok("isCollect", true, "收藏成功");
    }

    @LogOut("查询收藏的视频或文章")
    @RequestMapping("/collection")
    public Map collection(Integer page, Integer size, String type) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        Integer currId = session.getCurrId();
        List<Map<String, Object>> ls = collectionMapper.getCollection(type, page, size, currId);
        return RspEntity.ok(ls);
    }
}
