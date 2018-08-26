package com.hfkd.qhhealth.social.controller;


import com.hfkd.qhhealth.comment.model.Comment;
import com.hfkd.qhhealth.comment.service.CommentService;
import com.hfkd.qhhealth.common.annotation.CacheRefresh;
import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.util.FileUtil;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.health.service.HealthGoalService;
import com.hfkd.qhhealth.social.mapper.SocialFeedMapper;
import com.hfkd.qhhealth.social.mapper.SocialUserFollowingMapper;
import com.hfkd.qhhealth.social.mapper.SocialUserInfoMapper;
import com.hfkd.qhhealth.social.mapper.SocialUserLikeMapper;
import com.hfkd.qhhealth.social.model.SocialFeed;
import com.hfkd.qhhealth.social.model.SocialFeedVo;
import com.hfkd.qhhealth.social.service.SocialFeedService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 动态 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/social")
public class SocialFeedController {

    @Autowired
    private SocialFeedMapper feedMapper;
    @Autowired
    private SocialFeedService feedService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private HealthGoalService goalService;
    @Autowired
    private SocialUserLikeMapper userLikeMapper;
    @Autowired
    private SocialUserFollowingMapper followingMapper;
    @Autowired
    private SocialUserInfoMapper userInfoMapper;
    @Autowired
    private SessionUtil session;
    @Value("${file.path.img}")
    private String imgPath;
    @Value("${domain.image}")
    private String imgDomain;

    @LogOut("查看用户动态主页")
    @RequestMapping("/feedHome")
    public Map<String, Object> feedHome(Integer id) {
        Integer currId = session.getCurrId();
        boolean isFollow = false;
        boolean hiddenBtn = false;
        if (id == null || currId.equals(id)) {
            id = currId;
            hiddenBtn = true;
        } else {
            // 查询是否关注
            isFollow = followingMapper.getFollowLsId(ConstVal.USER, currId, id) != null;
        }
        Map<String, Object> socialInfo = userInfoMapper.getSocialInfo(id);
        // 计算减肥剩余天数
        String startTime = (String) socialInfo.get("startTime");
        Integer period = (Integer) socialInfo.get("period");
        Long remainDays = goalService.computeRemainDays(startTime, period);
        socialInfo.put("remainDays", remainDays);
        // 查询10条动态
        List<SocialFeedVo> userFeeds = feedMapper.getUserFeeds(id, currId, 0, 10);
        socialInfo.put("feeds", userFeeds);
        socialInfo.put("isFollow", isFollow);
        socialInfo.put("hiddenBtn", hiddenBtn);
        socialInfo.put("img", "https://app.xintianhong888.com/img/social.jpg");
        return RspUtil.ok(socialInfo);
    }

    @LogOut("查看首页动态列表")
    @RequestMapping("/homeFeeds")
    public Map<String, Object> homeFeeds(Integer page, Integer size) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> feeds = feedService.getPlazaFeeds(page, size);
        return RspUtil.ok(feeds);
    }

    @LogOut("查看动态列表")
    @RequestMapping("/feeds")
    public Map<String, Object> userFeeds(Integer id, String type, Integer page, Integer size) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        Integer currId = session.getCurrId();
        List<SocialFeedVo> feeds = null;
        if (ConstVal.USER.equals(type)) {
            id = id == null ? currId : id;
            feeds = feedMapper.getUserFeeds(id, currId, page, size);
        } else if (ConstVal.YYS.equals(type)) {
            feeds = feedMapper.getYysFeeds(id, currId, page, size);
        }
        return RspUtil.ok(feeds);
    }

    @LogOut("查看动态详情")
    @RequestMapping("/feedDetail")
    public Map<String, Object> feedDetail(Integer id) {
        SocialFeedVo feed = feedMapper.getFeed(id);
        if (feed == null) {
            return RspUtil.error("该动态已删除");
        }
        Integer currId = session.getCurrId();
        Integer authorId = feed.getAuthorId();
        String authorType = feed.getAuthorType();
        // 查询最近的10条评论
        List<Comment> comments = commentService.getFullCmt(ConstVal.CONTENT_TYPE_FEED, 0, 10, id);
        feed.setComments(comments);
        // 查询是否点赞
        Boolean isLike = userLikeMapper.getLikeId(currId, id) != null;
        feed.setIsLike(isLike);
        // 查询是否关注
        boolean isFollow = false;
        // 判断是否隐藏关注按钮，竟然还要后台判断，什么鬼前端 -_-#
        boolean hiddenBtn = false;
        if (authorType.equals(ConstVal.USER) && currId.equals(authorId)) {
            hiddenBtn = true;
        } else {
            // 查询是否关注
            isFollow = followingMapper.getFollowLsId(authorType, currId, authorId) != null;
        }
        feed.setIsFollow(isFollow);
        feed.setHiddenBtn(hiddenBtn);
        return RspUtil.ok(feed);
    }

    @LogOut("发布动态")
    @RequestMapping("/newFeed")
    public Map<String, Object> newFeed(String content, String isPrivate, List<MultipartFile> img) throws IOException {
        if (StringUtils.isBlank(content)) {
            return RspUtil.error("请填写动态内容");
        }
        if (content.length() > 500) {
            return RspUtil.error("内容超出限制");
        }
        if (img.size() > 9) {
            return RspUtil.error("图片数量超出限制");
        }
        Integer currId = session.getCurrId();
        String imgStr = FileUtil.upload(img, imgPath, imgDomain, true, true);
        SocialFeed feed = new SocialFeed(currId, ConstVal.USER, content, imgStr, isPrivate);
        feedMapper.insert(feed);
        // 动态数加一
        userInfoMapper.feedPlusOne(currId);
        return RspUtil.ok();
    }

    @LogOut("删除动态")
    @CacheRefresh
    @RequestMapping("/delFeed")
    public Map<String, Object> delFeed(Integer id) throws IOException {
        SocialFeedVo feed = feedMapper.getFeed(id);
        // 删除文件
        String[] imgs = feed.getImgs();
        if (ArrayUtils.isNotEmpty(imgs)) {
            for (String url : imgs) {
                String path = imgPath + FileUtil.getFilePathFromUrl(url);
                FileUtil.delFile(path);
            }
        }
        // 删除动态
        feedMapper.deleteById(id);
        // 动态数减一
        userInfoMapper.feedMinusOne(feed.getAuthorId());
        return RspUtil.ok();
    }

}
