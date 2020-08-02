package com.rsp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_t_yy_hospital.java
  * 描述： 医院信息表
  * 修改人： lingfe
  * 修改时间：2019年4月20日 上午9:04:40
  * 修改内容：
 */
public class Tab_t_yy_hospital {
	
	private String id;//医院信息表id标识
	private String pid;//上一级
	private String hospital_name;//医院名称
	
	private Integer sort;//	sort INT COMMENT  '编码排序',
	private Integer stop_flag=1;//	   stop_flag INT DEFAULT 1 COMMENT '停用标记 0=停用,1=启用',
	private String stop_code;//	   stop_code VARCHAR(64) COMMENT '停用人id',
	private String stop_name;//	   stop_name VARCHAR(64)  COMMENT '停用人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stop_date;//	   stop_date DATETIME COMMENT '停用时间',
	private String stop_explain;//停用说明
	private String remark;//	  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
	
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
	
	public String getId() {
		return id;
	}
	public String getPid() {
		return pid;
	}
	public String getHospital_name() {
		return hospital_name;
	}
	public Integer getSort() {
		return sort;
	}
	public Integer getStop_flag() {
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
	public String getRemark() {
		return remark;
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
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public void setStop_flag(Integer stop_flag) {
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
	public void setRemark(String remark) {
		this.remark = remark;
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
