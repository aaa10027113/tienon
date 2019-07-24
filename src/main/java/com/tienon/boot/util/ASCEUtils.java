/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.util;

import com.tienon.boot.constant.CommonStatic;

/**
 * @author zouhuaqiang
 * @Description TODO
 * @date 2019/07/24日
 */
public class ASCEUtils {
    private static ASCEncrypt ascEncrypt = new ASCEncrypt(CommonStatic.KEY);

    public static String encrypt(String msg) {
        return ascEncrypt.encrypt(msg);
    }

    public static String decrypt(String msg) {
        return ascEncrypt.decrypt(msg);
    }

    public static void main(String[] args) {
        String msg = "今天天气不错啊";
        String after = encrypt(msg);
        System.out.println("密文：" + after);
        System.out.println("明文：" + decrypt(after));
    }
}
