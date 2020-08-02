package com.rsp.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
  * 文件名：Tab_t_yw_sb_sbda.java
  * 描述： 医院设备档案表
  * 修改人： lingfe
  * 修改时间：2019年4月19日 下午2:51:37
  * 修改内容：
 */
public class Tab_t_yy_sb_sbda {

	private String id;//`id` VARCHAR(64) NOT NULL COMMENT '医院设备档案表id标识',
	private String hospital_id;//`hospital_id`   '医院id',
	
	private String sb_name;//`sb_name`   '设备名称',
	private String sb_ggxh;//`sb_ggxh`   '设备规格型号',
	private String sb_zzlb_id;//`sb_zzlb_id`   '设备装置类别id,关联编码信息表sys_coding,type=',
	private String sb_code;//`sb_code`   '设备编号',
	private String sb_manufacturer_id;//`sb_manufacturer_id`   '设备生产厂家id',
	private String sb_eddy;//`sb_eddy`   '额定电压（kV）',
	private String sb_eddl;//`sb_eddl`   '额定电流（mA）',
	private String sb_edgl;//`sb_edgl`   '额定功率（w,kw)',
	private String sb_glks_id;//`sb_glks_id`   '设备管理科室id，关联医院科室信息t_yy_ksxx表id',
	private String sb_syks_id;//`sb_syks_id`   '设备使用科室id，关联医院科室信息t_yy_ksxx表id',
	private String sb_yt_id;//`sb_yt_id`   '设备用途id,关联Sys_coding表id,Coding_type=20002',
	private String sb_szcs;//`sb_szcs` VARCHAR(200) DEFAULT NULL COMMENT '设备所在场所',
	
	private Integer  flag_fszlxkz=0;//`flag_fszlxkz` INT DEFAULT 0 COMMENT '是否取得放射诊疗许可证: 0=否，1=是',
	private String  fszlxkz_state_name;//`fszlxkz_state_name` VARCHAR(200) DEFAULT NULL COMMENT '放射诊疗许可证状态名称，从项目执行中进行反写',
	private String fszlxkz_xm_id;//`fszlxkz_xm_id`   '放射诊疗许可证关联的项目执行id',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  fszlxkz_date;//`fszlxkz_date`  '取得放射诊疗许可证时间',
	
	private Integer  flag_fsaqxkz=0;//`flag_fsaqxkz` INT DEFAULT 0 COMMENT '是否取得辐射安全许可证: 0=否，1=是',
	private String  fsaqxkz_state_name;//`fsaqxkz_state_name` VARCHAR(200) DEFAULT NULL COMMENT '辐射安全许可证 状态 名称，从项目执行中进行反写',
	private String  fsaqxkz_xm_id;//`fsaqxkz_xm_id`   '辐射安全许可证 关联的项目执行id',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  fsaqxkz_date;//`fsaqxkz_date`  '取得 辐射安全许可证 时间',
	
	private Integer  flag_dxpzxkz=0;//`flag_dxpzxkz` INT DEFAULT 0 COMMENT '是否取得 大型配置许可证: 0=否，1=是',
	private String   dxpzxkz_state_name;//`dxpzxkz_state_name` VARCHAR(200) DEFAULT NULL COMMENT '大型配置许可证 状态 名称，从项目执行中进行反写',
	private String   dxpzxkz_xm_id;//`dxpzxkz_xm_id`   '大型配置许可证 关联的项目执行id',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  dxpzxkz_date;//`dxpzxkz_date`  '取得 大型配置许可证 时间',  
	
	private String  sb_ly;//`sb_ly` VARCHAR(64)  COMMENT '来源',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  sb_azsj;//`sb_azsj` '安装时间',
	private String   sb_qx;//`sb_qx`  '去向',
	private Integer  flag_sb_state=0;//`flag_sb_state`  '设备状态标记: 0=正常在用,1=作废,2=报废',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sb_state_date;//`sb_state_date`  '设备状态时间',
	private String sb_state_remark;//`sb_state_remark` VARCHAR(200) DEFAULT NULL COMMENT '设备状态备注',
	private String sb_state_name;//`sb_state_name`   '设备状态操作人名称',
	private String remark;//`remark`   '备注',
	
