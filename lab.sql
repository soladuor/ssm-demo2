/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : ssm_demo2

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 23/05/2024 16:20:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ssm_lab1_file_comment
-- ----------------------------
DROP TABLE IF EXISTS `ssm_lab1_file_comment`;
CREATE TABLE `ssm_lab1_file_comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `file_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件id',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ssm_lab1_file_message
-- ----------------------------
DROP TABLE IF EXISTS `ssm_lab1_file_message`;
CREATE TABLE `ssm_lab1_file_message`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件编号',
  `space_id` int NOT NULL COMMENT '空间 外键',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实文件名',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存放文件名（路径）',
  `uploader_id` int NOT NULL COMMENT '上传人 外键',
  `update_time` datetime NOT NULL COMMENT '上传时间',
  `file_size` double NOT NULL COMMENT '文件大小（单位字节）',
  `download_count` int NOT NULL COMMENT '下载次数',
  `type_id` int NOT NULL COMMENT '文件类型 外键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ssm_lab1_file_space
-- ----------------------------
DROP TABLE IF EXISTS `ssm_lab1_file_space`;
CREATE TABLE `ssm_lab1_file_space`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '空间id',
  `user_id` int NOT NULL COMMENT '用户 外键',
  `space_size` double NOT NULL COMMENT '空间大小（单位字节）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '空间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ssm_lab1_file_type
-- ----------------------------
DROP TABLE IF EXISTS `ssm_lab1_file_type`;
CREATE TABLE `ssm_lab1_file_type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ssm_lab1_file_user
-- ----------------------------
DROP TABLE IF EXISTS `ssm_lab1_file_user`;
CREATE TABLE `ssm_lab1_file_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20211678 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ssm_lab1_log
-- ----------------------------
DROP TABLE IF EXISTS `ssm_lab1_log`;
CREATE TABLE `ssm_lab1_log`  (
  `log_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `datetime` datetime NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
