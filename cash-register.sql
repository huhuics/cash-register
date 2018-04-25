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

/*Table structure for table `goods_brand` */

DROP TABLE IF EXISTS `goods_brand`;

CREATE TABLE `goods_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `brand_name` varchar(128) NOT NULL COMMENT '品牌名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_brand` */

/*Table structure for table `goods_category` */

DROP TABLE IF EXISTS `goods_category`;

CREATE TABLE `goods_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `category_name` varchar(64) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父节点id',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `goods_category` */

insert  into `goods_category`(`id`,`category_name`,`parent_id`,`gmt_update`,`gmt_create`) values (1,'root',0,'2018-04-18 11:42:14','2018-04-18 11:42:14'),(2,'a',1,'2018-04-18 11:42:14','2018-04-18 11:42:14'),(3,'b',1,'2018-04-18 11:42:14','2018-04-18 11:42:14'),(4,'c',1,'2018-04-18 11:42:14','2018-04-18 11:42:14'),(6,'e',2,'2018-04-18 11:42:14','2018-04-18 11:42:14'),(7,'f',2,'2018-04-18 11:42:14','2018-04-18 11:42:14'),(8,'x',3,'2018-04-18 11:42:14','2018-04-18 11:42:14'),(9,'y',3,'2018-04-18 11:42:14','2018-04-18 11:42:14'),(10,'z',3,'2018-04-18 11:42:14','2018-04-18 11:42:14');

/*Table structure for table `goods_color` */

DROP TABLE IF EXISTS `goods_color`;

CREATE TABLE `goods_color` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `color` varchar(64) NOT NULL COMMENT '颜色',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `goods_color` */

insert  into `goods_color`(`id`,`color`,`gmt_update`,`gmt_create`) values (1,'红色','2018-04-19 10:23:23','2018-04-19 10:23:23');

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
  `pinyin_code` varchar(128) NOT NULL COMMENT '拼音码，即商品名称首字母组合',
  `category_name` varchar(128) NOT NULL COMMENT '商品分类名称',
  `goods_status` tinyint(1) DEFAULT NULL COMMENT '状态，1：启动，0：停用',
  `goods_brand` varchar(128) DEFAULT NULL COMMENT '商品品牌',
  `goods_color` varchar(64) NOT NULL COMMENT '商品颜色',
  `goods_size` varchar(64) NOT NULL COMMENT '商品尺码',
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
  `production_date` timestamp NULL DEFAULT NULL COMMENT '生产日期',
  `quality_guarantee_period` int(11) DEFAULT NULL COMMENT '保质期，单位：天',
  `is_integral` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否积分，1：是。0：否',
  `royalty_type` varchar(64) NOT NULL COMMENT 'JSON格式。提成方式。0：不提成。1：销售价*导购员提成百分比。2：利润*导购员提成百分比。3.固定金额。4.销售价*百分比。5.利润*百分比',
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
  KEY `IDX_BAR_CODE` (`bar_code`),
  KEY `IDX_PINYIN_CODE` (`pinyin_code`),
  KEY `IDX_GOODS_NAME` (`goods_name`),
  KEY `IDX_GOODS_TAG` (`goods_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_info` */

/*Table structure for table `goods_quantity_unit` */

DROP TABLE IF EXISTS `goods_quantity_unit`;

CREATE TABLE `goods_quantity_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `unit_name` varchar(32) NOT NULL COMMENT '单位名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_quantity_unit` */

/*Table structure for table `goods_size` */

DROP TABLE IF EXISTS `goods_size`;

CREATE TABLE `goods_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `size_name` varchar(64) NOT NULL COMMENT '尺寸名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_size` */

/*Table structure for table `goods_tag` */

DROP TABLE IF EXISTS `goods_tag`;

CREATE TABLE `goods_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `tag_name` varchar(32) NOT NULL COMMENT '标签名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_tag` */

/*Table structure for table `member_info` */

DROP TABLE IF EXISTS `member_info`;

