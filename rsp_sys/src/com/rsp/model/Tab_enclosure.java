package com.rsp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_enclosure.java
  * 描述： 附件表
  * 修改人： lingfe
  * 修改时间：2019年3月26日 上午9:24:16
  * 修改内容：
 */
public class Tab_enclosure {

	private String id;//	`id` VARCHAR(64) NOT NULL COMMENT '附件表id标识',
	private String set_id;//	  `set_id` VARCHAR(64) DEFAULT NULL COMMENT 'setid：可以标识设备id，其他id',
	private String enclosure_type;//	  `enclosure_type` VARCHAR(64) DEFAULT NULL COMMENT '附件类型',
	private String enclosure_name;//	  `enclosure_name` VARCHAR(1024) DEFAULT NULL COMMENT '附件名称',
	private String enclosure_remark;//	  `enclosure_remark` VARCHAR(1024) DEFAULT NULL COMMENT '附件说明',
	
	
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
	public String getSet_id() {
		return set_id;
	}
	public String getEnclosure_type() {
		return enclosure_type;
	}
	public String getEnclosure_name() {
		return enclosure_name;
	}
	public String getEnclosure_remark() {
		return enclosure_remark;
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
	public void setSet_id(String set_id) {
		this.set_id = set_id;
	}
	public void setEnclosure_type(String enclosure_type) {
		this.enclosure_type = enclosure_type;
	}
	public void setEnclosure_name(String enclosure_name) {
		this.enclosure_name = enclosure_name;
	}
	public void setEnclosure_remark(String enclosure_remark) {
		this.enclosure_remark = enclosure_remark;
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
