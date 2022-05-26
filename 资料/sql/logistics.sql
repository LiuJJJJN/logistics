/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : logistics

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 26/05/2022 12:34:32
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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_admin
-- ----------------------------
INSERT INTO `tbl_admin` VALUES ('53a713f50a58733ff6dad26988a68601', 'admin123', '53a713f50a58733ff6dad26988a68609', '<|IS', '纪老师');

-- ----------------------------
-- Table structure for tbl_building
-- ----------------------------
DROP TABLE IF EXISTS `tbl_building`;
CREATE TABLE `tbl_building`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `floor` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_no` int NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rooms` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_building
-- ----------------------------
INSERT INTO `tbl_building` VALUES ('003122c7a44f4d5aa9860efcd2f90871', '12#学生公寓', '15', 5, '公寓楼', '16');
INSERT INTO `tbl_building` VALUES ('04727319d02a4c02b40d5b4802357cb1', '旅顺校区图书馆', '5', 2, '图书馆', '1');
INSERT INTO `tbl_building` VALUES ('0d8dd712697e40b8b15b1500f9357791', '8#贝壳楼', '2', 14, '教学楼', '1');
INSERT INTO `tbl_building` VALUES ('165d38f52d6446528383e1a9b0576f72', '6#公寓楼', '6', 20, '公寓楼', '46');
INSERT INTO `tbl_building` VALUES ('1a0a2484eb3145e7a9b9176e8d239768', '9#贝壳楼', '6', 15, '教学楼', '2');
INSERT INTO `tbl_building` VALUES ('3b0e242f52184f0d8f3559a561f4c7c1', '13#学生公寓', '7', 6, '公寓楼', '23');
INSERT INTO `tbl_building` VALUES ('3da28639c16c4352853976b667304ecd', '6#贝壳楼', '6', 12, '教学楼', '2');
INSERT INTO `tbl_building` VALUES ('5ed09028587b48859efaf20a4a195d01', '5#贝壳楼', '6', 11, '教学楼', '2');
INSERT INTO `tbl_building` VALUES ('71ea104c37c44d688d3917dd80b03aa8', '5#公寓楼', '6', 19, '公寓楼', '46');
INSERT INTO `tbl_building` VALUES ('7e06b62767eb4b87b2c3d1cac03daffa', '7#公寓楼', '6', 21, '公寓楼', '46');
INSERT INTO `tbl_building` VALUES ('7e9fa6358958487a8afea3f367d9c960', '14#学生公寓', '6', 7, '公寓楼', '23');
INSERT INTO `tbl_building` VALUES ('7feece693b6e44218eae6703c05f155b', 'Y艺术楼', '5', 24, '教学楼', '12');
INSERT INTO `tbl_building` VALUES ('82550b0c760647ad89ff3ca23abaff29', '10#学生公寓', '15', 3, '公寓楼', '16');
INSERT INTO `tbl_building` VALUES ('889b51d9c9b54c71a0424725d3c4840b', 'A软件楼', '5', 16, '教学楼', '12');
INSERT INTO `tbl_building` VALUES ('8de8357e7497488c8c4366f6ebf1fc3c', '7#贝壳楼', '6', 13, '教学楼', '2');
INSERT INTO `tbl_building` VALUES ('9cc291c2df814414a4ba1ef0d3a912f0', '11#学生公寓', '15', 4, '公寓楼', '16');
INSERT INTO `tbl_building` VALUES ('a40b88f15e4b48a9976a9b59b952111c', '4#贝壳楼', '6', 10, '教学楼', '2');
INSERT INTO `tbl_building` VALUES ('a6b758d549ac4377aab6371084c2cc3a', 'J教学楼', '6', 1, '教学楼', '35');
INSERT INTO `tbl_building` VALUES ('bf7b2413808a495abd8ea31e9ae1ad0a', '2#贝壳楼', '6', 8, '教学楼', '2');
INSERT INTO `tbl_building` VALUES ('c4f77a9ff5334dbda4c4a3c8638aeea1', '9#公寓楼', '6', 23, '公寓楼', '46');
INSERT INTO `tbl_building` VALUES ('d80046a3d466433395329d28cedcfa8c', '8#公寓楼', '6', 22, '公寓楼', '46');
INSERT INTO `tbl_building` VALUES ('e87110167db64754804f40f5a8aee91d', '3#贝壳楼', '6', 9, '教学楼', '2');
INSERT INTO `tbl_building` VALUES ('ecd8695606d04c6591e5973df61421f9', '3#公寓楼', '6', 17, '公寓楼', '46');
INSERT INTO `tbl_building` VALUES ('f0302e28a6e049e58138aea1403d1e18', '本部图书馆', '6', 25, '图书馆', '1');
INSERT INTO `tbl_building` VALUES ('f199621aeaa54cc5aaedfac37fd6d6ed', '4#公寓楼', '6', 18, '公寓楼', '46');

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_id`(`stu_id` ASC) USING BTREE,
  INDEX `classroom_id`(`classroom_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_class_use
-- ----------------------------
INSERT INTO `tbl_class_use` VALUES ('ab1422ef28ad471ca8a5fa2ba0582656', '39145ae2cc0c43e18388fbfe3e67e73f', 'ab1411ef26ad471ba8a5fa2ba0582683', '2022-05-02 17:26:44', '2022-05-02 20:30:56');

