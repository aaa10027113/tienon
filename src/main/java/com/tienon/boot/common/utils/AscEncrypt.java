/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.common.utils;

/**
 * @author zouhuaqiang
 * @Description 加密解密
 * @date 2019/07/24日
 */

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AscEncrypt {

	private String key;

	public AscEncrypt(String key) {

		this.key = key;
	}

	public String encrypt(String string) {
		String base64Encode = null;
		try {
			base64Encode = base64Encode(aesEncryptToBytes(string, key));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return HexUtil.bytes2Hex(base64Encode.getBytes());

	}

	public String decrypt(String string) {

		byte[] bytes = HexUtil.hex2Bytes(string);
		String aesDecrypt = null;
		try {
			String string2 = new String(bytes, "utf-8");
			aesDecrypt = aesDecrypt(string2, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aesDecrypt;
	}

	/**
	 * 将base 64 code AES解密
	 *
	 * @param encryptStr
	 *            待解密的base 64 code
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
		return ("".equals(encryptStr) || encryptStr == null) ? null
				: aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
	}

	/**
	 * AES解密
	 *
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {

		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(decryptKey.getBytes()));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);

		return new String(decryptBytes);
	}

	/**
	 * base 64 decode
	 *
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode(String base64Code) throws Exception {
		return ("".equals(base64Code) || base64Code == null) ? null : new BASE64Decoder().decodeBuffer(base64Code);
	}

	/**
	 * base 64 encode
	 *
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	/**
	 * AES加密
	 *
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(encryptKey.getBytes()));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

		return cipher.doFinal(content.getBytes("utf-8"));
	}
}
