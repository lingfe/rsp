package com.rsp.inter;

import java.util.List;

import com.rsp.model.Tab_sys_china;

/**
 * 
  * 文件名：Ichina.java
  * 描述：区域信息表数据访问接口 
  * 修改人： lingfe
  * 修改时间：2019年3月22日 上午9:26:45
  * 修改内容：
 */
public interface Isys_china {
	
	/**
	 * 
	 * 根据pid删除区域信息
	 * @author lingfe     
	 * @created 2019年3月22日 上午9:58:44  
	 * @param pid
	 * @return
	 */
	int deleteWherePid(String pid);
	
	/**
	 * 
	 * 根据区域信息id标识删除信息
	 * @author lingfe     
	 * @created 2019年3月22日 上午9:58:07  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 模糊查询区域信息
	 * @author lingfe     
	 * @created 2019年3月22日 上午9:56:17  
	 * @param name
	 * @return
	 */
	List<Tab_sys_china> selectLike(String name);
	
	/**
	 * 
	 * 保存区域信息
	 * @author lingfe     
	 * @created 2019年3月22日 上午9:53:46  
	 * @param china
	 * @return
	 */
	int save(Tab_sys_china china);

	/**
	 * 
	 * 根据pid查询区域信息
	 * @author lingfe     
	 * @created 2019年3月22日 上午9:28:12  
	 * @param pid
	 * @return
	 */
	List<Tab_sys_china> selectWhwerePid(String pid);
}
