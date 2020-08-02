package com.rsp.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.rsp.model.Tab_sys_system_log;
import com.rsp.service.Isys_system_logService;

/**
 * 
  * 文件名：LogUtils.java
  * 描述： 日志工具类
  * 修改人： lingfe
  * 修改时间：2019年4月17日 下午5:33:53
  * 修改内容：
 */
public class LogUtils {
	
	/**
	 * 其他操作
	 */
	public static int OTHER=0;

	/**
	 * 新增/保存/添加,操作
	 */
	public static int ADD=1;
	
	/**
	 * 修改/更新，操作
	 */
	public static int UPDATE=2;
	
	/**
	 * 删除
	 */
	public static int DELETE=3;
	
	/**
	 * 查询
	 */
	public static int SELECT=4;

	
	/**
	 * 
	 * 记录系统日志
	 * @author lingfe     
	 * @created 2019年4月17日 下午5:57:00  
	 * @param request 请求
	 * @param cz	操作类型
	 * @param flag_bug 是否有bug
	 * @param model_name 模块名称
	 * @param msg 反馈内容
	 * @param creator 创建人
	 * @param isys_system_logService
	 */
	public static void jieLuSystemLog(HttpServletRequest request, int sELECT, int flag_bug, String model_name,
			Object msg, HttpSession session, Isys_system_logService isys_system_logService) {
		//实例化对象
		Tab_sys_system_log sysLog=new Tab_sys_system_log();
		//赋值
		sysLog.setIp(GetIpUtil.getIpAddr(request));
		sysLog.setModel_name(model_name);
		//得到userId 用户id
		Object creator=session.getAttribute("userid");
		if(!StringUtils.isEmpty(creator)){
			sysLog.setCrt_code(creator.toString());
		}
		//得到hospital_id 医院id
		Object hospital_id=session.getAttribute("hospital_id");
		if(!StringUtils.isEmpty(hospital_id)){
			sysLog.setHospital_id(hospital_id.toString());
		}
		//得到目标id
		Object target_id=session.getAttribute("target_id");
		if(!StringUtils.isEmpty(target_id)){
			sysLog.setTarget_id(target_id.toString());
		}
		sysLog.setTarget_name(request.getRequestURI());
		//操作说明
		if(msg!=null){
			sysLog.setYc_detailed(msg.toString());
		}
		//添加系统日志
		isys_system_logService.add(sysLog);
	}
}
