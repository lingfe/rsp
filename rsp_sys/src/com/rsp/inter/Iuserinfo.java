package com.rsp.inter;

import com.rsp.model.Tab_user_info;

/**
 * 
  * 文件名：Iuserinfo.java
  * 描述： 用户信息数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年3月19日 上午8:59:17
  * 修改内容：
 */
public interface Iuserinfo {
	
	/**
	 * 
	 * 根据id标识修改用户密码
	 * @author lingfe     
	 * @created 2019年3月20日 下午2:18:39  
	 * @param id
	 * @param newPwd 新密码
	 * @return
	 */
	int updatePwd(String id,String newPwd);
	
	/**
	 * 
	 * 根据用户信息id标识查询数据
	 * @author lingfe     
	 * @created 2019年3月20日 上午9:16:55  
	 * @param id
	 * @return
	 */
	Tab_user_info getWhereId(String id);

	/**
	 * 
	 * 根据用户名称(用户名)和密码查询用户
	 * @author lingfe     
	 * @created 2019年3月19日 上午9:29:12  
	 * @return
	 */
	Tab_user_info getWhereNamePwd(String username,String pwd);
}
