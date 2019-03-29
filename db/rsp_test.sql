/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.6.35-log : Database - rsp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rsp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rsp`;

/*Table structure for table `licence_walkthrough` */

CREATE TABLE project_manage(

	id VARCHAR(64) NOT NULL COMMENT '项目管理表id标识',
	NAME VARCHAR(64) COMMENT '项目名称',
	start_date DATETIME COMMENT '开始时间',
	end_date DATETIME COMMENT '结束时间',
	person_in_charge VARCHAR(64) COMMENT '负责人',
	attribution_office VARCHAR(64) COMMENT '归属科室',
	TYPE VARCHAR(64) COMMENT '项目类型',
	classification VARCHAR(64) COMMENT '项目分类',
	procedure_link VARCHAR(64) COMMENT '流程环节',
	remark VARCHAR(1024) COMMENT '项目说明',

  `state` INT(11) DEFAULT '1' COMMENT '状态',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='项目管理表';

DROP TABLE IF EXISTS `licence_walkthrough`;

CREATE TABLE `licence_walkthrough` (
  `id` VARCHAR(64) NOT NULL COMMENT '证照预排表id标识',
  `hospital_name` VARCHAR(64) DEFAULT NULL COMMENT '医院名称',
  `prearranged_type` VARCHAR(64) DEFAULT NULL COMMENT '预排类型',
  `licence_type` VARCHAR(64) DEFAULT NULL COMMENT '证照类型',
  `certificates_number` VARCHAR(64) DEFAULT NULL COMMENT '证件编号',
  `organization_name` VARCHAR(64) DEFAULT NULL COMMENT '机构名称',
  `legal_person_charge` VARCHAR(64) DEFAULT NULL COMMENT '负责人/法人',
  `address` VARCHAR(64) DEFAULT NULL COMMENT '地址',
  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
  `is_annual_inspection` INT(11) DEFAULT NULL COMMENT '是否年度检验:0=否，1=是',
  `is_annual_assessment` INT(11) DEFAULT NULL COMMENT '是否年度评估:0=否，1=是',
  `is_licence_continue` INT(11) DEFAULT NULL COMMENT '是否证照延续:0=否，1=是',
  `inspection_date` DATETIME DEFAULT NULL COMMENT '检验时间',
  `inspection_cycle` VARCHAR(64) NOT NULL COMMENT '检验周期',
  `assessment_date` DATETIME DEFAULT NULL COMMENT '评估时间',
  `evaluation_cycle` VARCHAR(64) NOT NULL COMMENT '评估周期',
  `valid_certificate` VARCHAR(64) DEFAULT NULL COMMENT '有效期',
  `certification_authority` VARCHAR(64) DEFAULT NULL COMMENT '发证机构',
  `issuing_date` DATETIME DEFAULT NULL COMMENT '发证时间',
  `state` INT(11) DEFAULT '1' COMMENT '状态',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`,`evaluation_cycle`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='证照预排表';

/*Data for the table `licence_walkthrough` */

INSERT  INTO `licence_walkthrough`(`id`,`hospital_name`,`prearranged_type`,`licence_type`,`certificates_number`,`organization_name`,`legal_person_charge`,`address`,`remark`,`is_annual_inspection`,`is_annual_assessment`,`is_licence_continue`,`inspection_date`,`inspection_cycle`,`assessment_date`,`evaluation_cycle`,`valid_certificate`,`certification_authority`,`issuing_date`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) VALUES ('234235234234','士大夫',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'',NULL,NULL,NULL,1,NULL,NULL,'','admin','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
