package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_sys_department_coding;

/**
 * 
  * 文件名：Isys_department_coding.java
  * 描述： 科室编码数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年4月8日 下午4:40:27
  * 修改内容：
 */
public interface Isys_department_coding {
	
	
	/**
	 * 
	 * 根据条件查询科室编码信息
	 * @author lingfe     
	 * @created 2019年4月11日 上午11:06:08  
	 * @param map
	 * @return
	 */
	List<Tab_sys_department_coding> getWhere(Map<String, Object> map);
	
	
	/**
	 * 
	 * 根据科室编码名称查询编码信息
	 * @author lingfe     
	 * @created 2019年3月28日 下午4:48:39  
	 * @param codingName
	 * @return
	 */
	Tab_sys_department_coding getWhereCodingName(String coding_name);
	
	/**
	 * 
	 * 根据id标识删除科室编码信息
	 * @author lingfe     
	 * @created 2019年4月8日 下午4:48:20  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 修改科室编码信息
	 * @author lingfe     
	 * @created 2019年4月8日 下午4:47:33  
	 * @param tab
	 * @return
	 */
	int update(Tab_sys_department_coding tab);
	
	/**
	 * 
	 * 获取总数据量
	 * @author lingfe     
	 * @created 2019年4月8日 下午4:45:07  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 分页查询科室编码信息
	 * @author lingfe     
	 * @created 2019年4月8日 下午4:43:55  
	 * @param map
	 * @return
	 */
	List<Tab_sys_department_coding> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据id标识查询科室编码信息
	 * @author lingfe     
	 * @created 2019年4月8日 下午4:42:57  
	 * @param id
	 * @return
	 */
	Tab_sys_department_coding getWhereId(String id);

	/**
	 * 
	 * 保存科室编码信息
	 * @author lingfe     
	 * @created 2019年4月8日 下午4:42:23  
	 * @param tab
	 * @return
	 */
	int save(Tab_sys_department_coding tab);
}
