package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_t_mark_info;

/**
 * 
  * 文件名：It_mark.java
  * 描述： 痕迹信息表信息访问接口
  * 修改人： lingfe
  * 修改时间：2019年4月16日 上午11:18:08
  * 修改内容：
 */
public interface It_mark_info {
	
	/**
	 * 
	 * 根据条件获取痕迹信息表数据
	 * @author lingfe     
	 * @created 2019年4月16日 上午11:20:22  
	 * @param map
	 * @return
	 */
	List<Tab_t_mark_info> getWhere(Map<String, Object> map);

	/**
	 * 
	 * 根据痕迹信息id标识删除
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:02:47  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 根据痕迹信息id标识获取
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:58:47  
	 * @param id
	 * @return
	 */
	Tab_t_mark_info getWhereId(String id);
	
	/**
	 * 保存痕迹信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:57:51  
	 * @param tab
	 * @return
	 */
	int save(Tab_t_mark_info tab);
}
