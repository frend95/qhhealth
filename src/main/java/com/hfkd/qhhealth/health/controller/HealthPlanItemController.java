package com.hfkd.qhhealth.health.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.util.PinyinUtil;
import com.hfkd.qhhealth.common.util.RspEntity;
import com.hfkd.qhhealth.health.mapper.HealthPlanItemMapper;
import com.hfkd.qhhealth.health.model.HealthPlanItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 饮食计划食物项 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/plan")
public class HealthPlanItemController {

    @Autowired
    private HealthPlanItemMapper planItemMapper;

    @LogOut("查询参考食物列表")
    @RequestMapping("/foods")
    public Map foods(Integer page, Integer size, String sort, String name) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        List<HealthPlanItem> foods;
        if (StringUtils.isNotBlank(name) && !PinyinUtil.isChinese(name)) {
            foods = planItemMapper.getFoodsPinyin(page, size, sort, name);
        } else {
            foods = planItemMapper.getFoods(page, size, sort, name);
        }
        return RspEntity.ok(foods);
    }

    @LogOut("查询参考食物分类")
    @RequestMapping("/foodSort")
    @Cacheable("API_CACHE")
    public Map foodSort() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>(4);
        map1.put("name", ConstEnum.FOOD_CEREALS.ename());
        map1.put("sort", ConstEnum.FOOD_CEREALS.eval());
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>(4);
        map2.put("name", ConstEnum.FOOD_MEAT.ename());
        map2.put("sort", ConstEnum.FOOD_MEAT.eval());
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>(4);
        map3.put("name", ConstEnum.FOOD_GREEN.ename());
        map3.put("sort", ConstEnum.FOOD_GREEN.eval());
        list.add(map3);

        Map<String, Object> map4 = new HashMap<>(4);
        map4.put("name", ConstEnum.FOOD_DISHES.ename());
        map4.put("sort", ConstEnum.FOOD_DISHES.eval());
        list.add(map4);

        Map<String, Object> map5 = new HashMap<>(4);
        map5.put("name", ConstEnum.FOOD_DRINK.ename());
        map5.put("sort", ConstEnum.FOOD_DRINK.eval());
        list.add(map5);

        Map<String, Object> map6 = new HashMap<>(4);
        map6.put("name", ConstEnum.FOOD_FAT.ename());
        map6.put("sort", ConstEnum.FOOD_FAT.eval());
        list.add(map6);

        Map<String, Object> map7 = new HashMap<>(4);
        map7.put("name", ConstEnum.FOOD_SNACK.ename());
        map7.put("sort", ConstEnum.FOOD_SNACK.eval());
        list.add(map7);

        Map<String, Object> map8 = new HashMap<>(4);
        map8.put("name", ConstEnum.FOOD_OTHER.ename());
        map8.put("sort", ConstEnum.FOOD_OTHER.eval());
        list.add(map8);

        return RspEntity.ok(list);
    }



}
