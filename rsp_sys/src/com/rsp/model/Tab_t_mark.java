package com.rsp.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_t_mark.java
  * 描述：痕迹表 
  * 修改人： lingfe
  * 修改时间：2019年4月16日 上午11:12:44
  * 修改内容：
 */
public class Tab_t_mark {

	private String id;//痕迹表id标识
	private String yw_id;//业务id,可以表示设备id等
	
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
	
	//痕迹信息集合
	public List<Tab_t_mark_info> mark_info;

	public String getId() {
		return id;
	}

	public String getYw_id() {
		return yw_id;
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

	public void setYw_id(String yw_id) {
		this.yw_id = yw_id;
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
