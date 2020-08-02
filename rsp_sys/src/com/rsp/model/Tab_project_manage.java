package com.rsp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_project_manage.java
  * 描述： 项目管理表
  * 修改人： lingfe
  * 修改时间：2019年3月29日 上午9:41:40
  * 修改内容：
 */
public class Tab_project_manage {
	
	private String id;//	id VARCHAR(64) NOT NULL COMMENT '项目管理表id标识',
	private String hospital_id;//医院id
	private String name;//	NAME VARCHAR(64) COMMENT '项目名称',
	private Date start_date;//	start_date DATETIME COMMENT '开始时间',
	private Date end_date;//	end_date DATETIME COMMENT '结束时间',
	private String person_in_charge;//	person_in_charge VARCHAR(64) COMMENT '负责人',
	private String attribution_office;//	attribution_office VARCHAR(64) COMMENT '归属科室',
	private String type;//	TYPE VARCHAR(64) COMMENT '项目类型',
	private String classification;//	classification VARCHAR(64) COMMENT '项目分类',
	private String procedure_link;//	procedure_link VARCHAR(64) COMMENT '流程环节',
	private String remark;//	remark VARCHAR(1024) COMMENT '项目说明',
	
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
	public String getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}
	public String getName() {
		return name;
	}
	public Date getStart_date() {
		return start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public String getPerson_in_charge() {
		return person_in_charge;
	}
	public String getAttribution_office() {
		return attribution_office;
	}
	public String getType() {
		return type;
	}
	public String getClassification() {
		return classification;
	}
	public String getProcedure_link() {
		return procedure_link;
	}
	public String getRemark() {
		return remark;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public void setPerson_in_charge(String person_in_charge) {
		this.person_in_charge = person_in_charge;
	}
	public void setAttribution_office(String attribution_office) {
		this.attribution_office = attribution_office;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public void setProcedure_link(String procedure_link) {
		this.procedure_link = procedure_link;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
