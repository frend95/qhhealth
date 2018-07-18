package com.hfkd.qhhealth.nutritionist.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hfkd.qhhealth.common.util.RandomUtil;
import com.hfkd.qhhealth.nutritionist.mapper.NutritionistMapper;
import com.hfkd.qhhealth.nutritionist.model.Nutritionist;
import com.hfkd.qhhealth.nutritionist.service.NutritionistService;
import com.hfkd.qhhealth.social.mapper.SocialNutritionistInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 营养师信息 ServiceImpl
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Service
public class NutritionistServiceImpl extends ServiceImpl<NutritionistMapper, Nutritionist> implements NutritionistService {

    @Autowired
    private NutritionistMapper yysMapper;
    @Autowired
    private SocialNutritionistInfoMapper socialYysInfoMapper;

    @Override
    public List<Map<String, Object>> recommendYys() {
        int[] ids = null;
        // 查询最大id并生成随机id
        Integer maxId = yysMapper.getMaxId();
        if (maxId == null) {
            return Collections.EMPTY_LIST;
        }
        if (maxId > 9) {
            // 生成随机id数组
            ids = RandomUtil.randomArray(1, maxId, 9);
        }
        return socialYysInfoMapper.getYysByIds(ids);
    }

    @Override
    public Integer getRandomYysId() {
        List<Integer> ids = yysMapper.getAllId();
        int size = ids.size();
        if (size < 1) {
            return null;
        } else if (size == 1) {
            return ids.get(0);
        }
        int randomIdx = RandomUtil.getRandom(size - 1);
        return ids.get(randomIdx);

    }
}
