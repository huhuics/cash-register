/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.37 : Database - cash-register
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cash-register` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cash-register`;

/*Table structure for table `exchange_job_detail` */

DROP TABLE IF EXISTS `exchange_job_detail`;

CREATE TABLE `exchange_job_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `seller_no` varchar(32) NOT NULL COMMENT '收银员编号',
  `checkout_total_amount` int(11) DEFAULT NULL COMMENT '收银总额，单位：分',
  `cash_amount` int(11) DEFAULT NULL COMMENT '现金支付总额，单位：分',
  `balance_amount` int(11) DEFAULT NULL COMMENT '用户余额支付,即储值卡支付,单位：分',
  `unionpay_amount` int(11) DEFAULT NULL COMMENT '银联支付，单位：分',
  `alipay_amount` int(11) DEFAULT NULL COMMENT '支付宝支付金额，单位：分',
  `wcpay_amount` int(11) DEFAULT NULL COMMENT '微信支付金额，单位：分',
  `petty_cash_amount` int(11) DEFAULT NULL COMMENT '备用金',
  `paid_amount` int(11) DEFAULT NULL COMMENT '备用，实缴金额',
  `isFinished` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已完成交接班，1：完成，0：未完成',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exchange_job_detail` */

/*Table structure for table `goods_brand` */

DROP TABLE IF EXISTS `goods_brand`;

