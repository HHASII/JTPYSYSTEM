/*
Navicat MySQL Data Transfer

Source Server         : HASI
Source Server Version : 50529
Source Host           : 127.0.0.1:3306
Source Database       : jtpsystem

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2019-09-30 15:42:54
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=4681 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('4645', '30231', '李彤', 'Male', 'X1', '2018-07-10', 'East', 'Capgemini', 'Kunshan', '2018-12-14', '18846436562', '746665@qq.com', '黑龙江大学', '国贸', '本科', '2019', 'CST ERP', 'CST ERP', 'SG', 'SS', '2019-07-26', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30111', '1990-02-06', '1', '2019-08-01 09:49:26', '1', '2019-08-01 02:50:41', '0');
INSERT INTO `employee` VALUES ('4646', '30171', '孙志刚', 'Male', 'X1', '2019-07-18', 'North', 'Capgemini', 'Kunshan', '2018-11-14', '15189827779', 'kept0611@163.com', '南京信息工程大学', '软件工程', '本科', '2019', 'CST PMO', 'CST PMO\r\n', 'SG', 'SS', '2019-07-29', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30100', '1999-03-29', '1', '2019-08-01 09:49:26', '1', '2019-08-01 02:50:41', '0');
INSERT INTO `employee` VALUES ('4647', '32222', '王超', 'Male', 'C1', '2019-07-03', 'East', 'Capgemini', 'Shanghai', '2019-06-26', '13827642387', '74666@qq.com', '辽宁大学', '数学', '博士', '2011', 'CST SFDC', 'CST SFDC', 'SG', 'SS', null, null, null, null, null, null, '1990-04-05', '1', '2019-08-01 09:49:26', '1', '2019-08-01 02:50:41', '0');
INSERT INTO `employee` VALUES ('4648', '32112', '华斌', 'Male', 'C2', '2019-07-02', 'East', 'Capgemini', 'Shanghai', '2019-06-22', '15629839222', 'asfiug@163.com', '长春工业大学', '计算机', '博士', '2011', 'CST CSD', 'CST CSD', 'SG', 'SS', '2019-07-28', 'Voluntary', 'Others', '', '', '30023', '1989-02-26', '1', '2019-08-01 09:49:26', '1', '2019-08-01 02:50:41', '0');
INSERT INTO `employee` VALUES ('4649', '31211', '李渡', 'Male', 'C1', '2019-07-02', 'East', 'capgemini', 'Beijing', '2019-06-25', '13827642387', '746665826@qq.com', '辽宁大学', '数学', '博士', '2013', 'CST SFDC', 'CST SFDC', 'SG', 'SS', null, null, null, null, null, null, '1990-05-31', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:41', '0');
INSERT INTO `employee` VALUES ('4650', '32330', '王伟', 'Male', 'C2', '2019-07-04', 'East', 'Capgemini', 'Shanghai', '2019-06-24', '15629839222', 'asfiug@163.com', '长春工业大学', '计算机', '本科', '2020', 'CST CSD', 'CST CSD', 'SG', 'SS', '2019-06-26', 'Voluntary', 'Others', '', '', '30012', '1999-06-27', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES ('4651', '31410', '付丽耀', 'Female', 'C1', '2019-07-05', 'East', 'Capgemini', 'Guangzhou', '2019-06-28', '13827642387', '29184@qq.com', '辽宁大学', '计算机', '本科', '2020', 'CST SFDC', 'CST SFDC', 'SG', 'SS', null, null, null, null, null, null, '1998-04-05', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES ('4652', '15154', '钱长江', 'Male', 'C2', '2019-07-06', 'East', 'Capgemini', 'Shanghai', '2019-06-26', '15629839222', 'asfiug@163.com', '长春工业大学', '计算机', '博士', '2011', 'CST CSD', 'CST CSD', 'SG', 'SS', '2019-06-19', 'Voluntary', 'Others', '', 'LA', '30090', '1969-12-27', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES ('4653', '32356', '陆抒龄', 'Female', 'C1', '2019-07-02', 'East', 'Capgemini', 'Shanghai', '2019-06-25', '13827642387', '29184@qq.com', '大连名族学校', '软件工程', '本科', '2012', 'CST SFDC', 'CST SFDC', 'SG', 'SS', null, null, null, null, null, null, '1989-09-28', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES ('4654', '33125', '陈美丹', 'Male', 'B1', '2019-07-05', 'North', 'Capgemini', 'Shanghai', '2019-06-28', '13827642387', '29184@qq.com', '北京大学', '软件工程', '硕士', '2012', 'CST SFDC', 'CST SFDC', 'SG', 'SS', null, null, null, null, null, null, '1989-01-08', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:41', '1');
INSERT INTO `employee` VALUES ('4655', '31111', '黄飞鸿', 'Male', 'C1', '2019-07-06', 'East', 'Capgemini', 'Kunshan', '2019-07-13', '13827642387', '29184@qq.com', '南昌航空大学', '软件工程', '本科', '2020', 'CST SFDC', 'CST SFDC', 'SG', 'SS', null, null, null, null, null, null, '1998-12-22', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:45', '1');
INSERT INTO `employee` VALUES ('4656', '33421', '李云飞', 'Male', 'C1', '2019-07-08', 'East', 'Capgemini', 'Shanghai', '2019-07-01', '13827642387', '29184@qq.com', '清华大学', '计算机', '本科', '2020', 'CST SFDC', 'CST SFDC', 'SG', 'SS', null, null, null, null, null, null, '1998-03-11', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:45', '1');
INSERT INTO `employee` VALUES ('4657', '34211', '张美丽', 'Female', 'C2', '2019-07-09', 'East', 'Capgemini', 'Kunshan', '2019-07-18', '14568676767', 'sad@qq.com', '北京大学', '计算机', '本科', '2020', 'CST Cloud', 'Cyber Security', 'SG', 'SS', null, null, null, null, null, null, '1998-08-03', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:45', '1');
INSERT INTO `employee` VALUES ('4659', '31003', '袁欢', 'Female', 'X1', '2019-01-28', 'RDC', 'Capgemini', 'Shanghai', '2019-02-14', '13241231222', '4112412412@qq.com', '长春工业大学', '软件工程', '本科', '2020', 'CST ERP', 'CST ERP', null, null, '2019-07-29', 'Involuntary', 'Unsatisfactory Performance', null, null, '30123', '1998-07-12', '1', '2019-08-01 09:49:27', '1', '2019-08-01 02:50:45', '1');
INSERT INTO `employee` VALUES ('4673', '34211', '张美丽', 'Female', 'C2', '2019-07-09', 'East', 'Capgemini', 'Kunshan', '2019-07-18', '14568676767', 'sad@qq.com', '北京大学', '计算机', '本科', '2020', 'CST Cloud', 'Cyber Security', 'SG', 'SS', null, null, null, null, null, null, '1998-08-03', '1', '2019-08-01 02:50:49', '1', '2019-08-01 02:50:49', '0');
INSERT INTO `employee` VALUES ('4674', '31888', '华佗', 'Male', 'X1', '2019-07-22', 'East', 'Capgemini', 'Kunshan', '2019-07-22', '18846436562', 'tong.a.li@capgemini.com', '南京信息工程学院', '计算机', '本科', '2020', 'CST ERP', 'CST ERP', 'SG', 'SS', '2019-07-24', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30831', '1998-05-10', '1', '2019-08-01 02:50:49', '1', '2019-08-01 02:50:49', '0');
INSERT INTO `employee` VALUES ('4675', '31003', '袁欢', 'Female', 'X1', '2019-01-28', 'RDC', 'Capgemini', 'Shanghai', '2019-02-14', '13241231222', '4112412412@qq.com', '长春工业大学', '软件工程', '本科', '2020', 'CST ERP', 'CST ERP', null, null, '2019-07-29', 'Involuntary', 'Unsatisfactory Performance', null, null, '30123', '1998-07-12', '1', '2019-08-01 02:50:49', '1', '2019-08-01 02:50:49', '0');
INSERT INTO `employee` VALUES ('4676', '30123', '延欢', 'Male', 'X2', '2018-07-06', 'North', 'Capgemini', 'Kunshan', '2018-12-10', '18846436560', 'tong.a.li@capgemini.com', '黑龙江大学', '国贸', '硕士', '2019', 'CST ERP', 'CST ERP', 'SG', 'SS', '2019-07-22', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30111', '1990-02-02', '1', '2019-08-01 04:43:31', '1', '2019-08-02 17:26:31', '1');
INSERT INTO `employee` VALUES ('4677', '30301', '李彤2', 'Male', 'X2', '2018-07-07', 'North', 'Capgemini', 'Kunshan', '2018-12-11', '18846436560', 'tong.a.li@capgemini.com', '黑龙江大学', '国贸', '硕士', '2019', 'CST ERP', 'CST ERP', 'SG', 'SS', '2019-07-23', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30111', '1990-02-03', '1', '2019-08-01 04:44:26', '1', '2019-08-01 05:04:33', '1');
INSERT INTO `employee` VALUES ('4678', '30302', '李彤3', 'Male', 'X2', '2018-07-07', 'North', 'Capgemini', 'Kunshan', '2018-12-11', '18846436560', '7466@qq.com', '黑龙江大学', '国贸', '硕士', '2019', 'CST ERP', 'CST ERP', 'SG', 'SS', '2019-07-23', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30111', '1990-02-03', '1', '2019-08-01 04:44:26', '1', '2019-08-01 05:04:37', '0');
INSERT INTO `employee` VALUES ('4679', '30213', '李彤1', 'Male', 'X2', '2018-07-07', 'North', 'Capgemini', 'Kunshan', '2018-12-11', '18846436560', 'tong.a.li@capgemini.com', '黑龙江大学', '国贸', '硕士', '2019', 'CST ERP', 'CST ERP', 'SG', 'SS', '2019-07-23', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30111', '1990-02-03', '1', '2019-08-01 05:35:43', '1', '2019-08-01 05:35:58', '0');
INSERT INTO `employee` VALUES ('4680', '30172', '李帅', 'Male', 'X1', '2019-07-18', 'North', 'Capgemini', 'Kunshan', '2018-11-14', '15189827779', 'tong.a.li@capgemini.com', '南京信息工程大学', '软件工程', '本科', '2019', 'CST PMO', 'CST PMO\r\n', 'SG', 'SS', '2019-07-29', 'Voluntary', 'Better Pay/Rewards', 'Thanks.', 'LA', '30100', '1999-03-29', '1', '2019-08-01 05:35:43', '1', '2019-08-01 05:35:58', '0');

-- ----------------------------
-- Table structure for employeestudydeclaration
-- ----------------------------
DROP TABLE IF EXISTS `employeestudydeclaration`;
CREATE TABLE `employeestudydeclaration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empId` bigint(20) DEFAULT NULL COMMENT '工号',
  `chineseName` varchar(50) DEFAULT NULL COMMENT '员工中文姓名',
  `EFInitialLevel` varchar(50) DEFAULT NULL COMMENT 'EF初始级别',
  `EFGoalLevel` varchar(50) DEFAULT NULL COMMENT 'EF应达级别',
  `EFYTDLevel` varchar(50) DEFAULT NULL COMMENT 'EFYTD级别',
  `EFStudyHour` varchar(50) DEFAULT NULL COMMENT 'EF小时数',
  `BCZPunch` int(20) DEFAULT NULL COMMENT '百词斩打卡数',
  `VideoPunch` int(20) DEFAULT NULL COMMENT '视频打卡数',
  `TEDParticipate` int(20) DEFAULT NULL COMMENT 'Ted参赛数',
  `StudyShare` int(20) DEFAULT NULL COMMENT '学习分享会',
  `UnqualifiedItem` varchar(50) DEFAULT NULL COMMENT '不合格项',
  `createTime` date DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `remarks` varchar(50) DEFAULT NULL COMMENT '备注',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `workID_key` (`empId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4681 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employeestudydeclaration
-- ----------------------------
INSERT INTO `employeestudydeclaration` VALUES ('1', '1234', '李齐宣', '1', '2', '3', '4', '28', '22', '21', '5', '百词斩', '2019-08-21', '无', '0');

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
INSERT INTO `employeeteamtransfer` VALUES ('4681', '121', '1231', '1231', '312', '231', '312', '1', '2018-12-14', '0');
INSERT INTO `employeeteamtransfer` VALUES ('4682', '0', 'string', 'string', 'string', 'string', 'string', '1', null, '0');
INSERT INTO `employeeteamtransfer` VALUES ('4683', '0', 'string', 'string', 'string', 'string', 'string', '1', '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES ('4684', '0', 'st2121g', 'string', 'string', 'string', 'string', '1', '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES ('4685', '0', 'string', 'string', 'string', 'string', 'string', '1', '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES ('4686', '0', 'lqx', 'string', 'string', 'string', 'string', '1', '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES ('4687', '0', 'string', 'string', 'string', 'string', 'string', '1', '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES ('4688', '0', 'string', 'string', 'string', 'string', 'string', '1', '2019-08-20', '0');
INSERT INTO `employeeteamtransfer` VALUES ('4689', '0', '李齐宣', 'string', 'string', 'string', 'string', '1', '2019-08-20', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_prize
-- ----------------------------
INSERT INTO `employee_prize` VALUES ('98', '32356', '奖学金', '积极表现，朝气蓬勃', '1', '2019-07-31 17:23:18', '1', '2019-08-29 04:09:59', '1');
INSERT INTO `employee_prize` VALUES ('99', '30171', '奖学金', '在此次培训中，个人表现十分优异', '1', '2019-07-31 17:25:28', '1', '2019-08-29 04:09:59', '1');
INSERT INTO `employee_prize` VALUES ('100', '32222', '奖学金', '表现优异', '1', '2019-07-31 17:26:24', '1', '2019-08-29 04:09:59', '1');
INSERT INTO `employee_prize` VALUES ('101', '32112', '雏鹰之星', 'xxxx', '1', '2019-07-31 17:27:53', '1', '2019-08-01 04:37:12', '1');
INSERT INTO `employee_prize` VALUES ('102', '32112', '雏鹰之星', '表现突出，个人表现优异', '1', '2019-07-31 17:28:25', '1', '2019-08-29 04:09:59', '1');
INSERT INTO `employee_prize` VALUES ('103', '32112', '奖学金', '在培训中获得了第一名', '1', '2019-07-31 17:29:00', '1', '2019-08-29 04:09:59', '1');
INSERT INTO `employee_prize` VALUES ('104', '33125', '雏鹰之星', '个人表现优异', '1', '2019-07-31 17:37:30', '1', '2019-08-29 04:09:59', '1');
INSERT INTO `employee_prize` VALUES ('105', '32356', '奖学金', '成绩优异，表现良好', '1', '2019-07-31 18:06:38', '1', '2019-08-29 04:09:59', '1');
INSERT INTO `employee_prize` VALUES ('107', '30231', '雏鹰之星', 'March 2019', '1', '2019-08-01 02:37:56', '1', '2019-08-01 02:50:41', '0');
INSERT INTO `employee_prize` VALUES ('108', '31211', '雏鹰之星', 'xxxxxxx', '1', '2019-08-01 03:03:18', '1', '2019-08-01 04:01:03', '1');
INSERT INTO `employee_prize` VALUES ('109', '30231', 'Project Angel', 'xxxx', '1', '2019-08-01 03:58:10', '1', '2019-08-01 04:00:58', '1');
INSERT INTO `employee_prize` VALUES ('110', '31211', 'Project Angel', 'xxx', '1', '2019-08-01 03:58:42', '1', '2019-08-01 04:00:55', '1');
INSERT INTO `employee_prize` VALUES ('111', '32112', '奖学金', 'xxxx', '1', '2019-08-01 03:59:41', '1', '2019-08-01 04:00:53', '1');
INSERT INTO `employee_prize` VALUES ('112', '31410', '雏鹰之星', 'xxx', '1', '2019-08-01 03:59:59', '1', '2019-08-01 04:00:52', '1');
INSERT INTO `employee_prize` VALUES ('113', '31888', '雏鹰之星', 'xxxx', '1', '2019-08-01 04:00:16', '1', '2019-08-01 04:00:48', '1');
INSERT INTO `employee_prize` VALUES ('114', '33421', 'Project Angel', 'xxxxx', '1', '2019-08-01 04:00:29', '1', '2019-08-01 04:00:42', '1');
INSERT INTO `employee_prize` VALUES ('115', '30231', '雏鹰之星', 'xxxxxxx', '1', '2019-08-01 04:31:48', '1', '2019-08-01 04:37:12', '1');
INSERT INTO `employee_prize` VALUES ('116', '30231', '雏鹰之星', 'xxxxx', '1', '2019-08-01 04:38:13', '1', '2019-08-01 04:45:23', '1');
INSERT INTO `employee_prize` VALUES ('117', '30231', 'CSR', 'xxx', '1', '2019-08-01 04:40:17', '1', '2019-08-01 04:45:23', '1');
INSERT INTO `employee_prize` VALUES ('118', '32112', 'Project Angel', 'xxxx', '1', '2019-08-01 04:45:15', '1', '2019-08-29 04:09:59', '1');
INSERT INTO `employee_prize` VALUES ('119', '30171', 'Project Angel', 'xxx', '26', '2019-08-01 04:49:19', '26', '2019-08-01 05:01:15', '1');
INSERT INTO `employee_prize` VALUES ('120', '30123', '雏鹰之星', '表现优异', '1', '2019-08-01 05:04:55', '1', '2019-08-02 17:26:31', '1');
INSERT INTO `employee_prize` VALUES ('121', '30231', '奖学金', '一等奖', '1', '2019-08-01 05:29:13', '1', '2019-08-01 05:29:29', '0');
INSERT INTO `employee_prize` VALUES ('122', '32112', '雏鹰之星', '三等奖', '27', '2019-08-01 05:32:41', '1', '2019-08-01 05:34:00', '1');
INSERT INTO `employee_prize` VALUES ('123', '34211', '雏鹰之星', '11', '1', '2019-08-20 21:03:40', '1', '2019-08-20 21:03:40', '0');
INSERT INTO `employee_prize` VALUES ('124', '34211', 'CSR', '21121', '1', '2019-08-23 20:46:55', '1', '2019-08-23 20:46:55', '0');

-- ----------------------------
-- Table structure for empmsg
-- ----------------------------
DROP TABLE IF EXISTS `empmsg`;
CREATE TABLE `empmsg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empMsg` varchar(50) DEFAULT NULL COMMENT '消息内容）',
  `createTime` datetime DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4806 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of empmsg
-- ----------------------------
INSERT INTO `empmsg` VALUES ('4799', '李彤添加了认证申报', '2019-09-03 09:55:03', '0');
INSERT INTO `empmsg` VALUES ('4800', '李彤添加了认证申报', '2019-09-03 09:57:25', '0');
INSERT INTO `empmsg` VALUES ('4801', '李彤3 提交了本月学习申报', '2019-09-04 11:14:59', '0');
INSERT INTO `empmsg` VALUES ('4802', '李彤3修改了本月学习申报', '2019-09-04 11:15:21', '0');
INSERT INTO `empmsg` VALUES ('4803', '李彤3添加了认证申报', '2019-09-04 11:15:41', '0');
INSERT INTO `empmsg` VALUES ('4804', '李彤3添加了认证申报', '2019-09-04 11:16:15', '0');
INSERT INTO `empmsg` VALUES ('4805', '李彤 提交了本月学习申报', '2019-09-04 11:22:43', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growing_experience
-- ----------------------------
INSERT INTO `growing_experience` VALUES ('37', '30231', '技术培训', '有关开发的技术', '1', '2019-07-31 18:40:42', '30231', '2019-08-29 17:04:41', '0');
INSERT INTO `growing_experience` VALUES ('38', '31111', 'PPT技能培训', '培训制作PPT的技巧', '1', '2019-07-31 18:42:47', '1', '2019-08-01 03:05:40', '1');
INSERT INTO `growing_experience` VALUES ('39', '31003', 'Excel技能训练', '培训员工使用EXCEL的技巧', '1', '2019-07-31 18:45:22', '1', '2019-08-01 02:50:45', '0');
INSERT INTO `growing_experience` VALUES ('40', '31211', '演讲培训', '培训学员演讲能力', '1', '2019-07-31 18:49:30', '1', '2019-08-28 07:31:57', '1');
INSERT INTO `growing_experience` VALUES ('43', '30231', 'JTP技术培训', '无嗡嗡嗡', '1', '2019-08-01 09:00:53', '1', '2019-08-29 04:09:29', '1');
INSERT INTO `growing_experience` VALUES ('44', '30171', 'xx', 'xx', '1', '2019-08-01 03:59:09', '1', '2019-08-01 03:59:19', '1');
INSERT INTO `growing_experience` VALUES ('45', '31410', 'PPT技能培训', '培训员工PPT技能', '1', '2019-08-01 04:26:50', '1', '2019-08-28 07:32:01', '1');
INSERT INTO `growing_experience` VALUES ('46', '30123', '111', '111', '1', '2019-08-01 05:05:55', '1', '2019-08-02 17:26:31', '1');
INSERT INTO `growing_experience` VALUES ('47', '34211', '2121', '2121', '1', '2019-08-20 22:23:51', '1', '2019-08-28 07:49:20', '1');
INSERT INTO `growing_experience` VALUES ('48', '31888', '后端培训', '学习了SpringBoot', '30231', '2019-08-26 14:20:16', '30231', '2019-08-26 14:20:16', '0');
INSERT INTO `growing_experience` VALUES ('50', '30231', 'springboot', '不菜', '1', '2019-08-26 23:00:22', '30231', '2019-08-28 08:02:53', '0');
INSERT INTO `growing_experience` VALUES ('51', '30231', 'vue', '学学vue', '30231', '2019-08-26 23:07:19', '30231', '2019-08-26 23:13:45', '1');
INSERT INTO `growing_experience` VALUES ('52', '30231', 'Meeting minutes', '共识很关键', '30231', '2019-08-26 23:36:32', '1', '2019-08-27 10:58:06', '1');
INSERT INTO `growing_experience` VALUES ('53', '30231', '敏捷方法论', '保守主义瓦达', '1', '2019-08-27 00:33:14', '1', '2019-08-28 07:49:13', '1');
INSERT INTO `growing_experience` VALUES ('54', '30231', '敏捷思维', 'bla', '30231', '2019-08-27 10:56:21', '1', '2019-08-28 07:49:31', '1');
INSERT INTO `growing_experience` VALUES ('55', '31888', '华佗', 'CST ERP', '1', '2019-08-27 19:48:52', '1', '2019-08-28 07:32:14', '1');
INSERT INTO `growing_experience` VALUES ('56', '31888', '华佗', 'CST ERP', '1', '2019-08-27 19:50:48', '1', '2019-08-28 07:32:14', '1');
INSERT INTO `growing_experience` VALUES ('57', '30231', '333', '6666', '30231', '2019-08-28 00:07:43', '1', '2019-08-28 07:49:05', '1');
INSERT INTO `growing_experience` VALUES ('58', '30231', 'BA', '好好学习', '30231', '2019-08-28 16:00:56', '1', '2019-08-29 04:09:29', '1');
INSERT INTO `growing_experience` VALUES ('59', '30231', 'VUE', '还需进步', '30231', '2019-08-28 17:54:13', '1', '2019-08-29 04:09:29', '1');
INSERT INTO `growing_experience` VALUES ('60', '30231', '威威', '达瓦达瓦', '30231', '2019-08-28 18:04:24', '1', '2019-08-29 04:09:29', '1');
INSERT INTO `growing_experience` VALUES ('61', '30231', 'VUE', '需要学习前端技术', '30231', '2019-08-29 09:29:17', '30231', '2019-08-29 09:29:17', '0');
INSERT INTO `growing_experience` VALUES ('62', '30231', 'JAVA', 'JAVAEE', '30231', '2019-08-29 17:42:14', '30231', '2019-08-29 17:42:14', '0');
INSERT INTO `growing_experience` VALUES ('63', '30231', 'C++', 'c++', '30231', '2019-08-29 21:17:35', '30231', '2019-08-29 21:17:35', '0');
INSERT INTO `growing_experience` VALUES ('64', '30231', '11', '11', '30231', '2019-08-29 22:42:52', '30231', '2019-08-29 22:42:52', '0');
INSERT INTO `growing_experience` VALUES ('65', '30231', '1', '1', '1', '2019-09-03 09:42:52', '1', '2019-09-03 09:42:52', '0');
INSERT INTO `growing_experience` VALUES ('66', '30231', '3', '3', '30231', '2019-09-03 09:47:07', '30231', '2019-09-03 09:47:07', '0');
INSERT INTO `growing_experience` VALUES ('67', '30231', '4', '4', '30231', '2019-09-03 09:49:15', '30231', '2019-09-03 09:49:15', '0');
INSERT INTO `growing_experience` VALUES ('68', '30231', '5', '5', '30231', '2019-09-03 09:50:40', '30231', '2019-09-03 09:50:40', '0');
INSERT INTO `growing_experience` VALUES ('69', '30231', '5', '5', '30231', '2019-09-03 09:55:03', '30231', '2019-09-03 09:55:03', '0');
INSERT INTO `growing_experience` VALUES ('70', '30231', '6', '6', '30231', '2019-09-03 09:57:25', '30231', '2019-09-03 09:57:25', '0');
INSERT INTO `growing_experience` VALUES ('71', '30302', 'C#', 'c#', '30302', '2019-09-04 11:15:41', '30302', '2019-09-04 11:15:41', '0');
INSERT INTO `growing_experience` VALUES ('72', '30302', 'JAVA', 'JAVA', '30302', '2019-09-04 11:16:15', '30302', '2019-09-04 11:16:15', '0');

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
INSERT INTO `headmaster_talk` VALUES ('35', '31888', '职位意向', '希望做什么工作', 'Yolanda', '1', '2019-07-31 19:00:45', '1', '2019-08-01 02:50:45', '0');
INSERT INTO `headmaster_talk` VALUES ('36', '31888', '去留意向', '希望你留下', 'Diane', '1', '2019-07-31 19:01:28', '1', '2019-08-01 02:50:45', '0');
INSERT INTO `headmaster_talk` VALUES ('37', '32222', '心理问题', '最近心态不好，心情出现了问题，导致抑郁寡欢', 'Yolanda', '1', '2019-07-31 19:06:04', '1', '2019-08-28 07:48:53', '1');
INSERT INTO `headmaster_talk` VALUES ('38', '32356', '态度矫正', '工作量大，想要放弃，劝说一番', 'Danie', '1', '2019-07-31 19:08:44', '1', '2019-08-28 07:48:55', '1');
INSERT INTO `headmaster_talk` VALUES ('39', '32112', '心理问题', '心情抑郁', 'Danie', '1', '2019-08-01 03:42:52', '1', '2019-08-01 03:43:00', '1');
INSERT INTO `headmaster_talk` VALUES ('40', '33125', '深入沟通', '对未来充满了迷茫', 'Daisy', '1', '2019-08-01 04:03:41', '1', '2019-08-28 07:48:57', '1');
INSERT INTO `headmaster_talk` VALUES ('41', '30123', '111', '111', '111', '1', '2019-08-01 05:06:02', '1', '2019-08-02 17:26:31', '1');
INSERT INTO `headmaster_talk` VALUES ('42', '30231', 'regular当发生的办法是空间', '最近很累圣诞节发就后端开发设计哈空间', 'yolanda', '1', '2019-08-02 17:23:22', '1', '2019-08-02 17:25:51', '1');

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
INSERT INTO `hr` VALUES ('1', 'Zhao, Yolanda', null, null, null, '1', 'admin', '$2a$10$9Pzc26jx024CIhhr/hE3Gex8sNmuvFRTKrmxfSPAI608L0CtAlEx2', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569831039675&di=c37087fa2c7f6d327ffb07f1426719e9&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201111%2F23%2F20111123155648_e3JyM.jpg', null, null, null);
INSERT INTO `hr` VALUES ('14', 'Li, Daisy', null, null, null, '1', 'normal', '$2a$10$XIQoLbvm4tZEPGHdvAiGZu8SULDuN3QZrUWdeQPtkkBLca7Tq41me', null, null, null, null);
INSERT INTO `hr` VALUES ('24', '杨可欣', null, null, null, '1', 'kexin.yang', '$2a$10$Ko87lf.AWu03zNgyMVYH4.EekVKUGcDwMFkbUUBvV8w.xHo6habTK', null, null, null, null);
INSERT INTO `hr` VALUES ('30231', '李彤', null, null, null, '1', 'jtp30231', '$2a$10$tPKJUseGOrf5iCKpxj0CD.YPsbCtkNeeL8z8tm13kE7LhUh7tYXM6', null, null, null, null);
INSERT INTO `hr` VALUES ('30231', '李彤', null, null, null, '1', 'jtp30172', '$2a$10$7mzWLkSkwmfqb2Swuvf6XeAd2mOzOlgvx1YYaYpOE9C3s9OjQ.rP.', null, null, null, null);
INSERT INTO `hr` VALUES ('31888', '华佗', null, null, null, '1', 'jtp30888', '$2a$10$KnmzJqlXL9ZMsFZyhQ.T1u0Pr.K6MDXjch3tfamEjGs2Y0vZ0Q23i', null, null, null, null);
INSERT INTO `hr` VALUES ('7', '哈斯', null, null, null, '1', 'hasi', '$2a$10$aLJdzfPlL3HXZvV2zVZXKO9dUdeCq6qycg4lMrrw54Z0UqlHtJgb.', 'https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1569820926&di=035bc8f83cd1b708f20385508930c61d&src=http://i1.sinaimg.cn/ty/up/2011-02-05/U2028P6T64D79701F1091DT20110205155706.jpg', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4708 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hr_msg
-- ----------------------------
INSERT INTO `hr_msg` VALUES ('4706', '4800', '1', '0');
INSERT INTO `hr_msg` VALUES ('4707', '4805', '1', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hr_role
-- ----------------------------
INSERT INTO `hr_role` VALUES ('51', '14', '1');
INSERT INTO `hr_role` VALUES ('58', '22', '1');
INSERT INTO `hr_role` VALUES ('59', '1', '2');
INSERT INTO `hr_role` VALUES ('61', '24', '1');
INSERT INTO `hr_role` VALUES ('66', '30231', '3');
INSERT INTO `hr_role` VALUES ('70', '31211', '3');
INSERT INTO `hr_role` VALUES ('71', '30302', '3');

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
INSERT INTO `menu` VALUES ('1', '/', null, null, '所有', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('2', '/', '/home', 'Home', '员工资料', 'fa fa-user-circle-o', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('3', '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('4', '/', '/home', 'Home', '薪资管理', 'fa fa-money', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('5', '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('6', '/', '/home', 'Home', '系统管理', 'fa fa-windows', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('7', '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('8', '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', null, null, '1', '2', '0');
INSERT INTO `menu` VALUES ('9', '/employee/prize/**', '/emp/prize', 'EmpPrize', '获奖情况', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('10', '/employee/experience/**', '/emp/learning', 'EmpLearning', '培训经历', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('11', '/employee/project/**', '/emp/project', 'EmpProject', '项目经历', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('12', '/employee/talk/**', '/emp/talking', 'EmpTalking', '校长访谈', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('13', '/employee/team/**', '/emp/teamtsf', 'EmpTeamtsf', '团队变更', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('14', '/employee/teamtsf/**', '/emp/EmpStudyDecoration', 'EmpStudyDecoration', '学习申报', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('15', '/', '/home', 'home', '用户相关', 'fa fa-user-circle-o', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('16', '/staff/baseInfo/**', './baseInfo', 'baseInfo', '个人资料', null, null, '1', '15', '1');
INSERT INTO `menu` VALUES ('17', '/staff/declaration/**', './declaration', 'declaration', '学习申报', null, null, '1', '15', '1');
INSERT INTO `menu` VALUES ('18', '/employee/admin/**', '/emp/admin', 'EmpAdmin', '用户管理', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('19', '/staff/certificateDecoration/**', './certificateDecoration', 'certificateDecoration', '认证申报', null, null, '1', '15', '1');
INSERT INTO `menu` VALUES ('20', '/employee/chart/**', '/emp/chart', 'EmpChart', '图表展示', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('21', '/employee/EmpMsg/**', '/EmpMsg', 'EmpMsg', '消息通知', null, null, '1', '2', '1');

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
INSERT INTO `menu_role` VALUES ('278', '1', '2');
INSERT INTO `menu_role` VALUES ('279', '2', '2');
INSERT INTO `menu_role` VALUES ('280', '3', '2');
INSERT INTO `menu_role` VALUES ('281', '4', '2');
INSERT INTO `menu_role` VALUES ('282', '5', '2');
INSERT INTO `menu_role` VALUES ('283', '6', '2');
INSERT INTO `menu_role` VALUES ('284', '7', '2');
INSERT INTO `menu_role` VALUES ('285', '8', '2');
INSERT INTO `menu_role` VALUES ('286', '9', '2');
INSERT INTO `menu_role` VALUES ('287', '10', '2');
INSERT INTO `menu_role` VALUES ('288', '11', '2');
INSERT INTO `menu_role` VALUES ('289', '12', '2');
INSERT INTO `menu_role` VALUES ('290', '13', '2');
INSERT INTO `menu_role` VALUES ('291', '1', '1');
INSERT INTO `menu_role` VALUES ('292', '2', '1');
INSERT INTO `menu_role` VALUES ('293', '3', '1');
INSERT INTO `menu_role` VALUES ('294', '4', '1');
INSERT INTO `menu_role` VALUES ('295', '5', '1');
INSERT INTO `menu_role` VALUES ('296', '6', '1');
INSERT INTO `menu_role` VALUES ('297', '7', '1');
INSERT INTO `menu_role` VALUES ('298', '8', '1');
INSERT INTO `menu_role` VALUES ('299', '9', '1');
INSERT INTO `menu_role` VALUES ('300', '10', '1');
INSERT INTO `menu_role` VALUES ('301', '11', '1');
INSERT INTO `menu_role` VALUES ('302', '12', '1');
INSERT INTO `menu_role` VALUES ('304', '14', '1');
INSERT INTO `menu_role` VALUES ('305', '14', '2');
INSERT INTO `menu_role` VALUES ('306', '15', '3');
INSERT INTO `menu_role` VALUES ('307', '16', '3');
INSERT INTO `menu_role` VALUES ('308', '17', '3');
INSERT INTO `menu_role` VALUES ('310', '19', '3');
INSERT INTO `menu_role` VALUES ('311', '20', '2');
INSERT INTO `menu_role` VALUES ('312', '21', '2');
INSERT INTO `menu_role` VALUES ('313', '14', '1');
INSERT INTO `menu_role` VALUES ('314', '21', '1');
INSERT INTO `menu_role` VALUES ('315', '20', '1');
INSERT INTO `menu_role` VALUES ('316', '13', '1');
INSERT INTO `menu_role` VALUES ('317', '18', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_experience
-- ----------------------------
INSERT INTO `project_experience` VALUES ('36', '32222', 'Yolanda', 'JTP员工管理系统', '朱逸寅', '2019-07-13', '2019-07-30', '1', '2019-07-31 18:10:54', '1', '2019-08-28 07:31:51', '1');
INSERT INTO `project_experience` VALUES ('37', '31211', 'Yolanda', 'JTP项目管理系统', '朱逸寅', '2019-07-14', '2019-07-31', '1', '2019-07-31 18:11:33', '1', '2019-08-29 04:09:04', '1');
INSERT INTO `project_experience` VALUES ('38', '32356', '华为', '华为手机5G开发', '金泽萱', '2019-07-02', '2019-07-29', '1', '2019-07-31 18:22:21', '1', '2019-08-29 04:09:11', '1');
INSERT INTO `project_experience` VALUES ('39', '33125', '华为', '华为手机5G开发', '金泽萱', '2019-07-05', '2019-07-29', '1', '2019-07-31 18:26:03', '1', '2019-08-29 04:09:11', '1');
INSERT INTO `project_experience` VALUES ('40', '30231', '华斌', '自动化', '李煌', '2019-08-06', '2019-08-29', '1', '2019-08-01 09:00:30', '1', '2019-08-01 02:50:41', '0');
INSERT INTO `project_experience` VALUES ('41', '30123', '111', '111', '111', null, null, '1', '2019-08-01 05:05:46', '1', '2019-08-02 17:26:31', '1');
INSERT INTO `project_experience` VALUES ('42', '32330', '华为', '华为手机P50研发', '刘勇', '2019-07-03', '2019-07-30', '1', '2019-08-01 05:07:59', '1', '2019-08-29 04:09:11', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_user', '用户');
INSERT INTO `role` VALUES ('2', 'ROLE_admin', '管理员');
INSERT INTO `role` VALUES ('3', 'ROLE_staff', '员工');

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
) ENGINE=InnoDB AUTO_INCREMENT=4721 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studydeclaration
-- ----------------------------
INSERT INTO `studydeclaration` VALUES ('4702', '30231', 'major', 'major', 'major', '22', '30', '0', '5', '5', '0', '30231', '2019-07-28 16:00:11', '1', '2019-09-04 13:41:04', 'wadada', '1');
INSERT INTO `studydeclaration` VALUES ('4703', '30231', 'major', 'major', 'major', '22', '30', '15', '5', '5', '0', '1', '2019-09-28 19:41:03', '1', '2019-09-04 13:12:53', '2019-07-28', '0');
INSERT INTO `studydeclaration` VALUES ('4710', '30231', '1', '1', '1', '1', '1', '1', '1', '1', '1', '30231', '2019-09-02 11:43:04', '1', '2019-09-04 13:18:52', '1666', '1');
INSERT INTO `studydeclaration` VALUES ('4711', '0', 'string', 'string', 'string', '0', '0', '0', '0', '0', '0', '1', '2019-09-02 21:55:04', '1', '2019-09-04 13:12:58', 'string', '1');
INSERT INTO `studydeclaration` VALUES ('4712', '66', '6', '6', '6', '6', '6', '6', '6', '6', '66', '1', '2019-09-02 21:59:31', '1', '2019-09-04 13:12:56', '66666', '1');
INSERT INTO `studydeclaration` VALUES ('4713', '555', '55', '5', '5', '5', '5', '5', '5', '5', '5', '1', '2019-09-02 22:19:55', '1', '2019-09-02 23:10:39', '5', '1');
INSERT INTO `studydeclaration` VALUES ('4714', '31888', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2019-09-03 09:31:14', '1', '2019-09-03 09:42:43', '1', '1');
INSERT INTO `studydeclaration` VALUES ('4715', '30231', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2019-09-03 09:43:31', '1', '2019-09-04 00:09:24', '1', '1');
INSERT INTO `studydeclaration` VALUES ('4716', '30231', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2019-09-04 00:09:32', '1', '2019-09-04 00:09:39', '1', '1');
INSERT INTO `studydeclaration` VALUES ('4717', '31888', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2019-09-04 09:20:36', '1', '2019-09-04 11:23:34', '1', '1');
INSERT INTO `studydeclaration` VALUES ('4718', '30302', '33', '1', '2', '1', '1', '1', '1', '1', '1', '30302', '2019-09-04 11:15:00', '1', '2019-09-04 11:17:57', '1', '1');
INSERT INTO `studydeclaration` VALUES ('4719', '30302', '2', '2', '2', '2', '2', '2', '2', '2', '2', '1', '2019-09-04 11:18:07', '1', '2019-09-04 11:18:11', '2', '1');
INSERT INTO `studydeclaration` VALUES ('4720', '30231', '55', '5', '5', '5', '5', '5', '5', '5', '5', '30231', '2019-09-04 11:22:43', '1', '2019-09-04 11:23:34', '5', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4783 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teamchangelog
-- ----------------------------
INSERT INTO `teamchangelog` VALUES ('4762', '30231', 'CST ERP', 'CST ERP', 'CST HCM', 'SAP', '1', '2019-08-29', '1', '2019-08-29', null, '1');
INSERT INTO `teamchangelog` VALUES ('4763', '34211', 'CST ERP', 'CST ERP', 'CST Cloud', 'Cyber Security', '1', '2019-08-29', '1', '2019-08-29', null, '0');
INSERT INTO `teamchangelog` VALUES ('4764', '31888', 'CST HCM', 'HCM', 'CST RDC', 'CSD', '1', '2019-08-29', '1', '2019-08-29', null, '0');
INSERT INTO `teamchangelog` VALUES ('4765', '30231', 'CST HCM', 'SAP', 'CST Central', 'CTO', '1', '2019-08-29', '1', '2019-08-29', null, '1');
INSERT INTO `teamchangelog` VALUES ('4766', '30231', 'CST Central', 'CTO', 'CST ERP', 'CST ERP', '30231', '2019-08-29', '1', '2019-09-02', null, '1');
INSERT INTO `teamchangelog` VALUES ('4767', '30231', 'CST Cloud', 'Testing', 'CST PMO', 'CST PMO', '1', '2019-08-29', '1', '2019-08-29', null, '1');
INSERT INTO `teamchangelog` VALUES ('4768', '30231', 'CST ERP', 'CST ERP', 'CST ERP', 'CST ERP', '30231', '2019-08-29', '30231', '2019-08-29', null, '0');
INSERT INTO `teamchangelog` VALUES ('4769', '30231', 'CST ERP', 'CST ERP', 'CST Cloud', 'Testing', '1', '2019-08-29', '1', '2019-08-29', null, '0');
INSERT INTO `teamchangelog` VALUES ('4770', '30231', 'CST Cloud', 'Testing', 'CST PMO', 'CST PMO', '1', '2019-08-29', '1', '2019-08-29', null, '1');
INSERT INTO `teamchangelog` VALUES ('4771', '0', 'string', 'string', 'string', 'string', '1', '2019-09-02', '1', '2019-09-02', '2019-09-02', '1');
INSERT INTO `teamchangelog` VALUES ('4772', '6660', 'CST ERP', 'CST ERP', 'CST ERP', 'CST ERP', '1', '2019-09-02', '1', '2019-09-02', null, '1');
INSERT INTO `teamchangelog` VALUES ('4773', '30231', 'CST PMO', 'CST PMO', 'CST PMO', 'CST PMO', '1', '2019-09-02', '1', '2019-09-02', null, '1');
INSERT INTO `teamchangelog` VALUES ('4774', '30231', 'CST ERP', 'CST ERP', 'CST ERP', 'CST ERP', '1', '2019-09-02', '1', '2019-09-02', null, '1');
INSERT INTO `teamchangelog` VALUES ('4775', '30231', 'CST ERP', 'CST ERP', 'CST ERP', 'CST ERP', '1', '2019-09-02', '1', '2019-09-02', null, '1');
INSERT INTO `teamchangelog` VALUES ('4776', '30231', 'CST ERP', 'CST ERP', 'CST ERP', 'CST ERP', '1', '2019-09-02', '1', '2019-09-02', null, '1');
INSERT INTO `teamchangelog` VALUES ('4777', '31888', 'CST ERP', 'CST ERP', 'CST ERP', 'CST ERP', '1', '2019-09-03', '1', '2019-09-03', null, '1');
INSERT INTO `teamchangelog` VALUES ('4778', '30231', 'CST ERP', 'CST ERP', 'CST Cloud', 'Testing', '1', '2019-09-04', '1', '2019-09-04', null, '1');
INSERT INTO `teamchangelog` VALUES ('4779', '30231', 'CST Cloud', 'Testing', 'CST LAB', 'CST LAB', '1', '2019-09-04', '1', '2019-09-04', null, '1');
INSERT INTO `teamchangelog` VALUES ('4780', '30231', 'CST ERP', 'CST ERP', 'CST ERP', 'CST ERP', '1', '2019-09-04', '1', '2019-09-04', null, '1');
INSERT INTO `teamchangelog` VALUES ('4781', '30231', 'CST ERP', 'CST ERP', 'CST ERP', 'CST ERP', '1', '2019-09-04', '1', '2019-09-04', null, '1');
INSERT INTO `teamchangelog` VALUES ('4782', '30231', 'CST ERP', 'CST ERP', 'CST ERP', 'CST ERP', '1', '2019-09-04', '1', '2019-09-04', null, '1');

-- ----------------------------
-- Table structure for verficode
-- ----------------------------
DROP TABLE IF EXISTS `verficode`;
CREATE TABLE `verficode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `staffUsername` varchar(50) DEFAULT NULL COMMENT '账号',
  `Verficode` bigint(20) DEFAULT NULL COMMENT '验证码',
  `createTime` datetime DEFAULT NULL,
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4847 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of verficode
-- ----------------------------
INSERT INTO `verficode` VALUES ('1', 'jtp30231', '641886', '2019-08-26 20:40:07', '0');
INSERT INTO `verficode` VALUES ('2', 'jtp30231', '933673', '2019-08-26 20:44:30', '0');
INSERT INTO `verficode` VALUES ('3', 'jtp30231', '460730', '2019-08-26 20:47:01', '0');
INSERT INTO `verficode` VALUES ('4684', 'jtp30231', '534327', '2019-08-26 20:49:12', '0');
INSERT INTO `verficode` VALUES ('4685', 'jtp30231', '229883', '2019-08-26 20:52:06', '0');
INSERT INTO `verficode` VALUES ('4686', 'jtp30231', '550790', '2019-08-26 20:52:46', '0');
INSERT INTO `verficode` VALUES ('4687', 'jtp30231', '603883', '2019-08-26 21:45:51', '0');
INSERT INTO `verficode` VALUES ('4688', 'jtp30231', '241733', '2019-08-26 21:50:31', '0');
INSERT INTO `verficode` VALUES ('4689', 'jtp30231', '364531', '2019-08-26 21:53:39', '0');
INSERT INTO `verficode` VALUES ('4690', 'jtp30231', '914049', '2019-08-26 22:10:21', '0');
INSERT INTO `verficode` VALUES ('4691', 'jtp30231', '477926', '2019-08-26 22:20:10', '0');
INSERT INTO `verficode` VALUES ('4692', 'jtp30231', '644542', '2019-08-26 22:25:46', '0');
INSERT INTO `verficode` VALUES ('4693', 'jtp30231', '736506', '2019-08-28 21:07:16', '0');
INSERT INTO `verficode` VALUES ('4694', 'jtp30231', '798819', '2019-08-28 23:08:09', '0');
INSERT INTO `verficode` VALUES ('4695', 'jtp30231', '786229', '2019-08-28 23:38:48', '0');
INSERT INTO `verficode` VALUES ('4696', 'jtp30231', '383344', '2019-08-28 23:42:58', '0');
INSERT INTO `verficode` VALUES ('4697', 'jtp30231', '511791', '2019-08-28 23:43:45', '0');
INSERT INTO `verficode` VALUES ('4698', 'jtp30231', '685977', '2019-08-28 23:48:10', '0');
INSERT INTO `verficode` VALUES ('4699', 'jtp30231', '261795', '2019-08-28 23:49:41', '0');
INSERT INTO `verficode` VALUES ('4700', 'jtp30231', '884110', '2019-08-28 23:53:47', '0');
INSERT INTO `verficode` VALUES ('4701', 'jtp30231', '482192', '2019-08-28 23:55:47', '0');
INSERT INTO `verficode` VALUES ('4702', 'jtp30231', '654047', '2019-08-29 00:03:59', '0');
INSERT INTO `verficode` VALUES ('4703', 'jtp30231', '103188', '2019-08-29 00:05:02', '0');
INSERT INTO `verficode` VALUES ('4704', 'jtp30231', '826459', '2019-08-29 00:06:57', '0');
INSERT INTO `verficode` VALUES ('4705', 'jtp30231', '312033', '2019-08-29 00:08:07', '0');
INSERT INTO `verficode` VALUES ('4706', 'jtp30231', '680905', '2019-08-29 00:11:13', '0');
INSERT INTO `verficode` VALUES ('4707', 'jtp30231', '507911', '2019-08-29 00:23:54', '0');
INSERT INTO `verficode` VALUES ('4708', 'jtp30231', '536159', '2019-08-29 00:26:36', '0');
INSERT INTO `verficode` VALUES ('4709', 'jtp30231', '404294', '2019-08-29 00:46:16', '0');
INSERT INTO `verficode` VALUES ('4710', 'jtp30231', '138240', '2019-08-29 01:15:14', '0');
INSERT INTO `verficode` VALUES ('4711', 'jtp30231', '413742', '2019-08-29 01:18:03', '0');
INSERT INTO `verficode` VALUES ('4712', null, '834789', '2019-08-29 02:09:17', '0');
INSERT INTO `verficode` VALUES ('4713', null, '854857', '2019-08-29 02:09:32', '0');
INSERT INTO `verficode` VALUES ('4714', null, '804064', '2019-08-29 02:09:36', '0');
INSERT INTO `verficode` VALUES ('4715', 'jtp30231', '447697', '2019-08-29 02:15:12', '0');
INSERT INTO `verficode` VALUES ('4716', 'jtp30231', '733809', '2019-08-29 02:17:28', '0');
INSERT INTO `verficode` VALUES ('4717', 'jtp30231', '994509', '2019-08-29 02:17:44', '0');
INSERT INTO `verficode` VALUES ('4718', 'jtp30231', '652898', '2019-08-29 02:21:22', '0');
INSERT INTO `verficode` VALUES ('4719', 'jtp30231', '490628', '2019-08-29 02:21:39', '0');
INSERT INTO `verficode` VALUES ('4720', 'jtp30231', '457538', '2019-08-29 02:23:22', '0');
INSERT INTO `verficode` VALUES ('4721', 'jtp30231', '985813', '2019-08-29 02:23:38', '0');
INSERT INTO `verficode` VALUES ('4722', 'jtp30231', '551786', '2019-08-29 02:23:38', '0');
INSERT INTO `verficode` VALUES ('4723', 'jtp30231', '459665', '2019-08-29 02:23:38', '0');
INSERT INTO `verficode` VALUES ('4724', 'jtp30231', '968417', '2019-08-29 02:24:10', '0');
INSERT INTO `verficode` VALUES ('4725', 'jtp30231', '104945', '2019-08-29 02:24:25', '0');
INSERT INTO `verficode` VALUES ('4726', 'jtp30231', '134597', '2019-08-29 02:32:07', '0');
INSERT INTO `verficode` VALUES ('4727', 'jtp30231', '639565', '2019-08-29 02:32:12', '0');
INSERT INTO `verficode` VALUES ('4728', 'jtp30231', '226797', '2019-08-29 02:32:19', '0');
INSERT INTO `verficode` VALUES ('4729', 'jtp30231', '355505', '2019-08-29 02:32:20', '0');
INSERT INTO `verficode` VALUES ('4730', 'jtp30231', '908826', '2019-08-29 02:32:21', '0');
INSERT INTO `verficode` VALUES ('4731', 'jtp30231', '984757', '2019-08-29 02:33:36', '0');
INSERT INTO `verficode` VALUES ('4732', 'jtp30231', '662659', '2019-08-29 02:34:10', '0');
INSERT INTO `verficode` VALUES ('4733', 'jtp30231', '277297', '2019-08-29 02:35:59', '0');
INSERT INTO `verficode` VALUES ('4734', 'jtp30231', '549553', '2019-08-29 02:36:00', '0');
INSERT INTO `verficode` VALUES ('4735', 'jtp30231', '688695', '2019-08-29 02:36:01', '0');
INSERT INTO `verficode` VALUES ('4736', 'jtp30231', '536625', '2019-08-29 02:36:01', '0');
INSERT INTO `verficode` VALUES ('4737', 'JTP30231', '665708', '2019-08-29 02:36:50', '0');
INSERT INTO `verficode` VALUES ('4738', 'JTP30231', '847434', '2019-08-29 02:36:57', '0');
INSERT INTO `verficode` VALUES ('4739', 'JTP30231', '927732', '2019-08-29 02:45:53', '0');
INSERT INTO `verficode` VALUES ('4740', 'JTP30231', '586740', '2019-08-29 02:45:57', '0');
INSERT INTO `verficode` VALUES ('4741', 'JTP30231', '983884', '2019-08-29 02:45:58', '0');
INSERT INTO `verficode` VALUES ('4742', '', '693971', '2019-08-29 02:49:09', '0');
INSERT INTO `verficode` VALUES ('4743', 'jtp30231', '812532', '2019-08-29 02:49:18', '0');
INSERT INTO `verficode` VALUES ('4744', 'jtp30231', '795825', '2019-08-29 02:49:20', '0');
INSERT INTO `verficode` VALUES ('4745', 'jtp30231', '229493', '2019-08-29 02:49:21', '0');
INSERT INTO `verficode` VALUES ('4746', 'jtp30231', '970047', '2019-08-29 02:49:21', '0');
INSERT INTO `verficode` VALUES ('4747', 'jtp30231', '374962', '2019-08-29 02:53:11', '0');
INSERT INTO `verficode` VALUES ('4748', 'jtp30231', '975040', '2019-08-29 02:53:16', '0');
INSERT INTO `verficode` VALUES ('4749', 'jtp30231', '571182', '2019-08-29 02:53:16', '0');
INSERT INTO `verficode` VALUES ('4750', 'jtp30231', '881012', '2019-08-29 02:53:29', '0');
INSERT INTO `verficode` VALUES ('4751', 'jtp30231', '137668', '2019-08-29 02:53:30', '0');
INSERT INTO `verficode` VALUES ('4752', 'jtp30231', '342571', '2019-08-29 02:55:26', '0');
INSERT INTO `verficode` VALUES ('4753', 'jtp30231', '664879', '2019-08-29 02:55:31', '0');
INSERT INTO `verficode` VALUES ('4754', 'jtp30231', '205616', '2019-08-29 02:55:31', '0');
INSERT INTO `verficode` VALUES ('4755', 'jtp30231', '160246', '2019-08-29 02:55:32', '0');
INSERT INTO `verficode` VALUES ('4756', 'jtp30231', '278172', '2019-08-29 02:57:48', '0');
INSERT INTO `verficode` VALUES ('4757', 'jtp30231', '999614', '2019-08-29 02:58:28', '0');
INSERT INTO `verficode` VALUES ('4758', 'jtp30231', '213714', '2019-08-29 02:59:23', '0');
INSERT INTO `verficode` VALUES ('4759', 'jtp30231', '126096', '2019-08-29 02:59:51', '0');
INSERT INTO `verficode` VALUES ('4760', 'jtp30231', '594182', '2019-08-29 02:59:52', '0');
INSERT INTO `verficode` VALUES ('4761', 'jtp30231', '534853', '2019-08-29 02:59:52', '0');
INSERT INTO `verficode` VALUES ('4762', 'jtp30231', '472425', '2019-08-29 02:59:53', '0');
INSERT INTO `verficode` VALUES ('4763', 'jtp30231', '103167', '2019-08-29 02:59:53', '0');
INSERT INTO `verficode` VALUES ('4764', 'jtp30231', '755980', '2019-08-29 02:59:53', '0');
INSERT INTO `verficode` VALUES ('4765', 'jtp30231', '371293', '2019-08-29 02:59:53', '0');
INSERT INTO `verficode` VALUES ('4766', 'jtp30231', '928127', '2019-08-29 03:00:16', '0');
INSERT INTO `verficode` VALUES ('4767', 'jtp30231', '740924', '2019-08-29 03:00:33', '0');
INSERT INTO `verficode` VALUES ('4768', 'jtp30231', '701990', '2019-08-29 03:00:33', '0');
INSERT INTO `verficode` VALUES ('4769', 'jtp30231', '438561', '2019-08-29 03:01:08', '0');
INSERT INTO `verficode` VALUES ('4770', 'jtp30231', '659400', '2019-08-29 03:01:43', '0');
INSERT INTO `verficode` VALUES ('4771', 'jtp30231', '133890', '2019-08-29 03:02:33', '0');
INSERT INTO `verficode` VALUES ('4772', 'jtp30231', '210341', '2019-08-29 03:02:36', '0');
INSERT INTO `verficode` VALUES ('4773', 'jtp30231', '215417', '2019-08-29 03:02:37', '0');
INSERT INTO `verficode` VALUES ('4774', 'jtp30231', '819549', '2019-08-29 03:03:43', '0');
INSERT INTO `verficode` VALUES ('4775', 'jtp30231', '403342', '2019-08-29 03:03:44', '0');
INSERT INTO `verficode` VALUES ('4776', 'jtp30231', '534379', '2019-08-29 03:03:45', '0');
INSERT INTO `verficode` VALUES ('4777', 'jtp30231', '633270', '2019-08-29 03:03:45', '0');
INSERT INTO `verficode` VALUES ('4778', 'jtp30231', '338984', '2019-08-29 03:03:45', '0');
INSERT INTO `verficode` VALUES ('4779', 'jtp30231', '923036', '2019-08-29 03:03:49', '0');
INSERT INTO `verficode` VALUES ('4780', '', '182447', '2019-08-29 03:08:37', '0');
INSERT INTO `verficode` VALUES ('4781', 'jtp30231', '152240', '2019-08-29 03:08:50', '0');
INSERT INTO `verficode` VALUES ('4782', 'jtp30231', '837555', '2019-08-29 03:09:30', '0');
INSERT INTO `verficode` VALUES ('4783', 'jtp30231', '242290', '2019-08-29 03:09:55', '0');
INSERT INTO `verficode` VALUES ('4784', 'jtp30231', '448689', '2019-08-29 03:10:09', '0');
INSERT INTO `verficode` VALUES ('4785', 'jtp30231', '640466', '2019-08-29 03:10:25', '0');
INSERT INTO `verficode` VALUES ('4786', 'jtp30231', '304802', '2019-08-29 03:10:55', '0');
INSERT INTO `verficode` VALUES ('4787', 'jtp30231', '340308', '2019-08-29 03:11:20', '0');
INSERT INTO `verficode` VALUES ('4788', 'jtp30231', '211138', '2019-08-29 03:11:59', '0');
INSERT INTO `verficode` VALUES ('4789', 'jtp30231', '832011', '2019-08-29 03:12:13', '0');
INSERT INTO `verficode` VALUES ('4790', 'jtp30231', '142167', '2019-08-29 03:12:26', '0');
INSERT INTO `verficode` VALUES ('4791', 'jtp30231', '279338', '2019-08-29 03:12:26', '0');
INSERT INTO `verficode` VALUES ('4792', 'jtp30231', '400903', '2019-08-29 03:12:26', '0');
INSERT INTO `verficode` VALUES ('4793', 'jtp30231', '786470', '2019-08-29 03:12:26', '0');
INSERT INTO `verficode` VALUES ('4794', 'jtp30231', '725335', '2019-08-29 03:12:27', '0');
INSERT INTO `verficode` VALUES ('4795', 'jtp30231', '511038', '2019-08-29 03:12:30', '0');
INSERT INTO `verficode` VALUES ('4796', 'jtp30231', '712027', '2019-08-29 03:12:31', '0');
INSERT INTO `verficode` VALUES ('4797', 'jtp30231', '724246', '2019-08-29 03:12:31', '0');
INSERT INTO `verficode` VALUES ('4798', 'jtp30231', '337170', '2019-08-29 03:12:31', '0');
INSERT INTO `verficode` VALUES ('4799', 'jtp30231', '630423', '2019-08-29 03:12:31', '0');
INSERT INTO `verficode` VALUES ('4800', 'jtp30231', '365415', '2019-08-29 03:12:50', '0');
INSERT INTO `verficode` VALUES ('4801', 'jtp30231', '365481', '2019-08-29 03:12:59', '0');
INSERT INTO `verficode` VALUES ('4802', 'jtp30231', '742137', '2019-08-29 03:13:27', '0');
INSERT INTO `verficode` VALUES ('4803', 'jtp30231', '864768', '2019-08-29 03:15:11', '0');
INSERT INTO `verficode` VALUES ('4804', 'jtp30231', '362212', '2019-08-29 03:16:31', '0');
INSERT INTO `verficode` VALUES ('4805', 'jtp30231', '457756', '2019-08-29 03:16:43', '0');
INSERT INTO `verficode` VALUES ('4806', 'jtp30231', '321605', '2019-08-29 03:16:45', '0');
INSERT INTO `verficode` VALUES ('4807', 'jtp30231', '331075', '2019-08-29 03:20:19', '0');
INSERT INTO `verficode` VALUES ('4808', 'jtp30231', '662117', '2019-08-29 03:20:47', '0');
INSERT INTO `verficode` VALUES ('4809', 'jtp30231', '837919', '2019-08-29 03:20:47', '0');
INSERT INTO `verficode` VALUES ('4810', 'jtp30231', '866112', '2019-08-29 03:20:50', '0');
INSERT INTO `verficode` VALUES ('4811', 'jtp30231', '495029', '2019-08-29 03:20:50', '0');
INSERT INTO `verficode` VALUES ('4812', 'jtp30231', '200314', '2019-08-29 03:21:11', '0');
INSERT INTO `verficode` VALUES ('4813', 'jtp30231', '736788', '2019-08-29 03:23:06', '0');
INSERT INTO `verficode` VALUES ('4814', 'jtp30231', '325451', '2019-08-29 03:23:19', '0');
INSERT INTO `verficode` VALUES ('4815', 'jtp30231', '116772', '2019-08-29 03:23:21', '0');
INSERT INTO `verficode` VALUES ('4816', 'jtp30231', '113089', '2019-08-29 03:23:22', '0');
INSERT INTO `verficode` VALUES ('4817', 'jtp30231', '201975', '2019-08-29 03:23:58', '0');
INSERT INTO `verficode` VALUES ('4818', 'jtp30231', '750343', '2019-08-29 03:24:10', '0');
INSERT INTO `verficode` VALUES ('4819', 'jtp30231', '974442', '2019-08-29 03:38:01', '0');
INSERT INTO `verficode` VALUES ('4820', 'jtp30231', '855831', '2019-08-29 03:39:19', '0');
INSERT INTO `verficode` VALUES ('4821', 'JTP30231', '905632', '2019-08-29 03:54:59', '0');
INSERT INTO `verficode` VALUES ('4822', 'jtp30231', '985330', '2019-08-29 03:56:00', '0');
INSERT INTO `verficode` VALUES ('4823', 'jtp30231', '926065', '2019-08-29 09:24:21', '0');
INSERT INTO `verficode` VALUES ('4824', 'jtp30231', '752551', '2019-08-29 09:39:19', '0');
INSERT INTO `verficode` VALUES ('4825', 'jtp30231', '218665', '2019-08-29 15:54:41', '0');
INSERT INTO `verficode` VALUES ('4826', 'jtp30231', '596275', '2019-08-29 15:56:51', '0');
INSERT INTO `verficode` VALUES ('4827', 'jtp30231', '908007', '2019-08-29 17:03:21', '0');
INSERT INTO `verficode` VALUES ('4828', 'jtp30231', '658188', '2019-08-29 17:38:41', '0');
INSERT INTO `verficode` VALUES ('4829', 'jtp30231', '445117', '2019-08-29 21:16:16', '0');
INSERT INTO `verficode` VALUES ('4830', null, '679374', '2019-09-02 14:34:31', '0');
INSERT INTO `verficode` VALUES ('4831', null, '710265', '2019-09-02 15:30:02', '0');
INSERT INTO `verficode` VALUES ('4832', 'jtp30231', '542109', '2019-09-02 16:10:58', '0');
INSERT INTO `verficode` VALUES ('4833', null, '575127', '2019-09-02 16:12:29', '0');
INSERT INTO `verficode` VALUES ('4834', null, '188942', '2019-09-02 16:30:38', '0');
INSERT INTO `verficode` VALUES ('4835', null, '918395', '2019-09-02 17:19:35', '0');
INSERT INTO `verficode` VALUES ('4836', null, '800612', '2019-09-02 17:21:47', '0');
INSERT INTO `verficode` VALUES ('4837', null, '827079', '2019-09-02 17:48:54', '0');
INSERT INTO `verficode` VALUES ('4838', null, '605438', '2019-09-02 17:49:01', '0');
INSERT INTO `verficode` VALUES ('4839', 'jtp30231', '967100', '2019-09-03 23:54:17', '0');
INSERT INTO `verficode` VALUES ('4840', null, '428937', '2019-09-04 00:00:06', '0');
INSERT INTO `verficode` VALUES ('4841', null, '319141', '2019-09-04 00:03:20', '0');
INSERT INTO `verficode` VALUES ('4842', null, '949154', '2019-09-04 00:05:25', '0');
INSERT INTO `verficode` VALUES ('4843', 'jtp30231', '277446', '2019-09-04 00:19:11', '0');
INSERT INTO `verficode` VALUES ('4844', null, '616316', '2019-09-04 00:19:46', '0');
INSERT INTO `verficode` VALUES ('4845', null, '650256', '2019-09-04 11:14:08', '0');
INSERT INTO `verficode` VALUES ('4846', 'jtp30231', '561278', '2019-09-04 11:24:30', '0');
