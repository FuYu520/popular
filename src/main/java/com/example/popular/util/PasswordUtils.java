package com.example.popular.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

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
}
