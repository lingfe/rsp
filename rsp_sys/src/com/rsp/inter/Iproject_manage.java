package com.rsp.inter;

import java.util.List;
import java.util.Map;

import com.rsp.model.Tab_project_manage;

/**
 * 
  * 文件名：Iproject_manage.java
  * 描述：项目管理表数据访问接口 
  * 修改人： lingfe
  * 修改时间：2019年3月29日 上午9:47:38
  * 修改内容：
 */
public interface Iproject_manage {

	/**
	 * 
	 * 根据项目id标识删除
	 * @author lingfe     
	 * @created 2019年3月29日 上午9:55:20  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 修改项目信息
	 * @author lingfe     
	 * @created 2019年3月29日 上午9:54:29  
	 * @param tab
	 * @return
	 */
	int update(Tab_project_manage tab);
	
	
	/**
	 * 
	 * 获取总数据量
	 * @author lingfe     
	 * @created 2019年3月29日 上午9:52:17  
	 * @param map
	 * @return
	 */
	int getCount(Map<String, Object> map);
	
	/**
	 * 
	 * 根据项目id标识查询项目信息
	 * @author lingfe     
	 * @created 2019年3月29日 上午9:51:25  
	 * @param id
	 * @return
	 */
	Tab_project_manage getWhereId(String id);
	
	/**
	 * 
	 * 分页查询项目管理信息
	 * @author lingfe     
	 * @created 2019年3月29日 上午9:50:36  
	 * @param map
	 * @return
	 */
	List<Tab_project_manage> pageSelect(Map<String, Object> map);
	
	/**
	 * 
	 * 保存项目管理信息
	 * @author lingfe     
	 * @created 2019年3月29日 上午9:48:36  
	 * @param tab
	 * @return
	 */
	int save(Tab_project_manage tab);
	
}
