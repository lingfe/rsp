

CREATE TABLE `system_log` (
  `id` VARCHAR(64) NOT NULL COMMENT '系统日志表id标识',
  
	ip VARCHAR(64) COMMENT 'ip地址',
	model_name VARCHAR(64) COMMENT '模块名称',
	operation_type INT DEFAULT 0 COMMENT '操作类型: 1=查询,2=修改,3=删除，4=添加，0=其他',
	is_bug INT COMMENT '是否有异常 0=否，1=是',
	exceptionally_detailed TEXT COMMENT '异常详细',
	target_id VARCHAR(64) COMMENT '目标id，在执行修改、删除时记录',
  
  `state` INT(11) DEFAULT '1' COMMENT '状态',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='系统日志表';



CREATE TABLE `process_coding` (
  `id` VARCHAR(64) NOT NULL COMMENT '流程编码表id标识',
  `remark` VARCHAR(1024) DEFAULT NULL COMMENT '备注',
  `state` INT(11) DEFAULT '1' COMMENT '状态：1=启用，0=停用',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='流程编码表';


CREATE TABLE `certificate_coding` (
  `id` VARCHAR(64) NOT NULL COMMENT '证照编码表id标识',
  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
  `state` INT(11) DEFAULT '1' COMMENT '状态：1=启用，0=停用',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='证照编码表';



SELECT * FROM coding WHERE 1=1 AND state=1 AND  coding_name LIKE '%测%' ORDER BY  mdate DESC  LIMIT 0,10
