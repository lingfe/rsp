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
public class Tab_sys_system_log {
	
	/**
	 * 初始化赋值
	 */
	public Tab_sys_system_log(){
		this.id=UUID.randomUUID().toString().replace("-", "");
		this.crt_name="游客";
	}

	private String id;//系统日志表id标识
	private String hospital_id; //关联医院表id
	private String ip;//'ip地址',
	private String model_name;// '模块名称',
	private int cz_type=1;// '操作类型: 1=查询,2=修改,3=删除，4=添加，0=其他',
	private int flag_bug=0;//'是否有异常 0=否，1=是',
	private String yc_detailed;// '异常详细',
	private String target_id;//	'目标id，在执行修改、删除时记录',
	private String target_name;//当期操作表名或对应接口名称
	private String crt_code;//创建人id
	private String crt_name;//创建人名称
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date crt_date=new Date();//'创建时间',
	
	public String getId() {
		return id;
	}
	public String getHospital_id() {
		return hospital_id;
	}
	public String getIp() {
		return ip;
	}
	public String getModel_name() {
		return model_name;
	}
	public int getCz_type() {
		return cz_type;
	}
	public int getFlag_bug() {
		return flag_bug;
	}
	public String getYc_detailed() {
		return yc_detailed;
	}
	public String getTarget_id() {
		return target_id;
	}
	public String getTarget_name() {
		return target_name;
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
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public void setCz_type(int cz_type) {
		this.cz_type = cz_type;
	}
	public void setFlag_bug(int flag_bug) {
		this.flag_bug = flag_bug;
	}
	public void setYc_detailed(String yc_detailed) {
		this.yc_detailed = yc_detailed;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	public void setTarget_name(String target_name) {
		this.target_name = target_name;
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