CREATE TABLE `member_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用。1：启用。0：不启用',
  `member_no` varchar(64) NOT NULL COMMENT '会员编号',
  `member_name` varchar(64) NOT NULL COMMENT '会员姓名',
  `member_rank` varchar(64) NOT NULL COMMENT '会员等级',
  `member_discount` int(11) NOT NULL DEFAULT '100' COMMENT '会员折扣。默认100即不打折，8.5折填85',
  `member_integral` double DEFAULT NULL COMMENT '会员积分',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `password` varchar(32) DEFAULT NULL COMMENT '会员密码',
  `birthday` varchar(32) DEFAULT NULL COMMENT '会员生日',
  `is_on_credit` tinyint(1) DEFAULT '0' COMMENT '是否允许赊账。1：允许。0：不允许',
  `qq_no` varchar(32) DEFAULT NULL COMMENT 'qq号码',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱地址',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `shopper_name` varchar(64) DEFAULT NULL COMMENT '导购员',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_MEMBER_NO` (`member_no`),
  KEY `IDX_PHONE` (`phone`),
  KEY `IDX_MEMBER_NAME` (`member_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member_info` */

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
  `discount` int(11) NOT NULL COMMENT '优惠折扣，整数，8.5折填85',
  `is_integral` tinyint(1) DEFAULT '1' COMMENT '是否积分。1：积分。0：不计分',
  `is_auto_upgrade` tinyint(1) DEFAULT '0' COMMENT '是否自动升级。1：是。0：否',
  `integral_to_upgrade` int(11) DEFAULT NULL COMMENT '当积分到达该值时自动升级到该等级',
  `rank_period` tinyint(1) DEFAULT '0' COMMENT '该等级有效期。1：1年。0：永久',
  `prepaid_card_no` varchar(64) DEFAULT NULL COMMENT '预留。储值卡卡号',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员等级表';

/*Data for the table `member_rank` */

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

insert  into `seller_info`(`id`,`part_of_shop`,`seller_no`,`name`,`role`,`password`,`phone`,`status`,`cash_permission`,`background_permission`,`gmt_update`,`gmt_create`) values (1,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',0,'[]','[]','2018-04-24 15:07:49','2018-04-19 11:04:06'),(2,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',0,'[]','[]','2018-04-24 15:07:51','2018-04-19 11:04:49'),(3,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(4,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(5,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(6,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(7,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(8,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(9,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(10,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(11,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(12,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(13,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(14,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(15,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(16,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(17,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(18,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(19,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(20,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(21,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(22,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(23,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(24,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(25,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(26,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(27,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(28,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(29,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(30,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(31,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(32,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49'),(33,'小熊维尼的糖果店','1001','维尼','seller','123','88886666',1,'[]','[]','2018-04-23 20:28:19','2018-04-19 11:04:49');

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
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `UQ_SHOPPER_NO` (`shopper_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shopper_info` */

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `supplier_info` */

insert  into `supplier_info`(`id`,`supplier_code`,`supplier_name`,`pinyin_code`,`contact_name`,`contact_phone`,`contact_email`,`status`,`delivery_rebate`,`regular_rebate`,`supplier_address`,`remark`,`gmt_update`,`gmt_create`) values (1,'1001','优衣库',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'2018-04-24 21:27:51','0000-00-00 00:00:00'),(2,'1002','JackJonse',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,'2018-04-24 21:28:07','0000-00-00 00:00:00');

/*Table structure for table `system_parameter` */

DROP TABLE IF EXISTS `system_parameter`;

CREATE TABLE `system_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `param_code` varchar(64) NOT NULL COMMENT '参数代码',
  `param_value` varchar(64) NOT NULL COMMENT '参数值',
  `example_value` varchar(64) DEFAULT NULL COMMENT '示例值',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_PARAM_CODE` (`param_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `system_parameter` */

insert  into `system_parameter`(`id`,`param_code`,`param_value`,`example_value`,`gmt_update`,`gmt_create`) values (1,'SHOP_NAME','小熊维尼的糖果店','小熊维尼的糖果店','2018-04-23 21:54:31','2018-04-23 21:44:25');

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
  `pay_chenal` varchar(256) NOT NULL COMMENT 'JSON格式，支付方式及该方式对应的金额。金额单位：分有可能是混合支付',
  `is_exchange_job` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否交接班。1：已交接。0：未交接',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `gmt_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INX_TRADE_NO` (`trade_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `trade_detail` */

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
  KEY `IDX_GOODS_TAG` (`goods_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `trade_goods_detail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
