package com.hexq.qh.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 信息摘要
 * @author hexq
 * @date 2017/11/15  14:02
 */
public class DigestUtil {

    /**
     * 用md5散列算法给指定字符串进行摘要
     * @param str 需要hash的字符串
     * @return String hash后字符串
     */
    public static String md5(String str) {
        try {
            // 生成一个MD5摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 密码加盐
     * @param pwdHash hash过的密码
     * @param salt 盐
     * @return 重新hash过的密码
     */
    public static String pwdSalt(String pwdHash, String salt) {
        int pwdLength = pwdHash.length();
        int i = pwdLength / 2;
        int saltLength = salt.length();
        int j = saltLength / 3;

        String str1 = pwdHash.substring(0, i);
        String str2 = pwdHash.substring(i, pwdLength - 1);

        String str3 = salt.substring(0, j);
        String str4 = salt.substring(j, saltLength - 1);

        String str5 = new StringBuilder()
                .append(str2)
                .append(str4)
                .append(str3)
                .append(str1)
                .toString();
        return md5(str5);
    }



}
