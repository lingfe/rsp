package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_manufacturer;

/**
 * 
  * 文件名：Imanufacturer.java
  * 描述： 生产厂家表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年3月22日 下午2:28:39
  * 修改内容：
 */
public interface Imanufacturer {
	
	/**
	 * 
	 * 根据生产厂家id标识获取信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午3:17:52  
	 * @param id
	 * @return
	 */
	Tab_manufacturer getWhereId(String id);
	
	/**
	 * 
	 * 获取总数据量
	 * @author lingfe     
	 * @created 2019年3月22日 下午2:41:26  
	 * @param map 
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 根据id标识修改生产厂家信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午2:33:30  
	 * @param man
	 * @return
	 */
	int update(Tab_manufacturer man);
	
	/**
	 * 
	 * 根据id标识删除生产厂家信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午2:32:20  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);

	/**
	 * 
	 * 分页查询生产厂家信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午2:31:25  
	 * @param map
	 * @return
	 */
	List<Tab_manufacturer> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 保存生产厂家信息
	 * @author lingfe     
	 * @created 2019年3月22日 下午2:29:22  
	 * @param man
	 * @return
	 */
	int save(Tab_manufacturer man);
}
