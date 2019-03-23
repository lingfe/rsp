package com.rsp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_china.java
  * 描述： 区域信息表
  * 修改人： lingfe
  * 修改时间：2019年3月22日 上午9:23:07
  * 修改内容：
 */
public class Tab_china {
	
	/**
	 * 初始化赋值
	 */
	public Tab_china(){
		this.pid="0";
	}

	private String id;//	`id` INT(11) NOT NULL COMMENT '区域信息表id标识',
	private String name;//	 `name` VARCHAR(64) DEFAULT NULL COMMENT '名称',
	private String pid;//	 `pid` INT(11) DEFAULT NULL COMMENT 'pid',
	
	private Integer state=0;//	  `state` INT(11) DEFAULT '1' COMMENT '状态',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAUL	T NULL COMMENT '最后修改时间',
	private String creator;//	  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
	private String modify;//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPid() {
		return pid;
	}
	public Integer getState() {
		return state;
	}
	public Date getCdate() {
		return cdate;
	}
	public Date getMdate() {
		return mdate;
	}
	public String getCreator() {
		return creator;
	}
	public String getModify() {
		return modify;
	}
	public String getVersion() {
		return version;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setModify(String modify) {
		this.modify = modify;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
