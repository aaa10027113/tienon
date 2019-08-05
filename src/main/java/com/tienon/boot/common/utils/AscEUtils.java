/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.common.utils;

import com.tienon.boot.common.constant.CommonStatic;

/**
 * @author zouhuaqiang
 * 
 * @Description 加密解密工具类
 * @date 2019/07/24日
 */
public class AscEUtils {
	private static AscEncrypt ascEncrypt = new AscEncrypt(CommonStatic.KEY);

	public static String encrypt(String msg) {
		return ascEncrypt.encrypt(msg);
	}

	public static String decrypt(String msg) {
		return ascEncrypt.decrypt(msg);
	}
}
