package com.hfkd.qhhealth.social.service.impl;

import com.hfkd.qhhealth.social.model.SocialFeed;
import com.hfkd.qhhealth.social.mapper.SocialFeedMapper;
import com.hfkd.qhhealth.social.service.SocialFeedService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 动态 ServiceImpl
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class SocialFeedServiceImpl extends ServiceImpl<SocialFeedMapper, SocialFeed> implements SocialFeedService {

    @Autowired
    private SocialFeedMapper feedMapper;

    @Override
    public List<Map<String, Object>> getRecentYysFeeds(Integer limit) {
        List<Map<String, Object>> feeds = feedMapper.getRecentYysFeeds(limit);
        for (Map<String, Object> feed : feeds) {
            String img = (String) feed.get("img");
            if (img == null) {
                continue;
            }
            String[] imgArr = img.split(",");
            if (imgArr.length > 3) {
                imgArr = ArrayUtils.subarray(imgArr, 0, 3);
            }
            feed.put("img", imgArr);
        }
        return feeds;
    }

    @Override
    public List<Map<String, Object>> getPlazaFeeds(Integer page, Integer size) {
        List<Map<String, Object>> feeds = feedMapper.getPlazaFeeds(page, size);
        for (Map<String, Object> feed : feeds) {
            String img = (String) feed.get("img");
            if (img == null) {
                continue;
            }
            int i = img.indexOf(",");
            if (i != -1) {
                feed.put("img", img.substring(0, i));
            }
        }
        return feeds;
    }
}
