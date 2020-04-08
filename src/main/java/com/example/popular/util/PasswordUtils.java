package com.example.popular.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @description:
 * @author: FuYu
 * @create: 03/20/2020
 **/
public class PasswordUtils {

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            SecureRandomNumberGenerator randomNumberGenerator =
                    new SecureRandomNumberGenerator();
            String hex = randomNumberGenerator.nextBytes().toHex();
            System.out.println(hex);
        }
    }

    private static final String salt = "1!2@3#4$";

    /**
     * input 表单提交加密
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    /**
     * 表单数据提交到数据库加密
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }
}
