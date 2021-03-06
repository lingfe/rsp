package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_sys_system_log;

/**
 * 
  * 文件名：Isystem_log.java
  * 描述： 系统日志表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年3月20日 下午5:13:38
  * 修改内容：
 */
public interface Isys_system_log {
	
	/**
	 * 
	 * 得到系统日志总数据量
	 * @author lingfe     
	 * @created 2019年3月21日 下午4:38:12  
	 * @return
	 */
	int getCount();
	
	/**
	 * 
	 * 分页查询系统日志
	 * @author lingfe     
	 * @created 2019年3月20日 下午5:16:17  
	 * @param map 参数
	 * @return
	 */
	List<Tab_sys_system_log> pageSelect(Map<String, Object> map);

	/**
	 * 
	 * 添加一条系统日志
	 * @author lingfe     
	 * @created 2019年3月20日 下午5:14:53  
	 * @param syslog
	 * @return
	 */
	int add(Tab_sys_system_log syslog);
}
