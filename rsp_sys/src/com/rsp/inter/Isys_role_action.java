package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_sys_role_action;

/**
 * 
  * 文件名：Isys_role_action_action.java
  * 描述： 角色权限表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年4月12日 下午4:57:28
  * 修改内容：
 */
public interface Isys_role_action {
	
	/**
	 * 
	 * 根据角色id，权限id得到角色权限数据
	 * @author lingfe     
	 * @created 2019年4月12日 下午6:00:38  
	 * @param role_id 角色id
	 * @param action_id 权限id
	 * @return
	 */
	Tab_sys_role_action getWhereRoleIdAndActionIdList(String role_id,Integer action_id);
	
	/**
	 * 
	 * 根据角色权限id标识删除
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:02:47  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);

	/**
	 * 
	 * 根据id修改角色权限信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:01:47  
	 * @param tab
	 * @return
	 */
	int update(Tab_sys_role_action tab);
	
	/**
	 * 
	 * 总数据量
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:00:54  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 分页查询角色权限信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:00:15  
	 * @param id
	 * @return
	 */
	List<Tab_sys_role_action> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据角色权限id标识获取
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:58:47  
	 * @param id
	 * @return
	 */
	Tab_sys_role_action getWhereId(String id);
	
	/**
	 * 
	 * 保存角色权限
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:57:51  
	 * @param tab
	 * @return
	 */
	int save(Tab_sys_role_action tab);
}