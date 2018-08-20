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
import java.util.LinkedList;
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
        List<Integer> ids = yysMapper.getAllId();
        int size = ids.size();
        if (size == 0) {
            return Collections.emptyList();
        } else if (size > 10) {
            List<Integer> list = new LinkedList<>();
            // 获取9个随机的营养师id
            int[] randomIdxs = RandomUtil.randomArray(0, size - 1, 9);
            for (int randomIdx : randomIdxs) {
                list.add(ids.get(randomIdx));
            }
            return socialYysInfoMapper.getYysByIds(list);
        } else {
            return socialYysInfoMapper.getYysByIds(null);
        }
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
