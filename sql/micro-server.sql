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

 Date: 07/01/2024 18:42:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth2_authorization
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization`;
CREATE TABLE `oauth2_authorization` (
  `id` varchar(100) NOT NULL,
  `registered_client_id` varchar(100) NOT NULL,
  `principal_name` varchar(200) NOT NULL,
  `authorization_grant_type` varchar(100) NOT NULL,
  `authorized_scopes` varchar(1000) DEFAULT NULL,
  `attributes` blob,
  `state` varchar(500) DEFAULT NULL,
  `authorization_code_value` blob,
  `authorization_code_issued_at` datetime DEFAULT NULL,
  `authorization_code_expires_at` datetime DEFAULT NULL,
  `authorization_code_metadata` blob,
  `access_token_value` blob,
  `access_token_issued_at` datetime DEFAULT NULL,
  `access_token_expires_at` datetime DEFAULT NULL,
  `access_token_metadata` blob,
  `access_token_type` varchar(100) DEFAULT NULL,
  `access_token_scopes` varchar(1000) DEFAULT NULL,
  `oidc_id_token_value` blob,
  `oidc_id_token_issued_at` datetime DEFAULT NULL,
  `oidc_id_token_expires_at` datetime DEFAULT NULL,
  `oidc_id_token_metadata` blob,
  `refresh_token_value` blob,
  `refresh_token_issued_at` datetime DEFAULT NULL,
  `refresh_token_expires_at` datetime DEFAULT NULL,
  `refresh_token_metadata` blob,
  `user_code_value` blob,
  `user_code_issued_at` datetime DEFAULT NULL,
  `user_code_expires_at` datetime DEFAULT NULL,
  `user_code_metadata` blob,
  `device_code_value` blob,
  `device_code_issued_at` datetime DEFAULT NULL,
  `device_code_expires_at` datetime DEFAULT NULL,
  `device_code_metadata` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth2_authorization
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth2_authorization_consent
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization_consent`;
CREATE TABLE `oauth2_authorization_consent` (
  `registered_client_id` varchar(100) NOT NULL,
  `principal_name` varchar(200) NOT NULL,
  `authorities` varchar(1000) NOT NULL,
  PRIMARY KEY (`registered_client_id`,`principal_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth2_authorization_consent
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_registered_client`;
CREATE TABLE `oauth2_registered_client` (
  `id` varchar(100) NOT NULL,
  `client_id` varchar(100) NOT NULL,
  `client_id_issued_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(200) DEFAULT NULL,
  `client_secret_expires_at` datetime DEFAULT NULL,
  `client_name` varchar(200) NOT NULL,
  `client_authentication_methods` varchar(1000) NOT NULL,
  `authorization_grant_types` varchar(1000) NOT NULL,
  `redirect_uris` varchar(1000) DEFAULT NULL,
  `post_logout_redirect_uris` varchar(1000) DEFAULT NULL,
  `scopes` varchar(1000) NOT NULL,
  `client_settings` varchar(2000) NOT NULL,
  `token_settings` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
BEGIN;
INSERT INTO `oauth2_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`, `client_secret_expires_at`, `client_name`, `client_authentication_methods`, `authorization_grant_types`, `redirect_uris`, `post_logout_redirect_uris`, `scopes`, `client_settings`, `token_settings`) VALUES ('c2790734-ca9a-4993-8767-3987de546377', 'device-message-client', '2023-12-06 16:47:54', NULL, NULL, 'c2790734-ca9a-4993-8767-3987de546377', 'none', 'refresh_token,urn:ietf:params:oauth:grant-type:device_code', '', '', 'message.read,message.write', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');
INSERT INTO `oauth2_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`, `client_secret_expires_at`, `client_name`, `client_authentication_methods`, `authorization_grant_types`, `redirect_uris`, `post_logout_redirect_uris`, `scopes`, `client_settings`, `token_settings`) VALUES ('cbf4e4a7-8e65-4a2d-85ec-f480a0fec45f', 'mall-id', '2023-12-06 16:47:42', '$2a$10$jqsN9P50TmaCd57lBzueH./VQxhqPfH4F0AL4Ts9ZfzRG5pJMADjC', NULL, '商城客户端', 'client_secret_basic', 'refresh_token,client_credentials,authorization_code', 'http://127.0.0.1:9999/home', '', 'openid,profile,message.read,message.write', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` (`client_id`, `client_secret`, `resource_ids`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('mall-id', 'mall-client-secret', 'account,business,storages,order', 'all', 'authorization_code,refresh_token', 'http://localhost:9999/test', 'ROLE_CLIENT', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '总额度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_account
-- ----------------------------
BEGIN;
INSERT INTO `t_account` (`id`, `user_id`, `amount`) VALUES (1, 1, 45800);
INSERT INTO `t_account` (`id`, `user_id`, `amount`) VALUES (2, NULL, 46800);
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
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

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
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (13, 'da40842dd38c4e9b94c795f1533748f0', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (14, '19216225fe314ff5a9339abac9a0c4c9', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (15, '069a8929bad648419456229d9ffd82ea', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (16, 'cac4784864c34d4cb8a4ab057109614f', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (17, 'b2c6be72be4b496285e9754ae88f928e', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (18, 'ad1fdaf4b2dd491a94455e1794703a91', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (19, '6ef1a0ca23494a3088608b1154b0adbd', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (20, 'c56939e5a9454b7a98ce41a4795400f8', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (21, 'a0fa1ece25bc4a49bafc614ff3bb9de3', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (22, '08e354d5b4d0499b8825aa1e895f68e1', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (23, '69ac887a2e5245aa96bb64dee3748253', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (24, '5accf3b57f4849e9a85d55c0fa3033f0', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (26, 'd2d153cca6ba49f8bbc99b9ff361da0d', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (29, 'edc85453ef074b8a9a8765f5948f0562', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (30, '9546a5843bdd418280dc8b9d56c36a9a', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (31, '0ad9eab190ab4fc5aef606e9a6573cf4', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (32, 'badca5fdae144237a628ff49420578cc', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (36, '96fb966df5ce4de2bc39cd2fbc79a6d1', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (37, 'd7429beff0d84bd3bf7207eaf46d044f', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (38, '2330bfb2eb4346838c08aa47c08ed0d0', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (39, '00ef5298ed124dbaa87003ef586c2e09', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (40, 'd341246db445410381237a4bfaea59d5', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (41, 'ceedae1f982948ff82708732b9cb857c', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (42, 'd2f05d700e4b49a58f955d0bc5719eb2', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (43, 'a82226468dfc48c69cd8ab24af283e91', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (44, '4103db1c19e24d66869029daa1081204', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (45, 'b487ad08d41e42d0a3fc1f1786368074', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (46, '97183d8155054ee3b1596e01cd95fba5', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (47, 'f5c7058ffa8c46bda0b472951cdbf227', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (48, 'c43826fe1e254ff091021112e046ed33', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (49, '741a797e977d4ecd8d9fd3960b7538d1', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (50, '513e36fcac3b482bb7bab405f9f64237', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (51, '6f84af0481d54ce7a927b4060ee29386', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (52, 'f7c45d860b0944fabbf78f8747acc55d', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (53, '9b979fa8bbe34bada28e151d51044000', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (54, 'a0239d2cc5854dacaa29428f8ad09daf', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (55, 'e16e930575d841ecb272e68b302cf0b5', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (56, 'df82a9b7153a4be09fe9d34e365f0f5f', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (58, '1b89bc9633584f0f90b7cc5de8027766', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (59, '79fe930a24054d419165d5d02de3256c', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (60, 'faba1c469c4743bbbed7295d2b3af3be', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (117, 'a8bef0477d7c423ea40a0c5a412af0cd', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (118, 'd4288d2ae54f4e54b2474941c5f69a9b', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (119, '3f132c49dc1f4aa8ad3b870bd8638072', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (120, '49f66ca8d9cc4e79802c1457b912950d', 1, 'T00001', 2, 200);
INSERT INTO `t_order` (`id`, `order_no`, `user_id`, `commodity_code`, `count`, `amount`) VALUES (126, '217b6927a984456284695b226a321afa', 1, 'T00001', 2, 200);
COMMIT;

-- ----------------------------
-- Table structure for t_storage
-- ----------------------------
DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage` (
  `id` int NOT NULL,
  `commodity_code` varchar(255) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `count` int DEFAULT NULL COMMENT '商品库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_storage
-- ----------------------------
BEGIN;
INSERT INTO `t_storage` (`id`, `commodity_code`, `name`, `count`) VALUES (1, 'T00001', '测试商品', 57);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `nickname`, `username`, `password`) VALUES (1, 'hongzhi.xu', 'hongzhi.xu', '$2a$10$x.EMhX78IrunRZXTF0rnH.N65W0okfOiDKMCQEnW5ya4e.nOyNoXa');
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
