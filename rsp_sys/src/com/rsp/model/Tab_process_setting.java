package com.rsp.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_process_setting.java
  * 描述： 流程设置表
  * 修改人： lingfe
  * 修改时间：2019年3月25日 上午10:06:59
  * 修改内容：
 */
public class Tab_process_setting {
	
	/**
	 * 初始化赋值
	 */
	public Tab_process_setting(){
		this.id=UUID.randomUUID().toString().replace("-", "");
	}

	private String id;//
	private String process_type;//	`process_type` VARCHAR(64) DEFAULT NULL COMMENT '流程类型',
	private String process_classification;//	  `process_classification` VARCHAR(64) DEFAULT NULL COMMENT '流程分类',
	private String process_name;//	  `process_name` VARCHAR(64) DEFAULT NULL COMMENT '流程名称',
	private String process_link;//	  `process_link` VARCHAR(64) DEFAULT NULL COMMENT '流程环节',
	private String remark;//	  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
	
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
	private String province;//省份
	
	//param ===>>表示该字段是参数需要,不会同步到数据库
	public String creator_name;//创建人名称
	public String modify_name;//修改人名称
	
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getId() {
		return id;
	}
	public String getProcess_type() {
		return process_type;
	}
	public String getProcess_classification() {
		return process_classification;
	}
	public String getProcess_name() {
		return process_name;
	}
	public String getProcess_link() {
		return process_link;
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
	public void setProcess_type(String process_type) {
		this.process_type = process_type;
	}
	public void setProcess_classification(String process_classification) {
		this.process_classification = process_classification;
	}
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	public void setProcess_link(String process_link) {
		this.process_link = process_link;
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
