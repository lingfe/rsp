package com.rsp.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_licence_walkthrough.java
  * 描述：证照预排表 
  * 修改人： lingfe
  * 修改时间：2019年3月28日 上午9:26:41
  * 修改内容：
 */
public class Tab_licence_walkthrough {
	
	Tab_licence_walkthrough(){
		this.id=UUID.randomUUID().toString().replace("-", "");
	}
	
	
	
	public String getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}



	private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '证照预排表id标识',
	private String hospital_name;//	  `hospital_name` VARCHAR(64) DEFAULT NULL COMMENT '医院名称',
	private String hospital_id;
	private String prearranged_type;//	  `prearranged_type` VARCHAR(64) DEFAULT NULL COMMENT '预排类型',
	private String licence_type;//	  `licence_type` VARCHAR(64) DEFAULT NULL COMMENT '证照类型',
	private String certificates_number;//	  `certificates_number` VARCHAR(64) DEFAULT NULL COMMENT '证件编号',
	private String organization_name;//	  `organization_name` VARCHAR(64) DEFAULT NULL COMMENT '机构名称',
	private String legal_person_charge;//	  `legal_person_charge` VARCHAR(64) DEFAULT NULL COMMENT '负责人/法人',
	private String address;//	  `address` VARCHAR(64) DEFAULT NULL COMMENT '地址',
	private String remark;//	  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
	private Integer is_annual_inspection;//	  `is_annual_inspection` INT(11) DEFAULT NULL COMMENT '是否年度检验:0=否，1=是',
	private Integer is_annual_assessment;//	  `is_annual_assessment` INT(11) DEFAULT NULL COMMENT '是否年度评估:0=否，1=是',
	private Integer is_licence_continue;//	  `is_licence_continue` INT(11) DEFAULT NULL COMMENT '是否证照延续:0=否，1=是',
	private Date inspection_date;//	  `inspection_date` DATETIME DEFAULT NULL COMMENT '检验时间',
	private String inspection_cycle;//	  `inspection_cycle` VARCHAR(64) NOT NULL COMMENT '检验周期',
	private Date assessment_date;//	  `assessment_date` DATETIME DEFAULT NULL COMMENT '评估时间',
	private String assessment_cycle;//	  `assessment_cycle` VARCHAR(64) NOT NULL COMMENT '评估周期',
	private String valid_certificate;//	  `valid_certificate` VARCHAR(64) DEFAULT NULL COMMENT '有效期',
	private String certification_authority;//	  `certification_authority` VARCHAR(64) DEFAULT NULL COMMENT '发证机构',
	private Date issuing_date;//	  `issuing_date` DATETIME DEFAULT NULL COMMENT '发证时间',
	

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
	public String getHospital_name() {
		return hospital_name;
	}
	public String getPrearranged_type() {
		return prearranged_type;
	}
	public String getLicence_type() {
		return licence_type;
	}
	public String getCertificates_number() {
		return certificates_number;
	}
	public String getOrganization_name() {
		return organization_name;
	}
	public String getLegal_person_charge() {
		return legal_person_charge;
	}
	public String getAddress() {
		return address;
	}
	public String getRemark() {
		return remark;
	}
	public Integer getIs_annual_inspection() {
		return is_annual_inspection;
	}
	public Integer getIs_annual_assessment() {
		return is_annual_assessment;
	}
	public Integer getIs_licence_continue() {
		return is_licence_continue;
	}
	public Date getInspection_date() {
		return inspection_date;
	}
	public String getInspection_cycle() {
		return inspection_cycle;
	}
	public Date getAssessment_date() {
		return assessment_date;
	}
	public String getAssessment_cycle() {
		return assessment_cycle;
	}
	public String getValid_certificate() {
		return valid_certificate;
	}
	public String getCertification_authority() {
		return certification_authority;
	}
	public Date getIssuing_date() {
		return issuing_date;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	public void setPrearranged_type(String prearranged_type) {
		this.prearranged_type = prearranged_type;
	}
	public void setLicence_type(String licence_type) {
		this.licence_type = licence_type;
	}
	public void setCertificates_number(String certificates_number) {
		this.certificates_number = certificates_number;
	}
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	public void setLegal_person_charge(String legal_person_charge) {
		this.legal_person_charge = legal_person_charge;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setIs_annual_inspection(Integer is_annual_inspection) {
		this.is_annual_inspection = is_annual_inspection;
	}
	public void setIs_annual_assessment(Integer is_annual_assessment) {
		this.is_annual_assessment = is_annual_assessment;
	}
	public void setIs_licence_continue(Integer is_licence_continue) {
		this.is_licence_continue = is_licence_continue;
	}
	public void setInspection_date(Date inspection_date) {
		this.inspection_date = inspection_date;
	}
	public void setInspection_cycle(String inspection_cycle) {
		this.inspection_cycle = inspection_cycle;
	}
	public void setAssessment_date(Date assessment_date) {
		this.assessment_date = assessment_date;
	}
	public void setAssessment_cycle(String assessment_cycle) {
		this.assessment_cycle = assessment_cycle;
	}
	public void setValid_certificate(String valid_certificate) {
		this.valid_certificate = valid_certificate;
	}
	public void setCertification_authority(String certification_authority) {
		this.certification_authority = certification_authority;
	}
	public void setIssuing_date(Date issuing_date) {
		this.issuing_date = issuing_date;
	}
}
