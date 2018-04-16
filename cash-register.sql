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

/*Table structure for table `good_quantity_unit` */

DROP TABLE IF EXISTS `good_quantity_unit`;

CREATE TABLE `good_quantity_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `unit_name` varchar(32) NOT NULL COMMENT '单位名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `good_quantity_unit` */

/*Table structure for table `goods_brand` */

DROP TABLE IF EXISTS `goods_brand`;

CREATE TABLE `goods_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `brand_name` varchar(128) NOT NULL COMMENT '品牌名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_brand` */

/*Table structure for table `goods_category` */

DROP TABLE IF EXISTS `goods_category`;

CREATE TABLE `goods_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `category_name` varchar(64) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父节点id',
  `key` varchar(32) NOT NULL COMMENT '线索名',
  `level` int(11) NOT NULL COMMENT '深度，表示当前节点到根节点的距离',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_CATEGORY_KEY` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_category` */

/*Table structure for table `goods_color` */

DROP TABLE IF EXISTS `goods_color`;

CREATE TABLE `goods_color` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `color` varchar(64) NOT NULL COMMENT '颜色',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_color` */

/*Table structure for table `goods_detail` */

DROP TABLE IF EXISTS `goods_detail`;

CREATE TABLE `goods_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `goods_image_id` bigint(20) DEFAULT NULL COMMENT '商品图片，外键id',
  `goods_name` varchar(256) NOT NULL COMMENT '商品名称',
  `bar_code` varchar(128) NOT NULL COMMENT '商品条码=商品货号+子条码，唯一标识一件商品',
  `product_number` varchar(128) NOT NULL COMMENT '商品货号,标识一类商品',
  `pinyin_code` varchar(128) NOT NULL COMMENT '拼音码，即商品名称首字母组合',
  `category_name` varchar(128) NOT NULL COMMENT '商品分类名称',
  `goods_status` tinyint(1) DEFAULT NULL,
  `goods_brand` varchar(128) DEFAULT NULL COMMENT '商品品牌',
  `goods_color` varchar(64) NOT NULL COMMENT '商品颜色',
  `goods_size` varchar(64) NOT NULL COMMENT '商品尺码',
  `goods_stock` int(20) NOT NULL COMMENT '商品库存',
  `quantity_unit` varchar(32) DEFAULT NULL COMMENT '库存单位。个，件，杯，瓶',
  `stock_upper_limit` int(11) DEFAULT NULL COMMENT '库存上限',
  `stock_lower_limit` int(11) DEFAULT NULL COMMENT '库存下限',
  `import_price` int(11) NOT NULL COMMENT '进货价，单位：分',
  `average_import_price` int(11) NOT NULL COMMENT '加权平均进货价，单位：分',
  `sales_price` int(11) NOT NULL COMMENT '销售价，单位：分',
  `trade_price` int(11) DEFAULT NULL COMMENT '批发价，单位：分',
  `vip_price` int(11) DEFAULT NULL COMMENT '会员价，单位：分',
  `is_vip_discount` tinyint(1) NOT NULL DEFAULT '1' COMMENT '会员是否有折扣。1：是。0：否',
  `supplier_name` varchar(128) DEFAULT NULL COMMENT '供货商名称',
  `production_date` timestamp NULL DEFAULT NULL COMMENT '生产日期',
  `quality_guarantee_period` int(11) DEFAULT NULL COMMENT '保质期，单位：天',
  `is_integral` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否积分，1：是。0：否',
  `royalty_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '提成方式。0：不提成。1：销售价*导购员提成百分比。2：利润*导购员提成百分比。3.固定金额。4.销售价*百分比。5.利润*百分比',
  `is_booked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '能否预约，1：能。0：否',
  `is_gift` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有赠品，1：是。0：否',
  `is_weigh` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否称重，1：是。0：否',
  `is_fixed_price` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否固价，1：是。0：否。固价表示不参与任何折扣',
  `is_timeing_price` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否时价，1：是。0：否。时价表示根据当天情况定价',
  `is_hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏，1：是。0：否',
  `remark` varchar(1024) DEFAULT NULL COMMENT '商品备注',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_BAR_CODE` (`bar_code`),
  KEY `IDX_PINYIN_CODE` (`pinyin_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_detail` */

/*Table structure for table `goods_image` */

DROP TABLE IF EXISTS `goods_image`;

CREATE TABLE `goods_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `goods_image` blob NOT NULL COMMENT '图片的二进制数据，大小限制为1MB',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_image` */

/*Table structure for table `goods_size` */

DROP TABLE IF EXISTS `goods_size`;

CREATE TABLE `goods_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `size_name` varchar(64) NOT NULL COMMENT '尺寸名称',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods_size` */

/*Table structure for table `supplier_detail` */

DROP TABLE IF EXISTS `supplier_detail`;

CREATE TABLE `supplier_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `supplier_code` varchar(64) NOT NULL COMMENT '供应商编号',
  `supplier_name` varchar(128) NOT NULL COMMENT '供应商名称',
  `pinyin_code` varchar(128) DEFAULT NULL COMMENT '拼音码，即名称首字母组合',
  `contact_name` varchar(64) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `contact_email` varchar(64) DEFAULT NULL COMMENT '联系邮箱',
  `delivery_rebate` double DEFAULT NULL COMMENT '配送费返点',
  `regular_rebate` double DEFAULT NULL COMMENT '固定返利点',
  `supplier_address` varchar(256) DEFAULT NULL COMMENT '地址',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `gmt_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `supplier_detail` */

/*Table structure for table `trade_detail` */

DROP TABLE IF EXISTS `trade_detail`;

CREATE TABLE `trade_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `trade_no` varchar(64) NOT NULL COMMENT '流水号，唯一确定一笔交易，规则为年月日时分秒毫秒',
  `trade_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易完成时间',
  `trade_type` varchar(32) NOT NULL COMMENT '交易类型。SALES：销售。REFUND：退款',
  `member_name` varchar(64) DEFAULT NULL COMMENT '会员姓名',
  `goods_count` int(11) NOT NULL COMMENT '商品数量。退款为负',
  `total_amount` int(11) NOT NULL COMMENT '商品原价之和，单位：分。退款为负',
  `goods_discount` int(11) NOT NULL DEFAULT '100' COMMENT '商品折扣，如9.8折就填98.默认100即不打折',
  `total_actual_amount` int(11) NOT NULL COMMENT '商品实收之和，单位：分。退款为负',
  `profit_amount` int(11) NOT NULL COMMENT '利润，单位：分。退款为负',
  `seller_no` varchar(32) NOT NULL COMMENT '收银员编号',
  `shopper_no` varchar(64) DEFAULT NULL COMMENT '导购员编号',
  `goods_detail` varchar(2048) NOT NULL COMMENT 'JSON格式，商品明细。包含商品名称、商品条码、颜色、尺寸、数量、原价、实收、利润、导购员字段',
  `pay_chenal` varchar(256) NOT NULL COMMENT 'JSON格式，支付方式及该方式对应的金额。有可能是混合支付',
  `is_exchange_job` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否交接班。1：已交接。0：未交接',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `INX_TRADE_NO` (`trade_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `trade_detail` */

