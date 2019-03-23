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

/*Table structure for table `manufacturer` */

DROP TABLE IF EXISTS `manufacturer`;

CREATE TABLE `manufacturer` (
  `id` varchar(64) NOT NULL COMMENT '生产厂家id标识表',
  `name` varchar(64) DEFAULT NULL COMMENT '生产厂家名称',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `state` int(11) DEFAULT '0' COMMENT '状态',
  `cdate` datetime DEFAULT NULL COMMENT '创建时间',
  `mdate` datetime DEFAULT NULL COMMENT '最后修改时间',
  `creator` varchar(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` varchar(64) DEFAULT 'admin' COMMENT '修改人',
  `version` varchar(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`),
  KEY `creator` (`creator`),
  CONSTRAINT `manufacturer_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生产厂家表';

/*Data for the table `manufacturer` */

insert  into `manufacturer`(`id`,`name`,`remark`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) values ('1111111assssssss','GE','。。。',0,NULL,'2019-03-22 14:23:45','23423423423','23423423423','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
