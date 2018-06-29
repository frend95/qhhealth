package com.hexq.qh.common.util;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * UUID主键工具类
 * @author yangzhi
 * @date 2017年9月21日 上午9:50:34
 */
public class UUIDUtil {

    /**
     * 生成UUID不带 "-"
     * @return UUID
     */
    public static String getUUid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * 生成100000000~999999999的随机数并转换成字符串
     * @return String
     */
    private static String getStr(){
        int min = 100000000;
        int max = 999999999;
        Integer number = new Random().nextInt(max - min) + min;
        return number.toString();
    }

    /**
     * 生成集合里没有的字符串序列
     * @param strList string list
     * @return String
     */
    public static String getSeq(List<String> strList){
        boolean bool = false;
        while(true){
            String str = getStr();
            for(String s : strList){
                if(s.equals(str)){
                    bool = true;
                }
            }
            if(!bool){
                return str;
            }
        }
    }



}
