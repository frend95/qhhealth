package com.hexq.qh.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Map;


/**
 * 对javabean进行快速处理
 * @author hexq
 * @date 2017/11/15  14:02
 */
public class BeanUtils {

    /**
     * 将Map转bean，通过反射机制
     * @param map map
     * @param beanClass 需要转的bean类型
     * @return obj,需强转成所需bean
     * @throws Exception
     */
    public static Object map2Bean(Map<String, Object> map, Class<?> beanClass) throws Exception {
        //传入map为空，返回null
        if (map == null) {
            return null;
        }
        Object obj;
        try {
            obj = beanClass.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                if(field.getType().equals(Long.class)){             //属性为Long类型的，将map中obj--->Long
                    field.set(obj, TypeUtil.obj2Long(map.get(field.getName())));
                }else if(field.getType().equals(Integer.class)){    //属性为Integer类型的，将map中obj--->Integer
                    field.set(obj, TypeUtil.obj2Integer(map.get(field.getName())));
                }else if(field.getType().equals(BigDecimal.class)){    //属性为BigDecimal类型的，将map中obj--->BigDecimal
                    field.set(obj, TypeUtil.obj2BigDecimal(map.get(field.getName())));
                }else{
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("map类型转bean失败！");
        }
        return obj;
    }

}
