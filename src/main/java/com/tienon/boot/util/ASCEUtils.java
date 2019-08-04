/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.util;

import com.tienon.boot.business.constant.CommonStatic;

/**
 * @author zouhuaqiang
 * @Description 加密解密工具类
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
}
