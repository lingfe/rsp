package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_t_mark;
import com.rsp.model.Tab_t_yy_hospital;

/**
 * 
  * 文件名：It_yy_hospital.java
  * 描述： 医院信息表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年4月20日 上午9:16:30
  * 修改内容：
 */
public interface It_yy_hospital {
	
	/**
	 * 
	 * 总数据量
	 * @author lingfe     
	 * @created 2019年4月20日 上午9:20:03  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 分页查询医院信息
	 * @author lingfe     
	 * @created 2019年4月20日 上午9:19:26  
	 * @param map
	 * @return
	 */
	List<Tab_t_yy_hospital> pageSelect(Map<String, Object> map);

	/**
	 * 
	 * 根据条件获取医院信息表数据
	 * @author lingfe     
	 * @created 2019年4月16日 上午11:20:22  
	 * @param map
	 * @return
	 */
	List<Tab_t_mark> getWhere(Map<String, Object> map);

	/**
	 * 
	 * 根据医院信息id标识删除
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:02:47  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 根据医院信息id标识获取
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:58:47  
	 * @param id
	 * @return
	 */
	Tab_t_yy_hospital getWhereId(String id);
	
	/**
	 * 保存医院信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:57:51  
	 * @param tab
	 * @return
	 */
	int save(Tab_t_yy_hospital tab);
	
	/**
	 * 
	 * 修改医院信息
	 * @author lingfe     
	 * @created 2019年4月20日 上午9:18:45  
	 * @param tab
	 * @return
	 */
	int update(Tab_t_yy_hospital tab);
}
