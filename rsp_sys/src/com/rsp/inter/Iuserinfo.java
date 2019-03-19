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
	 * 根据用户名称(用户名)和密码查询用户
	 * @author lingfe     
	 * @created 2019年3月19日 上午9:29:12  
	 * @return
	 */
	Tab_user_info getWhereNamePwd(String username,String pwd);
}
