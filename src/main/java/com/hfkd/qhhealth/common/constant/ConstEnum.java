package com.hfkd.qhhealth.common.constant;

/**
 * 常量
 * @author hexq
 * @date 2017/11/15 14:02
 */
public enum ConstEnum {

    /**客户状态*/
    CUSTOMER_BUY("已购买", 2),
    CUSTOMER_NOT_BUY("未购买", 1),
    CUSTOMER_INVALID("无效", 0),

    /**VIP状态*/
    VIP_YES("是", 1),
    VIP_NO("否", 0),

    /**内容类型*/
    CONTENT_TYPE_ARTICLE("文章", 0),
    CONTENT_TYPE_VIDEO("视频", 1),
    CONTENT_TYPE_FEED("社圈动态", 2),

    /**用户状态：0无效,1信息未完善,2有效*/
    USER_STATUS_ENABLE("有效", 2),
    USER_STATUS_INCOMPLETE("信息未完善", 1),
    USER_STATUS_DISABLE("无效", 0),

    /**目标类型：0减肥,1塑形*/
    GOAL_SLIM("减肥", 0),
    GOAL_SHAPING("塑形", 1),

    /**食物分类：0五谷杂粮,1鱼肉蛋奶,2果蔬菌藻,3菜肴,4饮料,5油脂,6零食,7其他*/
    FOOD_CEREALS("五谷杂粮", 0),
    FOOD_MEAT("鱼肉蛋奶", 1),
    FOOD_GREEN("果蔬菌藻", 2),
    FOOD_DISHES("菜肴", 3),
    FOOD_DRINK("饮料", 4),
    FOOD_FAT("油脂", 5),
    FOOD_SNACK("零食", 6),
    FOOD_OTHER("其他", 7),

    /**性别：0女,1男*/
    USER_GENDER_FEMALE("女", 0),
    USER_GENDER_MALE("男", 1),

    /**视频类型：0案例,1教程*/
    VIDEO_TYPE_CASE("案例", 0),
    VIDEO_TYPE_TUTORIAL("教程", 1),

    /**视频标签：0中年发福,1产后肥胖,2青春型肥胖,3单纯性肥胖,4遗传性肥胖*/
    VIDEO_TAG_MIDAGE("中年发福", 0),
    VIDEO_TAG_PARTUM("产后肥胖", 1),
    VIDEO_TAG_YOUTH("青春性肥胖", 2),
    VIDEO_TAG_SIMPLE("单纯性肥胖", 3),
    VIDEO_TAG_GENETIC("遗传性肥胖", 4),

    /**文章标签：0饮食，1运动，2减肥方法，3食谱*/
    ARTICLE_TAG_DIET("饮食减肥", 0),
    ARTICLE_TAG_SPORT("运动减肥", 1),
    ARTICLE_TAG_METHOD("你所不知道的减肥大法", 2),
    ARTICLE_TAG_RECIPE("代餐食谱", 3),

    /**反馈类型：0建议,1问题*/
    SYS_FEEDBACK_SUGGESTION("建议", 0),
    SYS_FEEDBACK_ISSUE("问题", 1),

    /**页面：0 splash，1 onboarding，2 tutorial，3 case，4 social*/
    IMG_PAGE_SPLASH("splash", 0),
    IMG_PAGE_ONBOARDING("onboarding", 1),
    IMG_PAGE_TUTORIAL("tutorial", 2),
    IMG_PAGE_CASE("case", 3),
    IMG_PAGE_SOCIAL("social", 4),

    /**被关注者类型：0用户,1营养师*/
    USER("用户", 0),
    YYS("营养师", 1),

    /**动态是否私密：0否,1是*/
    FEED_PRIVATE_NO("否", 0),
    FEED_PRIVATE_YES("是", 1),

    /**体脂秤数据标准*/
    SCALES_LOW("偏低", 0),
    SCALES_NORMAL("标准", 1),
    SCALES_HIGH("偏高", 2),
    SCALES_VERYHIGH("高", 3),

    INTAKE_BREAKFAST("早餐", 0),
    INTAKE_LUNCH("午餐", 1),
    INTAKE_DINNER("晚餐", 2),
    INTAKE_MEAL("加餐", 3);

    private String name;
    private Integer value;

    ConstEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String ename() {
        return name;
    }

    public Integer eval() {
        return value;
    }
}
