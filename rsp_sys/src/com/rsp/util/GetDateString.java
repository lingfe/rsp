package com.rsp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
  * 文件名：GetDateString.java
  * 描述： 时间字符串
  * 修改人： lingfe
  * 修改时间：2019年3月27日 下午5:25:41
  * 修改内容：
 */
public class GetDateString {

	/**
	 * 获取当前时间 yyyyMMdd
	 * 
	 * @return String
	 */
	public static String getDate() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMdd");
		String s = outFormat.format(now);
		return s;
	}
	
	
	
	
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getDateTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
}
