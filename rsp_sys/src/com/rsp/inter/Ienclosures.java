package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_enclosure;

/**
 * 
  * 文件名：Ienclosures.java
  * 描述： 附件信息表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年3月26日 上午9:29:24
  * 修改内容：
 */
public interface Ienclosures {
	
	/**
	 * 
	 * 根据条件查询附件信息
	 * @author lingfe     
	 * @created 2019年4月20日 下午2:13:40  
	 * @param map
	 * @return
	 */
	List<Tab_enclosure> getWhere(Map<String, Object> map);
	
	/**
	 * 
	 * 修改附件信息
	 * @author lingfe     
	 * @created 2019年3月26日 上午9:34:50  
	 * @param tab
	 * @return
	 */
	int update(Tab_enclosure tab);
	
	/**
	 * 
	 * 根据id标识删除附件信息
	 * @author lingfe     
	 * @created 2019年3月26日 上午9:34:11  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 获取总数据量
	 * @author lingfe     
	 * @created 2019年3月26日 上午9:33:37  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 分页查询附件信息
	 * @author lingfe     
	 * @created 2019年3月26日 上午9:32:51  
	 * @param map
	 * @return
	 */
	List<Tab_enclosure> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据id标识获取数据
	 * @author lingfe     
	 * @created 2019年3月26日 上午9:31:01  
	 * @param id
	 * @return
	 */
	Tab_enclosure getWhereId(String id);

	/**
	 * 
	 * 保存附件信息
	 * @author lingfe     
	 * @created 2019年3月26日 上午9:30:10  
	 * @param tab
	 * @return
	 */
	int save(Tab_enclosure tab);
}