CREATE TABLE `goods_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `brand_name` varchar(128) NOT NULL COMMENT '品牌名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_NAME` (`brand_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `goods_brand` */

insert  into `goods_brand`(`id`,`brand_name`,`gmt_update`,`gmt_create`) values (3,'优衣库','2018-04-28 18:59:53','2018-04-28 18:59:26'),(4,'Nike','2018-05-10 14:43:42','2018-05-10 14:42:52'),(5,'Adidas','2018-05-10 14:43:46','2018-05-10 14:42:56');

/*Table structure for table `goods_category` */

DROP TABLE IF EXISTS `goods_category`;

CREATE TABLE `goods_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `category_name` varchar(64) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父节点id',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_NAME_ID` (`category_name`,`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `goods_category` */

insert  into `goods_category`(`id`,`category_name`,`parent_id`,`gmt_update`,`gmt_create`) values (1,'root',0,'2018-04-18 11:42:14','2018-04-18 11:42:14'),(14,'男士上衣',1,'2018-05-10 14:38:18','2018-05-10 14:37:28'),(15,'男士裤子',1,'2018-05-10 14:39:53','2018-05-10 14:39:03'),(16,'女士上衣',1,'2018-05-10 14:40:02','2018-05-10 14:39:12'),(17,'男士衬衫',14,'2018-05-10 14:40:13','2018-05-10 14:39:23'),(18,'男士T恤',14,'2018-05-10 14:40:22','2018-05-10 14:39:32'),(19,'男士西裤',15,'2018-05-10 14:40:30','2018-05-10 14:39:40'),(20,'男士休闲裤',15,'2018-05-10 14:40:38','2018-05-10 14:39:48'),(21,'女士运动上衣',16,'2018-05-10 14:50:30','2018-05-10 14:49:40'),(22,'男鞋',1,'2018-05-10 15:17:05','2018-05-10 15:16:15'),(24,'男运动鞋',22,'2018-05-10 15:17:37','2018-05-10 15:16:47'),(46,'b',44,'2018-05-10 19:02:34','2018-05-10 19:01:47');

/*Table structure for table `goods_color` */

DROP TABLE IF EXISTS `goods_color`;

CREATE TABLE `goods_color` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `color` varchar(64) NOT NULL COMMENT '颜色',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `goods_color` */

insert  into `goods_color`(`id`,`color`,`gmt_update`,`gmt_create`) values (12,'红色','2018-05-10 14:42:05','2018-05-10 14:41:15'),(13,'黄色','2018-05-10 14:42:10','2018-05-10 14:41:20'),(14,'蓝色','2018-05-10 14:42:13','2018-05-10 14:41:23'),(15,'花色','2018-05-10 14:42:16','2018-05-10 14:41:26');

/*Table structure for table `goods_image` */

DROP TABLE IF EXISTS `goods_image`;

CREATE TABLE `goods_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `goods_image` blob NOT NULL COMMENT '图片的二进制数据，大小限制为1MB',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_image` */

/*Table structure for table `goods_info` */

DROP TABLE IF EXISTS `goods_info`;

CREATE TABLE `goods_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `goods_image_id` bigint(20) DEFAULT NULL COMMENT '商品图片，外键id',
  `goods_name` varchar(128) NOT NULL COMMENT '商品名称',
  `bar_code` varchar(128) NOT NULL COMMENT '商品条码=商品货号+子条码，唯一标识一件商品',
  `product_number` varchar(128) NOT NULL COMMENT '商品货号,标识一类商品',
  `pinyin_code` varchar(128) DEFAULT NULL COMMENT '拼音码，即商品名称首字母组合',
  `category_name` varchar(128) NOT NULL COMMENT '商品分类名称',
  `goods_status` tinyint(1) DEFAULT NULL COMMENT '状态，1：启动，0：停用',
  `goods_brand` varchar(128) DEFAULT NULL COMMENT '商品品牌',
  `goods_color` varchar(64) DEFAULT NULL COMMENT '商品颜色',
  `goods_size` varchar(64) DEFAULT NULL COMMENT '商品尺码',
  `goods_tag` varchar(128) DEFAULT NULL COMMENT '商品标签，半角逗号分隔开',
  `goods_stock` int(20) NOT NULL COMMENT '商品库存',
  `quantity_unit` varchar(32) DEFAULT NULL COMMENT '库存单位。个，件，杯，瓶',
  `stock_upper_limit` int(11) DEFAULT NULL COMMENT '库存上限',
  `stock_lower_limit` int(11) DEFAULT NULL COMMENT '库存下限',
  `last_import_price` int(11) NOT NULL COMMENT '该商品最近一次进货价，单位：分',
  `average_import_price` int(11) NOT NULL COMMENT '加权平均进货价，页面进货价显示该值，单位：分',
  `sales_price` int(11) NOT NULL COMMENT '销售价，单位：分',
  `trade_price` int(11) DEFAULT NULL COMMENT '批发价，单位：分',
  `vip_price` int(11) DEFAULT NULL COMMENT '会员价，单位：分',
  `is_vip_discount` tinyint(1) NOT NULL DEFAULT '1' COMMENT '会员是否有折扣。1：是。0：否',
  `supplier_name` varchar(128) DEFAULT NULL COMMENT '供货商名称',
  `production_date` varchar(32) DEFAULT NULL COMMENT '生产日期，年-月-日',
  `quality_guarantee_period` int(11) DEFAULT NULL COMMENT '保质期，单位：天',
  `is_integral` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否积分，1：是。0：否',
  `royalty_type` varchar(64) NOT NULL DEFAULT '{"type":"0","value":"0"}' COMMENT 'JSON格式。提成方式。0：不提成。1：销售价*导购员提成百分比。2：利润*导购员提成百分比。3.固定金额。4.销售价*百分比。5.利润*百分比',
  `is_booked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '能否预约，1：能。0：否',
  `is_gift` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品是否允许赠送，1：是。0：否',
  `is_weigh` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否称重，1：是。0：否',
  `is_fixed_price` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否固价，1：是。0：否。固价表示不参与任何折扣',
  `is_timeing_price` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否时价，1：是。0：否。时价表示根据当天情况定价',
  `is_hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏，1：是。0：否',
  `remark` varchar(1024) DEFAULT NULL COMMENT '商品备注',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_BAR_CODE` (`bar_code`),
  KEY `IDX_PINYIN_CODE` (`pinyin_code`),
  KEY `IDX_GOODS_NAME` (`goods_name`),
  KEY `IDX_GOODS_TAG` (`goods_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

/*Data for the table `goods_info` */

insert  into `goods_info`(`id`,`goods_image_id`,`goods_name`,`bar_code`,`product_number`,`pinyin_code`,`category_name`,`goods_status`,`goods_brand`,`goods_color`,`goods_size`,`goods_tag`,`goods_stock`,`quantity_unit`,`stock_upper_limit`,`stock_lower_limit`,`last_import_price`,`average_import_price`,`sales_price`,`trade_price`,`vip_price`,`is_vip_discount`,`supplier_name`,`production_date`,`quality_guarantee_period`,`is_integral`,`royalty_type`,`is_booked`,`is_gift`,`is_weigh`,`is_fixed_price`,`is_timeing_price`,`is_hidden`,`remark`,`gmt_update`,`gmt_create`) values (74,NULL,'优衣库男士衬衫','20180510144017883-001','20180510144017883','yyknscs','男士衬衫',1,'优衣库','红色','S','新衣服',160,'件',200,10,6000,6000,10000,8000,NULL,1,'优衣库','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 14:48:34','2018-05-10 14:47:44'),(75,NULL,'优衣库男士衬衫','20180510144017883-002','20180510144017883','yyknscs','男士衬衫',1,'优衣库','红色','M','新衣服',99,'件',200,10,6000,6000,10000,8000,NULL,1,'优衣库','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 15:30:01','2018-05-10 14:44:09'),(76,NULL,'优衣库男士衬衫','20180510144017883-003','20180510144017883','yyknscs','男士衬衫',1,'优衣库','黄色','S','新衣服',99,'件',200,10,6000,6000,10000,8000,NULL,1,'优衣库','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 15:30:01','2018-05-10 14:44:09'),(77,NULL,'优衣库男士衬衫','20180510144017883-004','20180510144017883','yyknscs','男士衬衫',1,'优衣库','黄色','M','新衣服',0,'件',200,10,6000,6000,10000,8000,NULL,1,'优衣库','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 14:44:59','2018-05-10 14:44:09'),(78,NULL,'adidas女士运动上衣','20180510144945408-001','20180510144945408','adidasnsydsy','女士运动上衣',1,'Adidas','红色','XL','',48,'件',NULL,NULL,8000,8000,20000,NULL,NULL,1,'Adidas','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 15:53:43','2018-05-10 14:50:51'),(79,NULL,'adidas女士运动上衣','20180510144945408-002','20180510144945408','adidasnsydsy','女士运动上衣',1,'Adidas','红色','L','',49,'件',40,10,8000,8000,20000,NULL,NULL,1,'Adidas','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 21:11:18','2018-05-10 21:10:31'),(80,NULL,'adidas女士运动上衣','20180510144945408-003','20180510144945408','adidasnsydsy','女士运动上衣',1,'Adidas','黄色','XL','',50,'件',NULL,NULL,8000,8000,20000,NULL,NULL,1,'Adidas','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 14:51:41','2018-05-10 14:50:51'),(81,NULL,'adidas女士运动上衣','20180510144945408-004','20180510144945408','adidasnsydsy','女士运动上衣',1,'Adidas','黄色','L','',50,'件',NULL,NULL,8000,8000,20000,NULL,NULL,1,'Adidas','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 14:51:41','2018-05-10 14:50:51'),(82,NULL,'adidas女士运动上衣','20180510144945408-005','20180510144945408','adidasnsydsy','女士运动上衣',1,'Adidas','蓝色','XL','',50,'件',NULL,NULL,8000,8000,20000,NULL,NULL,1,'Adidas','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 14:51:41','2018-05-10 14:50:51'),(83,NULL,'adidas女士运动上衣','20180510144945408-006','20180510144945408','adidasnsydsy','女士运动上衣',1,'Adidas','蓝色','L','',50,'件',NULL,NULL,8000,8000,20000,NULL,NULL,1,'Adidas','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 14:51:41','2018-05-10 14:50:51'),(84,NULL,'adidas女士运动上衣','20180510144945408-007','20180510144945408','adidasnsydsy','女士运动上衣',1,'Adidas','花色','XL','',50,'件',NULL,NULL,8000,8000,20000,NULL,NULL,1,'Adidas','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 14:51:41','2018-05-10 14:50:51'),(85,NULL,'adidas女士运动上衣','20180510144945408-008','20180510144945408','adidasnsydsy','女士运动上衣',1,'Adidas','花色','L','',50,'件',NULL,NULL,8000,8000,20000,NULL,NULL,1,'Adidas','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 14:51:41','2018-05-10 14:50:51'),(86,NULL,'Nike空军一号运动鞋','20180510151712752-001','20180510151712752','nikekjyhydx','男运动鞋',1,'Nike','红色','40','',19,'双',NULL,NULL,20000,20000,50000,45000,NULL,1,'Nike','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 15:30:01','2018-05-10 15:20:30'),(87,NULL,'Nike空军一号运动鞋','20180510151712752-002','20180510151712752','nikekjyhydx','男运动鞋',1,'Nike','红色','42','',20,'双',NULL,NULL,20000,20000,50000,NULL,NULL,1,'Nike','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 15:19:24','2018-05-10 15:18:34'),(88,NULL,'Nike空军一号运动鞋','20180510151712752-003','20180510151712752','nikekjyhydx','男运动鞋',1,'Nike','红色','41','',19,'双',NULL,NULL,20000,20000,50000,NULL,NULL,1,'Nike','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 15:30:01','2018-05-10 15:18:34'),(89,NULL,'Nike空军一号运动鞋','20180510151712752-004','20180510151712752','nikekjyhydx','男运动鞋',1,'Nike','黄色','40','',20,'双',NULL,NULL,20000,20000,50000,NULL,NULL,1,'Nike','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 15:19:24','2018-05-10 15:18:34'),(90,NULL,'Nike空军一号运动鞋','20180510151712752-005','20180510151712752','nikekjyhydx','男运动鞋',1,'Nike','黄色','42','',20,'双',NULL,NULL,20000,20000,50000,NULL,NULL,1,'Nike','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 15:19:24','2018-05-10 15:18:34'),(91,NULL,'Nike空军一号运动鞋','20180510151712752-006','20180510151712752','nikekjyhydx','男运动鞋',1,'Nike','黄色','41','',20,'双',NULL,NULL,20000,20000,50000,NULL,NULL,1,'Nike','',NULL,0,'{type:\"0\",value:\"0\"}',0,0,0,0,0,0,'','2018-05-10 15:19:24','2018-05-10 15:18:34');

/*Table structure for table `goods_lose_info` */

DROP TABLE IF EXISTS `goods_lose_info`;

CREATE TABLE `goods_lose_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `shop_name` varchar(128) DEFAULT NULL COMMENT '报损门店',
  `goods_detail` varchar(2048) DEFAULT NULL COMMENT '报损商品信息详情',
  `total_lose_amount` int(11) NOT NULL COMMENT '报损金额。单位：分',
  `turnover_percent` double DEFAULT NULL COMMENT '预留，营业额占比',
  `operator_no` varchar(32) DEFAULT NULL COMMENT '操作员编号',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品报损表';

/*Data for the table `goods_lose_info` */

/*Table structure for table `goods_lose_reason` */

DROP TABLE IF EXISTS `goods_lose_reason`;

CREATE TABLE `goods_lose_reason` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `reason` varchar(128) DEFAULT NULL COMMENT '报损原因',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_lose_reason` */

/*Table structure for table `goods_quantity_unit` */

DROP TABLE IF EXISTS `goods_quantity_unit`;

CREATE TABLE `goods_quantity_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `unit_name` varchar(32) NOT NULL COMMENT '单位名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_NAME` (`unit_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `goods_quantity_unit` */

insert  into `goods_quantity_unit`(`id`,`unit_name`,`gmt_update`,`gmt_create`) values (9,'件','2018-05-10 14:43:17','2018-05-10 14:42:27'),(10,'双','2018-05-10 15:19:11','2018-05-10 15:18:21');

/*Table structure for table `goods_size` */

DROP TABLE IF EXISTS `goods_size`;

CREATE TABLE `goods_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `size_name` varchar(64) NOT NULL COMMENT '尺寸名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `goods_size` */

insert  into `goods_size`(`id`,`size_name`,`gmt_update`,`gmt_create`) values (6,'S','2018-05-10 14:42:20','2018-05-10 14:41:30'),(7,'M','2018-05-10 14:42:23','2018-05-10 14:41:33'),(8,'L','2018-05-10 14:42:26','2018-05-10 14:41:36'),(9,'XL','2018-05-10 14:42:28','2018-05-10 14:41:38'),(10,'40','2018-05-10 15:18:29','2018-05-10 15:17:39'),(11,'41','2018-05-10 15:18:32','2018-05-10 15:17:42'),(12,'42','2018-05-10 15:18:34','2018-05-10 15:17:44');

/*Table structure for table `goods_stock_check` */

DROP TABLE IF EXISTS `goods_stock_check`;

CREATE TABLE `goods_stock_check` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `check_date` timestamp NULL DEFAULT NULL COMMENT '盘点时间',
  `seller_no` varchar(32) NOT NULL COMMENT '盘点人，即收银员编号',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='盘点表';

/*Data for the table `goods_stock_check` */

/*Table structure for table `goods_stock_check_detail` */

DROP TABLE IF EXISTS `goods_stock_check_detail`;

CREATE TABLE `goods_stock_check_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `check_id` bigint(20) NOT NULL COMMENT '外键，关联goods_stock_check主键',
  `goods_name` varchar(128) NOT NULL COMMENT '商品名称',
  `bar_code` varchar(128) NOT NULL COMMENT '商品条码',
  `goods_color` varchar(64) DEFAULT NULL COMMENT '商品颜色',
  `goods_size` varchar(64) DEFAULT NULL COMMENT '商品尺寸',
  `product_number` varchar(128) NOT NULL COMMENT '货号',
  `primary_goods_stock` int(11) NOT NULL COMMENT '原库存',
  `checked_goods_stock` int(11) NOT NULL COMMENT '盘点库存',
  `stock_diff` int(11) NOT NULL COMMENT '库存差异=盘点库存-原库存。可能为负',
  `profit_loss_amount` int(11) DEFAULT NULL COMMENT '盈亏金额=库存差异*加权平均进货价，单位：分。可能为负',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_stock_check_detail` */

/*Table structure for table `goods_stock_flow` */

DROP TABLE IF EXISTS `goods_stock_flow`;

CREATE TABLE `goods_stock_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `goods_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `bar_code` varchar(128) NOT NULL COMMENT '商品条码',
  `flow_type` varchar(64) NOT NULL COMMENT '库存变动类型。商户退货、商品销售、货流进货、货流退货等等',
  `flow_count` int(11) NOT NULL COMMENT '库存变动数量',
  `check_count` int(11) DEFAULT NULL COMMENT '校正库存数量',
  `out_biz_no` varchar(64) DEFAULT NULL COMMENT '外部流水号。例如商品销售，则是交易订单号；进货则是进货单号',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_GOODS_NAME` (`goods_name`),
  KEY `IDX_BAR_CODE` (`bar_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='库存流水表';

/*Data for the table `goods_stock_flow` */

insert  into `goods_stock_flow`(`id`,`goods_name`,`bar_code`,`flow_type`,`flow_count`,`check_count`,`out_biz_no`,`remark`,`gmt_update`,`gmt_create`) values (1,'优衣库男士衬衫','20180510144017883-001','goods_stock_edit',50,NULL,'20180510144017883-001',NULL,'2018-05-10 14:48:16','2018-05-10 14:47:26'),(2,'优衣库男士衬衫','20180510144017883-001','goods_stock_edit',10,NULL,'20180510144017883-001',NULL,'2018-05-10 14:48:34','2018-05-10 14:47:44');

/*Table structure for table `goods_tag` */

DROP TABLE IF EXISTS `goods_tag`;

CREATE TABLE `goods_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `tag_name` varchar(32) NOT NULL COMMENT '标签名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `goods_tag` */

insert  into `goods_tag`(`id`,`tag_name`,`gmt_update`,`gmt_create`) values (6,'新衣服','2018-04-26 20:17:55','2018-04-26 20:16:10'),(7,'新鞋','2018-04-26 21:25:01','2018-04-26 21:24:45'),(8,'运动','2018-04-26 21:25:05','2018-04-26 21:24:49');

/*Table structure for table `goods_traffic` */

DROP TABLE IF EXISTS `goods_traffic`;

CREATE TABLE `goods_traffic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `traffic_no` varchar(64) NOT NULL COMMENT '货流单号',
  `traffic_type` varchar(8) NOT NULL COMMENT '货流类型.in:进货,ordinaryOut:普通出库；supplierOut：退货给供货商',
  `status` tinyint(1) NOT NULL COMMENT '状态。1：已完成。0：处理中',
  `goods_name` varchar(128) NOT NULL COMMENT '商品名称',
  `bar_code` varchar(128) NOT NULL COMMENT '商品条码',
  `goods_color` varchar(64) DEFAULT NULL COMMENT '商品颜色',
  `goods_size` varchar(64) DEFAULT NULL COMMENT '商品尺寸',
  `supplier_name` varchar(128) DEFAULT NULL COMMENT '供货商名称',
  `goods_stock` int(11) NOT NULL COMMENT '商品库存',
  `in_count` int(11) DEFAULT NULL COMMENT '进货量',
  `in_amount` int(11) DEFAULT NULL COMMENT '进货价，单位：分',
  `free_count` int(11) DEFAULT NULL COMMENT '进货赠送量',
  `advance_payment_amount` int(11) DEFAULT NULL COMMENT '预付款，单位：分',
  `quantity_unit` varchar(32) DEFAULT NULL COMMENT '单位',
  `out_price_type` varchar(64) DEFAULT NULL COMMENT '出库价格类型。last_import_price：以最近进货价出库；average_import_price：以平均进货价出库；sales_price：以商品销售价；trade_price：以商品批发价',
  `out_amount` int(11) DEFAULT NULL COMMENT '出库价，单位：分',
  `out_count` int(11) DEFAULT NULL COMMENT '出库量',
  `total_amount` int(11) DEFAULT NULL COMMENT '小计。即进货总价或出库总价',
  `operator_no` varchar(64) DEFAULT NULL COMMENT '操作员编号',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_TRAFFIC_NO` (`traffic_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货流表';

/*Data for the table `goods_traffic` */

/*Table structure for table `member_info` */

DROP TABLE IF EXISTS `member_info`;

CREATE TABLE `member_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用。1：启用。0：不启用',
  `member_no` varchar(64) NOT NULL COMMENT '会员编号',
  `member_name` varchar(64) NOT NULL COMMENT '会员姓名',
  `member_rank` varchar(64) NOT NULL COMMENT '会员等级',
  `member_discount` int(11) NOT NULL DEFAULT '100' COMMENT '会员折扣。默认100即不打折，8.5折填85',
  `member_integral` double DEFAULT '0' COMMENT '会员积分',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `password` varchar(32) DEFAULT NULL COMMENT '会员密码',
  `birthday` varchar(32) DEFAULT NULL COMMENT '会员生日',
  `is_on_credit` tinyint(1) DEFAULT '0' COMMENT '是否允许赊账。1：允许。0：不允许',
  `qq_no` varchar(32) DEFAULT NULL COMMENT 'qq号码',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱地址',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `account_balance` int(11) DEFAULT '0' COMMENT '账户余额，单位:分',
  `shopper_name` varchar(64) DEFAULT NULL COMMENT '导购员',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_MEMBER_NO` (`member_no`),
  KEY `IDX_PHONE` (`phone`),
  KEY `IDX_MEMBER_NAME` (`member_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `member_info` */

insert  into `member_info`(`id`,`status`,`member_no`,`member_name`,`member_rank`,`member_discount`,`member_integral`,`phone`,`password`,`birthday`,`is_on_credit`,`qq_no`,`email`,`address`,`account_balance`,`shopper_name`,`remark`,`gmt_update`,`gmt_create`) values (6,1,'1001','张飞','银牌会员',80,0,'88886666','1001','',0,'','','',0,'','','2018-05-10 15:52:09','2018-05-10 15:07:37'),(7,1,'1002','关羽','铜牌会员',70,0,'55556666','1002','',1,'','','',0,'','','2018-05-10 15:52:13','2018-05-10 15:13:14'),(8,1,'1003','刘备','金牌会员',60,0,'11115555','1003','',1,'','','',0,'','','2018-05-10 15:52:02','2018-05-10 15:26:10');

/*Table structure for table `member_integral` */

DROP TABLE IF EXISTS `member_integral`;

CREATE TABLE `member_integral` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否启用。1：启用。0：停用',
  `integral_type` tinyint(1) DEFAULT NULL COMMENT '预留。积分方式.1:按金额积分。0：按商品积分',
  `integral_value` int(11) DEFAULT NULL COMMENT '每多少元积一分',
  `exchange_type` tinyint(1) DEFAULT NULL COMMENT '预留。积分兑换方式。1：兑换礼品。0：抵扣现金',
  `is_clear` tinyint(1) DEFAULT '0' COMMENT '是否清空积分。1：清空。0：不清空',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员积分方式表';

/*Data for the table `member_integral` */

/*Table structure for table `member_rank` */

DROP TABLE IF EXISTS `member_rank`;

CREATE TABLE `member_rank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `rank_title` varchar(128) NOT NULL COMMENT '等级名称',
  `discount` int(11) NOT NULL DEFAULT '100' COMMENT '优惠折扣，整数，8.5折填85',
  `is_integral` tinyint(1) DEFAULT '1' COMMENT '是否积分。1：积分。0：不计分',
  `is_auto_upgrade` tinyint(1) DEFAULT '0' COMMENT '是否自动升级。1：是。0：否',
  `integral_to_upgrade` int(11) DEFAULT NULL COMMENT '当积分到达该值时自动升级到该等级',
  `rank_period` tinyint(1) DEFAULT '0' COMMENT '该等级有效期。1：1年。0：永久',
  `prepaid_card_no` varchar(64) DEFAULT NULL COMMENT '预留。储值卡卡号',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员等级表';

/*Data for the table `member_rank` */

insert  into `member_rank`(`id`,`rank_title`,`discount`,`is_integral`,`is_auto_upgrade`,`integral_to_upgrade`,`rank_period`,`prepaid_card_no`,`gmt_update`,`gmt_create`) values (2,'金牌会员',80,1,1,300,0,'','2018-05-10 15:50:09','2018-05-10 15:49:19'),(3,'银牌会员',90,1,1,200,0,'','2018-05-10 15:51:11','2018-05-10 15:50:21'),(4,'铜牌会员',95,1,1,100,0,'','2018-05-10 15:51:30','2018-05-10 15:50:40');

/*Table structure for table `member_recharge_detail` */

DROP TABLE IF EXISTS `member_recharge_detail`;

CREATE TABLE `member_recharge_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `seller_no` varchar(32) NOT NULL COMMENT '收银员编号',
  `shopper_no` varchar(32) DEFAULT NULL COMMENT '导购编号',
  `recharge_amount` int(11) NOT NULL COMMENT '实际充值金额，单位:分',
  `donation_amount` int(11) DEFAULT NULL COMMENT '赠送金额，单位:分',
  `total_amount` int(11) NOT NULL COMMENT '充值总金额，单位:分',
  `pay_chenal` varchar(256) NOT NULL COMMENT 'JSON格式，支付方式及该方式对应的金额。金额单位：元。有可能是混合支付',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member_recharge_detail` */

/*Table structure for table `seller_info` */

DROP TABLE IF EXISTS `seller_info`;

CREATE TABLE `seller_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `part_of_shop` varchar(128) NOT NULL COMMENT '所属门店名',
  `seller_no` varchar(32) NOT NULL COMMENT '收银员编号，可用作登录名',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `role` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `password` varchar(32) DEFAULT NULL COMMENT '收银员登录密码，明文存储',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态，1表示启用，0表示禁用',
  `cash_permission` varchar(2048) DEFAULT '[]' COMMENT 'JSON格式，收银端权限代码集合',
  `background_permission` varchar(2048) DEFAULT '[]' COMMENT 'JSON格式，后台管理系统权限代码集合',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `UQ_SELLER_NO` (`seller_no`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `seller_info` */

insert  into `seller_info`(`id`,`part_of_shop`,`seller_no`,`name`,`role`,`password`,`phone`,`status`,`cash_permission`,`background_permission`,`gmt_update`,`gmt_create`) values (1,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',0,'[]','[]','2018-04-24 15:07:49','2018-04-19 11:04:06'),(2,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-05-02 09:43:59','2018-04-19 11:04:49'),(3,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(4,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(6,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(7,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(8,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(9,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(10,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[\"option1\",\"option2\",\"option3\",\"option4\"]','[]','2018-05-02 09:23:17','2018-04-19 11:04:49'),(11,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(12,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(13,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(14,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(15,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(16,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(17,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(18,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(19,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(20,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(21,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(22,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(23,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(24,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(25,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(26,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(27,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(28,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(29,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(30,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(31,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(32,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(33,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49');

/*Table structure for table `seller_permission_info` */

DROP TABLE IF EXISTS `seller_permission_info`;

CREATE TABLE `seller_permission_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `type` tinyint(1) NOT NULL COMMENT '权限种类，0：收银端，1：后台管理',
  `permission_code` varchar(32) NOT NULL COMMENT '权限代码',
  `permission_name` varchar(64) NOT NULL COMMENT '权限名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `seller_permission_info` */

/*Table structure for table `shopper_info` */

DROP TABLE IF EXISTS `shopper_info`;

CREATE TABLE `shopper_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `shopper_no` varchar(32) NOT NULL COMMENT '导购员编号',
  `name` varchar(64) NOT NULL COMMENT '导购姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  `sales_percentage` double DEFAULT NULL COMMENT '销售提成。如5.3%则填5.3',
  `recharge_percentage` double DEFAULT NULL COMMENT '充值提成。如5.3%则填5.3',
  `shopping_card_percentage` double DEFAULT NULL COMMENT '购物卡提成。如5.3%则填5.3',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态.1：启用。0：停用',
  `royalty_type` varchar(128) DEFAULT NULL COMMENT '提成方式',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `UQ_SHOPPER_NO` (`shopper_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `shopper_info` */

insert  into `shopper_info`(`id`,`shopper_no`,`name`,`phone`,`sales_percentage`,`recharge_percentage`,`shopping_card_percentage`,`status`,`royalty_type`,`gmt_update`,`gmt_create`) values (2,'5675','567567','567567',567,67,5675,1,'','2018-05-10 19:57:43','2018-05-10 19:56:56'),(3,'7898','7897','89789',879,789789,89,1,'','2018-05-10 20:13:14','2018-05-10 20:07:35');

/*Table structure for table `supplier_info` */

DROP TABLE IF EXISTS `supplier_info`;

CREATE TABLE `supplier_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `supplier_code` varchar(64) NOT NULL COMMENT '供货商编号',
  `supplier_name` varchar(128) NOT NULL COMMENT '供货商名称',
  `pinyin_code` varchar(128) DEFAULT NULL COMMENT '拼音码，即名称首字母组合',
  `contact_name` varchar(64) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `contact_email` varchar(64) DEFAULT NULL COMMENT '联系邮箱',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态。1：启用，0：禁用',
  `delivery_rebate` double DEFAULT NULL COMMENT '配送费返点',
  `regular_rebate` double DEFAULT NULL COMMENT '固定返利点',
  `supplier_address` varchar(256) DEFAULT NULL COMMENT '地址',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_SUPPLIER_NAME` (`supplier_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `supplier_info` */

insert  into `supplier_info`(`id`,`supplier_code`,`supplier_name`,`pinyin_code`,`contact_name`,`contact_phone`,`contact_email`,`status`,`delivery_rebate`,`regular_rebate`,`supplier_address`,`remark`,`gmt_update`,`gmt_create`) values (1,'1001','优衣库','yyk',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'2018-05-10 14:54:31','0000-00-00 00:00:00'),(2,'1002','JackJonse',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,'2018-04-24 21:28:07','0000-00-00 00:00:00'),(3,'1003','Adidas','adidas',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'2018-05-10 14:54:41','2018-05-10 14:53:48'),(4,'1004','Nike','nike',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'2018-05-10 15:19:49','2018-05-10 15:18:57');

/*Table structure for table `system_parameter` */

DROP TABLE IF EXISTS `system_parameter`;

CREATE TABLE `system_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `param_code` varchar(64) NOT NULL COMMENT '参数代码',
  `param_value` varchar(128) DEFAULT NULL COMMENT '参数值',
  `description` varchar(128) DEFAULT NULL COMMENT '参数描述',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_PARAM_CODE` (`param_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `system_parameter` */

insert  into `system_parameter`(`id`,`param_code`,`param_value`,`description`,`gmt_update`,`gmt_create`) values (1,'SHOP_NAME','小熊维尼的糖果店','商店名称','2018-05-02 17:25:40','2018-04-23 21:44:25'),(3,'PETTY_AMOUNT','false','备用金开关，true为启用,false为不启用','2018-05-02 17:32:17','2018-05-02 17:31:34');

/*Table structure for table `trade_detail` */

DROP TABLE IF EXISTS `trade_detail`;

CREATE TABLE `trade_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `trade_no` varchar(64) NOT NULL COMMENT '流水号，唯一确定一笔交易，规则为年月日时分秒毫秒',
  `trade_time` timestamp NULL DEFAULT NULL COMMENT '交易完成时间',
  `trade_type` varchar(32) NOT NULL COMMENT '交易类型。SALES：销售。REFUND：退款',
  `member_name` varchar(64) DEFAULT NULL COMMENT '会员姓名',
  `goods_count` int(11) NOT NULL COMMENT '商品数量。退款为负',
  `total_amount` int(11) NOT NULL COMMENT '商品原价之和，单位：分。退款为负',
  `goods_discount` int(11) NOT NULL DEFAULT '100' COMMENT '商品折扣，如9.8折就填98.默认100即不打折',
  `total_actual_amount` int(11) NOT NULL COMMENT '商品实收之和，单位：分。退款为负',
  `profit_amount` int(11) NOT NULL COMMENT '利润，单位：分。退款为负',
  `seller_no` varchar(32) NOT NULL COMMENT '收银员编号',
  `shopper_no` varchar(64) DEFAULT NULL COMMENT '导购员编号',
  `goods_detail` varchar(2048) DEFAULT NULL COMMENT 'JSON格式，商品明细。包含商品名称、商品条码、颜色、尺寸、数量、原价、实收、利润、导购员字段',
  `pay_chenal` varchar(256) NOT NULL COMMENT 'JSON格式，支付方式及该方式对应的金额。金额单位：元。有可能是混合支付',
  `is_exchange_job` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否交接班。1：已交接。0：未交接',
  `exchange_job_id` bigint(20) DEFAULT NULL COMMENT '交接班序号，对应交接班表主键id',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INX_TRADE_NO` (`trade_no`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `trade_detail` */

insert  into `trade_detail`(`id`,`trade_no`,`trade_time`,`trade_type`,`member_name`,`goods_count`,`total_amount`,`goods_discount`,`total_actual_amount`,`profit_amount`,`seller_no`,`shopper_no`,`goods_detail`,`pay_chenal`,`is_exchange_job`,`exchange_job_id`,`gmt_update`,`gmt_create`) values (8,'20180510152910946','2018-05-10 15:29:11','sales',NULL,5,140000,80,112000,89600,'1001',NULL,'[{\"barCode\":\"20180510144945408-001\",\"goodsCount\":1,\"goodsDiscount\":80,\"goodsId\":78,\"goodsName\":\"adidas女士运动上衣\",\"isVipDiscount\":\"true\",\"priceTotal\":\"160\",\"totalActualAmount\":\"160.00\",\"totalAmount\":\"200\"},{\"barCode\":\"20180510151712752-001\",\"goodsCount\":1,\"goodsDiscount\":80,\"goodsId\":86,\"goodsName\":\"Nike空军一号运动鞋\",\"isVipDiscount\":\"true\",\"priceTotal\":\"400\",\"totalActualAmount\":\"400.00\",\"totalAmount\":\"500\"},{\"barCode\":\"20180510151712752-003\",\"goodsCount\":1,\"goodsDiscount\":80,\"goodsId\":88,\"goodsName\":\"Nike空军一号运动鞋\",\"isVipDiscount\":\"true\",\"priceTotal\":\"400\",\"totalActualAmount\":\"400.00\",\"totalAmount\":\"500\"},{\"barCode\":\"20180510144017883-002\",\"goodsCount\":1,\"goodsDiscount\":80,\"goodsId\":75,\"goodsName\":\"优衣库男士衬衫\",\"isVipDiscount\":\"true\",\"priceTotal\":\"80\",\"totalActualAmount\":\"80.00\",\"totalAmount\":\"100\"},{\"barCode\":\"20180510144017883-003\",\"goodsCount\":1,\"goodsDiscount\":80,\"goodsId\":76,\"goodsName\":\"优衣库男士衬衫\",\"isVipDiscount\":\"true\",\"priceTotal\":\"80\",\"totalActualAmount\":\"80.00\",\"totalAmount\":\"100\"}]','[{\"amount\":\"1200\",\"chenal\":\"cash\"}]',0,1,'2018-05-10 15:30:01','2018-05-10 15:29:11'),(9,'20180510155252637','2018-05-10 15:52:53','sales',NULL,1,20000,70,14000,9800,'1001',NULL,'[{\"barCode\":\"20180510144945408-001\",\"goodsCount\":1,\"goodsDiscount\":70,\"goodsId\":78,\"goodsName\":\"adidas女士运动上衣\",\"isVipDiscount\":\"true\",\"priceTotal\":\"140\",\"totalActualAmount\":\"140.00\",\"totalAmount\":\"200\"}]','[{\"amount\":\"140\",\"chenal\":\"cash\"}]',0,1,'2018-05-10 15:53:43','2018-05-10 15:52:53'),(10,'20180510183310636','2018-05-10 18:33:11','sales',NULL,1,20000,100,20000,20000,'1001',NULL,'[{\"barCode\":\"20180510144945408-002\",\"goodsCount\":1,\"goodsDiscount\":100,\"goodsId\":79,\"goodsName\":\"adidas女士运动上衣\",\"isVipDiscount\":\"true\",\"priceTotal\":\"200\",\"totalActualAmount\":\"200\",\"totalAmount\":\"200\"}]','[{\"amount\":\"300\",\"chenal\":\"cash\"}]',0,1,'2018-05-10 18:34:01','2018-05-10 18:33:11'),(11,'20180510192322429','2018-05-10 19:23:22','sales',NULL,1,56700,100,56700,56700,'1001',NULL,'[{\"goodsCount\":1,\"goodsDiscount\":100,\"goodsId\":-1,\"goodsName\":\"无码商品\",\"isVipDiscount\":\"true\",\"priceTotal\":\"567\",\"totalActualAmount\":\"567\",\"totalAmount\":\"567\"}]','[{\"amount\":\"567\",\"chenal\":\"cash\"}]',0,1,'2018-05-10 19:24:10','2018-05-10 19:23:22');

/*Table structure for table `trade_goods_detail` */

DROP TABLE IF EXISTS `trade_goods_detail`;

CREATE TABLE `trade_goods_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `trade_no` varchar(64) NOT NULL COMMENT '流水号，关联trade_detail表',
  `trade_time` timestamp NULL DEFAULT NULL COMMENT '交易完成时间',
  `trade_type` varchar(32) NOT NULL COMMENT '交易类型。sales：销售。refund：退款',
  `goods_name` varchar(256) DEFAULT NULL COMMENT '商品名称',
  `goods_brand` varchar(128) DEFAULT NULL COMMENT '商品品牌',
  `bar_code` varchar(128) DEFAULT NULL COMMENT '商品条码，唯一标识一件商品',
  `product_number` varchar(128) DEFAULT NULL COMMENT '商品货号',
  `goods_color` varchar(64) DEFAULT NULL COMMENT '商品颜色',
  `goods_size` varchar(64) DEFAULT NULL COMMENT '商品尺码',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量，退款为负',
  `goods_tag` varchar(128) DEFAULT NULL COMMENT '商品标签，半角逗号分隔开',
  `category_name` varchar(128) DEFAULT NULL COMMENT '商品分类名称',
  `seller_no` varchar(32) DEFAULT NULL COMMENT '收银员编号',
  `shopper_no` varchar(64) DEFAULT NULL COMMENT '导购员编号',
  `supplier_name` varchar(128) DEFAULT NULL COMMENT '供货商名称',
  `total_amount` int(11) NOT NULL COMMENT '商品原价，单位：分。退款为负',
  `goods_discount` int(100) NOT NULL COMMENT '商品折扣，如9.8折就填98.默认100即不打折',
  `total_actual_amount` int(11) NOT NULL COMMENT '商品实收，单位：分。退款为负',
  `profit_amount` int(11) NOT NULL COMMENT '利润，单位：分。退款为负',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_TRADE_NO` (`trade_no`),
  KEY `IDX_BAR_CODE` (`bar_code`),
  KEY `IDX_GOODS_TAG` (`goods_tag`),
  KEY `IDX_SELLER_NO` (`seller_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `trade_goods_detail` */

insert  into `trade_goods_detail`(`id`,`trade_no`,`trade_time`,`trade_type`,`goods_name`,`goods_brand`,`bar_code`,`product_number`,`goods_color`,`goods_size`,`goods_count`,`goods_tag`,`category_name`,`seller_no`,`shopper_no`,`supplier_name`,`total_amount`,`goods_discount`,`total_actual_amount`,`profit_amount`,`gmt_update`,`gmt_create`) values (9,'20180510152910946','2018-05-10 15:29:11','sales','adidas女士运动上衣','Adidas','20180510144945408-001','20180510144945408','红色','XL',1,'','女士运动上衣','1001',NULL,'Adidas',20000,80,16000,12800,'2018-05-10 15:30:01','2018-05-10 15:29:11'),(10,'20180510152910946','2018-05-10 15:29:11','sales','Nike空军一号运动鞋','Nike','20180510151712752-001','20180510151712752','红色','40',1,'','男运动鞋','1001',NULL,'Nike',50000,80,40000,32000,'2018-05-10 15:30:01','2018-05-10 15:29:11'),(11,'20180510152910946','2018-05-10 15:29:11','sales','Nike空军一号运动鞋','Nike','20180510151712752-003','20180510151712752','红色','41',1,'','男运动鞋','1001',NULL,'Nike',50000,80,40000,32000,'2018-05-10 15:30:01','2018-05-10 15:29:11'),(12,'20180510152910946','2018-05-10 15:29:11','sales','优衣库男士衬衫','优衣库','20180510144017883-002','20180510144017883','红色','M',1,'新衣服','男士衬衫','1001',NULL,'优衣库',10000,80,8000,6400,'2018-05-10 15:30:01','2018-05-10 15:29:11'),(13,'20180510152910946','2018-05-10 15:29:11','sales','优衣库男士衬衫','优衣库','20180510144017883-003','20180510144017883','黄色','S',1,'新衣服','男士衬衫','1001',NULL,'优衣库',10000,80,8000,6400,'2018-05-10 15:30:01','2018-05-10 15:29:11'),(14,'20180510155252637','2018-05-10 15:52:53','sales','adidas女士运动上衣','Adidas','20180510144945408-001','20180510144945408','红色','XL',1,'','女士运动上衣','1001',NULL,'Adidas',20000,70,14000,9800,'2018-05-10 15:53:43','2018-05-10 15:52:53'),(15,'20180510183310636','2018-05-10 18:33:11','sales','adidas女士运动上衣','Adidas','20180510144945408-002','20180510144945408','红色','L',1,'','女士运动上衣','1001',NULL,'Adidas',20000,100,20000,20000,'2018-05-10 18:34:01','2018-05-10 18:33:11'),(16,'20180510192322429','2018-05-10 19:23:22','sales','无码商品',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,'1001',NULL,NULL,56700,100,56700,56700,'2018-05-10 19:24:10','2018-05-10 19:23:22');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
