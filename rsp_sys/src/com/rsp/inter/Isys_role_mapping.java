package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_sys_role_mapping;

/**
 * 
  * 文件名：Isys_role_mapping.java
  * 描述： 角色映射映射表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年4月12日 下午5:28:46
  * 修改内容：
 */
public interface Isys_role_mapping {
	
	/**
	 * 
	 * 根据user_id得到一条角色映射信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午5:50:50  
	 * @param user_id	员工id/用户id/管理员id
	 * @return
	 */
	Tab_sys_role_mapping getWhereUserId(String user_id);
	
	/**
	 * 
	 * 根据角色映射id标识删除
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:02:47  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);

	/**
	 * 
	 * 根据id修改角色映射信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:01:47  
	 * @param tab
	 * @return
	 */
	int update(Tab_sys_role_mapping tab);
	
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
	 * 分页查询角色映射信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:00:15  
	 * @param id
	 * @return
	 */
	List<Tab_sys_role_mapping> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据角色映射id标识获取
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:58:47  
	 * @param id
	 * @return
	 */
	Tab_sys_role_mapping getWhereId(String id);
	
	/**
	 * 
	 * 保存角色映射
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:57:51  
	 * @param tab
	 * @return
	 */
	int save(Tab_sys_role_mapping tab);
}
