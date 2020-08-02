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

/*Table structure for table `t_yw_sb_sbda` */

DROP TABLE IF EXISTS `t_yw_sb_sbda`;

CREATE TABLE `t_yw_sb_sbda` (
  `id` varchar(64) NOT NULL COMMENT '医院设备档案表id标识',
  `hospital_id` varchar(64) DEFAULT NULL COMMENT '医院id',
  `sb_name` varchar(64) DEFAULT NULL COMMENT '设备名称',
  `sb_ggxh` varchar(64) DEFAULT NULL COMMENT '设备规格型号',
  `sb_zzlb_id` varchar(64) DEFAULT NULL COMMENT '设备装置类别id,关联编码信息表sys_coding,type=',
  `sb_code` varchar(64) DEFAULT NULL COMMENT '设备编号',
  `sb_manufacturer_id` varchar(64) DEFAULT NULL COMMENT '设备生产厂家id',
  `sb_eddy` varchar(64) DEFAULT NULL COMMENT '额定电压（kV）',
  `sb_eddl` varchar(64) DEFAULT NULL COMMENT '额定电流（mA）',
  `sb_edgl` varchar(64) DEFAULT NULL COMMENT '额定功率（w,kw)',
  `sb_glks_id` varchar(64) DEFAULT NULL COMMENT '设备管理科室id，关联医院科室信息t_yy_ksxx表id',
  `sb_syks_id` varchar(64) DEFAULT NULL COMMENT '设备使用科室id，关联医院科室信息t_yy_ksxx表id',
  `sb_yt_id` varchar(64) DEFAULT NULL COMMENT '设备用途id,关联Sys_coding表id,Coding_type=20002',
  `sb_szcs` varchar(200) DEFAULT NULL COMMENT '设备所在场所',
  `flag_fszlxkz` int(11) DEFAULT '0' COMMENT '是否取得放射诊疗许可证: 0=否，1=是',
  `fszlxkz_state_name` varchar(200) DEFAULT NULL COMMENT '放射诊疗许可证状态名称，从项目执行中进行反写',
  `fszlxkz_xm_id` varchar(64) DEFAULT NULL COMMENT '放射诊疗许可证关联的项目执行id',
  `fszlxkz_date` date DEFAULT NULL COMMENT '取得放射诊疗许可证时间',
  `flag_fsaqxkz` int(11) DEFAULT '0' COMMENT '是否取得辐射安全许可证: 0=否，1=是',
  `fsaqxkz_state_name` varchar(200) DEFAULT NULL COMMENT '辐射安全许可证 状态 名称，从项目执行中进行反写',
  `fsaqxkz_xm_id` varchar(64) DEFAULT NULL COMMENT '辐射安全许可证 关联的项目执行id',
  `fsaqxkz_date` date DEFAULT NULL COMMENT '取得 辐射安全许可证 时间',
  `flag_dxpzxkz` int(11) DEFAULT '0' COMMENT '是否取得 大型配置许可证: 0=否，1=是',
  `dxpzxkz_state_name` varchar(200) DEFAULT NULL COMMENT '大型配置许可证 状态 名称，从项目执行中进行反写',
  `dxpzxkz_xm_id` varchar(64) DEFAULT NULL COMMENT '大型配置许可证 关联的项目执行id',
  `dxpzxkz_date` date DEFAULT NULL COMMENT '取得 大型配置许可证 时间',
  `sb_ly` varchar(64) DEFAULT NULL COMMENT '来源',
  `sb_azsj` date DEFAULT NULL COMMENT '安装时间',
  `sb_qx` varchar(64) DEFAULT NULL COMMENT '去向',
  `flag_sb_state` int(11) DEFAULT '0' COMMENT '设备状态标记: 0=正常在用,1=作废,2=报废',
  `sb_state_date` date DEFAULT NULL COMMENT '设备状态时间',
  `sb_state_remark` varchar(200) DEFAULT NULL COMMENT '设备状态备注',
  `sb_state_name` varchar(64) DEFAULT NULL COMMENT '设备状态操作人名称',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `stop_flag` int(11) DEFAULT '1' COMMENT '停用标记 0=停用,1=启用',
  `stop_code` varchar(64) DEFAULT NULL COMMENT '停用人id',
  `stop_name` varchar(64) DEFAULT NULL COMMENT '停用人姓名',
  `stop_date` datetime DEFAULT NULL COMMENT '停用时间',
  `stop_explain` varchar(1024) DEFAULT NULL COMMENT '停用说明',
  `crt_code` varchar(64) DEFAULT NULL COMMENT '创建人id',
  `crt_name` varchar(64) DEFAULT NULL COMMENT '创建人姓名',
  `crt_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_code` varchar(64) DEFAULT NULL COMMENT '修改人id',
  `modify_name` varchar(64) DEFAULT NULL COMMENT '修改人姓名',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '数据版本，整数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医院设备档案表';

/*Data for the table `t_yw_sb_sbda` */

insert  into `t_yw_sb_sbda`(`id`,`hospital_id`,`sb_name`,`sb_ggxh`,`sb_zzlb_id`,`sb_code`,`sb_manufacturer_id`,`sb_eddy`,`sb_eddl`,`sb_edgl`,`sb_glks_id`,`sb_syks_id`,`sb_yt_id`,`sb_szcs`,`flag_fszlxkz`,`fszlxkz_state_name`,`fszlxkz_xm_id`,`fszlxkz_date`,`flag_fsaqxkz`,`fsaqxkz_state_name`,`fsaqxkz_xm_id`,`fsaqxkz_date`,`flag_dxpzxkz`,`dxpzxkz_state_name`,`dxpzxkz_xm_id`,`dxpzxkz_date`,`sb_ly`,`sb_azsj`,`sb_qx`,`flag_sb_state`,`sb_state_date`,`sb_state_remark`,`sb_state_name`,`remark`,`stop_flag`,`stop_code`,`stop_name`,`stop_date`,`stop_explain`,`crt_code`,`crt_name`,`crt_date`,`modify_code`,`modify_name`,`modify_date`,`version`) values ('1001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,0,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
