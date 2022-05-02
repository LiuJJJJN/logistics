/*
 Navicat Premium Data Transfer

 Source Server         : 47.111.84.87
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 47.111.84.87:3306
 Source Schema         : djtu

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 30/04/2022 20:26:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_admin
-- ----------------------------
DROP TABLE IF EXISTS `tbl_admin`;
CREATE TABLE `tbl_admin`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salt` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_admin
-- ----------------------------
INSERT INTO `tbl_admin` VALUES ('53a713f50a58733ff6dad26988a68601', 'admin123', '53a713f50a58733ff6dad26988a68609', '<|IS', '纪老师');

-- ----------------------------
-- Table structure for tbl_class_use
-- ----------------------------
DROP TABLE IF EXISTS `tbl_class_use`;
CREATE TABLE `tbl_class_use`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stu_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `classroom_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_class_use
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_classroom
-- ----------------------------
DROP TABLE IF EXISTS `tbl_classroom`;
CREATE TABLE `tbl_classroom`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `room_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `build_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `elect_inf` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_use` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_open` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_clean` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_classroom
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_dic_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dic_type`;
CREATE TABLE `tbl_dic_type`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_dic_type
-- ----------------------------
INSERT INTO `tbl_dic_type` VALUES ('4e1eb3af3845474ab101e5fbbe94ad63', 'college', '学院', '学院');

-- ----------------------------
-- Table structure for tbl_dic_value
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dic_value`;
CREATE TABLE `tbl_dic_value`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_no` int NULL DEFAULT NULL,
  `type_code` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_dic_value
-- ----------------------------
INSERT INTO `tbl_dic_value` VALUES ('4e1eb3af3845474ab101e5fbbe94ad65', '信息学院', 1, 'college');
INSERT INTO `tbl_dic_value` VALUES ('a685df091cee4e8e95d29a65c4954265', '经济管理学院', 2, 'college');
INSERT INTO `tbl_dic_value` VALUES ('c4eb623b082d4dd19e750a167030e0fa', '软件学院', 3, 'college');
INSERT INTO `tbl_dic_value` VALUES ('db0bb5f763354d089dfed7e59052c143', '理学院', 4, 'college');

-- ----------------------------
-- Table structure for tbl_dorm
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dorm`;
CREATE TABLE `tbl_dorm`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `door_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `water` int NULL DEFAULT NULL,
  `power` int NULL DEFAULT NULL,
  `size` int NULL DEFAULT NULL,
  `have_wc` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_dorm
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_lib_use
-- ----------------------------
DROP TABLE IF EXISTS `tbl_lib_use`;
CREATE TABLE `tbl_lib_use`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stu_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `seat_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_lib_use
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_library
-- ----------------------------
DROP TABLE IF EXISTS `tbl_library`;
CREATE TABLE `tbl_library`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `open` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `close` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_clean` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_open` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_library
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_libseat
-- ----------------------------
DROP TABLE IF EXISTS `tbl_libseat`;
CREATE TABLE `tbl_libseat`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `floor` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `seat_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_libseat
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_permission
-- ----------------------------
DROP TABLE IF EXISTS `tbl_permission`;
CREATE TABLE `tbl_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `father_id` int NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_menu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_permission
-- ----------------------------
INSERT INTO `tbl_permission` VALUES (1, 'dorm', '寝室相关', 0, '/user', '1', '1');
INSERT INTO `tbl_permission` VALUES (2, 'dorm:select', '我的寝室', 1, '/user/Dorm', '1', '1');
INSERT INTO `tbl_permission` VALUES (3, 'dorm:update:power', '寝室缴纳电费', 1, '/user/powerDorm', '1', '1');
INSERT INTO `tbl_permission` VALUES (4, 'dorm:update:water', '寝室缴纳水费', 1, '/user/waterDorm', '1', '1');
INSERT INTO `tbl_permission` VALUES (5, 'dorm:change', '我要换寝', 1, '/user/changeDorm', '1', '1');
INSERT INTO `tbl_permission` VALUES (6, 'classroom', '教室相关', 0, '/user', '1', '1');
INSERT INTO `tbl_permission` VALUES (7, 'classroom:insert', '教室占座', 6, '/user/grabClassroom', '1', '1');
INSERT INTO `tbl_permission` VALUES (8, 'library', '图书馆相关', 0, '/user', '1', '1');
INSERT INTO `tbl_permission` VALUES (9, 'library:insert', '图书馆占座', 8, '/user/grabLibrary', '1', '1');
INSERT INTO `tbl_permission` VALUES (10, 'feedback', '反馈相关', 0, '/user', '1', '1');
INSERT INTO `tbl_permission` VALUES (11, 'feedback:insert', '我要反馈', 10, '/user/feedback', '1', '1');
INSERT INTO `tbl_permission` VALUES (12, 'feedback:select', '我的反馈', 10, '/user/myFeedback', '1', '1');
INSERT INTO `tbl_permission` VALUES (13, 'class', '班级相关', 0, '/tutor', '1', '2');
INSERT INTO `tbl_permission` VALUES (14, 'class:*', '管理班级', 13, '/tutor/class', '1', '2');
INSERT INTO `tbl_permission` VALUES (15, 'student', '学生相关', 0, '/tutor', '1', '2');
INSERT INTO `tbl_permission` VALUES (16, 'student:*', '管理学生', 15, '/tutor/studentManage', '1', '2');
INSERT INTO `tbl_permission` VALUES (17, 'feedback', '反馈相关', 0, '/tutor', '1', '2');
INSERT INTO `tbl_permission` VALUES (18, 'feedback:update', '学生反馈', 17, '/tutor/feedback', '1', '2');
INSERT INTO `tbl_permission` VALUES (19, 'grab', '占座相关', 0, '/tutor', '1', '2');
INSERT INTO `tbl_permission` VALUES (20, 'grab:*', '管理占座', 19, '/tutor/grab', '1', '2');
INSERT INTO `tbl_permission` VALUES (21, 'user', '用户相关', 0, '/admin', '1', '3');
INSERT INTO `tbl_permission` VALUES (22, 'user:*:stu', '管理学生', 21, '/admin/studentManage', '1', '3');
INSERT INTO `tbl_permission` VALUES (23, 'classroom', '教室相关', 0, '/admin', '1', '3');
INSERT INTO `tbl_permission` VALUES (24, 'classroom:*', '管理教室', 23, '/admin/classroom', '1', '3');
INSERT INTO `tbl_permission` VALUES (25, 'library', '图书馆相关', 0, '/admin', '1', '3');
INSERT INTO `tbl_permission` VALUES (26, 'library:*', '管理图书馆', 25, '/admin/library', '1', '3');
INSERT INTO `tbl_permission` VALUES (27, 'role', '权限相关', 0, '/admin', '1', '3');
INSERT INTO `tbl_permission` VALUES (28, 'role:update:stu', '学生权限管理', 27, '/admin/studentRole', '1', '3');
INSERT INTO `tbl_permission` VALUES (29, 'dict', '数据字典相关', 0, '/admin', '1', '3');
INSERT INTO `tbl_permission` VALUES (30, 'dicType:*', '管理数据字典Type', 29, '/admin/dicType', '1', '3');
INSERT INTO `tbl_permission` VALUES (31, 'dicValue:*', '管理数据字典Value', 29, '/admin/dicValue', '1', '3');
INSERT INTO `tbl_permission` VALUES (32, 'role:update:tutor', '导员权限管理', 27, '/admin/tutorRole', '1', '3');
INSERT INTO `tbl_permission` VALUES (33, 'role', '权限相关', 0, '/tutor', '1', '2');
INSERT INTO `tbl_permission` VALUES (34, 'role:update:stu', '学生权限管理', 33, '/admin/studentRole', '1', '2');
INSERT INTO `tbl_permission` VALUES (35, 'user:*:tutor', '管理导员', 21, '/admin/tutorManage', '1', '3');

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES ('1', '学生');
INSERT INTO `tbl_role` VALUES ('2', '导员');
INSERT INTO `tbl_role` VALUES ('3', '管理员');

-- ----------------------------
-- Table structure for tbl_stu_dorm
-- ----------------------------
DROP TABLE IF EXISTS `tbl_stu_dorm`;
CREATE TABLE `tbl_stu_dorm`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stu_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dorm_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_stu_dorm
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_student
-- ----------------------------
DROP TABLE IF EXISTS `tbl_student`;
CREATE TABLE `tbl_student`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salt` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enter_date` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `school_sys` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `college` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stu_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sno` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dorm_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tutor_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_student
-- ----------------------------
INSERT INTO `tbl_student` VALUES ('196e74d1927248f2959ff94b2bfe9460', 'fuzhaoyang', '6f4bfd8c210fd5ef5ce49b846382b18f', '=}hW', 'fuzhaoyang', '男', '2022-04-29', '4', '信息学院', '191', '夫照样哈哈哈123', '1921030110', NULL, NULL, '/196e74d1927248f2959ff94b2bfe9460/34165.jpg');

-- ----------------------------
-- Table structure for tbl_tutor
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tutor`;
CREATE TABLE `tbl_tutor`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salt` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `college` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dorm_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_tutor
-- ----------------------------
INSERT INTO `tbl_tutor` VALUES ('3d67ef0b2187417cbab9f2e985220f5d', 'yurentao', 'e26ed642b5047a5dfcdbc4dbf2182372', 'GfNO', '与人套', '男', '信息学院', NULL, '于仁涛', '/3d67ef0b2187417cbab9f2e985220f5d/海啸头像灰色001.jpg');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `student_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tutor_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `admin_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('310055ccf744440e93db35295a73a2f2', '196e74d1927248f2959ff94b2bfe9460', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('53a713f50a58733ff6dad26988a68602', NULL, NULL, '53a713f50a58733ff6dad26988a68601');
INSERT INTO `tbl_user` VALUES ('e200813253e74a72a46885f1c4bb5d59', NULL, '3d67ef0b2187417cbab9f2e985220f5d', NULL);

-- ----------------------------
-- Table structure for tbl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_role`;
CREATE TABLE `tbl_user_role`  (
  `role_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user_role
-- ----------------------------
INSERT INTO `tbl_user_role` VALUES ('1', '310055ccf744440e93db35295a73a2f2', '207547800d8e465c95664906a6087b13');
INSERT INTO `tbl_user_role` VALUES ('3', '53a713f50a58733ff6dad26988a68602', '53a713f50a58733ff6dad26988a68603');
INSERT INTO `tbl_user_role` VALUES ('2', 'e200813253e74a72a46885f1c4bb5d59', '5be7065c2d5a4980a91e2e9f70f2f064');

SET FOREIGN_KEY_CHECKS = 1;
