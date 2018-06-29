package com.hexq.qh.common.util;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密算法
 * @author hexq
 * @date 2017年9月22日 下午3:21:46
 */
public class RsaUtil {
     /** 算法名称 */
    private static final String ALGORITHOM = "RSA";
    /** 密钥大小 */
    private static final int KEY_SIZE = 1024;
    /** 默认的安全服务提供者 */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();
    private static KeyPairGenerator keyPairGen = null;
    /** 缓存的密钥对。 */
    private static KeyPair oneKeyPair = null;
    private static String randomKey = "";

    static {
        try {
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM,
                    DEFAULT_PROVIDER);
            KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 生成并返回RSA密钥对。
     */
    public static synchronized KeyPair generateKeyPair() {
        keyPairGen.initialize(KEY_SIZE, new SecureRandom(randomKey.getBytes()));
        oneKeyPair = keyPairGen.generateKeyPair();
        return oneKeyPair;
    }

    /**
     * 返回已初始化的默认的公钥
     * @return 已初始化的默认的公钥
     */
    public static RSAPublicKey getDefaultPublicKey() {
        KeyPair keyPair = generateKeyPair();
        if (keyPair != null) {
            return (RSAPublicKey) keyPair.getPublic();
        }
        return null;
    }
    
     /**
     * 使用指定的私钥解密数据。
     * @param privateKey 给定的私钥。
     * @param data 要解密的数据。
     * @return 原数据。
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }

    /**
     * 加密数据
     * @param publicKeyStr 公钥字符串
     * @param data 需要加密的数据
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String publicKeyStr, String data) throws Exception {
        byte[] keyBytes = Base64Utils.decodeFromString(publicKeyStr);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = ci.doFinal(data.getBytes());
        byte[] encode = Hex.encode(bytes);
        return new String(encode);
    }

    /**
     * 解密数据
     * @param privateKeyStr 私钥
     * @param data 密文
     * @return 解密后的数据
     * @throws Exception
     */
    public static String decrypt(String privateKeyStr, String data) throws Exception {
        byte[] dataByte = Hex.decode(data);
        byte[] keyBytes = Base64Utils.decodeFromString(privateKeyStr);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(ci.doFinal(dataByte));
    }

    /**
     * 使用默认的私钥解密给定的字符串。
     * 若encryptText为null或空字符串则返回null。
     * 私钥不匹配时，返回null。
     * @param encryptText 密文。
     * @return 原文字符串。
     */
    public static String decryptString(String encryptText) {
        if(StringUtils.isBlank(encryptText)) {
            return null;
        }
        KeyPair keyPair = generateKeyPair();
        try {
            byte[] enData = Hex.decode(encryptText);
            byte[] data = decrypt((RSAPrivateKey)keyPair.getPrivate(), enData);
            return new String(data);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * 使用默认的私钥解密由JS加密（使用此类提供的公钥加密）的字符串。
     * 
     * @param encryptText 密文。
     * @return 原文字符串。
     */
    public static String decryptStringByJs(String encryptText) {
        String text = decryptString(encryptText);
        if(text == null) {
            return null;
        }
        return StringUtils.reverse(text);
    }

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = key.getBytes();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static void main(String[] args) {
        //密码种子，一个密码种子生产一组RSA密码
        RsaUtil.randomKey = "1111";

        //获取公钥，分发公钥（e1,n）
        RSAPublicKey publicKey = RsaUtil.getDefaultPublicKey();

        //公钥-系数(n)
        System.out.println("public key modulus:"
                + new String(Hex.encode(publicKey.getModulus().toByteArray())));

        //公钥-指数(e1)
        System.out.println("public key exponent:"
                + new String(Hex.encode(publicKey.getPublicExponent()
                        .toByteArray())));

        //JS加密后的字符串
        String pppp = "6e269bdf7f7670ffaff669d86d93e63fd91f2d6dd4f7e57ca58c614de65c0828e608bdd0f3e81d3630f8533f5286e0f55caa005ebe6d3faf3b9cc130d9ecff51d1f77eeed48156431cbf5de62cf6899084a4e92890827edbebbf506ecbb022de4a0e3131f2ffefe3368c7b7f929362edb4af419b837456c275f643a5c760a4ff";
        
        //解密后的字符串
        String kkkk = RsaUtil.decryptStringByJs(pppp);

        System.out.println("解密后文字：" + kkkk);
    }

}