/*Table structure for table `trade_goods_detail` */

DROP TABLE IF EXISTS `trade_goods_detail`;

CREATE TABLE `trade_goods_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `trade_no` varchar(64) NOT NULL COMMENT '流水号，关联trade_detail表',
  `trade_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易完成时间',
  `trade_type` varchar(32) NOT NULL COMMENT '交易类型。SALES：销售。REFUND：退款',
  `goods_name` varchar(256) DEFAULT NULL COMMENT '商品名称',
  `bar_code` varchar(128) DEFAULT NULL COMMENT '商品条码',
  `product_number` varchar(128) DEFAULT NULL COMMENT '商品货号',
  `goods_color` varchar(64) DEFAULT NULL COMMENT '商品颜色',
  `goods_size` varchar(64) DEFAULT NULL COMMENT '商品尺码',
  `goods_count` int(11) NOT NULL COMMENT '商品数量，退款为负',
  `total_amount` int(11) NOT NULL COMMENT '商品原价之和，单位：分。退款为负',
  `goods_discount` int(100) NOT NULL COMMENT '商品折扣，如9.8折就填98.默认100即不打折',
  `total_actual_amount` int(11) NOT NULL COMMENT '商品实收之和，单位：分。退款为负',
  `profit_amount` int(11) NOT NULL COMMENT '利润，单位：分。退款为负',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_TRADE_NO` (`trade_no`),
  KEY `IDX_BAR_CODE` (`bar_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `trade_goods_detail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
