package com.hexq.qh.common.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * obj互转,用于Map内obj类型转换，拒绝传递(String)" "格式，否则抛错
 * @author hexq
 * @date 2017年9月11日 下午6:55:13
 */
public class TypeUtil {

    /**
     * obj-->Long
     * @param obj 类型
     * @return Long 类型
     */
    public static Long obj2Long(Object obj){
        if(obj == null) {
            return null;
        }
        if(StringUtils.isBlank(String.valueOf(obj))) {
            return null;
        }
        return new Long(obj.toString());
    }

    /**
     * obj-->Integer
     * @param obj 类型
     * @return Integer 类型
     */
    public static Integer obj2Integer(Object obj){
        if(obj == null || StringUtils.isBlank(String.valueOf(obj))) {
            return 0;
        }
        return new Integer(obj.toString());
    }

    /**
     * obj-->int
     * @param obj 类型
     * @return int 类型
     */
    public static int obj2Int(Object obj){
        return obj2Integer(obj);
    }

    /**
     * obj-->BigDecimal
     * @param obj 类型
     * @return BigDecimal 类型
     */
    public static BigDecimal obj2BigDecimal(Object obj){
        BigDecimal result = new BigDecimal(0.0);
        if(obj == null || StringUtils.isBlank(String.valueOf(obj)) || "NaN".equals(obj)) {
            return result;
        }
        if(obj instanceof String){
            return new BigDecimal(obj.toString());
        }
        if(obj instanceof BigDecimal) {
            return (BigDecimal)obj;
        }
        if(obj instanceof Number) {
            return new BigDecimal(((Number)obj).doubleValue());
        }
        return result;
    }

    public static BigDecimal obj2BigDecimal2(Object obj){
        BigDecimal result = new BigDecimal(0.0);
        if(obj == null || StringUtils.isBlank(String.valueOf(obj)) || "NaN".equals(obj)) {
            return result;
        }
        String str = obj.toString().substring(1, obj.toString().length()-1);
        if(obj instanceof String){
            return new BigDecimal(str);
        }
        return result;
    }

    /**
     * obj-->BigDecimal
     * @param obj 类型
     * @return BigDecimal 类型
     */
    public static String obj2String(Object obj){
        if(obj == null || StringUtils.isBlank(String.valueOf(obj)) || "NaN".equals(obj)) {
            return null;
        } else {
            return obj.toString();
        }
    }

}
