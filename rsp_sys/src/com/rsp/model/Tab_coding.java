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
	private int coding_type=0;//	  `coding_type` INT(11) DEFAULT NULL COMMENT '编码类型: 0=其他，\n					1=性别，2=职称，3=工作性质，\n					4=职业照射分类，5=附件类别，\n					6=装置类别，7=设备用途，8=流程类型，\n					9=流程分类，',
	private String coding_name;//	  `coding_name` VARCHAR(64) DEFAULT NULL COMMENT '编码名称',
	private String remark;//	  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
	private int state=1;//	  `state` INT(11) DEFAULT '1' COMMENT '状态：1=启用，0=停用',
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate=new Date();//	  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate=new Date();//	  `mdate` DATETIME DEFAUL	T NULL COMMENT '最后修改时间',
	private String creator;//	  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
	private String modify;//	  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
	private String version="0";//	  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
	
	
	public String getId() {
		return id;
	}
	public int getCoding_type() {
		return coding_type;
	}
	public String getCoding_name() {
		return coding_name;
	}
	public String getRemark() {
		return remark;
	}
	public int getState() {
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
	public void setId(String id) {
		this.id = id;
	}
	public void setCoding_type(int coding_type) {
		this.coding_type = coding_type;
	}
	public void setCoding_name(String coding_name) {
		this.coding_name = coding_name;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setState(int state) {
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
	
}
