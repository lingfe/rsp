package com.rsp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_enclosure.java
  * 描述： 附件信息表
  * 修改人： lingfe
  * 修改时间：2019年3月26日 上午9:24:16
  * 修改内容：
 */
public class Tab_enclosure {

	private String id;//	`id` VARCHAR(64) NOT NULL COMMENT '附件表id标识',
	private String hospital_id;//	  `set_id` VARCHAR(64) DEFAULT NULL COMMENT 'setid：可以标识设备id，其他id',
	private String coding_type;//	  `enclosure_type` VARCHAR(64) DEFAULT NULL COMMENT '附件类型',
	private String yw_id;//	  `enclosure_name` VARCHAR(1024) DEFAULT NULL COMMENT '附件名称',
	private Integer flag_type;//	  `enclosure_remark` VARCHAR(1024) DEFAULT NULL COMMENT '附件说明',
	private String path;//跟路径
	private String fj_name_real;//附件实际名称
	private String fj_name_sys;//附件系统名称
	private String fj_name_easy;//附件简介
	private Integer fj_size;//附件大小
	private String fj_remark;//附件说明
	private Integer sort;//排序
	
	private String crt_code;//	crt_code VARCHAR(64) COMMENT '创建人id',
	private String crt_name;//	crt_name VARCHAR(64) COMMENT '创建人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date crt_date;//	crt_date DATETIME COMMENT '创建时间',
	public String getId() {
		return id;
	}
	public String getHospital_id() {
		return hospital_id;
	}
	public String getCoding_type() {
		return coding_type;
	}
	public String getYw_id() {
		return yw_id;
	}
	public Integer getFlag_type() {
		return flag_type;
	}
	public String getPath() {
		return path;
	}
	public String getFj_name_real() {
		return fj_name_real;
	}
	public String getFj_name_sys() {
		return fj_name_sys;
	}
	public String getFj_name_easy() {
		return fj_name_easy;
	}
	public Integer getFj_size() {
		return fj_size;
	}
	public String getFj_remark() {
		return fj_remark;
	}
	public Integer getSort() {
		return sort;
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
	public void setId(String id) {
		this.id = id;
	}
	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}
	public void setCoding_type(String coding_type) {
		this.coding_type = coding_type;
	}
	public void setYw_id(String yw_id) {
		this.yw_id = yw_id;
	}
	public void setFlag_type(Integer flag_type) {
		this.flag_type = flag_type;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setFj_name_real(String fj_name_real) {
		this.fj_name_real = fj_name_real;
	}
	public void setFj_name_sys(String fj_name_sys) {
		this.fj_name_sys = fj_name_sys;
	}
	public void setFj_name_easy(String fj_name_easy) {
		this.fj_name_easy = fj_name_easy;
	}
	public void setFj_size(Integer fj_size) {
		this.fj_size = fj_size;
	}
	public void setFj_remark(String fj_remark) {
		this.fj_remark = fj_remark;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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
}
