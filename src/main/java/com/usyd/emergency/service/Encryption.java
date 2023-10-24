package com.usyd.emergency.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: JunyuLiang
 * @Date: 2023/10/24 - 10 - 24 -15:45
 */
public class Encryption {
    public static String getMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        }
    }
}
