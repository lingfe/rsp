package com.rsp.model;

import java.util.Date;
import java.util.UUID;

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
	private String name;//	NAME VARCHAR(64) COMMENT '项目名称',
	private Date start_date;//	start_date DATETIME COMMENT '开始时间',
	private Date end_date;//	end_date DATETIME COMMENT '结束时间',
	private String person_in_charge;//	person_in_charge VARCHAR(64) COMMENT '负责人',
	private String attribution_office;//	attribution_office VARCHAR(64) COMMENT '归属科室',
	private String type;//	TYPE VARCHAR(64) COMMENT '项目类型',
	private String classification;//	classification VARCHAR(64) COMMENT '项目分类',
	private String procedure_link;//	procedure_link VARCHAR(64) COMMENT '流程环节',
	private String remark;//	remark VARCHAR(1024) COMMENT '项目说明',
	
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
	public String getId() {
		return id;
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
	public String getProvince() {
		return province;
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
	public void setProvince(String province) {
		this.province = province;
	}
}
