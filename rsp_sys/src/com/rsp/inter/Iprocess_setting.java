package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_process_setting;

/**
 * 
  * 文件名：Iprocess_setting.java
  * 描述： 流程设置表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年3月25日 上午10:09:27
  * 修改内容：
 */
public interface Iprocess_setting {
	
	/**
	 * 
	 * 根据id查询流程设置信息
	 * @author lingfe     
	 * @created 2019年3月25日 上午10:23:34  
	 * @param id
	 * @return
	 */
	Tab_process_setting getWhereId(String id);
	
	/**
	 * 
	 * 修改流程设置信息
	 * @author lingfe     
	 * @created 2019年3月25日 上午10:22:54  
	 * @param pro
	 * @return
	 */
	int update(Tab_process_setting pro);
	
	/**
	 * 
	 * 获取总数据量
	 * @author lingfe     
	 * @created 2019年3月25日 上午10:14:11  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 分页查询流程设置信息
	 * @author lingfe     
	 * @created 2019年3月25日 上午10:13:15  
	 * @param map
	 * @return
	 */
	List<Tab_process_setting> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据id标识删除流程设置信息
	 * @author lingfe     
	 * @created 2019年3月25日 上午10:11:42  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);

	/**
	 * 
	 * 保存信息
	 * @author lingfe     
	 * @created 2019年3月25日 上午10:10:10  
	 * @param pro
	 * @return
	 */
	int save(Tab_process_setting pro);
	
}
