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

 Date: 26/04/2022 19:24:46
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
INSERT INTO `tbl_admin` VALUES ('53a713f50a58733ff6dad26988a68601', 'admin', '53a713f50a58733ff6dad26988a68609', '<|IS', '纪老师');

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
INSERT INTO `tbl_dic_type` VALUES ('1f8ddec42a824e2c8fb51333e6e6b13f', 'efe', 'ttttt', 'tttt');
INSERT INTO `tbl_dic_type` VALUES ('2e729555f47c4815b41b16512348fd2a', 'af', 'test5', NULL);
INSERT INTO `tbl_dic_type` VALUES ('395dd00faedf461dbfa3df74d1efd160', 'bf', 'test6', NULL);
INSERT INTO `tbl_dic_type` VALUES ('6438ec230bb1404e96053ead1fc9ea4f', 'dfd', 'test3', 'test3');
INSERT INTO `tbl_dic_type` VALUES ('70f02dacf6a44ee9a77846ed57d9fe95', 'abcde', 'test', 'test');
INSERT INTO `tbl_dic_type` VALUES ('8b69a7291c9d4fada6c13bdbbe7371ab', 'df', 'test8', NULL);
INSERT INTO `tbl_dic_type` VALUES ('8b84a40b13e746d49dd509e1388090d4', 'ff', 'test2', 'test2');
INSERT INTO `tbl_dic_type` VALUES ('b18750853a074460ba3626b171d7af32', 'college', '学院', '学院');
INSERT INTO `tbl_dic_type` VALUES ('ea2f2806ba00486cb4a6c6a64fb4c146', 'cccc', 'hello', '测试用例w');

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
INSERT INTO `tbl_dic_value` VALUES ('a685df091cee4e8e95d29a65c4954444', '未知学院', 4, 'abcde');
INSERT INTO `tbl_dic_value` VALUES ('c4eb623b082d4dd19e750a167030e0fa', '软件学院', 3, 'college');
INSERT INTO `tbl_dic_value` VALUES ('c4eb623b082d4dd19e750a167030e111', '未知哦', 5, 'cccc');
INSERT INTO `tbl_dic_value` VALUES ('c4eb623b082d4dd19e750a167030e222', 'test', 6, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `tbl_permission` VALUES (16, 'student:*', '管理学生', 15, '/tutor/student', '1', '2');
INSERT INTO `tbl_permission` VALUES (17, 'feedback', '反馈相关', 0, '/tutor', '1', '2');
INSERT INTO `tbl_permission` VALUES (18, 'feedback:update', '学生反馈', 17, '/tutor/feedback', '1', '2');
INSERT INTO `tbl_permission` VALUES (19, 'grab', '占座相关', 0, '/tutor', '1', '2');
INSERT INTO `tbl_permission` VALUES (20, 'grab:*', '管理占座', 19, '/tutor/grab', '1', '2');
INSERT INTO `tbl_permission` VALUES (21, 'user', '用户相关', 0, '/admin', '1', '3');
INSERT INTO `tbl_permission` VALUES (22, 'user:*', '管理用户', 21, '/admin/user', '1', '3');
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
INSERT INTO `tbl_student` VALUES ('125c834d81794bc48b48053c1e783d92', 'fuzhaoyangpy', '39b471f7814ebaecbbbcc4d85ab2f0e7', 'v$wi', '夫照样2', '男', '2022-04-11', '4', '信息学院', '0班', NULL, '250250', NULL, NULL, NULL);
INSERT INTO `tbl_student` VALUES ('45cfd05033ff4ad68d31b2e50ec11d41', 'wangzl', 'a47069428e8797e7a9c9ab94907c9e7f', '.!Ss', '王子两', '男', '2019-08-31', '4', '信息学院', '191', NULL, '1921030128', NULL, NULL, NULL);
INSERT INTO `tbl_student` VALUES ('5d452174b79840fa9477ec048446c0ab', 'shenbin', '3da57a13b6e833318efde1e740c786c1', 'J:_L', '沈斌', '男', '2022-04-24', '4', '信息学院', '191', NULL, '1921030129', NULL, NULL, NULL);
INSERT INTO `tbl_student` VALUES ('6a7b6a63fbb34802b2c8465e60ddc126', 'fuzhaoyang', 'e3e123db66923066062eaf168f2ce6ab', '\"lA-', '夫照样', '男', '2022-04-25', '4', '信息学院', '191', NULL, '1921030110', NULL, NULL, NULL);
INSERT INTO `tbl_student` VALUES ('db58c78833d3471f8818dfe3bb4d11e8', 'zhangrh', '1be1c54e28f4836aa8cbb7c8af6135ae', '`j(f', '张若涵', '男', '2019-03-31', '4', '信息学院', '191', NULL, '1921030101', NULL, NULL, NULL);

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
INSERT INTO `tbl_tutor` VALUES ('519a31cd13994e1b8b60a23e4d364345', 'yurentao', 'd87c6295538c16caaca10a55f525e70c', 'mD;S', '育人涛', '男', '信息学院', '', '', NULL);
INSERT INTO `tbl_tutor` VALUES ('be2e725f5dd04642b1293505bc1b4d82', 'yurentao666', 'cfefe519d8dfe8a8387bbc99c787111c', 'iE3b', '育人tao', '男', '信息学院', '', '', NULL);

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
INSERT INTO `tbl_user` VALUES ('1c1407621f8046339021f628aa351b56', '5d452174b79840fa9477ec048446c0ab', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('2b5f53c1484c48539635d8513a018006', NULL, '519a31cd13994e1b8b60a23e4d364345', NULL);
INSERT INTO `tbl_user` VALUES ('308a966bcfbd4cc9a697517269466804', '45cfd05033ff4ad68d31b2e50ec11d41', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('84b08ea9274145dabb8bc04d172733c7', 'db58c78833d3471f8818dfe3bb4d11e8', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('e1b195280bff4b558c49115528d6baf0', NULL, NULL, '53a713f50a58733ff6dad26988a68601');
INSERT INTO `tbl_user` VALUES ('ebadc813a0f24afea84a5aff1a94bce0', '125c834d81794bc48b48053c1e783d92', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('ee36e3f927484c809743003afcdaa8cd', NULL, 'be2e725f5dd04642b1293505bc1b4d82', NULL);
INSERT INTO `tbl_user` VALUES ('f11c7a289dc242b88d7f573b5496e57d', '6a7b6a63fbb34802b2c8465e60ddc126', NULL, NULL);

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
INSERT INTO `tbl_user_role` VALUES ('1', 'f11c7a289dc242b88d7f573b5496e57d', '1e8ac2c889dd4677bedaaa5346eb6ff1');
INSERT INTO `tbl_user_role` VALUES ('2', '2b5f53c1484c48539635d8513a018006', '3d0c948403b44eb393e377cf5ac7b4bb');
INSERT INTO `tbl_user_role` VALUES ('1', '308a966bcfbd4cc9a697517269466804', '3e6e86611c484394bc4f306f92bc3ad4');
INSERT INTO `tbl_user_role` VALUES ('1', 'f11c7a289dc242b88d7f573b5496e57d', '4e2ebaaf35044261b3b32e6037f84c11');
INSERT INTO `tbl_user_role` VALUES ('1', 'f11c7a289dc242b88d7f573b5496e57d', '5c392c37c55a49589465620c3b0d38e2');
INSERT INTO `tbl_user_role` VALUES ('2', 'ee36e3f927484c809743003afcdaa8cd', '64602ebd7ea543638faba5fd1d8bfd24');
INSERT INTO `tbl_user_role` VALUES ('1', 'ebadc813a0f24afea84a5aff1a94bce0', 'ac5c70d7cc92400d94397ddd8a3ba0d2');
INSERT INTO `tbl_user_role` VALUES ('1', '1c1407621f8046339021f628aa351b56', 'c2d01cbd25bc4ae28afd86a9fcfe1a98');
INSERT INTO `tbl_user_role` VALUES ('1', '84b08ea9274145dabb8bc04d172733c7', 'cc656fe5317d482da00cb736f5d69c78');
INSERT INTO `tbl_user_role` VALUES ('3', 'e1b195280bff4b558c49115528d6baf0', 'e1b195280bff4b558c49115528d6bafa');

SET FOREIGN_KEY_CHECKS = 1;
