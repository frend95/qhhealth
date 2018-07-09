package com.hfkd.qhhealth.common.constant;

/**
 * 常量
 * @author hexq
 * @date 2017/11/15 14:02
 */
public enum ConstEnum {

    /**客户状态*/
    CUSTOMER_BUY("2"),
    CUSTOMER_NOT_BUY("1"),
    CUSTOMER_INVALID("0"),

    /**VIP状态*/
    VIP_YES("1"),
    VIP_NO("0"),

    /**媒体类型*/
    MEDIA_TYPE_IMAGE("0"),
    MEDIA_TYPE_VIDEO("1"),

    /**用户状态：0无效,1信息未完善,2有效*/
    USER_STATUS_ENABLE("2"),
    USER_STATUS_INCOMPLETE("1"),
    USER_STATUS_DISABLE("0"),

    /**目标类型：0减肥,1塑形*/
    GOAL_SLIM("0"),
    GOAL_SHAPING("1"),

    /**食物分类：0五谷杂粮,1鱼肉蛋奶,2果蔬菌藻,3菜肴,4饮料,5油脂,6零食,7其他*/
    FOOD_CEREALS("0"),
    FOOD_MEAT("1"),
    FOOD_GREEN("2"),
    FOOD_DISHES("3"),
    FOOD_DRINK("4"),
    FOOD_FAT("5"),
    FOOD_SNACK("6"),
    FOOD_OTHER("7"),

    /**性别：0女,1男*/
    USER_GENDER_FEMALE("0"),
    USER_GENDER_MALE("1"),

    /**视频类型：0案例,1教程*/
    VIDEO_TYPE_CASE("0"),
    VIDEO_TYPE_TUTORIAL("1"),

    /**视频标签：0中年发福,1产后肥胖,2青春型肥胖,3单纯性肥胖,4遗传性肥胖*/
    VIDEO_TAG_MIDAGE("0"),
    VIDEO_TAG_PARTUM("1"),
    VIDEO_TAG_YOUTH("2"),
    VIDEO_TAG_SIMPLE("3"),
    VIDEO_TAG_GENETIC("4"),

    /**反馈类型：0建议,1问题*/
    SYS_FEEDBACK_SUGGESTION("0"),
    SYS_FEEDBACK_ISSUE("1"),

    /**页面：0 splash，1 onboarding，2 tutorial，3 case，4 social*/
    IMG_PAGE_SPLASH("0"),
    IMG_PAGE_ONBOARDING("1"),
    IMG_PAGE_TUTORIAL("2"),
    IMG_PAGE_CASE("3"),
    IMG_PAGE_SOCIAL("4"),

    /**被关注者类型：0用户,1营养师*/
    FOLLOW_TYPE_USER("0"),
    FOLLOW_TYPE_YYS("1"),

    /**动态是否私密：0否,1是*/
    FEED_PRIVATE_NO("0"),
    FEED_PRIVATE_YES("1"),

    /**作者类型：0用户,1营养师*/
    FEED_AUTHOR_USER("0"),
    FEED_AUTHOR_YYS("1");


    private String value;

    ConstEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
