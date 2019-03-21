package com.rsp.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_system_log.java
  * 描述： 系统日志表
  * 修改人： lingfe
  * 修改时间：2019年3月20日 下午5:08:23
  * 修改内容：
 */
public class Tab_system_log {
	
	/**
	 * 初始化赋值
	 */
	public Tab_system_log(){
		this.id=UUID.randomUUID().toString().replace("-", "");
		this.creator="游客";
	}

	private String id;//系统日志表id标识
	private String ip;//	ip VARCHAR(64) COMMENT 'ip地址',
	private String model_name;//	model_name VARCHAR(64) COMMENT '模块名称',
	private int operation_type=1;//	operation_type INT DEFAULT 0 COMMENT '操作类型: 1=查询,2=修改,3=删除，4=添加，0=其他',
	private int is_bug=0;//	is_bug INT COMMENT '是否有异常 0=否，1=是',
	private String exceptionally_detailed;//	exceptionally_detailed TEXT COMMENT '异常详细',
	private String target_id;//	target_id VARCHAR(64) COMMENT '目标id，在执行修改、删除时记录',
	private int state=0;//状态
	
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
	
	//param ===>>表示该字段是参数需要,不会同步到数据库
	public String creator_name;//创建人名称

	
	
	

	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public String getId() {
		return id;
	}


	public String getIp() {
		return ip;
	}


	public String getModel_name() {
		return model_name;
	}


	public int getOperation_type() {
		return operation_type;
	}


	public int getIs_bug() {
		return is_bug;
	}


	public String getExceptionally_detailed() {
		return exceptionally_detailed;
	}


	public String getTarget_id() {
		return target_id;
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


	public void setIp(String ip) {
		this.ip = ip;
	}


	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}


	/**
	 * 
	 * 操作类型: 1=查询,2=修改,3=删除，4=添加，0=其他',
	 * @author lingfe     
	 * @created 2019年3月20日 下午6:11:08  
	 * @param operation_type
	 */
	public void setOperation_type(int operation_type) {
		this.operation_type = operation_type;
	}


	/**
	 * 
	 * 是否有异常  0=否，1=是
	 * @author lingfe     
	 * @created 2019年3月20日 下午6:13:00  
	 * @param is_bug
	 */
	public void setIs_bug(int is_bug) {
		this.is_bug = is_bug;
	}


	public void setExceptionally_detailed(String exceptionally_detailed) {
		this.exceptionally_detailed = exceptionally_detailed;
	}


	public void setTarget_id(String target_id) {
		this.target_id = target_id;
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
