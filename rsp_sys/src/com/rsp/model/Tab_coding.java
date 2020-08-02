package com.rsp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_coding.java
  * 描述： 编码表
  * 修改人： lingfe
  * 修改时间：2019年3月19日 下午3:48:13
  * 修改内容：
 */
public class Tab_coding {
	
	private String id;//	  `id` VARCHAR(64) NOT NULL COMMENT '编码表id标识',
	
	private String hospital_id;//医院id
	
	/**
	 * 			10001=性别，
				10002=岗位
				10003=职称，
				10004=工作性质，
				10005=职业照射分类，
				10006=人员编码附件类别，
				-----------
				20001=流程类型，
				20002=设备用途
				20003=设备编码附件类别
				-----------
				30001=流程类型，
				30002=流程分类，
				30003=流程编码附件类别
	 */
	private Integer coding_type;//	  `coding_type` INT(11) DEFAULT NULL COMMENT '编码类型: 0=其他，\n					1=性别，2=职称，3=工作性质，\n					4=职业照射分类，5=附件类别，\n					6=装置类别，7=设备用途，8=流程类型，\n					9=流程分类，',
	private String coding_name;//	  `coding_name` VARCHAR(64) DEFAULT NULL COMMENT '编码名称',
	private Integer flag_yp;//是否预排0=否，1=是
	private String yp_name;//预排名称
	private Integer yp_cycle;//预排周期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date next_time;//下次时间
	
	private int sort;//	sort INT COMMENT  '编码排序',
	private int stop_flag=1;//	   stop_flag INT DEFAULT 1 COMMENT '停用标记 0=停用,1=启用',
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
	public String getHospital_id() {
		return hospital_id;
	}
	public Integer getCoding_type() {
		return coding_type;
	}
	public String getCoding_name() {
		return coding_name;
	}
	public Integer getFlag_yp() {
		return flag_yp;
	}
	public String getYp_name() {
		return yp_name;
	}
	public Integer getYp_cycle() {
		return yp_cycle;
	}
	public Date getNext_time() {
		return next_time;
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
	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}
	public void setCoding_type(Integer coding_type) {
		this.coding_type = coding_type;
	}
	public void setCoding_name(String coding_name) {
		this.coding_name = coding_name;
	}
	public void setFlag_yp(Integer flag_yp) {
		this.flag_yp = flag_yp;
	}
	public void setYp_name(String yp_name) {
		this.yp_name = yp_name;
	}
	public void setYp_cycle(Integer yp_cycle) {
		this.yp_cycle = yp_cycle;
	}
	public void setNext_time(Date next_time) {
		this.next_time = next_time;
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
