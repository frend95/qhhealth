package com.hexq.qh.common.util;

import java.io.*;

/**
 * 序列化与反序列化工具类
 * @author hexq
 * @date 2017年9月22日 下午3:21:46
 */
public class SerializeUtil {

    /**
     * 序列化方法
     * @param object 对象
     * @return byte[]
     */
    public static byte[] serialize(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
            return bos.toByteArray();
        }
    }

    /**
     * 反序列化方法
     * @param bytes byte array
     * @return Object
     */
    public static Object deserialization(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        }
    }

}
