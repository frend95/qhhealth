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

    /**
     * 生成 0 ～ limit的随机数
     * @param limit 范围
     * @return 随机数
     */
    public static int getRandom(int limit){
        Random random = new Random();
        return random.nextInt(limit + 1);
    }

    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;

        if (max < min || n > len) {
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

}
