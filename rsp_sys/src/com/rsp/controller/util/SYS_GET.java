package com.rsp.controller.util;

/**
 * 
  * 文件名：SYS_GET.java
  * 描述： 系统设置参数
  * 修改人： lingfe
  * 修改时间：2018年11月15日 下午12:40:23
  * 修改内容：
 */
public  class SYS_GET {
	
	
	
	/**
	 * 是否开启会话验证
	 */
	public static boolean IS_SESSION_VALIDATE=false;
	
	/**
	 * get或set图片路径 server(服务器）
	 */
	//public static String  GET_IMG_PATH_URL="https://lingfe.club/zber_sys/";
	
	/**
	 * get或set图片路径 localhost(本地路径）
	 */
	public static String  GET_IMG_PATH_URL="http://localhost:8080/zber_sys/";



	/**
	 * 访问文件，路径地址，
	 * 集合nginx代理，代理转发到资源文件夹
	 */
	//public static String  GET_IMG_PATH_URL="https://lingfe.club/zber_sys/";//service
	//public static String GET_IMG_PATH_URL="http://localhost/uploadFiles/";//localhost
			
	/**
	 * 上传文件,路径地址
	 */
	//service
	//public static String  GET_IMG_PATH_URL="https://lingfe.club/zber_sys/";
	//localhost C:\AworkingSpace\git\rsp\rsp_web
	public static String SET_IMG_PATH_URL="C:\\AworkingSpace\\git\\rsp\\rsp_web\\uploadFiles\\";

}
