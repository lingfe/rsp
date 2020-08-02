package com.rsp.model;

/**
 * 
  * 文件名：Tab_china.java
  * 描述： 区域信息表
  * 修改人： lingfe
  * 修改时间：2019年3月22日 上午9:23:07
  * 修改内容：
 */
public class Tab_sys_china {
	
	/**
	 * 初始化赋值
	 */
	public Tab_sys_china(){
		this.parent_id="0";
	}

	private String id;//	`id` INT(11) NOT NULL COMMENT '区域信息表id标识',
	private int sort;//排序
	private String china_name;// 区域名称
	private String parent_id;//	 上级id,
	
	public String getId() {
		return id;
	}
	public int getSort() {
		return sort;
	}
	public String getChina_name() {
		return china_name;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public void setChina_name(String china_name) {
		this.china_name = china_name;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
}
