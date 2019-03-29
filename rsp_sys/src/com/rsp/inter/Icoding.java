package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_coding;

/**
 * 
  * 文件名：Icoding.java
  * 描述： 编码表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年3月19日 下午3:51:51
  * 修改内容：
 */
public interface Icoding {
	
	/**
	 * 
	 * 根据编码名称查询编码信息
	 * @author lingfe     
	 * @created 2019年3月28日 下午4:48:39  
	 * @param codingName
	 * @return
	 */
	Tab_coding getWhereCodingName(String coding_name);
	
	/**
	 * 
	 * 根据编码信息id标识查询信息
	 * @author lingfe     
	 * @created 2019年3月20日 上午10:52:24  
	 * @param id
	 * @return
	 */
	Tab_coding getWhereId(String id);
	
	/**
	 * 
	 *查询总数据量
	 * @author lingfe     
	 * @created 2019年3月20日 上午9:58:01  
	 * @return
	 */
	int getCount();
	
	/**
	 * 
	 * 分页查询编码数据
	 * @author lingfe     
	 * @created 2019年3月19日 下午4:05:30  
	 * @param map 分页参数
	 * @return
	 */
	List<Tab_coding> pageSelect(Map<String, Object> map);
	

	/**
	 * 
	 * 保存编码数据
	 * @author lingfe     
	 * @created 2019年3月19日 下午3:53:12  
	 * @param coding 实体类
	 * @return
	 */
	public int save(Tab_coding coding);
	
	/**
	 * 
	 * 根据编码表id表示删除数据
	 * @author lingfe     
	 * @created 2019年3月19日 下午3:54:13  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 修改编码信息
	 * @author lingfe     
	 * @created 2019年3月19日 下午3:55:07  
	 * @param coding
	 * @return
	 */
	int update(Tab_coding coding);
}