	private int stop_flag=1;// stop_flag  '停用标记 0=停用,1=启用',
	private String stop_code;// stop_code  '停用人id',
	private String stop_name;// stop_name  '停用人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stop_date;// stop_date  '停用时间',
	private String stop_explain;//停用说明
	
	private Integer state;//state  '状态',
	private String crt_code;//crt_code  '创建人id',
	private String crt_name;//crt_name  '创建人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date crt_date;//crt_date  '创建时间',
	private String modify_code;//modify_code  '修改人id',
	private String modify_name;//modify_name  '修改人姓名',
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modify_date;//modify_date  '修改时间',
	private Integer version=0;// 	`version`   '数据版本',
	
	//设备装置类别名称
	public String sb_zzlb_name;
	//设备生产厂家名称
	public String sb_manfacturer_name;
	//设备使用科室名称
	public String sb_syks_name;
	//设备预排数据集合
	public List<Tab_equipment_walkthrough> yp_list;
	//修改内容
	public List<String> str_list;
	
	public String getId() {
		return id;
	}
	public String getHospital_id() {
		return hospital_id;
	}
	public String getSb_name() {
		return sb_name;
	}
	public String getSb_ggxh() {
		return sb_ggxh;
	}
	public String getSb_zzlb_id() {
		return sb_zzlb_id;
	}
	public String getSb_code() {
		return sb_code;
	}
	public String getSb_manufacturer_id() {
		return sb_manufacturer_id;
	}
	public String getSb_eddy() {
		return sb_eddy;
	}
	public String getSb_eddl() {
		return sb_eddl;
	}
	public String getSb_edgl() {
		return sb_edgl;
	}
	public String getSb_glks_id() {
		return sb_glks_id;
	}
	public String getSb_syks_id() {
		return sb_syks_id;
	}
	public String getSb_yt_id() {
		return sb_yt_id;
	}
	public String getSb_szcs() {
		return sb_szcs;
	}
	public Integer getFlag_fszlxkz() {
		return flag_fszlxkz;
	}
	public String getFszlxkz_state_name() {
		return fszlxkz_state_name;
	}
	public String getFszlxkz_xm_id() {
		return fszlxkz_xm_id;
	}
	public Date getFszlxkz_date() {
		return fszlxkz_date;
	}
	public Integer getFlag_fsaqxkz() {
		return flag_fsaqxkz;
	}
	public String getFsaqxkz_state_name() {
		return fsaqxkz_state_name;
	}
	public String getFsaqxkz_xm_id() {
		return fsaqxkz_xm_id;
	}
	public Date getFsaqxkz_date() {
		return fsaqxkz_date;
	}
	public Integer getFlag_dxpzxkz() {
		return flag_dxpzxkz;
	}
	public String getDxpzxkz_state_name() {
		return dxpzxkz_state_name;
	}
	public String getDxpzxkz_xm_id() {
		return dxpzxkz_xm_id;
	}
	public Date getDxpzxkz_date() {
		return dxpzxkz_date;
	}
	public String getSb_ly() {
		return sb_ly;
	}
	public Date getSb_azsj() {
		return sb_azsj;
	}
	public String getSb_qx() {
		return sb_qx;
	}
	public Integer getFlag_sb_state() {
		return flag_sb_state;
	}
	public Date getSb_state_date() {
		return sb_state_date;
	}
	public String getSb_state_remark() {
		return sb_state_remark;
	}
	public String getSb_state_name() {
		return sb_state_name;
	}
	public String getRemark() {
		return remark;
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
	public Integer getVersion() {
		return version;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}
	public void setSb_name(String sb_name) {
		this.sb_name = sb_name;
	}
	public void setSb_ggxh(String sb_ggxh) {
		this.sb_ggxh = sb_ggxh;
	}
	public void setSb_zzlb_id(String sb_zzlb_id) {
		this.sb_zzlb_id = sb_zzlb_id;
	}
	public void setSb_code(String sb_code) {
		this.sb_code = sb_code;
	}
	public void setSb_manufacturer_id(String sb_manufacturer_id) {
		this.sb_manufacturer_id = sb_manufacturer_id;
	}
	public void setSb_eddy(String sb_eddy) {
		this.sb_eddy = sb_eddy;
	}
	public void setSb_eddl(String sb_eddl) {
		this.sb_eddl = sb_eddl;
	}
	public void setSb_edgl(String sb_edgl) {
		this.sb_edgl = sb_edgl;
	}
	public void setSb_glks_id(String sb_glks_id) {
		this.sb_glks_id = sb_glks_id;
	}
	public void setSb_syks_id(String sb_syks_id) {
		this.sb_syks_id = sb_syks_id;
	}
	public void setSb_yt_id(String sb_yt_id) {
		this.sb_yt_id = sb_yt_id;
	}
	public void setSb_szcs(String sb_szcs) {
		this.sb_szcs = sb_szcs;
	}
	public void setFlag_fszlxkz(Integer flag_fszlxkz) {
		this.flag_fszlxkz = flag_fszlxkz;
	}
	public void setFszlxkz_state_name(String fszlxkz_state_name) {
		this.fszlxkz_state_name = fszlxkz_state_name;
	}
	public void setFszlxkz_xm_id(String fszlxkz_xm_id) {
		this.fszlxkz_xm_id = fszlxkz_xm_id;
	}
	public void setFszlxkz_date(Date fszlxkz_date) {
		this.fszlxkz_date = fszlxkz_date;
	}
	public void setFlag_fsaqxkz(Integer flag_fsaqxkz) {
		this.flag_fsaqxkz = flag_fsaqxkz;
	}
	public void setFsaqxkz_state_name(String fsaqxkz_state_name) {
		this.fsaqxkz_state_name = fsaqxkz_state_name;
	}
	public void setFsaqxkz_xm_id(String fsaqxkz_xm_id) {
		this.fsaqxkz_xm_id = fsaqxkz_xm_id;
	}
	public void setFsaqxkz_date(Date fsaqxkz_date) {
		this.fsaqxkz_date = fsaqxkz_date;
	}
	public void setFlag_dxpzxkz(Integer flag_dxpzxkz) {
		this.flag_dxpzxkz = flag_dxpzxkz;
	}
	public void setDxpzxkz_state_name(String dxpzxkz_state_name) {
		this.dxpzxkz_state_name = dxpzxkz_state_name;
	}
	public void setDxpzxkz_xm_id(String dxpzxkz_xm_id) {
		this.dxpzxkz_xm_id = dxpzxkz_xm_id;
	}
	public void setDxpzxkz_date(Date dxpzxkz_date) {
		this.dxpzxkz_date = dxpzxkz_date;
	}
	public void setSb_ly(String sb_ly) {
		this.sb_ly = sb_ly;
	}
	public void setSb_azsj(Date sb_azsj) {
		this.sb_azsj = sb_azsj;
	}
	public void setSb_qx(String sb_qx) {
		this.sb_qx = sb_qx;
	}
	public void setFlag_sb_state(Integer flag_sb_state) {
		this.flag_sb_state = flag_sb_state;
	}
	public void setSb_state_date(Date sb_state_date) {
		this.sb_state_date = sb_state_date;
	}
	public void setSb_state_remark(String sb_state_remark) {
		this.sb_state_remark = sb_state_remark;
	}
	public void setSb_state_name(String sb_state_name) {
		this.sb_state_name = sb_state_name;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public void setVersion(Integer version) {
		this.version = version;
	}
}
