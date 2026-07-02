package com.sdut.blood.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 隐私数据加解密工具
 * 采用AES对称加密，用于身份证号、病史等敏感字段的加密存储
 * 生产环境建议将密钥配置到配置文件中，课程项目可直接使用
 */
public class EncryptUtil {

    /**
     * 加密算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 加密密钥（必须16位）
     */
    private static final String SECRET_KEY = "sdut_blood_2026!!";

    /**
     * 数据加密
     *
     * @param content 明文内容
     * @return Base64编码后的密文
     */
    public static String encrypt(String content) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedBytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("敏感数据加密失败", e);
        }
    }

    /**
     * 数据解密
     *
     * @param encryptedContent Base64编码的密文
     * @return 明文内容
     */
    public static String decrypt(String encryptedContent) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedContent));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("敏感数据解密失败", e);
        }
    }
}