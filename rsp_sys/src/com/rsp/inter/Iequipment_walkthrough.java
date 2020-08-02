package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_equipment_walkthrough;

/**
 * 
  * 文件名：Iequipment_walkthrough.java
  * 描述： 设备预排表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年3月26日 下午2:52:09
  * 修改内容：
 */
public interface Iequipment_walkthrough {
	
	
	/**
	 * 
	 * 根据条件查询设备预排信息
	 * @author lingfe     
	 * @created 2019年4月15日 下午4:56:25  
	 * @param map
	 * @return
	 */
	List<Tab_equipment_walkthrough> getWhere(Map<String, Object> map);
	
	/**
	 * 
	 * 根据id标识删除信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午3:00:25  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 修改设备预排信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午2:59:12  
	 * @param tab
	 * @return
	 */
	int update(Tab_equipment_walkthrough tab);
	
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
	 * 分页查询设备预排信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午2:56:21  
	 * @param map
	 * @return
	 */
	List<Tab_equipment_walkthrough> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据设备预排id标识查询设备预排信息
	 * @author lingfe     
	 * @created 2019年3月26日 下午2:54:20  
	 * @param id
	 * @return
	 */
	Tab_equipment_walkthrough getWhereId(String id);

	/**
	 * 
	 * 保存设备预排休息
	 * @author lingfe     
	 * @created 2019年3月26日 下午2:53:42  
	 * @param tab
	 * @return
	 */
	int save(Tab_equipment_walkthrough tab);
}
