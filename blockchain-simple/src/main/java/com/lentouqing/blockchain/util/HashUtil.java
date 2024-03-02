package com.lentouqing.blockchain.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成电子签名的工具类，采用SHA-256算法
 */
public class HashUtil {

    /**
     * 利用Apache的工具类实现SHA-256加密
     * @param input
     * @return
     */
    public static String applySha256(String input) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(input.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    public static void main(String[] args) {
        System.out.println(HashUtil.applySha256("123"));
    }
}
