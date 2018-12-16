package com.example.demotool.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Des3 {
	private static final String Algorithm = "DESede"; // 定义 加密算法,可用

	// DES,DESede,Blowfish
	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(String keybyte, byte[] src) throws Exception {
		// 生成密钥
		SecretKey deskey = new SecretKeySpec(Base64.decodeBase64(keybyte.getBytes()), Algorithm);
		// 加密
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] ptext = c1.doFinal(src);
		return Base64.encodeBase64(ptext);
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(String keybyte, byte[] src) throws Exception {
		byte[] base64Str = Base64.decodeBase64(keybyte.getBytes());
		// 生成密钥
		SecretKey deskey = new SecretKeySpec(base64Str, Algorithm);
		// 解密
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		return c1.doFinal(src);
	}

	// 转换成十六进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

}