-- ----------------------------
-- Table structure for tbl_classroom
-- ----------------------------
DROP TABLE IF EXISTS `tbl_classroom`;
CREATE TABLE `tbl_classroom`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `room_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `building_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `elect_inf` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_use` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_open` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_clean` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_classroom
-- ----------------------------
INSERT INTO `tbl_classroom` VALUES ('2e1de15e43e0e3899a57b1cd501f47b1', '102', '7feece693b6e44218eae6703c05f155b', '1', '0', '0', '0');
INSERT INTO `tbl_classroom` VALUES ('5a1ff2f99846f1d73b18e9c663104e5d', '301', '0d8dd712697e40b8b15b1500f9357791', '0', '0', '0', '0');
INSERT INTO `tbl_classroom` VALUES ('6f9ca48eaa3b40008d7be8017dee0e8b', '777', 'a40b88f15e4b48a9976a9b59b952111c', '0', '0', '0', '0');
INSERT INTO `tbl_classroom` VALUES ('748c1f04c7cc34171bd43f3e57851777', '601', '5ed09028587b48859efaf20a4a195d01', '1', '0', '0', '1');
INSERT INTO `tbl_classroom` VALUES ('8c07710e03914dc2f5eb3b01461e56d6', '502', 'a40b88f15e4b48a9976a9b59b952111c', '1', '1', '1', '0');
INSERT INTO `tbl_classroom` VALUES ('ab1411ef26ad471ba8a5fa2ba0582683', '501', 'a40b88f15e4b48a9976a9b59b952111c', '1', '0', '1', '1');
INSERT INTO `tbl_classroom` VALUES ('b8d260dcaaba60b863d8a0ea421dcc5a', '203', 'a6b758d549ac4377aab6371084c2cc3a', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for tbl_dic_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dic_type`;
CREATE TABLE `tbl_dic_type`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `code`(`code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_dic_type
-- ----------------------------
INSERT INTO `tbl_dic_type` VALUES ('4e1eb3af3845474ab101e5fbbe94ad63', 'college', '学院', 'XX学院、XXX学院、XX学院');
INSERT INTO `tbl_dic_type` VALUES ('9f367794217c478d9c592b15dc0e4c30', 'building', '楼宇类型', '公寓、教学楼、图书馆、体育馆等');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_dic_value
-- ----------------------------
INSERT INTO `tbl_dic_value` VALUES ('4e1eb3af3845474ab101e5fbbe94ad65', '信息学院', 1, 'college');
INSERT INTO `tbl_dic_value` VALUES ('699e5e6b47f8403b85b7fdbc90f55c33', '图书馆', 3, 'building');
INSERT INTO `tbl_dic_value` VALUES ('6a1e6e316d2c4ae2933682b17b5263d9', '马克思学院', 5, 'college');
INSERT INTO `tbl_dic_value` VALUES ('a61a0ddaa061401fa7f4b62176a23e9d', '教学楼', 2, 'building');
INSERT INTO `tbl_dic_value` VALUES ('a685df091cee4e8e95d29a65c4954265', '经济管理学院', 2, 'college');
INSERT INTO `tbl_dic_value` VALUES ('c4eb623b082d4dd19e750a167030e0fa', '软件学院', 3, 'college');
INSERT INTO `tbl_dic_value` VALUES ('db0bb5f763354d089dfed7e59052c143', '理学院', 4, 'college');
INSERT INTO `tbl_dic_value` VALUES ('de330536d649418a88905bea40088adc', '公寓楼', 1, 'building');

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
  `building_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `building_id`(`building_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_dorm
-- ----------------------------
INSERT INTO `tbl_dorm` VALUES ('23bddd6b20334eafa75bb00038b2f6b7', '505', 10, 10, 6, '是', '', '9cc291c2df814414a4ba1ef0d3a912f0');
INSERT INTO `tbl_dorm` VALUES ('4d569eb175704c49ac120b26e3ac9e67', '508', 12, 22, 6, '是', '508508508', '9cc291c2df814414a4ba1ef0d3a912f0');
INSERT INTO `tbl_dorm` VALUES ('6549aa45f86c423bae901368ca7ad854', '504', 55, 66, 6, '是', '曹鸣轩之寝', '9cc291c2df814414a4ba1ef0d3a912f0');
INSERT INTO `tbl_dorm` VALUES ('78776fb3dbad44b09ff2814958a5a080', '506', 40, 45, 6, '是', '党员寝室', '9cc291c2df814414a4ba1ef0d3a912f0');
INSERT INTO `tbl_dorm` VALUES ('a95b406eadc54390816998600a8f33a9', '509', 10, 11, 6, '是', '', '9cc291c2df814414a4ba1ef0d3a912f0');

-- ----------------------------
-- Table structure for tbl_dorm_change
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dorm_change`;
CREATE TABLE `tbl_dorm_change`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stu_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `from_dorm_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `to_dorm_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_id`(`stu_id` ASC) USING BTREE,
  INDEX `from_dorm_id`(`from_dorm_id` ASC) USING BTREE,
  INDEX `to_dorm_id`(`to_dorm_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_dorm_change
-- ----------------------------
INSERT INTO `tbl_dorm_change` VALUES ('3a581172f62d460e9ee17702e500b282', '196e74d1927248f2959ff94b2bfe9460', '6549aa45f86c423bae901368ca7ad854', '78776fb3dbad44b09ff2814958a5a080', 5, '2022-05-10 16:17:23');
INSERT INTO `tbl_dorm_change` VALUES ('48aa3496ecb44238903627fcfad391c3', '196e74d1927248f2959ff94b2bfe9460', '78776fb3dbad44b09ff2814958a5a080', '6549aa45f86c423bae901368ca7ad854', 5, '2022-05-10 16:12:03');
INSERT INTO `tbl_dorm_change` VALUES ('72b76ca171aa4e52afd0fe13a2ac7aeb', '196e74d1927248f2959ff94b2bfe9460', '78776fb3dbad44b09ff2814958a5a080', '6549aa45f86c423bae901368ca7ad854', 5, '2022-05-12 10:28:31');
INSERT INTO `tbl_dorm_change` VALUES ('8cd38d815b804d2c8ae04f56b5798716', '123fc35993464045bad1827ac2b1cb35', '6549aa45f86c423bae901368ca7ad854', '78776fb3dbad44b09ff2814958a5a080', 5, '2022-05-10 16:06:52');
INSERT INTO `tbl_dorm_change` VALUES ('c72524ea014347999e6ca50e2806468f', 'e853c3dc4e194fd496362811c7e78f20', '4d569eb175704c49ac120b26e3ac9e67', 'a95b406eadc54390816998600a8f33a9', 5, '2022-05-10 16:15:04');
INSERT INTO `tbl_dorm_change` VALUES ('d73f94bfa78c44c9a7df80dfbbd65dc6', 'd457cbd966ef4645a5ae8a717a2a62cb', '4d569eb175704c49ac120b26e3ac9e67', 'a95b406eadc54390816998600a8f33a9', 5, '2022-05-10 16:14:46');
INSERT INTO `tbl_dorm_change` VALUES ('d9d6d8f139cb4babbcea5929b9caff6a', '196e74d1927248f2959ff94b2bfe9460', '6549aa45f86c423bae901368ca7ad854', '78776fb3dbad44b09ff2814958a5a080', 4, '2022-05-12 12:29:44');
INSERT INTO `tbl_dorm_change` VALUES ('f7a2bdcce7034a55b182019549466ac5', '196e74d1927248f2959ff94b2bfe9460', '78776fb3dbad44b09ff2814958a5a080', '6549aa45f86c423bae901368ca7ad854', 4, '2022-05-10 16:18:19');

-- ----------------------------
-- Table structure for tbl_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tbl_feedback`;
CREATE TABLE `tbl_feedback`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `feedback` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title_feedback` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stu_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_id`(`stu_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_feedback
-- ----------------------------
INSERT INTO `tbl_feedback` VALUES ('7426d36f42584853b7ce15bc80a0d663', '于导，你是我的神！！！于导，你是我的神！！！于导，你是我的神！！！于导，你是我的神！！！于导，你是我的神！！！于导，你是我的神！！！于导，你是我的神！！！于导，你是我的神！！！于导，你是我的神！！！于', '于导，你是我的神！！！', '196e74d1927248f2959ff94b2bfe9460', '2022-05-10 16:21:04', '3', '0');
INSERT INTO `tbl_feedback` VALUES ('c2b53de4455444f6a271430bd165953e', '曹明珠是傻瓜嘛?', '明珠', '39145ae2cc0c43e18388fbfe3e67e73f', '2022-05-06 14:33:04', '3', '0');
INSERT INTO `tbl_feedback` VALUES ('ca192daf2cbd4683a9107447d5f772ec', '夫照样不换体重秤！', '不还体重秤', '196e74d1927248f2959ff94b2bfe9460', '2022-05-06 17:43:19', '3', '0');
INSERT INTO `tbl_feedback` VALUES ('df3c9b65b8374e23bd3cf603937cdcfb', '123123', '我是夫照样', '196e74d1927248f2959ff94b2bfe9460', '2022-05-20 09:53:34', '3', '0');
INSERT INTO `tbl_feedback` VALUES ('ef514fbadece45dab63c3e944924835b', '曹洺瑄是笨蛋嘛', 'bendan', '39145ae2cc0c43e18388fbfe3e67e73f', '2022-05-01 17:22:31', '3', '0');

-- ----------------------------
-- Table structure for tbl_lib_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_lib_order`;
CREATE TABLE `tbl_lib_order`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `stu_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生id',
  `start_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开始占用时间',
  `end_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '结束占用时间',
  `create_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '占座订单创建时间',
  `status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0已预定 1已占用 2已取消 3已完成',
  `table_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '座位id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_id`(`stu_id` ASC) USING BTREE,
  INDEX `seat_id`(`table_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_lib_order
-- ----------------------------
INSERT INTO `tbl_lib_order` VALUES ('196f28ddabe3487d8231ef52f62dc9b5', '196e74d1927248f2959ff94b2bfe9460', '2022-05-23 16:41:43', '2022-05-23 22:00:00', '2022-05-23 16:41:43', '1', 'd84deda4b21b415991f4d297f4026183');
INSERT INTO `tbl_lib_order` VALUES ('959e912deed343749c1ded9fa5eae6c0', '196e74d1927248f2959ff94b2bfe9460', '2022-05-23 16:34:34', '2022-05-23 22:00:00', '2022-05-23 16:34:34', '2', 'd84deda4b21b415991f4d297f4026183');
INSERT INTO `tbl_lib_order` VALUES ('a3f6a19f3a004f3e8ce78a864e7d9e59', '196e74d1927248f2959ff94b2bfe9460', '2022-05-23 16:26:54', '2022-05-23 22:00:00', '2022-05-23 16:26:54', '2', 'd84deda4b21b415991f4d297f4026183');

-- ----------------------------
-- Table structure for tbl_lib_table
-- ----------------------------
DROP TABLE IF EXISTS `tbl_lib_table`;
CREATE TABLE `tbl_lib_table`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `floor` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `area` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `seat` int NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0可用 1不可用',
  `library_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `library_id`(`library_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_lib_table
-- ----------------------------
INSERT INTO `tbl_lib_table` VALUES ('0185d30a8f654af7a288169e2fda8841', '3', 'A', 4, '3003', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('01a87e5228c4498899008f38e72ede09', '2', 'A', 4, '2004', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('024cbfeb6c844ba89bb8fdeb149e4f0f', '2', 'C', 4, '2026', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('02fbf8ab4bd048f1b85b327d75c1f0b9', '2', 'D', 4, '2036', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('034e07ec85c24116bb84e688e1279cce', '4', 'B', 4, '4017', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('06735174fd134c3fb0869025ed344979', '5', 'A', 4, '5007', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('089a270e767640f381a7d2082a583e7d', '3', 'D', 4, '3032', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('08a75922c6dc44a588f0962bfeb95578', '4', 'A', 4, '4009', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('0d1fda25a1d149f69483249a8027aa6b', '5', 'C', 4, '5027', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('0d394d2860cf4ec0b4d79f4695f499da', '5', 'B', 4, '5019', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('0e79ab82684649ad94b1023664ecd339', '2', 'C', 4, '2028', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('0eab67de4ae24641a504878f19293262', '3', 'B', 4, '3015', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('0f7949fd5abd4c8aa797a54cef00a17f', '4', 'D', 4, '4036', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('106a7bdb40ac48c9af4123421a9b0a85', '4', 'B', 4, '4021', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('10d90ffb07184567a50ca2533525cd48', '2', 'B', 4, '2023', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('111155ba60bf45bda45de48968bc6783', '3', 'B', 4, '3014', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('1352c050628447e28c0c2bf968e3bfa1', '5', 'B', 4, '5016', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('14d5227e738e4f03b969c08c8e06c33f', '5', 'A', 4, '5003', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('16c88465c3014bb8bcc8a87bd239ae02', '4', 'D', 4, '4035', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('1e4c54541f41432594ec16290a4fab2c', '5', 'D', 4, '5032', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('1e62b2028682477ab37c476233c725dc', '3', 'B', 4, '3021', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('213af7c99f1c402da271444a8919a5e4', '5', 'A', 4, '5011', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('228388d96f324209aad606bf410cd8ac', '4', 'B', 4, '4019', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('22cae6d3b9a04501af6d0a448349892d', '2', 'A', 4, '2007', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('22dd392ae2b742c28bc32ecfa0dfc9a1', '4', 'A', 4, '4003', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('24a8d2d1924b4d4f963e4e463e029568', '5', 'B', 4, '5018', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('2892047b6684470cae11696f14b300d1', '2', 'B', 4, '2016', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('28a4d9f05cac4b93987f3e5527f979cf', '3', 'B', 4, '3023', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('2b1b793038eb44b5886db6982d42ec09', '5', 'B', 4, '5013', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('2b31dec074a84a8a9655640a7b05fdf5', '3', 'A', 4, '3008', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('2d4899bc571c4f3e9297084d3360faef', '3', 'D', 4, '3035', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('2f660cfd217b40d0828a9d3963dbf527', '4', 'D', 4, '4030', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('30c9a2be643d412c88251fbc15d75822', '3', 'A', 4, '3011', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('3282d97af3f34b6cb30617c09bd4eb3a', '2', 'A', 4, '2006', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('36d60315d5304075acfecfc7543c9044', '5', 'A', 4, '5010', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('37ac8b1328814c6aa64e1e4f8f413124', '3', 'A', 4, '3007', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('395eb93a657c43a2b448275d4748f959', '3', 'B', 4, '3019', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('3985e02b70f14b3680603e079430da4d', '2', 'D', 4, '2033', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('399c35c429a345539c3fef086746b26f', '4', 'C', 4, '4028', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('3b03b3aa01f94ecdaac8649ead19590f', '4', 'C', 4, '4026', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('3d73dc92f42a4370b12bd108a97b6df5', '3', 'D', 4, '3029', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('409f81c5fb3f43bb979b52db3d61dd27', '4', 'B', 4, '4016', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('412ead43eae94bf38f5851ec53683acb', '5', 'C', 4, '5028', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('424dfe0c33724c49b3b7a4f6fb634473', '5', 'D', 4, '5029', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('42b23aaa25a147ed9ee6d941f7ab52ad', '4', 'A', 4, '4012', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('44d6fbee548d480ebc7f75ec584f6957', '4', 'A', 4, '4010', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('452ab41bd150477cbdcb612779160497', '3', 'B', 4, '3016', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('45a4321256334f10b48edb0c13333725', '3', 'D', 4, '3030', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('4884a53ab54641cea78c22c46fa56985', '5', 'D', 4, '5030', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('491960f2252a4846b39b4a8645c7565c', '4', 'B', 4, '4013', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('4c92b51b12a74978bbe54d2baa6755f8', '5', 'B', 4, '5023', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('4ccf10205d494db896977cd1ca2f8670', '2', 'A', 4, '2003', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('4cf0a7f334524d1598d232535d98e8d2', '5', 'B', 4, '5020', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('4d7dcb1065fd4acda570a66b4ecce790', '2', 'D', 4, '2034', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('50a036345a1c4a13991e05e181d04330', '3', 'D', 4, '3033', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('549c91fe95bf43789b61faa6122ba64f', '4', 'A', 4, '4006', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('5a50e57c88fb49d3bf173312c6d8e1b7', '2', 'D', 4, '2035', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('5a668a3023be4141b06a35695924983d', '2', 'A', 4, '2010', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('5a69c90c6b2643fe80471a3815b1c6fd', '4', 'A', 4, '4005', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('5c4beca07dfe4a6eac22acfae18b42fc', '4', 'D', 4, '4034', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('5ed893ab088b464c93f0e77f21de2c60', '2', 'A', 4, '2002', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('5f888b3319e24c1397580fb49dac2ef6', '4', 'B', 4, '4020', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('6121136b55a04c04a2daf94c763b753b', '2', 'B', 4, '2013', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('615f423502d9497abcfbbd8b0366f81c', '2', 'B', 4, '2014', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('625935e90bbe41d8ac2ceeb86226ca88', '2', 'A', 4, '2012', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('632cf6bd976d44f19c6e5a3b7649dd77', '2', 'A', 4, '2008', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('64ad10f6c7844d97bf81915edd5887c9', '3', 'B', 4, '3017', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('68ac551c71de4185b65ed224ca65db29', '4', 'A', 4, '4001', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('6930d7af6a9c4d10ad52256c3dc62855', '4', 'B', 4, '4015', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('6b6c1c5a5fe24cb381c523b5f2c8c12b', '5', 'C', 4, '5025', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('6b7c4b3bca664be39db5dfc19c3e6ae8', '5', 'B', 4, '5017', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('6bf77f2700db4ea5bbbd4535383bb5d0', '2', 'B', 4, '2022', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('6c9c37f29a44431b9f691aec497115df', '2', 'D', 4, '2030', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('6cdf71517423494a9aa0d066e0daee43', '3', 'A', 4, '3012', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('6fddd14e475146088c396d5366e79518', '2', 'B', 4, '2015', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('71f712a10b5040a68ad575cc1981a552', '5', 'A', 4, '5002', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('725e0286e7524675b34da2b41dec3b95', '3', 'B', 4, '3024', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('781e376cda464b0886bb54295cce47e4', '4', 'D', 4, '4032', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('7a941066db31474980b877dfad0e6a0f', '5', 'B', 4, '5022', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('7e31517030ab4026be8682714b5f3094', '3', 'A', 4, '3002', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('7e9577c01ee54725a6bc804d976b3529', '4', 'B', 4, '4014', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('7e96f568781044e789a6e0c20dac66c3', '4', 'D', 4, '4029', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('7ed31abb653f4dbea37e060d053f2c6a', '3', 'A', 4, '3010', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('809bf7b2d3fe464e808c7da91785f772', '5', 'A', 4, '5012', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('82c3ef830edf411785bdbb72fe008cbb', '2', 'D', 4, '2029', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('83622289f7a44879a619ad5a75b85ab9', '4', 'A', 4, '4004', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('85b9246947cc4fc797b95cb18a1ff3b8', '3', 'A', 4, '3001', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('8668097587774510b5476ee53a6e4395', '2', 'C', 4, '2027', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('89c1b51c5d2549deaaa7f0dbd660b721', '3', 'B', 4, '3013', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('8b2a1991161a4535a8f6a88eb915229e', '4', 'A', 4, '4002', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('934ca531379e4fb4a628ed88011c8f87', '4', 'C', 4, '4027', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('938d88453f3249d2b5bb131235936206', '3', 'C', 4, '3026', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('94334f820abb46298a463bab1a8d4e17', '2', 'B', 4, '2021', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('94f2bb57b65a479a838df50e27cd9185', '3', 'B', 4, '3020', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('94feca93ee3d4f678c26bed716240904', '3', 'A', 4, '3009', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('954f827fa7f44a6cafe212a8d3ece49b', '5', 'B', 4, '5021', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('98aa7e11b0824769b47873cf72d9ec76', '4', 'C', 4, '4025', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('a0312aab960f4c7e9466fd4a85a541f6', '2', 'A', 4, '2011', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('a16248b3e0144dd684c5522f02719a39', '5', 'A', 4, '5008', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('a1dc5a27732a47dcb00ff04a5514f490', '3', 'B', 4, '3022', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('a507f13061c74fbf82c8be219219e066', '5', 'B', 4, '5014', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('a6449c75e12f41c096dbe67863750a0c', '3', 'D', 4, '3031', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('a6a6f0a26bad4a7e8fd6d55fa797a889', '5', 'A', 4, '5009', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('ab562615cf404292b7d3496c7ab2ca37', '2', 'B', 4, '2018', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('afe2f2f1c07f4dfe87695c994fc30757', '5', 'B', 4, '5015', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('b04f473cb10d43deada0639448870656', '3', 'C', 4, '3025', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('b64399b6caee41f59b004edc79b41345', '4', 'A', 4, '4011', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('ba67604b724f41f59c089d4e90a329cd', '4', 'B', 4, '4023', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('ba6c9679f7474498b2e9f3a45fcde179', '3', 'C', 4, '3027', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('bce9e74237f34120bea8691e5257baa5', '4', 'D', 4, '4031', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('be17c82694034a21ac86cbd0c5346bd9', '3', 'A', 4, '3005', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('c1f6b78a218642c0a4a7ca8f54d14df3', '2', 'B', 4, '2019', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('c39961a4b0b843819723b81a955a8ef6', '3', 'C', 4, '3028', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('c42058d8beab4973947b9299a375e05a', '3', 'B', 4, '3018', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('c4c26e8e531e4e1d9e5f2f7e65e440b3', '4', 'B', 4, '4018', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('c59731ccca68454c8de98b298e555c61', '3', 'A', 4, '3004', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('c7312676b414426fb45206d26a5e1dc6', '4', 'D', 4, '4033', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('cc031148d5304a56adb041ee9abff0d2', '3', 'D', 4, '3036', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('cdb5ce775cc64ebba0cfdfb1c46344c3', '2', 'D', 4, '2032', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('ce641aeaf44444319ec54ccba138bea8', '5', 'B', 4, '5024', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('d1d8264dfb394102ad63ae9fd0da4947', '2', 'A', 4, '2009', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('d6581e6ad57641319c021c8ffd5d0a8a', '2', 'A', 4, '2005', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('d819cbecfc154667aed6292ec4dc4e4a', '5', 'C', 4, '5026', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('d84deda4b21b415991f4d297f4026183', '2', 'A', 4, '2001', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('dad7b5cc35424b95b95775ba07ebc95e', '2', 'B', 4, '2024', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('e1226010b9d94d658e1e5bdd45e94e91', '5', 'D', 4, '5031', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('e1a363c443d24019b0e363aa61ffbb32', '5', 'A', 4, '5006', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('e281dde51e9b4c0da217c8d5cc4f412b', '2', 'B', 4, '2017', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('e4c2777154fe4bbd9fdef4043de2401a', '4', 'B', 4, '4024', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('e5975cd1850f4d6fb0356641c2bdd76c', '3', 'D', 4, '3034', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('e5d958192d1d44979df6b18ce3f93d9e', '2', 'C', 4, '2025', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('e91a3a357bf348508858aa1195ba2c6b', '2', 'D', 4, '2031', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('e979155653e54292a71c43cb0d0ed942', '4', 'B', 4, '4022', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('ed362c90169e4ea4a96ddbd203de8cfe', '3', 'A', 4, '3006', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('ede631d9e78b45f3a4c7ed220944aad3', '5', 'A', 4, '5004', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('f2b4dd583a3840698eb8b734315e162a', '4', 'A', 4, '4008', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('f307fa14b4744bd2b10d62ec90d10108', '5', 'A', 4, '5001', '1', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('fbaddd0c3e1f4769b703ff805794599f', '4', 'A', 4, '4007', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('fc26a040ca5742a1ba9a2c498d5459ad', '5', 'A', 4, '5005', '0', 'fa55bf1acf424f8a922af98534cf4e65');
INSERT INTO `tbl_lib_table` VALUES ('ff8ed4a1346f40209e0ab589c759b08d', '2', 'B', 4, '2020', '0', 'fa55bf1acf424f8a922af98534cf4e65');

-- ----------------------------
-- Table structure for tbl_library
-- ----------------------------
DROP TABLE IF EXISTS `tbl_library`;
CREATE TABLE `tbl_library`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `open_time` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `close_time` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_open` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `building_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `building_id`(`building_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_library
-- ----------------------------
INSERT INTO `tbl_library` VALUES ('b998d90d8777474199de9d1e7b28653c', '本部图书馆', '06:30:00', '22:00:00', '是', 'f0302e28a6e049e58138aea1403d1e18');
INSERT INTO `tbl_library` VALUES ('fa55bf1acf424f8a922af98534cf4e65', '旅顺图书馆', '07:30:00', '22:00:00', '是', '04727319d02a4c02b40d5b4802357cb1');

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
  `order_no` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_permission
-- ----------------------------
INSERT INTO `tbl_permission` VALUES (1, 'dorm', '寝室相关', 0, '/stu', '1', '1', 1);
INSERT INTO `tbl_permission` VALUES (2, 'dorm:select', '我的寝室', 1, '/stu/myDorm', '1', '1', 1);
INSERT INTO `tbl_permission` VALUES (3, 'dorm:update:power', '寝室缴纳电费', 1, '/stu/powerDorm', '1', '1', 2);
INSERT INTO `tbl_permission` VALUES (4, 'dorm:update:water', '寝室缴纳水费', 1, '/stu/waterDorm', '1', '1', 3);
INSERT INTO `tbl_permission` VALUES (5, 'dorm:change', '我要换寝', 1, '/stu/changeDorm', '1', '1', 4);
INSERT INTO `tbl_permission` VALUES (6, 'classroom', '教室相关', 0, '/stu', '1', '1', 2);
INSERT INTO `tbl_permission` VALUES (7, 'classroom:insert', '教室占座', 6, '/stu/grabClassroom', '1', '1', 1);
INSERT INTO `tbl_permission` VALUES (8, 'library', '图书馆相关', 0, '/stu', '1', '1', 3);
INSERT INTO `tbl_permission` VALUES (9, 'library:insert', '图书馆占座', 8, '/stu/grabLibrary/floor/1', '1', '1', 1);
INSERT INTO `tbl_permission` VALUES (10, 'feedback', '反馈相关', 0, '/stu', '1', '1', 4);
INSERT INTO `tbl_permission` VALUES (11, 'feedback:insert', '我要反馈', 10, '/stu/feedback', '1', '1', 1);
INSERT INTO `tbl_permission` VALUES (12, 'building', '楼宇相关', 0, '/admin', '1', '3', 2);
INSERT INTO `tbl_permission` VALUES (13, 'building:*', '管理楼宇', 12, '/admin/buildingManage', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (14, 'dorm', '寝室相关', 0, '/admin', '1', '3', 3);
INSERT INTO `tbl_permission` VALUES (15, 'student', '学生相关', 0, '/tutor', '1', '2', 1);
INSERT INTO `tbl_permission` VALUES (16, 'student:*', '认领学生', 15, '/tutor/studentManage', '1', '2', 1);
INSERT INTO `tbl_permission` VALUES (17, 'feedback', '反馈相关', 0, '/tutor', '1', '2', 5);
INSERT INTO `tbl_permission` VALUES (18, 'feedback:update', '学生反馈', 17, '/tutor/feedback', '1', '2', 1);
INSERT INTO `tbl_permission` VALUES (19, 'grab', '占座相关', 0, '/tutor', '1', '2', 3);
INSERT INTO `tbl_permission` VALUES (20, 'grab:*', '管理占座', 19, '/tutor/grab', '1', '2', 1);
INSERT INTO `tbl_permission` VALUES (21, 'user', '用户相关', 0, '/admin', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (22, 'user:*:stu', '管理学生', 21, '/admin/studentManage', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (23, 'classroom', '教室相关', 0, '/admin', '1', '3', 4);
INSERT INTO `tbl_permission` VALUES (24, 'classroom:*', '管理教室', 23, '/admin/classroom', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (25, 'library', '图书馆相关', 0, '/admin', '1', '3', 5);
INSERT INTO `tbl_permission` VALUES (26, 'library:*', '管理图书馆', 25, '/admin/library', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (27, 'role', '权限相关', 0, '/admin', '1', '3', 6);
INSERT INTO `tbl_permission` VALUES (28, 'role:update:stu', '学生权限管理', 27, '/admin/studentRole', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (29, 'dict', '数据字典相关', 0, '/admin', '1', '3', 7);
INSERT INTO `tbl_permission` VALUES (30, 'dicType:*', '管理数据字典Type', 29, '/admin/dicType', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (31, 'dicValue:*', '管理数据字典Value', 29, '/admin/dicValue', '1', '3', 2);
INSERT INTO `tbl_permission` VALUES (32, 'role:update:tutor', '导员权限管理', 27, '/admin/tutorRole', '1', '3', 2);
INSERT INTO `tbl_permission` VALUES (33, 'role', '权限相关', 0, '/tutor', '1', '2', 2);
INSERT INTO `tbl_permission` VALUES (34, 'role:update:stu', '学生权限管理', 33, '/admin/studentRole', '1', '2', 1);
INSERT INTO `tbl_permission` VALUES (35, 'user:*:tutor', '管理导员', 21, '/admin/tutorManage', '1', '3', 2);
INSERT INTO `tbl_permission` VALUES (36, 'carousel', '公告相关', 0, '/admin', '1', '3', 8);
INSERT INTO `tbl_permission` VALUES (37, 'carousel:*', '管理公告', 36, '/admin/carousel', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (38, 'student:*', '我的学生', 15, '/tutor/myStudent', '1', '2', 2);
INSERT INTO `tbl_permission` VALUES (39, 'admin:*', '管理管理员', 21, '/admin/adminManage', '1', '3', 3);
INSERT INTO `tbl_permission` VALUES (40, 'dorm:*', '管理寝室', 14, '/admin/dormManage', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (42, 'dormChange:*', '换寝申请', 14, '/admin/dormChange', '1', '3', 1);
INSERT INTO `tbl_permission` VALUES (43, 'dormChange', '寝室相关', 0, '/tutor', '1', '2', 4);
INSERT INTO `tbl_permission` VALUES (44, 'dormChange:*', '换寝申请', 43, '/admin/dormChange', '1', '2', 1);

-- ----------------------------
-- Table structure for tbl_reply
-- ----------------------------
DROP TABLE IF EXISTS `tbl_reply`;
CREATE TABLE `tbl_reply`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reply` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tutor_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `feedback_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tutor_id`(`tutor_id` ASC) USING BTREE,
  INDEX `feedback_id`(`feedback_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_reply
-- ----------------------------
INSERT INTO `tbl_reply` VALUES ('3f1b61e70fe745bf9a4e78b0751189bd', '就不换！', '3d67ef0b2187417cbab9f2e985220f5d', 'ca192daf2cbd4683a9107447d5f772ec', '2022-05-06 17:43:36');
INSERT INTO `tbl_reply` VALUES ('607f4ecc43e64b6e9b19bd50cab375e2', '应该是傻瓜吧', '3d67ef0b2187417cbab9f2e985220f5d', 'c2b53de4455444f6a271430bd165953e', '2022-05-06 14:34:28');
INSERT INTO `tbl_reply` VALUES ('a286bb9bfbae4ebd85844312d24d383f', '必须还给504！！', '3d67ef0b2187417cbab9f2e985220f5d', 'ca192daf2cbd4683a9107447d5f772ec', '2022-05-06 17:43:59');
INSERT INTO `tbl_reply` VALUES ('e0c7fc839d964ed8b53299c5de54e06c', '好的', '3d67ef0b2187417cbab9f2e985220f5d', '7426d36f42584853b7ce15bc80a0d663', '2022-05-10 16:32:56');
INSERT INTO `tbl_reply` VALUES ('e3d575c54fec402d92fcf020d4745290', 'sad', '3d67ef0b2187417cbab9f2e985220f5d', 'df3c9b65b8374e23bd3cf603937cdcfb', '2022-05-20 09:54:20');
INSERT INTO `tbl_reply` VALUES ('e87883bcb44049649ef471561e3061d3', '就是傻瓜', '3d67ef0b2187417cbab9f2e985220f5d', 'c2b53de4455444f6a271430bd165953e', '2022-05-06 16:11:42');
INSERT INTO `tbl_reply` VALUES ('fcc76c1cd192413695ec1a7f492f74db', '就是大笨蛋', '3d67ef0b2187417cbab9f2e985220f5d', 'ef514fbadece45dab63c3e944924835b', '2022-05-06 15:32:09');

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES ('1', '学生');
INSERT INTO `tbl_role` VALUES ('2', '导员');
INSERT INTO `tbl_role` VALUES ('3', '管理员');

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dorm_id`(`dorm_id` ASC) USING BTREE,
  INDEX `tutor_id`(`tutor_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_student
-- ----------------------------
INSERT INTO `tbl_student` VALUES ('196e74d1927248f2959ff94b2bfe9460', 'fuzhaoyang', '6f4bfd8c210fd5ef5ce49b846382b18f', '=}hW', '傅朝阳', '男', '2022-04-29', '4', '信息学院', '192', '夫照样', '1921030110', '78776fb3dbad44b09ff2814958a5a080', '3d67ef0b2187417cbab9f2e985220f5d', '/196e74d1927248f2959ff94b2bfe9460/海啸头像灰色001.jpg');
INSERT INTO `tbl_student` VALUES ('2e0871881b5b418aa49a7068276d658a', 'liujianing', 'd4497668ab85f120c39a9475091e964d', 'DP`n', '刘嘉宁', '男', '2019-09-01', '4', '信息学院', '191', NULL, '1921030124', '78776fb3dbad44b09ff2814958a5a080', NULL, NULL);
INSERT INTO `tbl_student` VALUES ('39145ae2cc0c43e18388fbfe3e67e73f', 'caomingxuan', '1b4a58640cea69ee1f2f0e459d8bab82', '3 rH', '曹洺珠', '男', '2022-04-26', '4', '信息学院', '191', '有请——曹明珠 21岁就读于大连交通大学', '1921030104', '6549aa45f86c423bae901368ca7ad854', '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_student` VALUES ('55aa003f786941d0ba34e99b18bc1f43', 'shaqianju', 'df44e033523607cd4d8242ff0fea1f31', 'bXzo', '杀千聚', '男', '2022-04-26', '4', '信息学院', '191', NULL, '1921030112', '23bddd6b20334eafa75bb00038b2f6b7', '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_student` VALUES ('5c18861eb99649b4874b548789df9d95', 'zrhzrh', '10ce61de39b4e8b2bc841da82d7d0b85', 'fblc', '张箬晗', '男', '2022-05-03', '4', '信息学院', '191', NULL, '1921030101', '6549aa45f86c423bae901368ca7ad854', '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_student` VALUES ('6209f762eb174afabbda96369faab1e6', 'zhangyulin', '06fb1e65341b7e5dc2e3a69211bd1725', 'b87!', '张宇林', '男', '2022-05-03', '4', '信息学院', '192', NULL, '1921030230', '4d569eb175704c49ac120b26e3ac9e67', '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_student` VALUES ('7c2a8d97dfb14ee6a93241824de07103', 'shenbin', '18a355bd0d38562d19c98f4403c18c5b', ')V|=', '沈斌', '男', '2022-04-26', '4', '信息学院', '191', NULL, '1921030129', '78776fb3dbad44b09ff2814958a5a080', '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_student` VALUES ('c45e7461bbe54fff97faf004a045ac0f', 'youzhibo', '29f528b50827b863a2ad914489c7eb14', 'F#)c', '由志博', '男', '2022-04-28', '4', '信息学院', '191', '党员', '1921030125', '6549aa45f86c423bae901368ca7ad854', '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_student` VALUES ('c82cd17a9c004ed790cfba7715c4feb4', 'wangziliang', '1a783f69af3bf7e540bf54619ff3bcfe', 'oS;U', '王梓良', '男', '2019-09-01', '4', '信息学院', '191', NULL, '1921030128', '78776fb3dbad44b09ff2814958a5a080', '3d67ef0b2187417cbab9f2e985220f5d', '/c82cd17a9c004ed790cfba7715c4feb4/ADEF151A-5AE0-4419-95FF-DA44EDFA452C.jpeg');
INSERT INTO `tbl_student` VALUES ('d457cbd966ef4645a5ae8a717a2a62cb', 'chenzhonghai', '23c75cdb23537970124b54f5eb36041c', '[eob', '陈忠海', '男', '2022-05-02', '4', '信息学院', '192', NULL, '1921030226', 'a95b406eadc54390816998600a8f33a9', '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_student` VALUES ('e853c3dc4e194fd496362811c7e78f20', 'wangkeji', '3b34dde76bd00a7b777610cf1a5a7475', ':]g*', '王克吉', '男', '2022-05-03', '4', '信息学院', '192', NULL, '1921030218', 'a95b406eadc54390816998600a8f33a9', '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_student` VALUES ('f398a670a1904128aba765315949fea0', 'xieninghui', '7f8fa6eedae5ade284d86d8a8d076101', '_atm', '血凝灰', '男', '2022-05-02', '4', '信息学院', '192', NULL, '1921030209', '4d569eb175704c49ac120b26e3ac9e67', '809b64f025694e0da1e57569cd6af3ed', NULL);
INSERT INTO `tbl_student` VALUES ('f82f4f316d634b9aaa211e762fb37171', 'hukaiwen', 'a0c49cf53dd7b7801725a6d8ccbee491', '#,,Y', '胡开文', '男', '2022-04-26', '4', '信息学院', '191', NULL, '1921030108', '23bddd6b20334eafa75bb00038b2f6b7', '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_student` VALUES ('f8bdf33ec5da4f088b2e33243b202ec9', '222333', '8c93e64a701e2d0fe42a2d06f67f93f0', 'xwT7', '张三', '男', '2001-02-03', '5', 'xx学院', '193', '所有单元格格式均要设置为文本', '222333', 'a95b406eadc54390816998600a8f33a9', NULL, NULL);

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
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_tutor
-- ----------------------------
INSERT INTO `tbl_tutor` VALUES ('21b51dd611d24feb9118a6db2038cc04', 'sunxianli', '0448935c9c8e16e4c4829c02314e60e6', 'VZyd', '孙贤丽', '男', '信息学院', '', NULL);
INSERT INTO `tbl_tutor` VALUES ('3d67ef0b2187417cbab9f2e985220f5d', 'yurentao', 'e26ed642b5047a5dfcdbc4dbf2182372', 'GfNO', '与人套', '男', '信息学院', '于仁涛', '/3d67ef0b2187417cbab9f2e985220f5d/海啸头像灰色001.jpg');
INSERT INTO `tbl_tutor` VALUES ('45f2f070898c4aec80cc341259c5cc3d', 'yufengyan', 'bb9857b7d1584ff1aa548c7166bad85e', 'kc86', '遇风烟', '男', '信息学院', '', NULL);
INSERT INTO `tbl_tutor` VALUES ('809b64f025694e0da1e57569cd6af3ed', 'zhouchangming', 'a68216333a3eb5c36f8ea4678fe313cb', 'wJj[', '走长命', '男', '信息学院', '', NULL);
INSERT INTO `tbl_tutor` VALUES ('879d4d9a81da48558251e32b909c24aa', 'test123', '1e98956a2f5168749da0daac98d1c8ab', 'EAD~', '测试', '女', '理学院', '', NULL);
INSERT INTO `tbl_tutor` VALUES ('a830e17d0e1f43ffb2f7627efc00bc2d', 'qinyi123', '7a9694cd663a37042e9df1a024471019', '6/*[', '秦怡', '男', '信息学院', '', NULL);
INSERT INTO `tbl_tutor` VALUES ('c44ab7c3c00d44b79449fc1f4e012b06', 'helin123', 'd75b8b9316979ecc46123ec629c371df', 'CwJA', '和林', '男', '信息学院', '', NULL);
INSERT INTO `tbl_tutor` VALUES ('ce67541a239c4994b6ad4da7b1ea3d70', 'wangli', '1e750952ac93a7a5e45f9deb17d9427a', 'I)@E', '王立', '男', '信息学院', '', NULL);

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `student_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tutor_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `admin_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id` ASC) USING BTREE,
  INDEX `tutor_id`(`tutor_id` ASC) USING BTREE,
  INDEX `admin_id`(`admin_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('0323d729d303445c899ecd8808c25bb5', 'e853c3dc4e194fd496362811c7e78f20', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('157a79b5136b4b6ba9cb08580cf3e3a3', '7c2a8d97dfb14ee6a93241824de07103', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('22b35c376e22430fa8d8e4ea04bf7b46', '688659fbf6ce4dcdace21ac832a125b5', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('23cf0d200f6d4f9e85ef80e6ad568b8f', NULL, '45f2f070898c4aec80cc341259c5cc3d', NULL);
INSERT INTO `tbl_user` VALUES ('310055ccf744440e93db35295a73a2f2', '196e74d1927248f2959ff94b2bfe9460', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('3f0ecf299c51480ebf57438967a4c0db', NULL, 'ce67541a239c4994b6ad4da7b1ea3d70', NULL);
INSERT INTO `tbl_user` VALUES ('40f6408fe22c4646a00007ee03e9ecb1', '55aa003f786941d0ba34e99b18bc1f43', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('53a713f50a58733ff6dad26988a68602', NULL, NULL, '53a713f50a58733ff6dad26988a68601');
INSERT INTO `tbl_user` VALUES ('55cff93349d944e3a9a762309276b645', NULL, '809b64f025694e0da1e57569cd6af3ed', NULL);
INSERT INTO `tbl_user` VALUES ('6f3ef3df30a24f35890da1c4b3bc0664', '6209f762eb174afabbda96369faab1e6', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('72a4d62d6c1c41caa8e63d67dd65d1db', '4e80fd1feda14d84921cc9fe285b657a', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('73b1c9e8d95c43de93ca061b94ba9c38', '5c18861eb99649b4874b548789df9d95', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('8a0f064676204840943c0804b3fa4b81', 'd457cbd966ef4645a5ae8a717a2a62cb', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('aae991528e554ae1a58e5da163540865', NULL, 'c44ab7c3c00d44b79449fc1f4e012b06', NULL);
INSERT INTO `tbl_user` VALUES ('abb61ca29f7b4a529d26cacf1f2962dc', NULL, '879d4d9a81da48558251e32b909c24aa', NULL);
INSERT INTO `tbl_user` VALUES ('b093125667c74c2d976e27f300fca758', 'f82f4f316d634b9aaa211e762fb37171', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('b17d53f4bd1349caa088dcb7662bd0a1', '39145ae2cc0c43e18388fbfe3e67e73f', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('c80569dca91d4c779aaed248a5062c16', 'f8bdf33ec5da4f088b2e33243b202ec9', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('d9cf579d7d8646dd9227bc4a4b791fe0', 'cee5821536d14bb19b2537bb1dd5c68f', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('dc2c72f57fec41f99e4891adc672c740', 'c82cd17a9c004ed790cfba7715c4feb4', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('e0838e09b1404474ad100b9c69631685', 'f398a670a1904128aba765315949fea0', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('e200813253e74a72a46885f1c4bb5d59', NULL, '3d67ef0b2187417cbab9f2e985220f5d', NULL);
INSERT INTO `tbl_user` VALUES ('e7543c53116f492082e74f9bd683b82c', NULL, '21b51dd611d24feb9118a6db2038cc04', NULL);
INSERT INTO `tbl_user` VALUES ('efc7e7976b664466a55755596076b28a', '2e0871881b5b418aa49a7068276d658a', NULL, NULL);
INSERT INTO `tbl_user` VALUES ('f2e5081f0ce84cc680d0b1f0fcb94f21', NULL, 'a830e17d0e1f43ffb2f7627efc00bc2d', NULL);
INSERT INTO `tbl_user` VALUES ('f9c3a7b1cf84437fb5ec88f8c30b2ddd', 'c45e7461bbe54fff97faf004a045ac0f', NULL, NULL);

-- ----------------------------
-- Table structure for tbl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_role`;
CREATE TABLE `tbl_user_role`  (
  `role_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_user_role
-- ----------------------------
INSERT INTO `tbl_user_role` VALUES ('2', '55cff93349d944e3a9a762309276b645', '154bf87458de4382aeb26b08dd6bc2a3');
INSERT INTO `tbl_user_role` VALUES ('1', '310055ccf744440e93db35295a73a2f2', '207547800d8e465c95664906a6087b13');
INSERT INTO `tbl_user_role` VALUES ('1', '22b35c376e22430fa8d8e4ea04bf7b46', '259c541d1d9042e785c49343093085eb');
INSERT INTO `tbl_user_role` VALUES ('1', 'c80569dca91d4c779aaed248a5062c16', '2fb74c3e640349e38651054d3a0ee627');
INSERT INTO `tbl_user_role` VALUES ('1', 'efc7e7976b664466a55755596076b28a', '34be72c68e194feabebe5c14a9e91e3b');
INSERT INTO `tbl_user_role` VALUES ('1', '8a0f064676204840943c0804b3fa4b81', '3df650fc39b44150a90c4520407f5cdb');
INSERT INTO `tbl_user_role` VALUES ('2', 'e7543c53116f492082e74f9bd683b82c', '4ad7488aa23c47d2b256be9d24a5b48b');
INSERT INTO `tbl_user_role` VALUES ('1', '157a79b5136b4b6ba9cb08580cf3e3a3', '519efe7f94ab4e48a07fc6abc84e5f7d');
INSERT INTO `tbl_user_role` VALUES ('3', '53a713f50a58733ff6dad26988a68602', '53a713f50a58733ff6dad26988a68603');
INSERT INTO `tbl_user_role` VALUES ('2', 'e200813253e74a72a46885f1c4bb5d59', '5be7065c2d5a4980a91e2e9f70f2f064');
INSERT INTO `tbl_user_role` VALUES ('2', '3f0ecf299c51480ebf57438967a4c0db', '6d21268f38a24afa8d4d81c1575dd67b');
INSERT INTO `tbl_user_role` VALUES ('1', 'e0838e09b1404474ad100b9c69631685', '799c1ace3dfd4067bce2801c4ffc3d97');
INSERT INTO `tbl_user_role` VALUES ('1', '0323d729d303445c899ecd8808c25bb5', '86f6adedc9fb407da681e4ecb638c1f9');
INSERT INTO `tbl_user_role` VALUES ('2', 'f2e5081f0ce84cc680d0b1f0fcb94f21', '8f7c2a337a6a4b0584822def1a2e9182');
INSERT INTO `tbl_user_role` VALUES ('2', 'abb61ca29f7b4a529d26cacf1f2962dc', '92da84b4175e400baaf7e7f37fff48b6');
INSERT INTO `tbl_user_role` VALUES ('1', '72a4d62d6c1c41caa8e63d67dd65d1db', '93422aa950aa432e93605b0e0e5f31a1');
INSERT INTO `tbl_user_role` VALUES ('2', 'aae991528e554ae1a58e5da163540865', '950e433ae7dd439a9d511f622da8865a');
INSERT INTO `tbl_user_role` VALUES ('1', 'f9c3a7b1cf84437fb5ec88f8c30b2ddd', 'ac63239b5f67441f9ad3ee175b932535');
INSERT INTO `tbl_user_role` VALUES ('1', '40f6408fe22c4646a00007ee03e9ecb1', 'ba85f8729dac4480a18d27dbb35ea653');
INSERT INTO `tbl_user_role` VALUES ('1', 'b093125667c74c2d976e27f300fca758', 'c4b01af892e84478a030a541646f6066');
INSERT INTO `tbl_user_role` VALUES ('1', '73b1c9e8d95c43de93ca061b94ba9c38', 'daaae093166144f1bc7633d8ed953221');
INSERT INTO `tbl_user_role` VALUES ('1', 'b17d53f4bd1349caa088dcb7662bd0a1', 'dedeeacfda8443ab956d3e31e7430a0b');
INSERT INTO `tbl_user_role` VALUES ('1', 'dc2c72f57fec41f99e4891adc672c740', 'e837cd114a7841248ecbccde0609bfd4');
INSERT INTO `tbl_user_role` VALUES ('2', '23cf0d200f6d4f9e85ef80e6ad568b8f', 'ee9d7d25113141649b06b5838acb1bcd');
INSERT INTO `tbl_user_role` VALUES ('1', '6f3ef3df30a24f35890da1c4b3bc0664', 'fe93af8f210b48669934b2b9ffcc3afc');

SET FOREIGN_KEY_CHECKS = 1;
