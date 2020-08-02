package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_t_yy_ksxx;

/**
 * 
  * 文件名：It_department_management.java
  * 描述： 科室管理访问接口
  * 修改人： lingfe
  * 修改时间：2019年4月12日 上午11:27:00
  * 修改内容：
 */
public interface It_yy_ksxx {
	
	/**
	 * 
	 * 根据条件查询科室管理信息
	 * @author lingfe     
	 * @created 2019年4月12日 上午11:35:31  
	 * @param map
	 * @return
	 */
	List<Tab_t_yy_ksxx> getWhere(Map<String, Object> map);
	
	/**
	 * 
	 * 根据科室管理id标识删除
	 * @author lingfe     
	 * @created 2019年4月12日 上午11:34:01  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 修改科室管理信息
	 * @author lingfe     
	 * @created 2019年4月12日 上午11:33:18  
	 * @param tab
	 * @return
	 */
	int update(Tab_t_yy_ksxx tab);

	/**
	 * 
	 * 分页查询科室管理信息
	 * @author lingfe     
	 * @created 2019年4月12日 上午11:32:27  
	 * @param map
	 * @return
	 */
	List<Tab_t_yy_ksxx> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据科室管理id标识获取数据
	 * @author lingfe     
	 * @created 2019年4月12日 上午11:31:23  
	 * @param id
	 * @return
	 */
	Tab_t_yy_ksxx getWhereId(String id);
	
	/**
	 * 
	 * 总数据量
	 * @author lingfe     
	 * @created 2019年4月12日 上午11:30:13  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 保存科室管理信息
	 * @author lingfe     
	 * @created 2019年4月12日 上午11:29:13  
	 * @param tab
	 * @return
	 */
	int save(Tab_t_yy_ksxx tab);
}
