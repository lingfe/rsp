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

/*Table structure for table `equipment_walkthrough` */

DROP TABLE IF EXISTS `equipment_walkthrough`;

CREATE TABLE `equipment_walkthrough` (
  `id` varchar(64) NOT NULL COMMENT '设备预排表id标识',
  `equipment_name` varchar(64) DEFAULT NULL COMMENT '设备名称',
  `specification_type` varchar(64) DEFAULT NULL COMMENT '规格型号',
  `device_class` varchar(64) DEFAULT NULL COMMENT '装置类别',
  `equipment_number` varchar(64) DEFAULT NULL COMMENT '设备编号',
  `manufacturer` varchar(64) DEFAULT NULL COMMENT '生产厂家',
  `rated_voltage_kv` varchar(64) DEFAULT NULL COMMENT '额定电压（kV）',
  `rated_current_mA` varchar(64) DEFAULT NULL COMMENT '额定电流（mA）',
  `rated_power_w` varchar(64) DEFAULT NULL COMMENT '额定功率（w,kw)',
  `equipment_nameplate_photo` varchar(64) DEFAULT NULL COMMENT '设备铭牌照片',
  `department` varchar(64) DEFAULT NULL COMMENT '科室',
  `purpose` varchar(64) DEFAULT NULL COMMENT '用途',
  `location` varchar(64) DEFAULT NULL COMMENT '所在场所',
  `is_obtain_rdtl` int(11) DEFAULT '0' COMMENT '是否取得放射诊疗许可证: 0=否，1=是',
  `obtain_rdtl_date` date DEFAULT NULL COMMENT '取得放射诊疗许可证时间',
  `is_obtain_rsl` int(11) DEFAULT '0' COMMENT '是否取得辐射安全许可证: 0=否，1=是',
  `obtain_rsl_date` date DEFAULT NULL COMMENT '取得辐射安全许可证时间',
  `is_obtain_lcl` int(11) DEFAULT '0' COMMENT '是否取得大型配置许可证: 0=否，1=是',
  `obtain_lcl_date` date DEFAULT NULL COMMENT '取得大型配置许可证时间',
  `source` varchar(64) DEFAULT NULL COMMENT '来源',
  `installation_date` date DEFAULT NULL COMMENT '安装时间',
  `whereabouts` varchar(64) DEFAULT NULL COMMENT '去向',
  `scrap_date` date DEFAULT NULL COMMENT '报废时间',
  `annual_inspection_report` varchar(64) DEFAULT NULL COMMENT '年检报告(上传)',
  `instructions_for_equipment` varchar(64) DEFAULT NULL COMMENT '设备说明书(上传)',
  `medical_care_certificate` varchar(64) DEFAULT NULL COMMENT '医疗器械注册证(上传)',
  `state` int(11) DEFAULT '1' COMMENT '状态：1=启用，0=停用',
  `cdate` datetime DEFAULT NULL COMMENT '创建时间',
  `mdate` datetime DEFAULT NULL COMMENT '最后修改时间',
  `creator` varchar(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` varchar(64) DEFAULT 'admin' COMMENT '修改人或者停用人的id',
  `version` varchar(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`),
  KEY `creator` (`creator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备编码表';

/*Data for the table `equipment_walkthrough` */

insert  into `equipment_walkthrough`(`id`,`equipment_name`,`specification_type`,`device_class`,`equipment_number`,`manufacturer`,`rated_voltage_kv`,`rated_current_mA`,`rated_power_w`,`equipment_nameplate_photo`,`department`,`purpose`,`location`,`is_obtain_rdtl`,`obtain_rdtl_date`,`is_obtain_rsl`,`obtain_rsl_date`,`is_obtain_lcl`,`obtain_lcl_date`,`source`,`installation_date`,`whereabouts`,`scrap_date`,`annual_inspection_report`,`instructions_for_equipment`,`medical_care_certificate`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) values ('2222222','343','1','2','3','4','5','6','7','8','9','10','11',0,'2019-03-26',0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,'23423423423','admin','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
