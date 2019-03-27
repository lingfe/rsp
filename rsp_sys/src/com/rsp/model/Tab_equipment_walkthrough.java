package com.rsp.model;

import java.util.Date;
import java.util.UUID;

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
	
	Tab_equipment_walkthrough(){
		this.id=UUID.randomUUID().toString().replace("-", "");
	}

	private String id;//	`id` VARCHAR(64) NOT NULL COMMENT '设备预排表id标识',
	private String equipment_name;//	  `equipment_name` VARCHAR(64) DEFAULT NULL COMMENT '设备名称',
	private String specification_type;//	  `specification_type` VARCHAR(64) DEFAULT NULL COMMENT '规格型号',
	private String device_class;//	  `device_class` VARCHAR(64) DEFAULT NULL COMMENT '装置类别',
	private String equipment_number;//	  `equipment_number` VARCHAR(64) DEFAULT NULL COMMENT '设备编号',
	private String coding_id;//编码表id标识
	private String manfacturer_id;//生产厂家表id标识	  `manufacturer` VARCHAR(64) DEFAULT NULL COMMENT '生产厂家',
	private String rated_voltage_kv;//	  `rated_voltage_kv` VARCHAR(64) DEFAULT NULL COMMENT '额定电压（kV）',
	private String rated_current_ma;//	  `rated_current_ma` VARCHAR(64) DEFAULT NULL COMMENT '额定电流（mA）',
	private String rated_power_w;//	  `rated_power_w` VARCHAR(64) DEFAULT NULL COMMENT '额定功率（w,kw)',
	private String equipment_nameplate_photo;//	  `equipment_nameplate_photo` VARCHAR(64) DEFAULT NULL COMMENT '设备铭牌照片',
	private String department;//	  `department` VARCHAR(64) DEFAULT NULL COMMENT '科室',
	private String purpose;//	  `purpose` VARCHAR(64) DEFAULT NULL COMMENT '用途',
	private String location;//	  `location` VARCHAR(64) DEFAULT NULL COMMENT '所在场所',
	private Integer is_obtain_rdtl;//	  `is_obtain_rdtl` INT(11) DEFAULT '0' COMMENT '是否取得放射诊疗许可证: 0=否，1=是',
	private Date obtain_rdtl_date;//	  `obtain_rdtl_date` DATE DEFAULT NULL COMMENT '取得放射诊疗许可证时间',
	private Integer is_obtain_rsl;//	  `is_obtain_rsl` INT(11) DEFAULT '0' COMMENT '是否取得辐射安全许可证: 0=否，1=是',
	private Date obtain_rsl_date;//	  `obtain_rsl_date` DATE DEFAULT NULL COMMENT '取得辐射安全许可证时间',
	private Integer is_obtain_lcl;//	  `is_obtain_lcl` INT(11) DEFAULT '0' COMMENT '是否取得大型配置许可证: 0=否，1=是',
	private Date obtain_lcl_date;//	  `obtain_lcl_date` DATE DEFAULT NULL COMMENT '取得大型配置许可证时间',
	private String source;//	  `source` VARCHAR(64) DEFAULT NULL COMMENT '来源',
	private Date installation_date;//	  `installation_date` DATE DEFAULT NULL COMMENT '安装时间',
	private String whereabouts;//	  `whereabouts` VARCHAR(64) DEFAULT NULL COMMENT '去向',
	private Date scrap_date;//	  `scrap_date` DATE DEFAULT NULL COMMENT '报废时间',
	
	
