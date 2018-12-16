package com.example.demotool.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 加密解密工具
 * @author Administrator
 *
 */
public class EncDecUtil {

	private static Logger logger = Logger.getLogger(EncDecUtil.class);
	
	/**
	 * 解密
	 * @param encMsg
	 * @return
	 */
	public static String dec(String key,String encMsg){
		String decMsg="";
		//空字符串不要编码直接返回
		if("".equals(encMsg)){
			return decMsg;
		}
		try{
			byte[] srcBytes = Des3.decryptMode(key, Base64.decodeBase64(encMsg.getBytes()));
			decMsg =new String(srcBytes,"UTF-8");
		}catch(Exception e){
			logger.warn(encMsg+"--->"+e.getMessage());
			decMsg="";
		}
		return decMsg;
	}
	
	/**
	 * 加密
	 * @param msg
	 * @return
	 */
	public static String enc(String key,String msg){
		String encMsg="";
		if("".equals(msg)){
			return encMsg;
		}
		try{
			byte[] encoded = Des3.encryptMode(key, msg.getBytes("UTF-8"));
			encMsg=new String(encoded);
		}catch(Exception e){
			logger.warn(msg+"--->"+e.getMessage());
			encMsg="";
		}
		return encMsg;
	}
	
}
