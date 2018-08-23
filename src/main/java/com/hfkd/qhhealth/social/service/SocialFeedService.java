package com.hfkd.qhhealth.social.service;

import com.baomidou.mybatisplus.service.IService;
import com.hfkd.qhhealth.social.model.SocialFeed;

import java.util.List;
import java.util.Map;

/**
 * 动态 Service
 * @author hexq
 * @date 2018/7/5 10:12
 */
public interface SocialFeedService extends IService<SocialFeed> {

    List<Map<String, Object>> getRecentYysFeeds(Integer limit);

    List<Map<String, Object>> getPlazaFeeds(Integer page, Integer size);

}
