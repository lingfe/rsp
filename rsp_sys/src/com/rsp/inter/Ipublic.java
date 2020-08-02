package com.rsp.inter;

/**
 * 
  * 文件名：Ipublic.java
  * 描述： 公共接口，
  * 修改人： lingfe
  * 修改时间：2019年5月23日 下午3:58:43
  * 修改内容：
 */
public interface Ipublic<T> {

	/**
	 * 
	 * 保存
	 * @author lingfe     
	 * @created 2019年5月23日 下午4:01:53  
	 * @param tab
	 * @return
	 */
	int save(T tab);
}
