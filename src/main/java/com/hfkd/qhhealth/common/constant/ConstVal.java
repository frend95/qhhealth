package com.hfkd.qhhealth.common.constant;

/**
 * 常量
 * @author hexq
 * @date 2018/7/13 13:58
 */
public class ConstVal {

    /**客户状态*/
    public static final String CUSTOMER_BUY = "2";
    public static final String CUSTOMER_NOT_BUY = "1";
    public static final String CUSTOMER_INVALID = "0";

    /**VIP状态*/
    public static final String VIP_YES = "1";
    public static final String VIP_NO = "0";

    /**内容类型*/
    public static final String CONTENT_TYPE_ARTICLE = "0";
    public static final String CONTENT_TYPE_VIDEO = "1";
    public static final String CONTENT_TYPE_FEED = "2";

    /**用户状态：0无效,1信息未完善,2有效*/
    public static final String USER_STATUS_ENABLE = "2";
    public static final String USER_STATUS_INCOMPLETE = "1";
    public static final String USER_STATUS_DISABLE = "0";

    /**目标类型：0减肥,1塑形*/
    public static final String GOAL_SLIM = "0";
    public static final String GOAL_SHAPING = "1";

    /**食物分类：0五谷杂粮,1鱼肉蛋奶,2果蔬菌藻,3菜肴,4饮料,5油脂,6零食,7其他*/
    public static final String FOOD_CEREALS = "0";
    public static final String FOOD_MEAT = "1";
    public static final String FOOD_GREEN = "2";
    public static final String FOOD_DISHES = "3";
    public static final String FOOD_DRINK = "4";
    public static final String FOOD_FAT = "5";
    public static final String FOOD_SNACK = "6";
    public static final String FOOD_OTHER = "7";

    /**性别：0女,1男*/
    public static final String USER_GENDER_FEMALE = "0";
    public static final String USER_GENDER_MALE = "1";

    /**视频类型：0案例,1教程*/
    public static final String VIDEO_TYPE_CASE = "0";
    public static final String VIDEO_TYPE_TUTORIAL = "1";

    /**视频标签：0中年发福,1产后肥胖,2青春型肥胖,3单纯性肥胖,4遗传性肥胖*/
    public static final String VIDEO_TAG_MIDAGE = "0";
    public static final String VIDEO_TAG_PARTUM = "1";
    public static final String VIDEO_TAG_YOUTH = "2";
    public static final String VIDEO_TAG_SIMPLE = "3";
    public static final String VIDEO_TAG_GENETIC = "4";

    /**文章标签：0饮食，1运动，2减肥方法，3食谱*/
    public static final String ARTICLE_TAG_DIET = "0";
    public static final String ARTICLE_TAG_SPORT = "1";
    public static final String ARTICLE_TAG_METHOD = "2";
    public static final String ARTICLE_TAG_RECIPE = "3";

    /**反馈类型：0建议,1问题*/
    public static final String SYS_FEEDBACK_SUGGESTION = "0";
    public static final String SYS_FEEDBACK_ISSUE = "1";

    /**页面：0 splash，1 onboarding，2 tutorial，3 case，4 social*/
    public static final String IMG_PAGE_SPLASH = "0";
    public static final String IMG_PAGE_ONBOARDING = "1";
    public static final String IMG_PAGE_TUTORIAL = "2";
    public static final String IMG_PAGE_CASE = "3";
    public static final String IMG_PAGE_SOCIAL = "4";

    /**被关注者类型：0用户,1营养师*/
    public static final String USER = "0";
    public static final String YYS = "1";

    /**动态是否私密：0否,1是*/
    public static final String FEED_PRIVATE_NO = "0";
    public static final String FEED_PRIVATE_YES = "1";

    /**评论是否审核：0否,1是*/
    public static final String CMT_UNREVIEWED = "0";
    public static final String CMT_REVIEWED = "1";

    /**体脂秤数据标准*/
    public static final int SCALES_LOW = 0;
    public static final int SCALES_NORMAL = 1;
    public static final int SCALES_HIGH = 2;
    public static final int SCALES_VERYHIGH = 3;

    public static final int INTAKE_BREAKFAST = 0;
    public static final int INTAKE_LUNCH = 1;
    public static final int INTAKE_DINNER = 2;
    public static final int INTAKE_MEAL = 3;

}
