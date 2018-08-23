package com.hfkd.qhhealth.social.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.social.mapper.SocialFeedMapper;
import com.hfkd.qhhealth.social.mapper.SocialUserLikeMapper;
import com.hfkd.qhhealth.social.model.SocialFeedVo;
import com.hfkd.qhhealth.social.model.SocialUserLike;
import com.hfkd.qhhealth.social.service.SocialUserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用户点赞 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/social")
public class SocialUserLikeController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private SocialUserLikeMapper userLikeMapper;
    @Autowired
    private SocialUserLikeService userLikeService;
    @Autowired
    private SocialFeedMapper feedMapper;

    @LogOut("点赞动态")
    @RequestMapping("/like")
    public Map<String, Object> like(Integer id) {
        Integer currId = session.getCurrId();
        Integer likeId = userLikeMapper.getLikeId(currId, id);
        if (likeId != null) {
            userLikeMapper.delLike(likeId);
            feedMapper.likeMinusOne(id);
            return RspUtil.ok("isLike", false, "取消点赞成功");
        }
        SocialUserLike userLike = new SocialUserLike(currId, id);
        userLikeService.insert(userLike);
        feedMapper.likePlusOne(id);
        return RspUtil.ok("isLike", true, "点赞成功");
    }

    @LogOut("查询点赞的动态")
    @RequestMapping("/myLike")
    public Map<String, Object> myLike(Integer page, Integer size) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        Integer currId = session.getCurrId();
        List<SocialFeedVo> feeds = feedMapper.getLikeFeeds(currId, page, size);
        return RspUtil.ok(feeds);
    }

}
