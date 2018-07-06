package com.hfkd.qhhealth.common.util;

import java.util.Random;

/**
 * 随机数util
 * @author hexq
 * @date 2017/11/15 14:02
 */
public class RandomUtil {

    /**
     * 生成6位随机数
     * @return 随机数String
     */
    public static String getRandom(){
        Random random = new Random();
        return String.valueOf(random.nextInt(900000) + 100000);
    }

}
