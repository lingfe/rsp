package com.rsp.controller.util;

import com.rsp.model.JosnModel;
import com.rsp.model.Tab_sys_role_action;
import com.rsp.model.Tab_sys_role_mapping;
import com.rsp.service.Isys_role_actionService;
import com.rsp.service.Isys_role_mappingService;

/**
 * 
  * 文件名：ActionUtils.java
  * 描述： 权限类
  * 修改人： lingfe
  * 修改时间：2019年4月12日 上午9:25:50
  * 修改内容：
 */
public class ActionUtils {
	
	/**
	 * 新增权限
	 */
	public static int ADD_ACTION=1;
	
	/**
	 * 修改权限
	 */
	public static int UPDATE_ACTION=2;
	
	/**
	 * 删除权限
	 */
	public static int DELETE_ACTION=3;
	
	/**
	 * 查询权限
	 */
	public static int SELECT_ACTION=4;
	
	//-----------------------------
	
	/**
	 * 新增角色
	 */
	public static int ADD_ROLE=5;
	
	/**
	 * 修改角色
	 */
	public static int UPDATE_ROLE=6;
	
	/**
	 * 删除角色
	 */
	public static int DELETE_ROLE=7;
	
	/**
	 * 查询角色
	 */
	public static int SELECT_ROLE=8;
	
	//-----------------------------
	
	/**
	 * 新增角色权限
	 */
	public static int ADD_ROLE_ACTION=9;
	
	/**
	 * 修改角色权限
	 */
	public static int UPDATE_ROLE_ACTION=10;
	
	/**
	 * 删除角色权限
	 */
	public static int DELETE_ROLE_ACTION=11;
	
	/**
	 * 查询角色权限
	 */
	public static int SELECT_ROLE_ACTION=12;
	
	//-----------------------------
	
	/**
	 * 新增角色映射
	 */
	public static int ADD_ROLE_MAPPING=13;
	
	/**
	 * 修改角色映射
	 */
	public static int UPDATE_ROLE_MAPPING=14;
	
	/**
	 * 删除角色映射
	 */
	public static int DELETE_ROLE_MAPPING=15;
	
	/**
	 * 查询角色映射
	 */
	public static int SELECT_ROLE_MAPPING=16;
	
	
	
	
	
	/**
	 * 
	 * 验证用户是否有权限,得到角色和权限
	 * @author lingfe     
	 * @created 2019年4月15日 上午11:07:26  
	 * @param isys_role_actionService	角色权限
	 * @param isys_role_mappingService	角色映射
	 * @param josn
	 * @return
	 */
	public static JosnModel<Object> isActionis(Isys_role_actionService isys_role_actionService,
			Isys_role_mappingService isys_role_mappingService, JosnModel<Object> josn) {
		//得到角色映射信息
		Tab_sys_role_mapping role_mapping = isys_role_mappingService.getWhereUserId(josn.data.toString());
		if(role_mapping!=null){
			//根据角色id，得到角色权限
			Tab_sys_role_action role_action = isys_role_actionService.getWhereRoleIdAndActionIdList(role_mapping.getRole_id(),ADD_ACTION);;
			if(role_action == null){
				josn.msg="您没有操作权限!";
				josn.state=402;
				return josn;
			}
		}else{
			josn.msg="您没有角色,请联系管理员设置";
			return josn;
		}
		
		return josn;
	}

}
