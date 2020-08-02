package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_t_yy_sb_sbda;

/**
 * 
  * 文件名：It_yw_sb_sbda.java
  * 描述： 医院设备档案信息表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年4月19日 下午3:10:48
  * 修改内容：
 */
public interface It_yy_sb_sbda {
	
	/**
	 * 
	 * 根据医院设备档案信息id标识删除
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:02:47  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);

	/**
	 * 
	 * 根据id标识修改医院设备档案信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:01:47  
	 * @param tab
	 * @return
	 */
	int update(Tab_t_yy_sb_sbda tab);
	
	/**
	 * 
	 * 总数据量
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:00:54  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 分页查询医院设备档案信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午3:00:15  
	 * @param id
	 * @return
	 */
	List<Tab_t_yy_sb_sbda> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 根据医院设备档案信息id标识获取
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:58:47  
	 * @param id
	 * @return
	 */
	Tab_t_yy_sb_sbda getWhereId(String id);
	
	/**
	 * 
	 * 保存医院设备档案信息
	 * @author lingfe     
	 * @created 2019年4月12日 下午2:57:51  
	 * @param tab
	 * @return
	 */
	int save(Tab_t_yy_sb_sbda tab);
}
