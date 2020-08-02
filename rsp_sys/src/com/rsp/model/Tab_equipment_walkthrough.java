package com.rsp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_equipment_walkthrough.java
  * 描述： 设备预排表
  * 修改人： lingfe
  * 修改时间：2019年3月26日 下午2:38:22
  * 修改内容：
 */
public class Tab_equipment_walkthrough {
	
	private String id;//设备预排表id标识
	private String equipment_id;//设备id
	private Integer is_walkthrough;//是否预排0=否，1=是
	private String walkthrough_name;//预排名称
	private Integer walkthrough_cycle;//预排周期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date next_time;//下次时间

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
	public String getEquipment_id() {
		return equipment_id;
	}
	public Integer getIs_walkthrough() {
		return is_walkthrough;
	}
	public String getWalkthrough_name() {
		return walkthrough_name;
	}
	public Integer getWalkthrough_cycle() {
		return walkthrough_cycle;
	}
	public Date getNext_time() {
		return next_time;
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
	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}
	public void setIs_walkthrough(Integer is_walkthrough) {
		this.is_walkthrough = is_walkthrough;
	}
	public void setWalkthrough_name(String walkthrough_name) {
		this.walkthrough_name = walkthrough_name;
	}
	public void setWalkthrough_cycle(Integer walkthrough_cycle) {
		this.walkthrough_cycle = walkthrough_cycle;
	}
	public void setNext_time(Date next_time) {
		this.next_time = next_time;
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
