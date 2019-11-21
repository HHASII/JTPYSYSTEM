/*
 Navicat Premium Data Transfer

 Source Server         : HASI
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : jtpsystem

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 21/11/2019 14:34:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for EmpMsg
-- ----------------------------
DROP TABLE IF EXISTS `EmpMsg`;
CREATE TABLE `EmpMsg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empMsg` varchar(50) DEFAULT NULL COMMENT '消息内容）',
  `createTime` datetime DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4809 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of EmpMsg
-- ----------------------------
BEGIN;
INSERT INTO `EmpMsg` VALUES (4806, '李彤 提交了本月学习申报', '2019-10-15 15:18:22', '0');
INSERT INTO `EmpMsg` VALUES (4807, '李彤添加了认证申报', '2019-10-15 16:18:59', '0');
INSERT INTO `EmpMsg` VALUES (4808, '刘少雄 提交了本月学习申报', '2019-10-20 01:03:03', '0');
COMMIT;

-- ----------------------------
-- Table structure for VerfiCode
-- ----------------------------
DROP TABLE IF EXISTS `VerfiCode`;
CREATE TABLE `VerfiCode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `staffUsername` varchar(50) DEFAULT NULL COMMENT '账号',
  `Verficode` bigint(20) DEFAULT NULL COMMENT '验证码',
  `createTime` datetime DEFAULT NULL,
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of VerfiCode
-- ----------------------------
BEGIN;
INSERT INTO `VerfiCode` VALUES (2, 'lit', 962530, '2019-10-15 11:18:08', '0');
INSERT INTO `VerfiCode` VALUES (3, 'lit', 618061, '2019-10-15 11:20:04', '0');
INSERT INTO `VerfiCode` VALUES (4, 'lit', 784143, '2019-10-15 11:20:43', '0');
INSERT INTO `VerfiCode` VALUES (5, NULL, 729133, '2019-10-19 21:15:58', '0');
INSERT INTO `VerfiCode` VALUES (6, NULL, 382475, '2019-10-19 21:16:38', '0');
INSERT INTO `VerfiCode` VALUES (7, NULL, 445613, '2019-10-19 21:16:43', '0');
INSERT INTO `VerfiCode` VALUES (8, 'wc', 621030, '2019-10-20 01:15:07', '0');
INSERT INTO `VerfiCode` VALUES (9, 'wc', 266332, '2019-10-20 01:16:34', '0');
INSERT INTO `VerfiCode` VALUES (10, NULL, 721893, '2019-10-20 20:14:27', '0');
INSERT INTO `VerfiCode` VALUES (11, NULL, 404829, '2019-10-20 20:14:34', '0');
INSERT INTO `VerfiCode` VALUES (12, NULL, 442779, '2019-10-20 20:14:35', '0');
INSERT INTO `VerfiCode` VALUES (13, NULL, 226769, '2019-10-20 20:14:35', '0');
INSERT INTO `VerfiCode` VALUES (14, NULL, 149906, '2019-10-20 20:14:36', '0');
INSERT INTO `VerfiCode` VALUES (15, NULL, 563998, '2019-10-20 20:14:36', '0');
COMMIT;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '工号',
  `chineseName` varchar(50) DEFAULT NULL COMMENT '员工中文姓名',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `grade` varchar(50) DEFAULT NULL COMMENT '员工级别（从指定列表中选择）',
  `hireDate` date DEFAULT NULL COMMENT '入职日期',
  `department` varchar(50) DEFAULT NULL COMMENT '部门（从指定列表中选择）',
  `legalEntity` varchar(50) DEFAULT NULL COMMENT 'UNKNOWN',
  `workingLocation` varchar(50) DEFAULT NULL COMMENT '工作地点',
  `teachingStartDate` date DEFAULT NULL COMMENT '培训开始日期',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `school` varchar(100) DEFAULT NULL COMMENT '毕业院校',
  `major` varchar(100) DEFAULT NULL COMMENT '所属专业',
  `degree` varchar(50) DEFAULT NULL COMMENT '学历',
  `graduationYear` varchar(10) DEFAULT NULL COMMENT '毕业年份',
  `cst` varchar(50) DEFAULT NULL COMMENT 'CST（从指定列表中选择）',
  `subCst` varchar(50) DEFAULT NULL COMMENT 'SubCST（从指定列表中选择）',
  `skillGroup` varchar(50) DEFAULT NULL,
  `skillSets` varchar(50) DEFAULT NULL,
  `leaveDate` date DEFAULT NULL COMMENT '离职时间',
  `terminationType` varchar(100) DEFAULT NULL COMMENT '离职类型',
  `terminationReason` varchar(255) DEFAULT NULL COMMENT '离职理由',
  `exitComments` varchar(255) DEFAULT NULL COMMENT 'Exit interview comments',
  `leavingAccount` varchar(100) DEFAULT NULL COMMENT 'Leaving Account',
  `formerId` varchar(20) DEFAULT NULL COMMENT '曾用员工ID',
  `birthday` date DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `createUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `createTime` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updateUser` bigint(20) DEFAULT NULL COMMENT '记录更新人',
  `updateTime` datetime DEFAULT NULL COMMENT '记录更新时间',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `workID_key` (`empId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
BEGIN;
INSERT INTO `employee` VALUES (1, 10007, '哈斯', 'Male', 'X1', '2019-07-18', 'North', 'Capgemini', 'Kunshan', '2018-11-14', '15189827779', '2055243497@qq.com', '内蒙古科技大学包头师范学院', '物联网工程', '本科', '2019', 'CST PMO', 'CST PMO', 'SG', 'SS', '2019-07-29', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30100', '1999-03-29', 1, '2019-08-01 05:35:43', 1, '2019-10-21 11:27:49', '0');
INSERT INTO `employee` VALUES (2, 19003, '袁欢', 'Female', 'X1', '2019-01-28', 'RDC', 'Capgemini', 'Shanghai', '2019-02-14', '13241231222', '4112412412@qq.com', '长春工业大学', '软件工程', '本科', '2020', 'CST PMO', 'CST PMO', NULL, NULL, '2019-07-29', 'Involuntary', 'Unsatisfactory Performance', NULL, NULL, '30123', '1998-07-12', 1, '2019-08-01 02:50:49', 1, '2019-08-01 02:50:49', '0');
INSERT INTO `employee` VALUES (3, 31888, '袁欢', 'Male', 'X1', '2019-07-22', 'East', 'Capgemini', 'Kunshan', '2019-07-22', '18846436562', 'tong.a.li@capgemini.com', '南京信息工程学院', '计算机', '本科', '2020', 'CST ERP', 'CST ERP', 'SG', 'SS', '2019-07-24', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30831', '1998-05-10', 1, '2019-08-01 02:50:49', 1, '2019-08-01 02:50:49', '0');
INSERT INTO `employee` VALUES (4, 10009, '刘少雄', 'Male', 'C2', '2019-07-09', 'East', 'Capgemini', 'Kunshan', '2019-07-18', '14568676767', '2055243497@qq.com', '内蒙古科技大学包头师范学院', '计算机', '本科', '2020', 'CST Cloud', 'Cyber Security', 'SG', 'SS', NULL, NULL, NULL, NULL, NULL, NULL, '1998-08-03', 1, '2019-08-01 02:50:49', 1, '2019-08-01 02:50:49', '0');
INSERT INTO `employee` VALUES (5, 10010, '富磊', 'Female', 'X1', '2019-01-28', 'RDC', 'Capgemini', 'Shanghai', '2019-02-14', '13241231222', '4112412412@qq.com', '长春工业大学', '软件工程', '本科', '2020', 'CST ERP', 'CST ERP', NULL, NULL, '2019-07-29', 'Involuntary', 'Unsatisfactory Performance', NULL, NULL, '30123', '1998-07-12', 1, '2019-08-01 09:49:27', 1, '2019-08-01 02:50:45', '1');
INSERT INTO `employee` VALUES (7, 19002, '张卓飞', 'Male', 'X2', '2018-07-06', 'North', 'Capgemini', 'Kunshan', '2018-12-10', '18846436560', '2055243497@qq.com', '黑龙江大学', '国贸', '硕士', '2019', 'CST ERP', 'CST ERP', 'SG', 'SS', '2019-07-22', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30111', '1990-02-02', 1, '2019-08-01 04:43:31', 1, '2019-08-02 17:26:31', '1');
INSERT INTO `employee` VALUES (8, 33421, '李云飞', 'Male', 'C1', '2019-07-08', 'East', 'Capgemini', 'Shanghai', '2019-07-01', '13827642387', '29184@qq.com', '清华大学', '计算机', '本科', '2020', 'CST SFDC', 'CST SFDC', 'SG', 'SS', NULL, NULL, NULL, NULL, NULL, NULL, '1998-03-11', 1, '2019-08-01 09:49:27', 1, '2019-08-01 02:50:45', '1');
INSERT INTO `employee` VALUES (9, 31111, '黄飞鸿', 'Male', 'C1', '2019-07-06', 'East', 'Capgemini', 'Kunshan', '2019-07-13', '13827642387', '29184@qq.com', '南昌航空大学', '软件工程', '本科', '2020', 'CST SFDC', 'CST SFDC', 'SG', 'SS', NULL, NULL, NULL, NULL, NULL, NULL, '1998-12-22', 1, '2019-08-01 09:49:27', 1, '2019-08-01 02:50:45', '1');
INSERT INTO `employee` VALUES (10, 33125, '陈美丹', 'Male', 'B1', '2019-07-05', 'North', 'Capgemini', 'Shanghai', '2019-06-28', '13827642387', '29184@qq.com', '北京大学', '软件工程', '硕士', '2012', 'CST SFDC', 'CST SFDC', 'SG', 'SS', NULL, NULL, NULL, NULL, NULL, NULL, '1989-01-08', 1, '2019-08-01 09:49:27', 1, '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES (11, 32356, '陆抒龄', 'Female', 'C1', '2019-07-02', 'East', 'Capgemini', 'Shanghai', '2019-06-25', '13827642387', '29184@qq.com', '大连名族学校', '软件工程', '本科', '2012', 'CST SFDC', 'CST SFDC', 'SG', 'SS', NULL, NULL, NULL, NULL, NULL, NULL, '1989-09-28', 1, '2019-08-01 09:49:27', 1, '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES (12, 15154, '钱长江', 'Male', 'C2', '2019-07-06', 'East', 'Capgemini', 'Shanghai', '2019-06-26', '15629839222', 'asfiug@163.com', '长春工业大学', '计算机', '博士', '2011', 'CST CSD', 'CST CSD', 'SG', 'SS', '2019-06-19', 'Voluntary', 'Others', '', 'LA', '30090', '1969-12-27', 1, '2019-08-01 09:49:27', 1, '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES (13, 31410, '付丽耀', 'Female', 'C1', '2019-07-05', 'East', 'Capgemini', 'Guangzhou', '2019-06-28', '13827642387', '29184@qq.com', '辽宁大学', '计算机', '本科', '2020', 'CST SFDC', 'CST SFDC', 'SG', 'SS', NULL, NULL, NULL, NULL, NULL, NULL, '1998-04-05', 1, '2019-08-01 09:49:27', 1, '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES (14, 32330, '王伟', 'Male', 'C2', '2019-07-04', 'East', 'Capgemini', 'Shanghai', '2019-06-24', '15629839222', 'asfiug@163.com', '长春工业大学', '计算机', '本科', '2020', 'CST CSD', 'CST CSD', 'SG', 'SS', '2019-06-26', 'Voluntary', 'Others', '', '', '30012', '1999-06-27', 1, '2019-08-01 09:49:27', 1, '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES (15, 31211, '杜晶', 'Male', 'C1', '2019-07-02', 'East', 'capgemini', 'Beijing', '2019-06-25', '13827642387', '2055243497@qq.com', '辽宁大学', '数学', '博士', '2013', 'CST SFDC', 'CST SFDC', 'SG', 'SS', NULL, NULL, NULL, NULL, NULL, NULL, '1990-05-30', 1, '2019-08-01 09:49:27', 1, '2019-10-20 20:11:17', '0');
INSERT INTO `employee` VALUES (16, 32112, '华斌', 'Male', 'C2', '2019-07-02', 'East', 'Capgemini', 'Shanghai', '2019-06-22', '15629839222', 'asfiug@163.com', '长春工业大学', '计算机', '博士', '2011', 'CST CSD', 'CST CSD', 'SG', 'SS', '2019-07-28', 'Voluntary', 'Others', '', '', '30023', '1989-02-26', 1, '2019-08-01 09:49:26', 1, '2019-08-01 02:50:41', '0');
INSERT INTO `employee` VALUES (17, 32222, '王超', 'Male', 'C1', '2019-07-03', 'East', 'Capgemini', 'Shanghai', '2019-06-26', '13827642387', '2055243497@qq.com', '辽宁大学', '数学', '博士', '2011', 'CST SFDC', 'CST SFDC', 'SG', 'SS', NULL, NULL, NULL, NULL, NULL, NULL, '1990-04-05', 1, '2019-08-01 09:49:26', 1, '2019-10-19 21:04:04', '0');
INSERT INTO `employee` VALUES (18, 30171, '孙志刚', 'Male', 'X1', '2019-07-18', 'North', 'Capgemini', 'Kunshan', '2018-11-14', '15189827779', 'kept0611@163.com', '南京信息工程大学', 'IOT', '本科', '2019', 'CST PMO', 'CST PMO\r\n', 'SG', 'SS', '2019-07-29', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30100', '1999-03-29', 1, '2019-08-01 09:49:26', 1, '2019-08-01 02:50:41', '0');
INSERT INTO `employee` VALUES (19, 30231, '李彤', 'Male', 'X1', '2018-07-10', 'East', 'Capgemini', 'Kunshan', '2018-12-14', '18846436562', '2055243497@qq.com', '清华大学', '国贸', '本科', '2019', 'CST PMO', 'CST PMO', 'SG', 'SS', '2019-07-26', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30111', '1990-02-06', 1, '2019-08-01 09:49:26', 1, '2019-08-01 02:50:41', '0');
COMMIT;

-- ----------------------------
-- Table structure for employee_prize
-- ----------------------------
DROP TABLE IF EXISTS `employee_prize`;
CREATE TABLE `employee_prize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '工号',
  `prizeType` varchar(20) DEFAULT '' COMMENT '获奖类别',
  `prizeDetail` varchar(100) DEFAULT NULL COMMENT '得奖内容',
  `createUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `createTime` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updateUser` bigint(20) DEFAULT NULL COMMENT '记录更新人',
  `updateTime` datetime DEFAULT NULL COMMENT '记录更新时间',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `empId` (`empId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_prize
-- ----------------------------
BEGIN;
INSERT INTO `employee_prize` VALUES (125, 10007, '雏鹰之星', '本次培训雏鹰之星', 1, '2019-10-15 16:22:27', 1, '2019-10-15 16:22:27', '0');
INSERT INTO `employee_prize` VALUES (126, 30231, 'Project Angel', 'Angel for Project and Create a new group', 1, '2019-10-15 16:23:41', 1, '2019-10-15 16:23:41', '0');
INSERT INTO `employee_prize` VALUES (127, 10009, '潜力派学员', '最具潜力成员', 1, '2019-10-15 16:49:22', 1, '2019-10-15 16:49:22', '0');
COMMIT;

-- ----------------------------
-- Table structure for employeeteamtransfer
-- ----------------------------
DROP TABLE IF EXISTS `employeeteamtransfer`;
CREATE TABLE `employeeteamtransfer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '工号',
  `chineseName` varchar(50) DEFAULT NULL COMMENT '员工中文姓名',
  `nowCst` varchar(50) DEFAULT NULL COMMENT 'CST（从指定列表中选择）',
  `nowSubCst` varchar(50) DEFAULT NULL COMMENT 'SubCST（从指定列表中选择）',
  `newCst` varchar(50) DEFAULT NULL COMMENT 'CST（从指定列表中选择）',
  `newSubCst` varchar(50) DEFAULT NULL COMMENT 'SubCST（从指定列表中选择）',
  `createUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `createTime` date DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `workID_key` (`empId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4690 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeeteamtransfer
-- ----------------------------
BEGIN;
INSERT INTO `employeeteamtransfer` VALUES (4681, 121, '1231', '1231', '312', '231', '312', 1, '2018-12-14', '0');
INSERT INTO `employeeteamtransfer` VALUES (4682, 0, 'string', 'string', 'string', 'string', 'string', 1, NULL, '0');
INSERT INTO `employeeteamtransfer` VALUES (4683, 0, 'string', 'string', 'string', 'string', 'string', 1, '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES (4684, 0, 'st2121g', 'string', 'string', 'string', 'string', 1, '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES (4685, 0, 'string', 'string', 'string', 'string', 'string', 1, '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES (4686, 0, 'lqx', 'string', 'string', 'string', 'string', 1, '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES (4687, 0, 'string', 'string', 'string', 'string', 'string', 1, '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES (4688, 0, 'string', 'string', 'string', 'string', 'string', 1, '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES (4689, 0, '李齐宣', 'string', 'string', 'string', 'string', 1, '2019-08-20', '0');
COMMIT;

-- ----------------------------
-- Table structure for growing_experience
-- ----------------------------
DROP TABLE IF EXISTS `growing_experience`;
CREATE TABLE `growing_experience` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '工号',
  `growingType` varchar(50) DEFAULT NULL COMMENT '培训类别',
  `growingDetail` text COMMENT '培训经历详细信息',
  `createUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `createTime` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updateUser` bigint(20) DEFAULT NULL COMMENT '记录更新人',
  `updateTime` datetime DEFAULT NULL COMMENT '记录更新时间',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `empId` (`empId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growing_experience
-- ----------------------------
BEGIN;
INSERT INTO `growing_experience` VALUES (37, 30231, '技术培训', '有关开发的技术', 1, '2019-07-31 18:40:42', 30231, '2019-08-29 17:04:41', '0');
INSERT INTO `growing_experience` VALUES (61, 30231, 'VUE', '需要学习前端技术', 30231, '2019-08-29 09:29:17', 30231, '2019-08-29 09:29:17', '0');
INSERT INTO `growing_experience` VALUES (62, 30231, 'JAVA', 'JAVAEE', 30231, '2019-08-29 17:42:14', 30231, '2019-08-29 17:42:14', '0');
INSERT INTO `growing_experience` VALUES (63, 30231, 'C++', 'c++', 30231, '2019-08-29 21:17:35', 30231, '2019-08-29 21:17:35', '0');
INSERT INTO `growing_experience` VALUES (76, 30231, 'SpringSecurity', '新的知识点学习', 30231, '2019-10-15 15:04:40', 30231, '2019-10-15 15:04:40', '0');
INSERT INTO `growing_experience` VALUES (81, 30231, '11', '1', 30231, '2019-10-15 16:18:59', 30231, '2019-10-15 16:18:59', '0');
COMMIT;

-- ----------------------------
-- Table structure for headmaster_talk
-- ----------------------------
DROP TABLE IF EXISTS `headmaster_talk`;
CREATE TABLE `headmaster_talk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '工号',
  `talkType` varchar(50) DEFAULT NULL COMMENT '谈话类型',
  `talkContent` text COMMENT '谈话内容',
  `headMaster` varchar(50) DEFAULT NULL COMMENT '谈话校长',
  `createUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `createTime` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updateUser` bigint(20) DEFAULT NULL COMMENT '记录更新人',
  `updateTime` datetime DEFAULT NULL COMMENT '记录更新时间',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `empId` (`empId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of headmaster_talk
-- ----------------------------
BEGIN;
INSERT INTO `headmaster_talk` VALUES (42, 30231, '公司活动', '中秋节目安排', 'yolanda', 1, '2019-08-02 17:23:22', 1, '2019-08-02 17:25:51', '0');
COMMIT;

-- ----------------------------
-- Table structure for hr
-- ----------------------------
DROP TABLE IF EXISTS `hr`;
CREATE TABLE `hr` (
  `id` int(11) NOT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) DEFAULT '1',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `UnreadMsg` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hr
-- ----------------------------
BEGIN;
INSERT INTO `hr` VALUES (1, '哈斯', '15995659794', NULL, '内蒙古呼和浩特', 1, 'admin', '$2a$10$9Pzc26jx024CIhhr/hE3Gex8sNmuvFRTKrmxfSPAI608L0CtAlEx2', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1012507464,1777027127&fm=26&gp=0.jpg', '', NULL, NULL);
INSERT INTO `hr` VALUES (30231, '李彤', '15995654565', NULL, '内蒙古呼和浩特', 1, 'lit', '$2a$10$eg/hT5e5/9Na521OANvev.7P0msgg7WYY9.7LjyEofzu5JadcK3KO', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=512486046,3334277539&fm=26&gp=0.jpg', NULL, NULL, NULL);
INSERT INTO `hr` VALUES (10009, '刘少雄', NULL, NULL, NULL, 1, 'lsx', '$2a$10$EfaYT7KjUqa3HpcbzZC8vuVkCpqqcULpWXaZp9UjQDOuP5v/xxXiG', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=649527891,1397916839&fm=26&gp=0.jpg', NULL, NULL, NULL);
INSERT INTO `hr` VALUES (32222, '王超', NULL, NULL, NULL, 1, 'wc', '$2a$10$uZ5cgbY0f6vAh4YfCUeFQ.SuLKjITRdAB02YgiJoOPKrIz5fd1ZN6', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for hr_msg
-- ----------------------------
DROP TABLE IF EXISTS `hr_msg`;
CREATE TABLE `hr_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `msgId` bigint(20) DEFAULT NULL COMMENT '消息ID',
  `hrId` bigint(20) DEFAULT NULL COMMENT 'HRid',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4716 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hr_msg
-- ----------------------------
BEGIN;
INSERT INTO `hr_msg` VALUES (4713, 4805, 1, '0');
INSERT INTO `hr_msg` VALUES (4714, 4800, 1, '0');
INSERT INTO `hr_msg` VALUES (4715, 4799, 1, '0');
COMMIT;

-- ----------------------------
-- Table structure for hr_role
-- ----------------------------
DROP TABLE IF EXISTS `hr_role`;
CREATE TABLE `hr_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hrid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  KEY `hr_role_ibfk_1` (`hrid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hr_role
-- ----------------------------
BEGIN;
INSERT INTO `hr_role` VALUES (1, 1, 2);
INSERT INTO `hr_role` VALUES (2, 30231, 1);
INSERT INTO `hr_role` VALUES (72, 10009, 1);
INSERT INTO `hr_role` VALUES (73, 32222, 1);
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parentId` (`parentId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, '/', NULL, NULL, '所有', NULL, NULL, NULL, NULL, 1);
INSERT INTO `menu` VALUES (2, '/', '/home', 'Home', '员工资料', 'fa fa-user-circle-o', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (3, '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (4, '/', '/home', 'Home', '薪资管理', 'fa fa-money', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (5, '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (6, '/', '/home', 'Home', '系统管理', 'fa fa-windows', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (7, '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', 'fa fa-id-card', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (8, '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', 'fa fa-id-card', NULL, 1, 2, 0);
INSERT INTO `menu` VALUES (9, '/employee/prize/**', '/emp/prize', 'EmpPrize', '获奖情况', 'fa fa-list-alt', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (10, '/employee/experience/**', '/emp/learning', 'EmpLearning', '培训经历', 'fa fa-window-restore', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (11, '/employee/project/**', '/emp/project', 'EmpProject', '项目经历', 'fa fa-superpowers', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (12, '/employee/talk/**', '/system/talking', 'EmpTalking', '校长访谈', 'fa fa-coffee', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (13, '/employee/team/**', '/emp/teamtsf', 'EmpTeamtsf', '团队变更', 'fa fa-users', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (14, '/employee/teamtsf/**', '/emp/EmpStudyDecoration', 'EmpStudyDecoration', '学习申报', 'fa fa-graduation-cap', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (15, '/', '/home', 'home', '用户相关', 'fa fa-user-circle-o', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (16, '/staff/baseInfo/**', './baseInfo', 'baseInfo', '个人资料', 'fa fa-id-card', NULL, 1, 15, 1);
INSERT INTO `menu` VALUES (17, '/staff/declaration/**', './declaration', 'declaration', '学习申报', 'fa fa-graduation-cap', NULL, 1, 15, 1);
INSERT INTO `menu` VALUES (18, '/employee/admin/**', '/emp/admin', 'EmpAdmin', '用户管理', 'fa fa-user', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (19, '/staff/certificateDecoration/**', './certificateDecoration', 'certificateDecoration', '认证申报', 'fa fa-hand-o-right', NULL, 1, 15, 1);
INSERT INTO `menu` VALUES (21, '/employee/EmpMsg/**', '/EmpMsg', 'EmpMsg', '消息通知', 'fa fa-commenting', NULL, 1, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `mid` (`mid`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
BEGIN;
INSERT INTO `menu_role` VALUES (278, 1, 2);
INSERT INTO `menu_role` VALUES (279, 2, 2);
INSERT INTO `menu_role` VALUES (280, 3, 2);
INSERT INTO `menu_role` VALUES (281, 4, 2);
INSERT INTO `menu_role` VALUES (282, 5, 2);
INSERT INTO `menu_role` VALUES (283, 6, 2);
INSERT INTO `menu_role` VALUES (284, 7, 2);
INSERT INTO `menu_role` VALUES (285, 8, 2);
INSERT INTO `menu_role` VALUES (286, 9, 2);
INSERT INTO `menu_role` VALUES (287, 10, 2);
INSERT INTO `menu_role` VALUES (288, 11, 2);
INSERT INTO `menu_role` VALUES (289, 12, 2);
INSERT INTO `menu_role` VALUES (290, 13, 2);
INSERT INTO `menu_role` VALUES (305, 14, 2);
INSERT INTO `menu_role` VALUES (306, 15, 1);
INSERT INTO `menu_role` VALUES (307, 16, 1);
INSERT INTO `menu_role` VALUES (308, 17, 1);
INSERT INTO `menu_role` VALUES (310, 19, 1);
INSERT INTO `menu_role` VALUES (311, 20, 2);
INSERT INTO `menu_role` VALUES (312, 21, 2);
INSERT INTO `menu_role` VALUES (317, 18, 2);
COMMIT;

-- ----------------------------
-- Table structure for project_experience
-- ----------------------------
DROP TABLE IF EXISTS `project_experience`;
CREATE TABLE `project_experience` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '工号',
  `account` varchar(50) DEFAULT NULL COMMENT '客户名称',
  `projectName` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `projectManager` varchar(50) DEFAULT NULL COMMENT '项目经理',
  `joinTime` date DEFAULT NULL COMMENT '加入时间',
  `endTime` date DEFAULT NULL COMMENT '结束时间',
  `createUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `createTime` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updateUser` bigint(20) DEFAULT NULL COMMENT '记录更新人',
  `updateTime` datetime DEFAULT NULL COMMENT '记录更新时间',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `empId` (`empId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_experience
-- ----------------------------
BEGIN;
INSERT INTO `project_experience` VALUES (36, 32222, '凯捷', 'JTP员工管理系统', 'LkmdD.zhao', '2019-07-13', '2019-07-30', 1, '2019-07-31 18:10:54', 1, '2019-10-02 16:01:47', '0');
INSERT INTO `project_experience` VALUES (38, 32356, '华为', '华为手机5G开发', 'yoda.zhao', '2019-07-02', '2019-07-29', 1, '2019-07-31 18:22:21', 1, '2019-10-16 01:13:42', '1');
INSERT INTO `project_experience` VALUES (39, 33125, '华为', '华为手机5G开发', 'mkgh.jin', '2019-07-05', '2019-07-29', 1, '2019-07-31 18:26:03', 1, '2019-10-16 01:13:42', '1');
INSERT INTO `project_experience` VALUES (40, 30231, '小米', '自动化', 'zhuoli,zhang', '2019-08-06', '2019-08-29', 1, '2019-08-01 09:00:30', 1, '2019-10-20 02:50:41', '0');
INSERT INTO `project_experience` VALUES (42, 32330, '华为', '华为手机P50研发', 'shaoyong.zhang ', '2019-07-03', '2019-07-30', 1, '2019-08-01 05:07:59', 1, '2019-10-16 01:13:42', '1');
INSERT INTO `project_experience` VALUES (43, 10009, '45645', '中国移动', 'yoda.zhao', '2019-10-22', '2019-10-31', 1, '2019-10-15 16:01:47', 1, '2019-10-15 16:01:47', '0');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'ROLE_staff', '员工');
INSERT INTO `role` VALUES (2, 'ROLE_admin', '管理员');
COMMIT;

-- ----------------------------
-- Table structure for studydeclaration
-- ----------------------------
DROP TABLE IF EXISTS `studydeclaration`;
CREATE TABLE `studydeclaration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '员工编号',
  `EFcslevel` varchar(50) DEFAULT NULL COMMENT 'EF初始级别',
  `EFydlevel` varchar(50) DEFAULT NULL COMMENT 'EF应答级别',
  `EFYTDlevel` varchar(50) DEFAULT NULL COMMENT 'EF YTD级别',
  `EFhours` bigint(20) DEFAULT NULL COMMENT 'EF小时数/月',
  `BCZhours` bigint(20) DEFAULT NULL COMMENT '百词斩/月',
  `Videohours` bigint(20) DEFAULT NULL COMMENT '视频打卡/月',
  `TEDtimes` bigint(20) DEFAULT NULL COMMENT 'TED参赛/月',
  `sharetimes` bigint(20) DEFAULT NULL COMMENT '学习分享会/月',
  `unqualified` bigint(20) DEFAULT NULL COMMENT '不合格项',
  `createUser` bigint(20) DEFAULT NULL COMMENT '记录更新人',
  `createTime` datetime DEFAULT NULL COMMENT '记录创建时间',
  `updateUser` bigint(20) DEFAULT NULL COMMENT '记录更新人',
  `updateTime` datetime DEFAULT NULL COMMENT '记录更新时间',
  `note` varchar(50) DEFAULT NULL COMMENT '备注',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studydeclaration
-- ----------------------------
BEGIN;
INSERT INTO `studydeclaration` VALUES (4, 30231, '2', '1', '1', 1, 1, 1, 1, 1, 1, 30231, '2019-10-13 21:18:09', 1, '2019-10-13 21:27:54', '1', '1');
INSERT INTO `studydeclaration` VALUES (5, 30231, '11', '1', '1', 1, 1, 1, 1, 1, 1, 30231, '2019-10-15 15:18:22', 30231, '2019-10-15 15:18:22', '1', '0');
INSERT INTO `studydeclaration` VALUES (6, 10009, '1', '8', '8', 1, 8, 8, 1, 8, 0, 10009, '2019-10-20 01:03:03', 10009, '2019-10-20 01:03:03', '应该加强TED的学习\n', '0');
COMMIT;

-- ----------------------------
-- Table structure for teamchangelog
-- ----------------------------
DROP TABLE IF EXISTS `teamchangelog`;
CREATE TABLE `teamchangelog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '工号',
  `nowCst` varchar(50) DEFAULT NULL COMMENT 'CST（从指定列表中选择）',
  `nowSubCst` varchar(50) DEFAULT NULL COMMENT 'SubCST（从指定列表中选择）',
  `newCst` varchar(50) DEFAULT NULL COMMENT 'CST（从指定列表中选择）',
  `newSubCst` varchar(50) DEFAULT NULL COMMENT 'SubCST（从指定列表中选择）',
  `createUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `createTime` date DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `updateUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `updateTime` date DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `effectiveDate` date DEFAULT NULL COMMENT '生效日期',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `workID_key` (`empId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teamchangelog
-- ----------------------------
BEGIN;
INSERT INTO `teamchangelog` VALUES (1, 10003, 'CST ERP', 'CST ERP', 'CST PMO', 'CST PMO', 1, '2019-10-12', 1, '2019-10-12', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for teamchangelog_copy
-- ----------------------------
DROP TABLE IF EXISTS `teamchangelog_copy`;
CREATE TABLE `teamchangelog_copy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '工号',
  `nowCst` varchar(50) DEFAULT NULL COMMENT 'CST（从指定列表中选择）',
  `nowSubCst` varchar(50) DEFAULT NULL COMMENT 'SubCST（从指定列表中选择）',
  `newCst` varchar(50) DEFAULT NULL COMMENT 'CST（从指定列表中选择）',
  `newSubCst` varchar(50) DEFAULT NULL COMMENT 'SubCST（从指定列表中选择）',
  `createUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `createTime` date DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `updateUser` bigint(20) DEFAULT NULL COMMENT '记录创建人',
  `updateTime` date DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `effectiveDate` date DEFAULT NULL COMMENT '生效日期',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `workID_key` (`empId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
