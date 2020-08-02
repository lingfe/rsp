package com.rsp.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_t_equipment_archives.java
  * 描述： 设备档案表
  * 修改人： lingfe
  * 修改时间：2019年4月11日 上午9:42:35
  * 修改内容：
 */
public class Tab_t_equipment_archives {
	
	private String id;//	`id` VARCHAR(64) NOT NULL COMMENT '设备预排表id标识',
	private String hospital_id;//医院id
	private String hospital_name;//医院名称	  `equipment_name` VARCHAR(64) DEFAULT NULL COMMENT '设备名称',
	private String equipment_name;//设备名称
	private String specification_type;//	  `specification_type` VARCHAR(64) DEFAULT NULL COMMENT '规格型号',
	private String device_class;//	  `device_class` VARCHAR(64) DEFAULT NULL COMMENT '装置类别',
	private String equipment_number;//	  `equipment_number` VARCHAR(64) DEFAULT NULL COMMENT '设备编号',
	private String coding_id;//编码表id标识
	private String manfacturer_id;//生产厂家表id标识	  `manufacturer` VARCHAR(64) DEFAULT NULL COMMENT '生产厂家',
	private String rated_voltage_kv;//	  `rated_voltage_kv` VARCHAR(64) DEFAULT NULL COMMENT '额定电压（kV）',
	private String rated_current_ma;//	  `rated_current_ma` VARCHAR(64) DEFAULT NULL COMMENT '额定电流（mA）',
	private String rated_power_w;//	  `rated_power_w` VARCHAR(64) DEFAULT NULL COMMENT '额定功率（w,kw)',
	private String equipment_nameplate_photo;//	  `equipment_nameplate_photo` VARCHAR(64) DEFAULT NULL COMMENT '设备铭牌照片',
	private String department_id;//科室id
	private String department_name;//科室名称	  `department` VARCHAR(64) DEFAULT NULL COMMENT '科室',
	private String purpose;//	  `purpose` VARCHAR(64) DEFAULT NULL COMMENT '用途',
	private String location;//	  `location` VARCHAR(64) DEFAULT NULL COMMENT '所在场所',
	private Integer is_obtain_rdtl;//	  `is_obtain_rdtl` INT(11) DEFAULT '0' COMMENT '是否取得放射诊疗许可证: 0=否，1=是',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date obtain_rdtl_date;//	  `obtain_rdtl_date` DATE DEFAULT NULL COMMENT '取得放射诊疗许可证时间',
	private Integer is_obtain_rsl;//	  `is_obtain_rsl` INT(11) DEFAULT '0' COMMENT '是否取得辐射安全许可证: 0=否，1=是',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date obtain_rsl_date;//	  `obtain_rsl_date` DATE DEFAULT NULL COMMENT '取得辐射安全许可证时间',
	private Integer is_obtain_lcl;//	  `is_obtain_lcl` INT(11) DEFAULT '0' COMMENT '是否取得大型配置许可证: 0=否，1=是',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date obtain_lcl_date;//	  `obtain_lcl_date` DATE DEFAULT NULL COMMENT '取得大型配置许可证时间',
	private String source;//	  `source` VARCHAR(64) DEFAULT NULL COMMENT '来源',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date installation_date;//	  `installation_date` DATE DEFAULT NULL COMMENT '安装时间',
	private String whereabouts;//	  `whereabouts` VARCHAR(64) DEFAULT NULL COMMENT '去向',
	private Integer is_scrap;//是否报废
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date scrap_date;//	  `scrap_date` DATE DEFAULT NULL COMMENT '报废时间',
	
	private Integer stop_flag;//	   stop_flag INT DEFAULT 1 COMMENT '停用标记 0=停用,1=启用',
	private String stop_code;//	   stop_code VARCHAR(64) COMMENT '停用人id',
	private String stop_name;//	   stop_name VARCHAR(64)  COMMENT '停用人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stop_date;//	   stop_date DATETIME COMMENT '停用时间',
	private String stop_explain;//停用说明
	
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
	
	public List<Tab_equipment_walkthrough> eq_list;//设备预排list
	public String coding_name;//编码名称
	public String manfacturer_name;//生产厂家名称
	
	

	public String getId() {
		return id;
	}
	public String getHospital_id() {
		return hospital_id;
	}
	public String getHospital_name() {
		return hospital_name;
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
	public String getDepartment_id() {
		return department_id;
	}
	public String getDepartment_name() {
		return department_name;
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
	public Integer getIs_scrap() {
		return is_scrap;
	}
	public Date getScrap_date() {
		return scrap_date;
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
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
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
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
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
	public void setIs_scrap(Integer is_scrap) {
		this.is_scrap = is_scrap;
	}
	public void setScrap_date(Date scrap_date) {
		this.scrap_date = scrap_date;
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
