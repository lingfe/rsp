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

/*Table structure for table `system_log` */

DROP TABLE IF EXISTS `system_log`;

CREATE TABLE `system_log` (
  `id` varchar(64) NOT NULL COMMENT '系统日志表id标识',
  `ip` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `model_name` varchar(64) DEFAULT NULL COMMENT '模块名称',
  `operation_type` int(11) DEFAULT '0' COMMENT '操作类型: 1=查询,2=修改,3=删除，4=添加，0=其他',
  `is_bug` int(11) DEFAULT '0' COMMENT '是否有异常 0=否，1=是',
  `exceptionally_detailed` text COMMENT '异常详细',
  `target_id` varchar(64) DEFAULT NULL COMMENT '目标id，在执行修改、删除时记录',
  `state` int(11) DEFAULT '1' COMMENT '状态',
  `cdate` datetime DEFAULT NULL COMMENT '创建时间',
  `mdate` datetime DEFAULT NULL COMMENT '最后修改时间',
  `creator` varchar(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` varchar(64) DEFAULT 'admin' COMMENT '修改人',
  `version` varchar(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

/*Data for the table `system_log` */

insert  into `system_log`(`id`,`ip`,`model_name`,`operation_type`,`is_bug`,`exceptionally_detailed`,`target_id`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) values ('2222222222','192.168.44.23','update',2,0,'sdfsf','4323534245324',1,NULL,'2019-03-20 17:20:44','admin','admin','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
