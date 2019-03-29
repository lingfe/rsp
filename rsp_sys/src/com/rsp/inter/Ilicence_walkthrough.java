package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_licence_walkthrough;

/**
 * 
  * 文件名：Ilicence_walkthrough.java
  * 描述： 证照预排数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年3月28日 上午9:35:50
  * 修改内容：
 */
public interface Ilicence_walkthrough {

	/**
	 * 
	 * 修改证照预排信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午9:42:01  
	 * @param tab
	 * @return
	 */
	int update(Tab_licence_walkthrough tab);
	
	/**
	 * 
	 * 根据id标识删除证照预排信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午9:41:25  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 根据id标识获取证照预排信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午9:40:40  
	 * @param id
	 * @return
	 */
	Tab_licence_walkthrough getWhereId(String id);
	
	/**
	 * 
	 * 获取总数据量
	 * @author lingfe     
	 * @created 2019年3月28日 上午9:40:04  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 分页查询证照预排信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午9:38:59  
	 * @param map
	 * @return
	 */
	List<Tab_licence_walkthrough> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 保存证照预排信息
	 * @author lingfe     
	 * @created 2019年3月28日 上午9:36:55  
	 * @param tab
	 * @return
	 */
	int save(Tab_licence_walkthrough tab);
}
