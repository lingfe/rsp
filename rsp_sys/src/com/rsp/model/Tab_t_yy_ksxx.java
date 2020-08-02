package com.rsp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_t_department_management.java
  * 描述： 科室管理表
  * 修改人： lingfe
  * 修改时间：2019年4月12日 上午11:06:31
  * 修改内容：
 */
public class Tab_t_yy_ksxx {

	private String id;//科室管理id
	private String hospital_id;//医院id
	private String parent_id;//上一级
	private String kssx_id;//科室属性id
	private String ks_name;//科室名称
	private String ks_tel;//科室电话
	private String ks_fzr;//科室负责人
	
	private int sort;//	'排序',
	private int stop_flag=1;//	'停用标记 0=停用,1=启用',
	private String stop_code;//	'停用人id',
	private String stop_name;//	停用人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stop_date;//'停用时间',
	private String stop_explain;//停用说明
	private String remark;//备注
	
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
	
	//科室属性名称
	public String kssx_name;
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public String getHospital_id() {
		return hospital_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public String getKssx_id() {
		return kssx_id;
	}
	public String getKs_name() {
		return ks_name;
	}
	public String getKs_tel() {
		return ks_tel;
	}
	public String getKs_fzr() {
		return ks_fzr;
	}
	public int getSort() {
		return sort;
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
	public void setId(String id) {
		this.id = id;
	}
	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public void setKssx_id(String kssx_id) {
		this.kssx_id = kssx_id;
	}
	public void setKs_name(String ks_name) {
		this.ks_name = ks_name;
	}
	public void setKs_tel(String ks_tel) {
		this.ks_tel = ks_tel;
	}
	public void setKs_fzr(String ks_fzr) {
		this.ks_fzr = ks_fzr;
	}
	public void setSort(int sort) {
		this.sort = sort;
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
}
