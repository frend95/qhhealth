package com.hfkd.qhhealth.health.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.health.mapper.HealthPlanIntakeMapper;
import com.hfkd.qhhealth.health.mapper.HealthPlanItemMapper;
import com.hfkd.qhhealth.health.model.HealthPlanIntake;
import com.hfkd.qhhealth.health.model.HealthPlanItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 饮食计划已选食物 Controller
 * @author hexq
 * @date 2018-07-18
 */
@Verify
@RestController
@RequestMapping("/plan")
public class HealthPlanIntakeController {

    @Autowired
    private SessionUtil session;
    @Autowired
    private HealthPlanIntakeMapper intakeMapper;
    @Autowired
    private HealthPlanItemMapper itemMapper;

    @LogOut("选择食物")
    @RequestMapping("/selectFood")
    public Map<String, Object> selectFood(Integer foodId, Integer amount, Integer type) {
        Integer currId = session.getCurrId();
        HealthPlanItem food = itemMapper.selectById(foodId);
        Integer kcalPer100g = food.getKcalPer100g();
        // 查询是否已选择该食物
        HealthPlanIntake selectedFood = intakeMapper.getSelectedFood(currId, foodId, type);
        HealthPlanIntake intake;
        if (selectedFood != null) {
            // 计算总卡路里
            Integer totalAmount = amount + selectedFood.getAmount();
            Integer totalKcal = kcalPer100g * totalAmount;
            intake = new HealthPlanIntake(selectedFood.getId(), totalKcal, totalAmount);
            intakeMapper.updateById(intake);
        } else {
            // 计算总卡路里
            Integer totalKcal = kcalPer100g * amount;
            intake = new HealthPlanIntake(currId, foodId, amount, type, totalKcal);
            intakeMapper.addIntake(intake);
        }
        return RspUtil.ok(intakeMapper.getMyFood(intake.getId()));
    }

    @LogOut("编辑已选食物")
    @RequestMapping("/editMyFood")
    public Map<String, Object> editMyFood(Integer itemId, Integer amount) {
        if (amount == null || amount < 1) {
            return RspUtil.error("数量错误");
        }
        // 计算总卡路里
        Integer kcalPer100g = intakeMapper.getKcalPer100gByItemId(itemId);
        Integer kcal = amount * kcalPer100g;

        HealthPlanIntake intake = new HealthPlanIntake();
        intake.setId(itemId);
        intake.setAmount(amount);
        intake.setKcal(kcal);
        intakeMapper.updateById(intake);
        return RspUtil.ok(intakeMapper.getMyFood(itemId));
    }

    @LogOut("查询已选食物列表")
    @RequestMapping("/myFoods")
    public Map<String, Object> myFoods(Integer page, Integer size, Integer type) {
        size = size == null ? 10 : size;
        page = page <= 0 ? 0 : (page - 1) * size;
        Integer currId = session.getCurrId();
        List<HealthPlanIntake> list = intakeMapper.getMyFoods(page, size, currId, type);
        return RspUtil.ok(list);
    }

    @LogOut("删除已选择食物")
    @RequestMapping("/delMyFood")
    public Map<String, Object> delMyFood(Integer itemId) {
        intakeMapper.deleteById(itemId);
        return RspUtil.ok();
    }

    @LogOut("查询饮食分类标签")
    @RequestMapping("/intakeTag")
    @Cacheable("API_CACHE")
    public Map<String, Object> intakeTag() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>(4);
        map1.put("name", ConstEnum.INTAKE_BREAKFAST.ename());
        map1.put("type", ConstEnum.INTAKE_BREAKFAST.eval());
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>(4);
        map2.put("name", ConstEnum.INTAKE_LUNCH.ename());
        map2.put("type", ConstEnum.INTAKE_LUNCH.eval());
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>(4);
        map3.put("name", ConstEnum.INTAKE_DINNER.ename());
        map3.put("type", ConstEnum.INTAKE_DINNER.eval());
        list.add(map3);

        Map<String, Object> map4 = new HashMap<>(4);
        map4.put("name", ConstEnum.INTAKE_MEAL.ename());
        map4.put("type", ConstEnum.INTAKE_MEAL.eval());
        list.add(map4);

        return RspUtil.ok(list);
    }
}
