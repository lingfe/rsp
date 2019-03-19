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

/*Table structure for table `coding` */

DROP TABLE IF EXISTS `coding`;

CREATE TABLE `coding` (
  `id` VARCHAR(64) NOT NULL COMMENT '编码表id标识',
  `coding_type` INT(11) DEFAULT NULL COMMENT '编码类型: 0=其他，\n					1=性别，2=职称，3=工作性质，\n					4=职业照射分类，5=附件类别，\n					6=装置类别，7=设备用途，8=流程类型，\n					9=流程分类，',
  `coding_name` VARCHAR(64) DEFAULT NULL COMMENT '编码名称',
  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
  `state` INT(11) DEFAULT '1' COMMENT '状态：1=启用，0=停用',
  `cdate` DATETIME DEFAULT NULL COMMENT '创建时间',
  `mdate` DATETIME DEFAULT NULL COMMENT '最后修改时间',
  `creator` VARCHAR(64) DEFAULT 'admin' COMMENT '创建人',
  `modify` VARCHAR(64) DEFAULT 'admin' COMMENT '修改人',
  `version` VARCHAR(64) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`id`),
  KEY `creator` (`creator`),
  CONSTRAINT `coding_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `user_info` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='编码表';

/*Data for the table `coding` */

INSERT  INTO `coding`(`id`,`coding_type`,`coding_name`,`remark`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) VALUES ('32334234234',1,'测试数据','无',1,'2019-03-19 16:08:26','2019-03-19 16:08:24','23423423423','admin','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!4insert  into `coding`(`id`,`coding_type`,`coding_name`,`remark`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) values ('32334234234',1,'测试数据','无',1,'2019-03-19 16:08:26','2019-03-19 16:08:24','23423423423','admin','0');
0014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
