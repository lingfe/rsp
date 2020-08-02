package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_sys_action;

/**
 * 
  * 文件名：Isys_action.java
  * 描述： 权限表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年4月12日 下午2:56:50
  * 修改内容：
 */
public interface Isys_action {
	
	/**
	 * 
	 * 根据权限id标识删除
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:02:47  
	 * @param id
	 * @return
	 */
	int deleteWhereId(Integer id);

	/**
	 * 
	 * 根据id修改权限信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:01:47  
	 * @param tab
	 * @return
	 */
	int update(Tab_sys_action tab);
	
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
	 * 分页查询权限信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:00:15  
	 * @param id
	 * @return
	 */
	List<Tab_sys_action> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据权限id标识获取
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:58:47  
	 * @param id
	 * @return
	 */
	Tab_sys_action getWhereId(Integer id);
	
	/**
	 * 
	 * 保存权限
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:57:51  
	 * @param tab
	 * @return
	 */
	int save(Tab_sys_action tab);
}