//	/*	private String annual_inspection_report;
//	 * //	  `annual_inspection_report` VARCHAR(64) DEFAULT NULL COMMENT '年检报告(上传)',
//		private String instructions_for_equipment;
//		//	  `instructions_for_equipment` VARCHAR(64) DEFAULT NULL COMMENT '设备说明书(上传)',
//		private String medical_care_certificate;
//		//	  `medical_care_certificate` VARCHAR(64) DEFAULT NULL COMMENT '医疗器械注册证(上传)',
//	*/
	
	private Integer state=0;// 1=启用，0=停用	  `state` INT(11) DEFAULT '1' COMMENT '状态',
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
	private String infirmary_name;//医院名称
	private Integer is_scrap;//是否报废 0=否，1=是
	
	//param ===>>表示该字段是参数需要,不会同步到数据库
	public String creator_name;//创建人名称
	public String modify_name;//修改人名称
	
	
	
	
	public Integer getIs_scrap() {
		return is_scrap;
	}
	public void setIs_scrap(Integer is_scrap) {
		this.is_scrap = is_scrap;
	}
	public String getInfirmary_name() {
		return infirmary_name;
	}
	public void setInfirmary_name(String infirmary_name) {
		this.infirmary_name = infirmary_name;
	}
	public String getId() {
		return id;
	}
	public String getEquipment_name() {
		return equipment_name;
	}
	public String getSpecification_type() {
		return specification_type;
	}
	public String getDevice_class() {
		return device_class;
	}
	public String getEquipment_number() {
		return equipment_number;
	}
	public String getCoding_id() {
		return coding_id;
	}
	public String getManfacturer_id() {
		return manfacturer_id;
	}
	public String getRated_voltage_kv() {
		return rated_voltage_kv;
	}
	public String getRated_current_ma() {
		return rated_current_ma;
	}
	public String getRated_power_w() {
		return rated_power_w;
	}
	public String getEquipment_nameplate_photo() {
		return equipment_nameplate_photo;
	}
	public String getDepartment() {
		return department;
	}
	public String getPurpose() {
		return purpose;
	}
	public String getLocation() {
		return location;
	}
	public Integer getIs_obtain_rdtl() {
		return is_obtain_rdtl;
	}
	public Date getObtain_rdtl_date() {
		return obtain_rdtl_date;
	}
	public Integer getIs_obtain_rsl() {
		return is_obtain_rsl;
	}
	public Date getObtain_rsl_date() {
		return obtain_rsl_date;
	}
	public Integer getIs_obtain_lcl() {
		return is_obtain_lcl;
	}
	public Date getObtain_lcl_date() {
		return obtain_lcl_date;
	}
	public String getSource() {
		return source;
	}
	public Date getInstallation_date() {
		return installation_date;
	}
	public String getWhereabouts() {
		return whereabouts;
	}
	public Date getScrap_date() {
		return scrap_date;
	}
	public Integer getState() {
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
	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}
	public void setSpecification_type(String specification_type) {
		this.specification_type = specification_type;
	}
	public void setDevice_class(String device_class) {
		this.device_class = device_class;
	}
	public void setEquipment_number(String equipment_number) {
		this.equipment_number = equipment_number;
	}
	public void setCoding_id(String coding_id) {
		this.coding_id = coding_id;
	}
	public void setManfacturer_id(String manfacturer_id) {
		this.manfacturer_id = manfacturer_id;
	}
	public void setRated_voltage_kv(String rated_voltage_kv) {
		this.rated_voltage_kv = rated_voltage_kv;
	}
	public void setRated_current_ma(String rated_current_ma) {
		this.rated_current_ma = rated_current_ma;
	}
	public void setRated_power_w(String rated_power_w) {
		this.rated_power_w = rated_power_w;
	}
	public void setEquipment_nameplate_photo(String equipment_nameplate_photo) {
		this.equipment_nameplate_photo = equipment_nameplate_photo;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setIs_obtain_rdtl(Integer is_obtain_rdtl) {
		this.is_obtain_rdtl = is_obtain_rdtl;
	}
	public void setObtain_rdtl_date(Date obtain_rdtl_date) {
		this.obtain_rdtl_date = obtain_rdtl_date;
	}
	public void setIs_obtain_rsl(Integer is_obtain_rsl) {
		this.is_obtain_rsl = is_obtain_rsl;
	}
	public void setObtain_rsl_date(Date obtain_rsl_date) {
		this.obtain_rsl_date = obtain_rsl_date;
	}
	public void setIs_obtain_lcl(Integer is_obtain_lcl) {
		this.is_obtain_lcl = is_obtain_lcl;
	}
	public void setObtain_lcl_date(Date obtain_lcl_date) {
		this.obtain_lcl_date = obtain_lcl_date;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setInstallation_date(Date installation_date) {
		this.installation_date = installation_date;
	}
	public void setWhereabouts(String whereabouts) {
		this.whereabouts = whereabouts;
	}
	public void setScrap_date(Date scrap_date) {
		this.scrap_date = scrap_date;
	}
	public void setState(Integer state) {
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
