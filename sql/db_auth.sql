/*
 Navicat Premium Data Transfer

 Source Server         : 本机-mysql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : db_auth

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 26/10/2023 08:32:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userid` varchar(255) DEFAULT NULL,
  `clientid` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastmodifiedat` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) DEFAULT NULL,
  `client_name` varchar(50) DEFAULT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` (`client_id`, `client_name`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client1', 'system1', 'web-client', '$2a$10$B.tJA2CI.G1IB2tqLMgamOllhcO3CAcT0s8usTG65cBc4oPsdY9mK', 'all,qf.read,qf.write', 'authorization_code,password,refresh_token,client_credentials,implicit', 'http://localhost:9999/order', NULL, 1, 31536000, '{}', NULL);
INSERT INTO `oauth_client_details` (`client_id`, `client_name`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client2', 'system2', 'admin-client', '$2a$10$B.tJA2CI.G1IB2tqLMgamOllhcO3CAcT0s8usTG65cBc4oPsdY9mK', 'user.admin,client.admin', 'authorization_code,password,refresh_token,client_credentials', 'http://localhost:5500/code.html', NULL, 60, 31536000, '{}', '1');
INSERT INTO `oauth_client_details` (`client_id`, `client_name`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client3', 'system3', 'ios-client', '$2a$10$B.tJA2CI.G1IB2tqLMgamOllhcO3CAcT0s8usTG65cBc4oPsdY9mK', 'qf.read,qf.write', 'authorization_code,password,refresh_token,client_credentials', 'com.example.app://action', NULL, 900, 31536000, '{}', NULL);
INSERT INTO `oauth_client_details` (`client_id`, `client_name`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client4', 'system4', 'android-client', '$2a$10$B.tJA2CI.G1IB2tqLMgamOllhcO3CAcT0s8usTG65cBc4oPsdY9mK', 'qf.read,qf.write', 'authorization_code,password,refresh_token,client_credentials', 'com.example.app://action', NULL, 900, 31536000, '{}', NULL);
INSERT INTO `oauth_client_details` (`client_id`, `client_name`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client5', 'system5', 'todos-service', '$2a$10$B.tJA2CI.G1IB2tqLMgamOllhcO3CAcT0s8usTG65cBc4oPsdY9mK', 'qf.read,qf.write', 'authorization_code,refresh_token,client_credentials', 'http://localhost:8082/authorized', NULL, 900, 31536000, '{}', '1');
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(50) NOT NULL,
  `display_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_mooc_permissions_permission_name` (`permission_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of permissions
-- ----------------------------
BEGIN;
INSERT INTO `permissions` (`id`, `permission_name`, `display_name`) VALUES (1, 'USER_READ', '查询用户信息');
INSERT INTO `permissions` (`id`, `permission_name`, `display_name`) VALUES (2, 'USER_CREATE', '新建用户');
INSERT INTO `permissions` (`id`, `permission_name`, `display_name`) VALUES (3, 'USER_UPDATE', '编辑用户信息');
INSERT INTO `permissions` (`id`, `permission_name`, `display_name`) VALUES (4, 'USER_ADMIN', '用户管理');
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `display_name` varchar(50) NOT NULL,
  `built_in` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_mooc_roles_role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` (`id`, `role_name`, `display_name`, `built_in`) VALUES (1, 'ROLE_USER', '客户端用户', b'0');
INSERT INTO `roles` (`id`, `role_name`, `display_name`, `built_in`) VALUES (2, 'ROLE_ADMIN', '超级管理员', b'0');
INSERT INTO `roles` (`id`, `role_name`, `display_name`, `built_in`) VALUES (3, 'ROLE_STAFF', '管理后台用户', b'0');
COMMIT;

-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `fk_roles_permissions_permission_id_mooc_permissions_id` (`permission_id`),
  CONSTRAINT `fk_roles_permissions_permission_id_mooc_permissions_id` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`),
  CONSTRAINT `fk_roles_permissions_role_id_mooc_roles_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of roles_permissions
-- ----------------------------
BEGIN;
INSERT INTO `roles_permissions` (`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `roles_permissions` (`role_id`, `permission_id`) VALUES (2, 1);
INSERT INTO `roles_permissions` (`role_id`, `permission_id`) VALUES (2, 2);
INSERT INTO `roles_permissions` (`role_id`, `permission_id`) VALUES (2, 3);
INSERT INTO `roles_permissions` (`role_id`, `permission_id`) VALUES (2, 4);
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
INSERT INTO `t_user` (`id`, `nickname`, `username`, `password`) VALUES (1, 'hongzhi.xu', 'hongzhi.xu', '$2a$10$B.tJA2CI.G1IB2tqLMgamOllhcO3CAcT0s8usTG65cBc4oPsdY9mK');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `email` varchar(254) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `mfa_key` varchar(255) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password_hash` varchar(80) NOT NULL,
  `username` varchar(50) NOT NULL,
  `using_mfa` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_mooc_users_username` (`username`),
  UNIQUE KEY `uk_mooc_users_mobile` (`mobile`),
  UNIQUE KEY `uk_mooc_users_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` (`id`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `email`, `enabled`, `mfa_key`, `mobile`, `name`, `password_hash`, `username`, `using_mfa`) VALUES (1, b'0', b'0', b'0', 'zhangsan@local.dev', b'0', '8Uy+OZUaZur9WwcP0z+YxNy+QdsWbtfqA70GQMxMfLeisTd8Na6C7DkjhJWLrGyEyBsnEmmkza6iorytQRh7OQ==', '13012341234', 'Zhang San', '$2a$10$x.EMhX78IrunRZXTF0rnH.N65W0okfOiDKMCQEnW5ya4e.nOyNoXa', 'user', b'0');
COMMIT;

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_users_roles_role_id_mooc_roles_id` (`role_id`),
  CONSTRAINT `fk_users_roles_role_id_mooc_roles_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `fk_users_roles_user_id_mooc_users_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
BEGIN;
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
