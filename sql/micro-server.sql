/*
 Navicat Premium Data Transfer

 Source Server         : 本机-mysql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : micro-server

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 23/02/2023 10:42:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `total` decimal(10,0) DEFAULT NULL COMMENT '总额度',
  `used` decimal(10,0) DEFAULT NULL COMMENT '已用余额',
  `residue` decimal(10,0) DEFAULT '0' COMMENT '剩余可用额度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_account
-- ----------------------------
BEGIN;
INSERT INTO `t_account` (`id`, `user_id`, `total`, `used`, `residue`) VALUES (1, 1, 5000, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `commodity_code` varchar(255) DEFAULT NULL COMMENT '产品代码',
  `count` int DEFAULT NULL COMMENT '数量',
  `amount` decimal(11,0) DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (7, 'd91dd3b9862c4a4c95322741cda4d9e0', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (8, 'e8107a8ed73c470e995e44aa22be9b12', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (9, 'b04bed625d9f403693668cf8ddad3fa3', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (10, '4442a9eb7dc54ca68667e4634f0331d8', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (11, '7f21db67d7ac4522a9db750b1549aba5', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (12, '04e9d72347604ce08c8283067ac84f9e', 1, 'T00001', 2, 200);
COMMIT;

-- ----------------------------
-- Table structure for t_storage
-- ----------------------------
DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage` (
  `id` int DEFAULT NULL,
  `commodity_code` varchar(255) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `count` int DEFAULT NULL COMMENT '商品库存'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_storage
-- ----------------------------
BEGIN;
INSERT INTO `t_storage` (`id`, `commodity_code`, `name`, `count`) VALUES (1, 'T00001', '测试商品', 18);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
