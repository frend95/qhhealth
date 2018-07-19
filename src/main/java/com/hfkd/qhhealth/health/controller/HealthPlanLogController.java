package com.hfkd.qhhealth.health.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.constant.ConstVal;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.util.SessionUtil;
import com.hfkd.qhhealth.health.mapper.HealthPlanIntakeMapper;
import com.hfkd.qhhealth.health.model.HealthPlanConst;
import com.hfkd.qhhealth.health.model.HealthPlanIntake;
import com.hfkd.qhhealth.health.service.HealthPlanConstService;
import com.hfkd.qhhealth.health.service.IHealthPlanIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 饮食日常记录 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@Verify
@RestController
@RequestMapping("/plan")
public class HealthPlanLogController {

    @Autowired
    private HealthPlanConstService planConstService;
    @Autowired
    private IHealthPlanIntakeService intakeService;
    @Autowired
    private SessionUtil session;
    @Autowired
    private HealthPlanIntakeMapper intakeMapper;

    private static final String ICON_BREAKFAST = "https://app.xintianhong888.com/img/breakfast_icon.jpg";
    private static final String ICON_LUNCH = "https://app.xintianhong888.com/img/lunch_icon.jpg";
    private static final String ICON_DINNER = "https://app.xintianhong888.com/img/dinner_icon.jpg";
    private static final String ICON_MEAL = "https://app.xintianhong888.com/img/meal_icon.jpg";
    private static final String BG_BREAKFAST = "https://app.xintianhong888.com/img/breakfast_bg.jpg";
    private static final String BG_LUNCH = "https://app.xintianhong888.com/img/lunch_bg.jpg";
    private static final String BG_DINNER = "https://app.xintianhong888.com/img/dinner_bg.jpg";
    private static final String BG_MEAL = "https://app.xintianhong888.com/img/meal_bg.jpg";

    @LogOut("饮食计划主页")
    @RequestMapping("/planPage")
    public Map<String, Object> planPage() {
        Integer currId = session.getCurrId();

        HealthPlanConst planConst = planConstService.getPlanConst();
        Integer breakfastTh = planConst.getBreakfastThreshold();
        Integer lunchTh = planConst.getLunchThreshold();
        Integer dinnerTh = planConst.getDinnerThreshold();
        Integer mealTh = planConst.getMealThreshold();
        Integer total = planConst.getTotalBudget();
        Integer intake = 0;

        List<Map<String, Object>> intakeSortByType = intakeService.getTotalIntakeSortByType(currId);

        for (Map<String, Object> map : intakeSortByType) {
            Integer type = (Integer) map.get("type");
            switch (type) {
                case ConstVal.INTAKE_BREAKFAST:
                    List<HealthPlanIntake> breakfastLs = intakeMapper.getMyFoods(0, 20, currId, type);
                    map.put("foods", breakfastLs);
                    map.put("name", ConstEnum.INTAKE_BREAKFAST.ename());
                    map.put("suggestion", breakfastTh);
                    map.put("icon", ICON_BREAKFAST);
                    map.put("background", BG_BREAKFAST);
                    break;
                case ConstVal.INTAKE_LUNCH:
                    List<HealthPlanIntake> lunchLs = intakeMapper.getMyFoods(0, 20, currId, type);
                    map.put("foods", lunchLs);
                    map.put("name", ConstEnum.INTAKE_LUNCH.ename());
                    map.put("suggestion", lunchTh);
                    map.put("icon", ICON_LUNCH);
                    map.put("background", BG_LUNCH);
                    break;
                case ConstVal.INTAKE_DINNER:
                    List<HealthPlanIntake> dinnerLs = intakeMapper.getMyFoods(0, 20, currId, type);
                    map.put("foods", dinnerLs);
                    map.put("name", ConstEnum.INTAKE_DINNER.ename());
                    map.put("suggestion", dinnerTh);
                    map.put("icon", ICON_DINNER);
                    map.put("background", BG_DINNER);
                    break;
                case ConstVal.INTAKE_MEAL:
                    List<HealthPlanIntake> mealLs = intakeMapper.getMyFoods(0, 20, currId, type);
                    map.put("foods", mealLs);
                    map.put("name", ConstEnum.INTAKE_MEAL.ename());
                    map.put("suggestion", mealTh);
                    map.put("icon", ICON_MEAL);
                    map.put("background", BG_MEAL);
                    break;
                default:
            }
            Integer kcal = Integer.parseInt(String.valueOf(map.get("kcal"))) ;
            intake = intake + kcal;
        }

        Integer remain = total - intake;
        HashMap<Object, Object> map = new HashMap<>(8);
        map.put("remain", remain < 0 ? 0 : remain);
        map.put("intakeList", intakeSortByType);
        map.put("total", total);
        map.put("intake", intake);
        return RspUtil.ok(map);
    }

}
