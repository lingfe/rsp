package com.rsp.model;

import java.util.Date;

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

	private String id;//流程设置id标识
	private String hospital_id;//医院id
	public String getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}
	private String process_type;//	`process_type` VARCHAR(64) DEFAULT NULL COMMENT '流程类型',
	private String process_classification;//	  `process_classification` VARCHAR(64) DEFAULT NULL COMMENT '流程分类',
	private String process_name;//	  `process_name` VARCHAR(64) DEFAULT NULL COMMENT '流程名称',
	private String process_link;//	  `process_link` VARCHAR(64) DEFAULT NULL COMMENT '流程环节',
	private String remark;//	  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
	
	private int stop_flag;//	   stop_flag INT DEFAULT 1 COMMENT '停用标记 0=停用,1=启用',
	private String stop_code;//	   stop_code VARCHAR(64) COMMENT '停用人id',
	private String stop_name;//	   stop_name VARCHAR(64)  COMMENT '停用人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stop_date;//	   stop_date DATETIME COMMENT '停用时间',
	private String stop_explain;//停用说明

	/// start
	private Integer state;//	state INT DEFAULT 0 COMMENT '状态',
	private String crt_code;//	crt_code VARCHAR(64) COMMENT '创建人id',
	private String crt_name;//	crt_name VARCHAR(64) COMMENT '创建人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date crt_date;//	crt_date DATETIME COMMENT '创建时间',
	private String modify_code;//	modify_code VARCHAR(64) COMMENT '修改人id',
	private String modify_name;//	modify_name VARCHAR(64) COMMENT '修改人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modify_date;//	modify_date DATETIME COMMENT '修改时间',
	private String version="0";// 	`version`  VARCHAR(64) COMMENT '数据版本',
	
	public Integer getState() {
		return state;
	}
	public String getCrt_code() {
		return crt_code;
	}
	public String getCrt_name() {
		return crt_name;
	}
	public Date getCrt_date() {
		return crt_date;
	}
	public String getModify_code() {
		return modify_code;
	}
	public String getModify_name() {
		return modify_name;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public String getVersion() {
		return version;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setCrt_code(String crt_code) {
		this.crt_code = crt_code;
	}
	public void setCrt_name(String crt_name) {
		this.crt_name = crt_name;
	}
	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}
	public void setModify_code(String modify_code) {
		this.modify_code = modify_code;
	}
	public void setModify_name(String modify_name) {
		this.modify_name = modify_name;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	///end
	
	
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
	public int getStop_flag() {
		return stop_flag;
	}
	public String getStop_code() {
		return stop_code;
	}
	public String getStop_name() {
		return stop_name;
	}
	public Date getStop_date() {
		return stop_date;
	}
	public String getStop_explain() {
		return stop_explain;
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
	public void setStop_flag(int stop_flag) {
		this.stop_flag = stop_flag;
	}
	public void setStop_code(String stop_code) {
		this.stop_code = stop_code;
	}
	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}
	public void setStop_date(Date stop_date) {
		this.stop_date = stop_date;
	}
	public void setStop_explain(String stop_explain) {
		this.stop_explain = stop_explain;
	}
}
