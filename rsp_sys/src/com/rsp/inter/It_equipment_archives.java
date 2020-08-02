package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_t_equipment_archives;

/**
 * 
  * 文件名：It_equipment_archives.java
  * 描述： 设备档案接口访问类
  * 修改人： lingfe
  * 修改时间：2019年4月11日 上午9:46:36
  * 修改内容：
 */
public interface It_equipment_archives {
	
	/**
	 * 
	 * 根据设备档案id标识删除信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午3:00:25  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 修改设备档案信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午2:59:12  
	 * @param tab
	 * @return
	 */
	int update(Tab_t_equipment_archives tab);
	
	/**
	 * 
	 * 获取总数据量
	 * @author lingfe     
	 * @created 2019年3月26日 下午2:57:54  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	
	/**
	 *
	 * 分页查询设备档案信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午2:56:21  
	 * @param map
	 * @return
	 */
	List<Tab_t_equipment_archives> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据设备档案id标识查询设备档案信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午2:54:20  
	 * @param id
	 * @return
	 */
	Tab_t_equipment_archives getWhereId(String id);

	/**
	 * 
	 * 保存设备档案信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午2:53:42  
	 * @param tab
	 * @return
	 */
	int save(Tab_t_equipment_archives tab);
}
