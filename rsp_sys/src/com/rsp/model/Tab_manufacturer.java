package com.rsp.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_manufacturer.java
  * 描述： 生产厂家表
  * 修改人： lingfe
  * 修改时间：2019年3月22日 下午2:25:20
  * 修改内容：
 */
public class Tab_manufacturer {
	
	/**
	 * 初始化赋值
	 */
	public Tab_manufacturer(){
		this.id=UUID.randomUUID().toString().replace("-", "");
	}

	private String id;//	`id` VARCHAR(64) NOT NULL COMMENT '生产厂家id标识表',
	private String name;//	   `name` VARCHAR(64) COMMENT '生产厂家名称', 
	private String remark;//	  `remark` VARCHAR(1024) DEFAULT NULL COMMENT '备注',
	
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
	
	//add   ===>>表示该字段是后来添加，会同步到数据库
	
	//param ===>>表示该字段是参数需要,不会同步到数据库
	public String creator_name;//创建人名称
	public String modify_name;//修改人名称
		
	
	
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRemark() {
		return remark;
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
	public void setRemark(String remark) {
		this.remark = remark;
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
