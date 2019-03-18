

/*
	coding			1.编码表
	process_setting		2.流程设置表
	enclosure		3.附件表
	certificate_coding	4.证照编码
	process_coding		5.流程编码
	manufacturer		6.生产厂家
	device_coding		7.设备编码表
	personnel_coding	8.人员编码表
	user_info		10.用户信息表
*/


/*证照预排*/
CREATE TABLE certificate_prearrangement(
	
	id VARCHAR(64) NOT NULL COMMENT '证照预排表id标识',
	hospital_name VARCHAR(64) COMMENT '医院名称',
	prearranged_type VARCHAR(64) COMMENT '预排类型',
	certification_type VARCHAR(64) COMMENT '证照类型',
	organization_name VARCHAR(64) COMMENT '机构名称',
	legal_person_charge VARCHAR(64) COMMENT '负责人/法人',
	address VARCHAR(64) COMMENT '地址',
	remark VARCHAR(64) COMMENT '备注',
	is_annual_inspection INT COMMENT '是否年度检验:0=否，1=是',
	is_annual_assessment INT COMMENT '是否年度评估:0=否，1=是',
	is_licence_continue INT COMMENT '是否证照延续:0=否，1=是',
	inspection_date DATE COMMENT '检验时间',
	inspection_cycle VARCHAR(64) COMMENT '检验周期',
	assessment_date VARCHAR(64) COMMENT '评估时间',
	evaluation_cycle VARCHAR(64) COMMENT '评估周期',
	valid_certificate VARCHAR(64) COMMENT '有效证',
	certificates_number VARCHAR(64) COMMENT '证件编号',
	certification_authority VARCHAR(64) COMMENT '发证机构',
	issuing_date VARCHAR(64) COMMENT '发证时间',
	

  `state` INT(11) DEFAULT 1 COMMENT '状态',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='证照预排表';

/*编码表*/
CREATE TABLE coding(
	id VARCHAR(64) NOT NULL COMMENT '编码表id标识',
	coding_type INT(11) COMMENT '编码类型: 0=其他，
					1=性别，2=职称，3=工作性质，
					4=职业照射分类，5=附件类别，
					6=装置类别，7=设备用途，8=流程类型，
					9=流程分类，',
	
	coding_name VARCHAR(64) COMMENT '编码名称',
	remark VARCHAR(64) COMMENT '备注',
	
	
  `state` INT(11) DEFAULT 1 COMMENT '状态：1=启用，0=停用',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='编码表';

/*附件表*/
CREATE TABLE enclosure(
	id VARCHAR(64) NOT NULL COMMENT '附件表id标识',
	enclosure_type VARCHAR(64) COMMENT '附件类型',
	enclosure_name VARCHAR(1024) COMMENT '附件名称',
	enclosure_remark VARCHAR(1024) COMMENT '附件说明',
	
  `state` INT(11) DEFAULT 1 COMMENT '状态',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='附件表';

/*流程设置*/
CREATE TABLE process_setting(

	id VARCHAR(64) NOT NULL COMMENT '流程设置表id标识',
	process_type VARCHAR(64) COMMENT '流程类型',
	process_classification VARCHAR(64) COMMENT '流程分类',
	process_name VARCHAR(64) COMMENT '流程名称',
	process_link INT(11) DEFAULT 0 COMMENT '流程环节',
	remark VARCHAR(64) COMMENT '备注',
	
  `state` INT(11) DEFAULT 1 COMMENT '状态',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='流程设置表';

/*证照编码*/
CREATE TABLE certificate_coding(
	
	id VARCHAR(64) NOT NULL COMMENT '证照编码表id标识',
	remark VARCHAR(64) COMMENT '备注',
	
  `state` INT(11) DEFAULT 1 COMMENT '状态：1=启用，0=停用',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='证照编码表';

/*流程编码*/
CREATE TABLE process_coding(

	id VARCHAR(64) NOT NULL COMMENT '流程编码表id标识',
	remark VARCHAR(1024) COMMENT '备注',
	
	
  `state` INT(11) DEFAULT 1 COMMENT '状态：1=启用，0=停用',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='流程编码表';

/*生产厂家*/
CREATE TABLE manufacturer(
	
	id VARCHAR(64) NOT NULL COMMENT '生产厂家id标识表',
	remark VARCHAR(1024) COMMENT '备注',
	
  `state` INT(11) DEFAULT 0 COMMENT '状态',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='生产厂家表';

/*设备编码表*/
CREATE TABLE device_coding(
	id VARCHAR(64) NOT NULL COMMENT '设备编码表id标识',
	device_name VARCHAR(64) COMMENT '设备名称',
	specification_type VARCHAR(64) COMMENT '规格型号',
	device_class VARCHAR(64) COMMENT '装置类别',
	equipment_number VARCHAR(64) COMMENT '设备编号',
	manufacturer VARCHAR(64) COMMENT '生产厂家',
	rated_voltage_kv VARCHAR(64) COMMENT '额定电压（kV）',
	rated_current_mA VARCHAR(64) COMMENT '额定电流（mA）',
	rated_power_w VARCHAR(64) COMMENT '额定功率（w,kw)',
	equipment_nameplate_photo VARCHAR(64) COMMENT '设备铭牌照片',
	department VARCHAR(64) COMMENT '科室',
	purpose VARCHAR(64) COMMENT '用途',
	location VARCHAR(64) COMMENT '所在场所',
	is_obtain_rdtl INT DEFAULT 0 COMMENT '是否取得放射诊疗许可证: 0=否，1=是',
	obtain_rdtl_date DATE COMMENT '取得放射诊疗许可证时间',
	is_obtain_rsl INT DEFAULT 0 COMMENT '是否取得辐射安全许可证: 0=否，1=是',
	obtain_rsl_date DATE COMMENT '取得辐射安全许可证时间',
	is_obtain_lcl INT DEFAULT 0 COMMENT '是否取得大型配置许可证: 0=否，1=是',
	obtain_lcl_date DATE COMMENT '取得大型配置许可证时间',
	source VARCHAR(64) COMMENT '来源',
	installation_date DATE COMMENT '安装时间',
	whereabouts VARCHAR(64) COMMENT '去向',
	scrap_date DATE COMMENT '报废时间',
	annual_inspection_report VARCHAR(64) COMMENT '年检报告(上传)',
	instructions_for_equipment VARCHAR(64) COMMENT '设备说明书(上传)',
	medical_care_certificate VARCHAR(64) COMMENT '医疗器械注册证(上传)',

	
  `state` INT(11) DEFAULT '1' COMMENT '状态：1=启用，0=停用',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人或者停用人的id',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='设备编码表';

/*人员编码*/
CREATE TABLE personnel_coding(
	id VARCHAR(64) NOT NULL COMMENT '人员编码id标识',
	sex INT DEFAULT 0 COMMENT '性别: 0=男，1=女',
	full_name VARCHAR(64) COMMENT '姓名',
	job_number VARCHAR(64) COMMENT '工号',
	department VARCHAR(64) COMMENT '科室',
	age INT COMMENT '年龄',
	position_title VARCHAR(64) COMMENT '职称',
	practising_range VARCHAR(64) COMMENT '执业范围',
	practising_place VARCHAR(64) COMMENT '执业地点',
	quarters INT	DEFAULT 0 COMMENT '岗位：1=医生，2=技师，3=护士，0=其他',
	work_nature INT DEFAULT 0 COMMENT '工作性质：1=正式，2=临时，3=岗培，4=规培，0=其他',
	is_equipment_work_license INT DEFAULT 0 COMMENT '是否取得大型设备上岗证: 0=否，1=是',
	sfz VARCHAR(64) COMMENT '身份证',
	birth_date DATE COMMENT '出生日期',
	entry_date DATE COMMENT '入职日期',
	quit_date DATE COMMENT '离职日期',
	workradiation_date DATE COMMENT '开始从事放射工作时间',
	working_years INT COMMENT '接害工龄(月)',
	knock_off_date DATE COMMENT '取得放工证时间',
	knock_off_certificate_number VARCHAR(64) COMMENT '放工证号',
	is_dorn_personal_meter INT COMMENT '是否佩戴个人剂量仪:0=否，1=是',
	occupation_irradiation_code VARCHAR(64) COMMENT '职业照射代码',
	vironmental_protection_train VARCHAR(64) COMMENT '环保培训证',
	vironmental_protection_train_date DATE COMMENT '环保培训时间',
	hygiene_train VARCHAR(64) COMMENT '卫生培训证',
	hygiene_train_date DATE COMMENT '卫生培训时间',
	physical_examination_date DATE COMMENT '体检时间',
	remark VARCHAR(1024) COMMENT '备注',
	
	
  `state` INT(11) DEFAULT '1' COMMENT '状态：1=启用，0=停用',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人id',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人或者停用人的id',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='人员编码表';

/*用户信息表*/
CREATE TABLE `user_info` (
  `id` VARCHAR(64) NOT NULL COMMENT '用户信息表id标识',
  `openid` VARCHAR(64) DEFAULT NULL COMMENT '微信用户对应小程序的openid',
  `username` VARCHAR(64) DEFAULT NULL COMMENT '用户名称',
  `realname` VARCHAR(64) DEFAULT NULL COMMENT '真实姓名',
  `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像',
  `relo` INT(11) DEFAULT '0' COMMENT '用户角色，0=普通用户  1=商户用  2=管理用户\n 3=企业用户',
  `pwd` VARCHAR(64) DEFAULT NULL COMMENT '登录密码',
  `tel` VARCHAR(64) DEFAULT NULL COMMENT '电话',
  `email` VARCHAR(64) DEFAULT NULL COMMENT '邮件',
  `mobile` VARCHAR(64) DEFAULT NULL COMMENT '移动电话号码',
  `qq` VARCHAR(64) DEFAULT NULL COMMENT 'QQ',
  `balance` DOUBLE DEFAULT '0' COMMENT '余额',
  `lastTime` DATETIME DEFAULT NULL COMMENT '最后一次登陆时间',
  `state` INT(11) DEFAULT '0' COMMENT '账号状态：0未启用，1正常，2、异常，3冻结',
  `idCard` VARCHAR(255) DEFAULT NULL COMMENT '身份证号码',
  `provinceCode` VARCHAR(64) DEFAULT NULL COMMENT '省',
  `provinceName` VARCHAR(64) DEFAULT NULL COMMENT '省',
  `cityCode` VARCHAR(64) DEFAULT NULL COMMENT '市',
  `cityName` VARCHAR(64) DEFAULT NULL COMMENT '市',
  `regionCode` VARCHAR(64) DEFAULT NULL COMMENT '区',
  `regionName` VARCHAR(64) DEFAULT NULL COMMENT '区',
  `address` VARCHAR(256) DEFAULT NULL COMMENT '详细地址',
  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
