package com.hfkd.qhhealth.social.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.Model.PageVo;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.social.mapper.SocialUserFollowingMapper;
import com.hfkd.qhhealth.social.model.FollowVo;
import com.hfkd.qhhealth.social.model.SocialUserFollowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用户关注 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/social")
public class SocialUserFollowingController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private SocialUserFollowingMapper followingMapper;

    @LogOut("关注或取消关注对方")
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/follow")
    public Map<String, Object> follow(Integer followingId, String followingType) {
        Integer currId = session.getCurrId();
        if (followingId.equals(currId)) {
            return RspUtil.error("不能关注自己");
        }
        // 查询是否关注对方
        Integer followLsId = followingMapper.getFollowLsId(followingType, currId, followingId);
        if (followLsId != null) {
            // 取消关注
            followingMapper.deleteById(followLsId);
            // 对方粉丝数减一
            followingMapper.followerMinusOne(followingId, followingType);
            // 关注数减一
            followingMapper.followingMinusOne(currId);
            return RspUtil.ok("isFollow", false, "取消关注成功");
        } else {
            // 关注
            SocialUserFollowing userFollowing = new SocialUserFollowing(currId, followingId, followingType);
            followingMapper.insert(userFollowing);
            // 对方粉丝数加一
            followingMapper.followerPlusOne(followingId, followingType);
            // 关注数加一
            followingMapper.followingPlusOne(currId);
            return RspUtil.ok("isFollow", true, "关注成功");
        }
    }

    @LogOut("查看关注列表")
    @RequestMapping("/myFollow")
    public Map<String, Object> myFollow(PageVo pageVo, Integer id) {
        Integer currId = session.getCurrId();
        id = id == null ? currId : id;
        List<FollowVo> ls = followingMapper.getFollowingLs(pageVo, id, currId);
        return RspUtil.ok(ls);
    }

    @LogOut("查看粉丝列表")
    @RequestMapping("/followers")
    public Map<String, Object> followers(PageVo pageVo, Integer id, String type) {
        Integer currId = session.getCurrId();
        if (id == null) {
            id = currId;
            type = ConstVal.USER;
        }
        List<FollowVo> ls = followingMapper.getFollowers(pageVo, id, currId, type);
        return RspUtil.ok(ls);
    }

}
