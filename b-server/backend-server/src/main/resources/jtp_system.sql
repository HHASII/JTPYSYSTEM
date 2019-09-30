-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: jtp_system
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (4645,30231,'李彤','Male','X1','2018-07-10','East','Capgemini','Kunshan','2018-12-14','18846436562','746665@qq.com','黑龙江大学','国贸','本科','2019','CST ERP','CST ERP','SG','SS','2019-07-26','Voluntary','Better Pay/Rewards','Thanks.','LA','30111','1990-02-06',1,'2019-08-01 09:49:26',1,'2019-08-01 02:50:41','0'),(4646,30171,'孙志刚','Male','X1','2019-07-18','North','Capgemini','Kunshan','2018-11-14','15189827779','kept0611@163.com','南京信息工程大学','软件工程','本科','2019','CST PMO','CST PMO\r\n','SG','SS','2019-07-29','Voluntary','Better Pay/Rewards','Thanks.','LA','30100','1999-03-29',1,'2019-08-01 09:49:26',1,'2019-08-01 02:50:41','0'),(4647,32222,'王超','Male','C1','2019-07-03','East','Capgemini','Shanghai','2019-06-26','13827642387','74666@qq.com','辽宁大学','数学','博士','2011','CST SFDC','CST SFDC','SG','SS',NULL,NULL,NULL,NULL,NULL,NULL,'1990-04-05',1,'2019-08-01 09:49:26',1,'2019-08-01 02:50:41','0'),(4648,32112,'华斌','Male','C2','2019-07-02','East','Capgemini','Shanghai','2019-06-22','15629839222','asfiug@163.com','长春工业大学','计算机','博士','2011','CST CSD','CST CSD','SG','SS','2019-07-28','Voluntary','Others','','','30023','1989-02-26',1,'2019-08-01 09:49:26',1,'2019-08-01 02:50:41','0'),(4649,31211,'李渡','Male','C1','2019-07-02','East','capgemini','Beijing','2019-06-25','13827642387','746665826@qq.com','辽宁大学','数学','博士','2013','CST SFDC','CST SFDC','SG','SS',NULL,NULL,NULL,NULL,NULL,NULL,'1990-05-31',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:41','0'),(4650,32330,'王伟','Male','C2','2019-07-04','East','Capgemini','Shanghai','2019-06-24','15629839222','asfiug@163.com','长春工业大学','计算机','本科','2020','CST CSD','CST CSD','SG','SS','2019-06-26','Voluntary','Others','','','30012','1999-06-27',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:41','1'),(4651,31410,'付丽耀','Female','C1','2019-07-05','East','Capgemini','Guangzhou','2019-06-28','13827642387','29184@qq.com','辽宁大学','计算机','本科','2020','CST SFDC','CST SFDC','SG','SS',NULL,NULL,NULL,NULL,NULL,NULL,'1998-04-05',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:41','1'),(4652,15154,'钱长江','Male','C2','2019-07-06','East','Capgemini','Shanghai','2019-06-26','15629839222','asfiug@163.com','长春工业大学','计算机','博士','2011','CST CSD','CST CSD','SG','SS','2019-06-19','Voluntary','Others','','LA','30090','1969-12-27',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:41','1'),(4653,32356,'陆抒龄','Female','C1','2019-07-02','East','Capgemini','Shanghai','2019-06-25','13827642387','29184@qq.com','大连名族学校','软件工程','本科','2012','CST SFDC','CST SFDC','SG','SS',NULL,NULL,NULL,NULL,NULL,NULL,'1989-09-28',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:41','1'),(4654,33125,'陈美丹','Male','B1','2019-07-05','North','Capgemini','Shanghai','2019-06-28','13827642387','29184@qq.com','北京大学','软件工程','硕士','2012','CST SFDC','CST SFDC','SG','SS',NULL,NULL,NULL,NULL,NULL,NULL,'1989-01-08',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:41','1'),(4655,31111,'黄飞鸿','Male','C1','2019-07-06','East','Capgemini','Kunshan','2019-07-13','13827642387','29184@qq.com','南昌航空大学','软件工程','本科','2020','CST SFDC','CST SFDC','SG','SS',NULL,NULL,NULL,NULL,NULL,NULL,'1998-12-22',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:45','1'),(4656,33421,'李云飞','Male','C1','2019-07-08','East','Capgemini','Shanghai','2019-07-01','13827642387','29184@qq.com','清华大学','计算机','本科','2020','CST SFDC','CST SFDC','SG','SS',NULL,NULL,NULL,NULL,NULL,NULL,'1998-03-11',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:45','1'),(4657,34211,'张美丽','Female','C2','2019-07-09','East','Capgemini','Kunshan','2019-07-18','14568676767','sad@qq.com','北京大学','计算机','本科','2020','CST Cloud','Cyber Security','SG','SS',NULL,NULL,NULL,NULL,NULL,NULL,'1998-08-03',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:45','1'),(4659,31003,'袁欢','Female','X1','2019-01-28','RDC','Capgemini','Shanghai','2019-02-14','13241231222','4112412412@qq.com','长春工业大学','软件工程','本科','2020','CST ERP','CST ERP',NULL,NULL,'2019-07-29','Involuntary','Unsatisfactory Performance',NULL,NULL,'30123','1998-07-12',1,'2019-08-01 09:49:27',1,'2019-08-01 02:50:45','1'),(4673,34211,'张美丽','Female','C2','2019-07-09','East','Capgemini','Kunshan','2019-07-18','14568676767','sad@qq.com','北京大学','计算机','本科','2020','CST Cloud','Cyber Security','SG','SS',NULL,NULL,NULL,NULL,NULL,NULL,'1998-08-03',1,'2019-08-01 02:50:49',1,'2019-08-01 02:50:49','0'),(4674,31888,'华佗','Male','X1','2019-07-22','East','Capgemini','Kunshan','2019-07-22','18846436562','tong.a.li@capgemini.com','南京信息工程学院','计算机','本科','2020','CST ERP','CST ERP','SG','SS','2019-07-24','Voluntary','Better Pay/Rewards','Thanks.','LA','30831','1998-05-10',1,'2019-08-01 02:50:49',1,'2019-08-01 02:50:49','0'),(4675,31003,'袁欢','Female','X1','2019-01-28','RDC','Capgemini','Shanghai','2019-02-14','13241231222','4112412412@qq.com','长春工业大学','软件工程','本科','2020','CST ERP','CST ERP',NULL,NULL,'2019-07-29','Involuntary','Unsatisfactory Performance',NULL,NULL,'30123','1998-07-12',1,'2019-08-01 02:50:49',1,'2019-08-01 02:50:49','0'),(4676,30123,'延欢','Male','X2','2018-07-06','North','Capgemini','Kunshan','2018-12-10','18846436560','tong.a.li@capgemini.com','黑龙江大学','国贸','硕士','2019','CST ERP','CST ERP','SG','SS','2019-07-22','Voluntary','Better Pay/Rewards','Thanks.','LA','30111','1990-02-02',1,'2019-08-01 04:43:31',1,'2019-08-02 17:26:31','1'),(4677,30301,'李彤2','Male','X2','2018-07-07','North','Capgemini','Kunshan','2018-12-11','18846436560','tong.a.li@capgemini.com','黑龙江大学','国贸','硕士','2019','CST ERP','CST ERP','SG','SS','2019-07-23','Voluntary','Better Pay/Rewards','Thanks.','LA','30111','1990-02-03',1,'2019-08-01 04:44:26',1,'2019-08-01 05:04:33','1'),(4678,30302,'李彤3','Male','X2','2018-07-07','North','Capgemini','Kunshan','2018-12-11','18846436560','7466@qq.com','黑龙江大学','国贸','硕士','2019','CST ERP','CST ERP','SG','SS','2019-07-23','Voluntary','Better Pay/Rewards','Thanks.','LA','30111','1990-02-03',1,'2019-08-01 04:44:26',1,'2019-08-01 05:04:37','0'),(4679,30213,'李彤1','Male','X2','2018-07-07','North','Capgemini','Kunshan','2018-12-11','18846436560','tong.a.li@capgemini.com','黑龙江大学','国贸','硕士','2019','CST ERP','CST ERP','SG','SS','2019-07-23','Voluntary','Better Pay/Rewards','Thanks.','LA','30111','1990-02-03',1,'2019-08-01 05:35:43',1,'2019-08-01 05:35:58','0'),(4680,30172,'李帅','Male','X1','2019-07-18','North','Capgemini','Kunshan','2018-11-14','15189827779','tong.a.li@capgemini.com','南京信息工程大学','软件工程','本科','2019','CST PMO','CST PMO\r\n','SG','SS','2019-07-29','Voluntary','Better Pay/Rewards','Thanks.','LA','30100','1999-03-29',1,'2019-08-01 05:35:43',1,'2019-08-01 05:35:58','0');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_prize`
--

DROP TABLE IF EXISTS `employee_prize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_prize`
--

LOCK TABLES `employee_prize` WRITE;
/*!40000 ALTER TABLE `employee_prize` DISABLE KEYS */;
INSERT INTO `employee_prize` VALUES (98,32356,'奖学金','积极表现，朝气蓬勃',1,'2019-07-31 17:23:18',1,'2019-08-29 04:09:59','1'),(99,30171,'奖学金','在此次培训中，个人表现十分优异',1,'2019-07-31 17:25:28',1,'2019-08-29 04:09:59','1'),(100,32222,'奖学金','表现优异',1,'2019-07-31 17:26:24',1,'2019-08-29 04:09:59','1'),(101,32112,'雏鹰之星','xxxx',1,'2019-07-31 17:27:53',1,'2019-08-01 04:37:12','1'),(102,32112,'雏鹰之星','表现突出，个人表现优异',1,'2019-07-31 17:28:25',1,'2019-08-29 04:09:59','1'),(103,32112,'奖学金','在培训中获得了第一名',1,'2019-07-31 17:29:00',1,'2019-08-29 04:09:59','1'),(104,33125,'雏鹰之星','个人表现优异',1,'2019-07-31 17:37:30',1,'2019-08-29 04:09:59','1'),(105,32356,'奖学金','成绩优异，表现良好',1,'2019-07-31 18:06:38',1,'2019-08-29 04:09:59','1'),(107,30231,'雏鹰之星','March 2019',1,'2019-08-01 02:37:56',1,'2019-08-01 02:50:41','0'),(108,31211,'雏鹰之星','xxxxxxx',1,'2019-08-01 03:03:18',1,'2019-08-01 04:01:03','1'),(109,30231,'Project Angel','xxxx',1,'2019-08-01 03:58:10',1,'2019-08-01 04:00:58','1'),(110,31211,'Project Angel','xxx',1,'2019-08-01 03:58:42',1,'2019-08-01 04:00:55','1'),(111,32112,'奖学金','xxxx',1,'2019-08-01 03:59:41',1,'2019-08-01 04:00:53','1'),(112,31410,'雏鹰之星','xxx',1,'2019-08-01 03:59:59',1,'2019-08-01 04:00:52','1'),(113,31888,'雏鹰之星','xxxx',1,'2019-08-01 04:00:16',1,'2019-08-01 04:00:48','1'),(114,33421,'Project Angel','xxxxx',1,'2019-08-01 04:00:29',1,'2019-08-01 04:00:42','1'),(115,30231,'雏鹰之星','xxxxxxx',1,'2019-08-01 04:31:48',1,'2019-08-01 04:37:12','1'),(116,30231,'雏鹰之星','xxxxx',1,'2019-08-01 04:38:13',1,'2019-08-01 04:45:23','1'),(117,30231,'CSR','xxx',1,'2019-08-01 04:40:17',1,'2019-08-01 04:45:23','1'),(118,32112,'Project Angel','xxxx',1,'2019-08-01 04:45:15',1,'2019-08-29 04:09:59','1'),(119,30171,'Project Angel','xxx',26,'2019-08-01 04:49:19',26,'2019-08-01 05:01:15','1'),(120,30123,'雏鹰之星','表现优异',1,'2019-08-01 05:04:55',1,'2019-08-02 17:26:31','1'),(121,30231,'奖学金','一等奖',1,'2019-08-01 05:29:13',1,'2019-08-01 05:29:29','0'),(122,32112,'雏鹰之星','三等奖',27,'2019-08-01 05:32:41',1,'2019-08-01 05:34:00','1'),(123,34211,'雏鹰之星','11',1,'2019-08-20 21:03:40',1,'2019-08-20 21:03:40','0'),(124,34211,'CSR','21121',1,'2019-08-23 20:46:55',1,'2019-08-23 20:46:55','0');
/*!40000 ALTER TABLE `employee_prize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeestudydeclaration`
--

DROP TABLE IF EXISTS `employeestudydeclaration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeestudydeclaration`
--

LOCK TABLES `employeestudydeclaration` WRITE;
/*!40000 ALTER TABLE `employeestudydeclaration` DISABLE KEYS */;
INSERT INTO `employeestudydeclaration` VALUES (1,1234,'李齐宣','1','2','3','4',28,22,21,5,'百词斩','2019-08-21','无','0');
/*!40000 ALTER TABLE `employeestudydeclaration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeeteamtransfer`
--

DROP TABLE IF EXISTS `employeeteamtransfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeeteamtransfer`
--

LOCK TABLES `employeeteamtransfer` WRITE;
/*!40000 ALTER TABLE `employeeteamtransfer` DISABLE KEYS */;
INSERT INTO `employeeteamtransfer` VALUES (4681,121,'1231','1231','312','231','312',1,'2018-12-14','0'),(4682,0,'string','string','string','string','string',1,NULL,'0'),(4683,0,'string','string','string','string','string',1,'2019-08-20','0'),(4684,0,'st2121g','string','string','string','string',1,'2019-08-20','0'),(4685,0,'string','string','string','string','string',1,'2019-08-20','0'),(4686,0,'lqx','string','string','string','string',1,'2019-08-20','0'),(4687,0,'string','string','string','string','string',1,'2019-08-20','0'),(4688,0,'string','string','string','string','string',1,'2019-08-20','0'),(4689,0,'李齐宣','string','string','string','string',1,'2019-08-20','0');
/*!40000 ALTER TABLE `employeeteamtransfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empmsg`
--

DROP TABLE IF EXISTS `empmsg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empmsg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `empMsg` varchar(50) DEFAULT NULL COMMENT '消息内容）',
  `createTime` datetime DEFAULT NULL COMMENT '展示请用MM-DD-YYYY',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4806 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empmsg`
--

LOCK TABLES `empmsg` WRITE;
/*!40000 ALTER TABLE `empmsg` DISABLE KEYS */;
INSERT INTO `empmsg` VALUES (4799,'李彤添加了认证申报','2019-09-03 09:55:03','0'),(4800,'李彤添加了认证申报','2019-09-03 09:57:25','0'),(4801,'李彤3 提交了本月学习申报','2019-09-04 11:14:59','0'),(4802,'李彤3修改了本月学习申报','2019-09-04 11:15:21','0'),(4803,'李彤3添加了认证申报','2019-09-04 11:15:41','0'),(4804,'李彤3添加了认证申报','2019-09-04 11:16:15','0'),(4805,'李彤 提交了本月学习申报','2019-09-04 11:22:43','0');
/*!40000 ALTER TABLE `empmsg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `growing_experience`
--

DROP TABLE IF EXISTS `growing_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `growing_experience`
--

LOCK TABLES `growing_experience` WRITE;
/*!40000 ALTER TABLE `growing_experience` DISABLE KEYS */;
INSERT INTO `growing_experience` VALUES (37,30231,'技术培训','有关开发的技术',1,'2019-07-31 18:40:42',30231,'2019-08-29 17:04:41','0'),(38,31111,'PPT技能培训','培训制作PPT的技巧',1,'2019-07-31 18:42:47',1,'2019-08-01 03:05:40','1'),(39,31003,'Excel技能训练','培训员工使用EXCEL的技巧',1,'2019-07-31 18:45:22',1,'2019-08-01 02:50:45','0'),(40,31211,'演讲培训','培训学员演讲能力',1,'2019-07-31 18:49:30',1,'2019-08-28 07:31:57','1'),(43,30231,'JTP技术培训','无嗡嗡嗡',1,'2019-08-01 09:00:53',1,'2019-08-29 04:09:29','1'),(44,30171,'xx','xx',1,'2019-08-01 03:59:09',1,'2019-08-01 03:59:19','1'),(45,31410,'PPT技能培训','培训员工PPT技能',1,'2019-08-01 04:26:50',1,'2019-08-28 07:32:01','1'),(46,30123,'111','111',1,'2019-08-01 05:05:55',1,'2019-08-02 17:26:31','1'),(47,34211,'2121','2121',1,'2019-08-20 22:23:51',1,'2019-08-28 07:49:20','1'),(48,31888,'后端培训','学习了SpringBoot',30231,'2019-08-26 14:20:16',30231,'2019-08-26 14:20:16','0'),(50,30231,'springboot','不菜',1,'2019-08-26 23:00:22',30231,'2019-08-28 08:02:53','0'),(51,30231,'vue','学学vue',30231,'2019-08-26 23:07:19',30231,'2019-08-26 23:13:45','1'),(52,30231,'Meeting minutes','共识很关键',30231,'2019-08-26 23:36:32',1,'2019-08-27 10:58:06','1'),(53,30231,'敏捷方法论','保守主义瓦达',1,'2019-08-27 00:33:14',1,'2019-08-28 07:49:13','1'),(54,30231,'敏捷思维','bla',30231,'2019-08-27 10:56:21',1,'2019-08-28 07:49:31','1'),(55,31888,'华佗','CST ERP',1,'2019-08-27 19:48:52',1,'2019-08-28 07:32:14','1'),(56,31888,'华佗','CST ERP',1,'2019-08-27 19:50:48',1,'2019-08-28 07:32:14','1'),(57,30231,'333','6666',30231,'2019-08-28 00:07:43',1,'2019-08-28 07:49:05','1'),(58,30231,'BA','好好学习',30231,'2019-08-28 16:00:56',1,'2019-08-29 04:09:29','1'),(59,30231,'VUE','还需进步',30231,'2019-08-28 17:54:13',1,'2019-08-29 04:09:29','1'),(60,30231,'威威','达瓦达瓦',30231,'2019-08-28 18:04:24',1,'2019-08-29 04:09:29','1'),(61,30231,'VUE','需要学习前端技术',30231,'2019-08-29 09:29:17',30231,'2019-08-29 09:29:17','0'),(62,30231,'JAVA','JAVAEE',30231,'2019-08-29 17:42:14',30231,'2019-08-29 17:42:14','0'),(63,30231,'C++','c++',30231,'2019-08-29 21:17:35',30231,'2019-08-29 21:17:35','0'),(64,30231,'11','11',30231,'2019-08-29 22:42:52',30231,'2019-08-29 22:42:52','0'),(65,30231,'1','1',1,'2019-09-03 09:42:52',1,'2019-09-03 09:42:52','0'),(66,30231,'3','3',30231,'2019-09-03 09:47:07',30231,'2019-09-03 09:47:07','0'),(67,30231,'4','4',30231,'2019-09-03 09:49:15',30231,'2019-09-03 09:49:15','0'),(68,30231,'5','5',30231,'2019-09-03 09:50:40',30231,'2019-09-03 09:50:40','0'),(69,30231,'5','5',30231,'2019-09-03 09:55:03',30231,'2019-09-03 09:55:03','0'),(70,30231,'6','6',30231,'2019-09-03 09:57:25',30231,'2019-09-03 09:57:25','0'),(71,30302,'C#','c#',30302,'2019-09-04 11:15:41',30302,'2019-09-04 11:15:41','0'),(72,30302,'JAVA','JAVA',30302,'2019-09-04 11:16:15',30302,'2019-09-04 11:16:15','0');
/*!40000 ALTER TABLE `growing_experience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `headmaster_talk`
--

DROP TABLE IF EXISTS `headmaster_talk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `headmaster_talk`
--

LOCK TABLES `headmaster_talk` WRITE;
/*!40000 ALTER TABLE `headmaster_talk` DISABLE KEYS */;
INSERT INTO `headmaster_talk` VALUES (35,31888,'职位意向','希望做什么工作','Yolanda',1,'2019-07-31 19:00:45',1,'2019-08-01 02:50:45','0'),(36,31888,'去留意向','希望你留下','Diane',1,'2019-07-31 19:01:28',1,'2019-08-01 02:50:45','0'),(37,32222,'心理问题','最近心态不好，心情出现了问题，导致抑郁寡欢','Yolanda',1,'2019-07-31 19:06:04',1,'2019-08-28 07:48:53','1'),(38,32356,'态度矫正','工作量大，想要放弃，劝说一番','Danie',1,'2019-07-31 19:08:44',1,'2019-08-28 07:48:55','1'),(39,32112,'心理问题','心情抑郁','Danie',1,'2019-08-01 03:42:52',1,'2019-08-01 03:43:00','1'),(40,33125,'深入沟通','对未来充满了迷茫','Daisy',1,'2019-08-01 04:03:41',1,'2019-08-28 07:48:57','1'),(41,30123,'111','111','111',1,'2019-08-01 05:06:02',1,'2019-08-02 17:26:31','1'),(42,30231,'regular当发生的办法是空间','最近很累圣诞节发就后端开发设计哈空间','yolanda',1,'2019-08-02 17:23:22',1,'2019-08-02 17:25:51','1');
/*!40000 ALTER TABLE `headmaster_talk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr`
--

DROP TABLE IF EXISTS `hr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr`
--

LOCK TABLES `hr` WRITE;
/*!40000 ALTER TABLE `hr` DISABLE KEYS */;
INSERT INTO `hr` VALUES (1,'Zhao, Yolanda',NULL,NULL,NULL,1,'admin','$2a$10$6gVFR0gPLsBhxBsqqGD3Uu41PnBanOBPdBiPASOwjHpCD047inatO',NULL,NULL,NULL,NULL),(14,'Li, Daisy',NULL,NULL,NULL,1,'normal','$2a$10$XIQoLbvm4tZEPGHdvAiGZu8SULDuN3QZrUWdeQPtkkBLca7Tq41me',NULL,NULL,NULL,NULL),(19,'罗',NULL,NULL,NULL,1,'Luo','$2a$10$K0oxy7Jbepw9o3R6ZVJptu3HquIM5lyFsaPxPMxjgnIiM9aR7vjwm',NULL,NULL,NULL,NULL),(23,'华',NULL,NULL,NULL,1,'huabin','$2a$10$QSxfkuxau5.SnEQi08VyEOjFkIK2nUOVSbFzfVVuMnxLN1AQqK0tq',NULL,NULL,NULL,NULL),(24,'杨可欣',NULL,NULL,NULL,1,'kexin.yang','$2a$10$Ko87lf.AWu03zNgyMVYH4.EekVKUGcDwMFkbUUBvV8w.xHo6habTK',NULL,NULL,NULL,NULL),(25,'Shawn',NULL,NULL,NULL,1,'Shawn.chao','$2a$10$bRz.IO3.5A9WKbqYR7EmS..C8.tqAuMR79b03wfhLv35hAr7P4lDK',NULL,NULL,NULL,NULL),(28,'TEST',NULL,NULL,NULL,1,'test','$2a$10$T6GQcDjX5iNeQzTYXR/ZKuukYdxEz.XDCV27DdSc9lODn8NH6k/Mm',NULL,NULL,NULL,NULL),(30231,'李彤',NULL,NULL,NULL,1,'jtp30231','$2a$10$tPKJUseGOrf5iCKpxj0CD.YPsbCtkNeeL8z8tm13kE7LhUh7tYXM6',NULL,NULL,NULL,NULL),(30231,'李彤',NULL,NULL,NULL,1,'jtp30172','$2a$10$7mzWLkSkwmfqb2Swuvf6XeAd2mOzOlgvx1YYaYpOE9C3s9OjQ.rP.',NULL,NULL,NULL,NULL),(30123,NULL,NULL,NULL,NULL,1,'jtp30123','$2a$10$oOllogkCAE.e0K3KbG.AFOxNI0TXTWSEvUYeKHZip54KussKdKFG2',NULL,NULL,NULL,NULL),(30888,NULL,NULL,NULL,NULL,1,'jtp31888','$2a$10$gnMxjrmPjiqFwlK6iZjfUO8pw.krntQk1QfkMXawJGZuWpzpqpmn.',NULL,NULL,NULL,NULL),(31888,'华佗',NULL,NULL,NULL,1,'jtp30888','$2a$10$KnmzJqlXL9ZMsFZyhQ.T1u0Pr.K6MDXjch3tfamEjGs2Y0vZ0Q23i',NULL,NULL,NULL,NULL),(30231,'李彤',NULL,NULL,NULL,1,'jtp30213','$2a$10$7lPtT8.W5WgwkU5TOp9BxOrbySndBO8.A0A.19/N1dcxPeD677kMi',NULL,NULL,NULL,NULL),(30213,'李彤1',NULL,NULL,NULL,1,'lqx1234','$2a$10$/Y0M62e.PHzGZo5LgEG91OBDnAanYzrvcceSH1jae0QoZLwkCHad2',NULL,NULL,NULL,NULL),(32222,'王超',NULL,NULL,NULL,1,'kept0610','$2a$10$MKCXy7E2fK.F4ZHNuPDy3OM9gibsMWMTo4h.3nSkxJRL1CtLhPlKu',NULL,NULL,NULL,NULL),(31211,'李渡',NULL,NULL,NULL,1,'lqx12345','$2a$10$Hfi5hcjvG70/OCDO7wVUouAyHXxfRjA6AC.F5BboA6ZbePm49pJFu',NULL,NULL,NULL,NULL),(30302,'李彤3',NULL,NULL,NULL,1,'lqx123456789','$2a$10$2hLDXNTOZz8yLIzYMZFX9eJKlZnB3mjrlCplMEfNhqtEB98xcTKnK',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_msg`
--

DROP TABLE IF EXISTS `hr_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `msgId` bigint(20) DEFAULT NULL COMMENT '消息ID',
  `hrId` bigint(20) DEFAULT NULL COMMENT 'HRid',
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4708 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_msg`
--

LOCK TABLES `hr_msg` WRITE;
/*!40000 ALTER TABLE `hr_msg` DISABLE KEYS */;
INSERT INTO `hr_msg` VALUES (4706,4800,1,'0'),(4707,4805,1,'0');
/*!40000 ALTER TABLE `hr_msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_role`
--

DROP TABLE IF EXISTS `hr_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hrid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  KEY `hr_role_ibfk_1` (`hrid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_role`
--

LOCK TABLES `hr_role` WRITE;
/*!40000 ALTER TABLE `hr_role` DISABLE KEYS */;
INSERT INTO `hr_role` VALUES (51,14,1),(55,19,1),(58,22,1),(59,1,2),(60,23,1),(61,24,1),(62,25,1),(65,28,1),(66,30231,3),(70,31211,3),(71,30302,3);
/*!40000 ALTER TABLE `hr_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'/',NULL,NULL,'所有',NULL,NULL,NULL,NULL,1),(2,'/','/home','Home','员工资料','fa fa-user-circle-o',NULL,1,1,1),(3,'/','/home','Home','人事管理','fa fa-address-card-o',NULL,1,1,1),(4,'/','/home','Home','薪资管理','fa fa-money',NULL,1,1,1),(5,'/','/home','Home','统计管理','fa fa-bar-chart',NULL,1,1,1),(6,'/','/home','Home','系统管理','fa fa-windows',NULL,1,1,1),(7,'/employee/basic/**','/emp/basic','EmpBasic','基本资料',NULL,NULL,1,2,1),(8,'/employee/advanced/**','/emp/adv','EmpAdv','高级资料',NULL,NULL,1,2,0),(9,'/employee/prize/**','/emp/prize','EmpPrize','获奖情况',NULL,NULL,1,2,1),(10,'/employee/experience/**','/emp/learning','EmpLearning','培训经历',NULL,NULL,1,2,1),(11,'/employee/project/**','/emp/project','EmpProject','项目经历',NULL,NULL,1,2,1),(12,'/employee/talk/**','/emp/talking','EmpTalking','校长访谈',NULL,NULL,1,2,1),(13,'/employee/team/**','/emp/teamtsf','EmpTeamtsf','团队变更',NULL,NULL,1,2,1),(14,'/employee/teamtsf/**','/emp/EmpStudyDecoration','EmpStudyDecoration','学习申报',NULL,NULL,1,2,1),(15,'/','/home','home','用户相关','fa fa-user-circle-o',NULL,1,1,1),(16,'/staff/baseInfo/**','./baseInfo','baseInfo','个人资料',NULL,NULL,1,15,1),(17,'/staff/declaration/**','./declaration','declaration','学习申报',NULL,NULL,1,15,1),(18,'/employee/admin/**','/emp/admin','EmpAdmin','用户管理',NULL,NULL,1,2,1),(19,'/staff/certificateDecoration/**','./certificateDecoration','certificateDecoration','认证申报',NULL,NULL,1,15,1),(20,'/employee/chart/**','/emp/chart','EmpChart','图表展示',NULL,NULL,1,2,1),(21,'/employee/EmpMsg/**','/EmpMsg','EmpMsg','消息通知',NULL,NULL,1,2,1);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_role`
--

DROP TABLE IF EXISTS `menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `mid` (`mid`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_role`
--

LOCK TABLES `menu_role` WRITE;
/*!40000 ALTER TABLE `menu_role` DISABLE KEYS */;
INSERT INTO `menu_role` VALUES (278,1,2),(279,2,2),(280,3,2),(281,4,2),(282,5,2),(283,6,2),(284,7,2),(285,8,2),(286,9,2),(287,10,2),(288,11,2),(289,12,2),(290,13,2),(291,1,1),(292,2,1),(293,3,1),(294,4,1),(295,5,1),(296,6,1),(297,7,1),(298,8,1),(299,9,1),(300,10,1),(301,11,1),(302,12,1),(304,14,1),(305,14,2),(306,15,3),(307,16,3),(308,17,3),(310,19,3),(311,20,2),(312,21,2),(313,14,1),(314,21,1),(315,20,1),(316,13,1),(317,18,2);
/*!40000 ALTER TABLE `menu_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_experience`
--

DROP TABLE IF EXISTS `project_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_experience`
--

LOCK TABLES `project_experience` WRITE;
/*!40000 ALTER TABLE `project_experience` DISABLE KEYS */;
INSERT INTO `project_experience` VALUES (36,32222,'Yolanda','JTP员工管理系统','朱逸寅','2019-07-13','2019-07-30',1,'2019-07-31 18:10:54',1,'2019-08-28 07:31:51','1'),(37,31211,'Yolanda','JTP项目管理系统','朱逸寅','2019-07-14','2019-07-31',1,'2019-07-31 18:11:33',1,'2019-08-29 04:09:04','1'),(38,32356,'华为','华为手机5G开发','金泽萱','2019-07-02','2019-07-29',1,'2019-07-31 18:22:21',1,'2019-08-29 04:09:11','1'),(39,33125,'华为','华为手机5G开发','金泽萱','2019-07-05','2019-07-29',1,'2019-07-31 18:26:03',1,'2019-08-29 04:09:11','1'),(40,30231,'华斌','自动化','李煌','2019-08-06','2019-08-29',1,'2019-08-01 09:00:30',1,'2019-08-01 02:50:41','0'),(41,30123,'111','111','111',NULL,NULL,1,'2019-08-01 05:05:46',1,'2019-08-02 17:26:31','1'),(42,32330,'华为','华为手机P50研发','刘勇','2019-07-03','2019-07-30',1,'2019-08-01 05:07:59',1,'2019-08-29 04:09:11','1');
/*!40000 ALTER TABLE `project_experience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_user','用户'),(2,'ROLE_admin','管理员'),(3,'ROLE_staff','员工');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studydeclaration`
--

DROP TABLE IF EXISTS `studydeclaration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studydeclaration`
--

LOCK TABLES `studydeclaration` WRITE;
/*!40000 ALTER TABLE `studydeclaration` DISABLE KEYS */;
INSERT INTO `studydeclaration` VALUES (4702,30231,'major','major','major',22,30,0,5,5,0,30231,'2019-07-28 16:00:11',1,'2019-09-04 13:41:04','wadada','1'),(4703,30231,'major','major','major',22,30,15,5,5,0,1,'2019-09-28 19:41:03',1,'2019-09-04 13:12:53','2019-07-28','0'),(4710,30231,'1','1','1',1,1,1,1,1,1,30231,'2019-09-02 11:43:04',1,'2019-09-04 13:18:52','1666','1'),(4711,0,'string','string','string',0,0,0,0,0,0,1,'2019-09-02 21:55:04',1,'2019-09-04 13:12:58','string','1'),(4712,66,'6','6','6',6,6,6,6,6,66,1,'2019-09-02 21:59:31',1,'2019-09-04 13:12:56','66666','1'),(4713,555,'55','5','5',5,5,5,5,5,5,1,'2019-09-02 22:19:55',1,'2019-09-02 23:10:39','5','1'),(4714,31888,'1','1','1',1,1,1,1,1,1,1,'2019-09-03 09:31:14',1,'2019-09-03 09:42:43','1','1'),(4715,30231,'1','1','1',1,1,1,1,1,1,1,'2019-09-03 09:43:31',1,'2019-09-04 00:09:24','1','1'),(4716,30231,'1','1','1',1,1,1,1,1,1,1,'2019-09-04 00:09:32',1,'2019-09-04 00:09:39','1','1'),(4717,31888,'1','1','1',1,1,1,1,1,1,1,'2019-09-04 09:20:36',1,'2019-09-04 11:23:34','1','1'),(4718,30302,'33','1','2',1,1,1,1,1,1,30302,'2019-09-04 11:15:00',1,'2019-09-04 11:17:57','1','1'),(4719,30302,'2','2','2',2,2,2,2,2,2,1,'2019-09-04 11:18:07',1,'2019-09-04 11:18:11','2','1'),(4720,30231,'55','5','5',5,5,5,5,5,5,30231,'2019-09-04 11:22:43',1,'2019-09-04 11:23:34','5','1');
/*!40000 ALTER TABLE `studydeclaration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teamchangelog`
--

DROP TABLE IF EXISTS `teamchangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teamchangelog`
--

LOCK TABLES `teamchangelog` WRITE;
/*!40000 ALTER TABLE `teamchangelog` DISABLE KEYS */;
INSERT INTO `teamchangelog` VALUES (4762,30231,'CST ERP','CST ERP','CST HCM','SAP',1,'2019-08-29',1,'2019-08-29',NULL,'1'),(4763,34211,'CST ERP','CST ERP','CST Cloud','Cyber Security',1,'2019-08-29',1,'2019-08-29',NULL,'0'),(4764,31888,'CST HCM','HCM','CST RDC','CSD',1,'2019-08-29',1,'2019-08-29',NULL,'0'),(4765,30231,'CST HCM','SAP','CST Central','CTO',1,'2019-08-29',1,'2019-08-29',NULL,'1'),(4766,30231,'CST Central','CTO','CST ERP','CST ERP',30231,'2019-08-29',1,'2019-09-02',NULL,'1'),(4767,30231,'CST Cloud','Testing','CST PMO','CST PMO',1,'2019-08-29',1,'2019-08-29',NULL,'1'),(4768,30231,'CST ERP','CST ERP','CST ERP','CST ERP',30231,'2019-08-29',30231,'2019-08-29',NULL,'0'),(4769,30231,'CST ERP','CST ERP','CST Cloud','Testing',1,'2019-08-29',1,'2019-08-29',NULL,'0'),(4770,30231,'CST Cloud','Testing','CST PMO','CST PMO',1,'2019-08-29',1,'2019-08-29',NULL,'1'),(4771,0,'string','string','string','string',1,'2019-09-02',1,'2019-09-02','2019-09-02','1'),(4772,6660,'CST ERP','CST ERP','CST ERP','CST ERP',1,'2019-09-02',1,'2019-09-02',NULL,'1'),(4773,30231,'CST PMO','CST PMO','CST PMO','CST PMO',1,'2019-09-02',1,'2019-09-02',NULL,'1'),(4774,30231,'CST ERP','CST ERP','CST ERP','CST ERP',1,'2019-09-02',1,'2019-09-02',NULL,'1'),(4775,30231,'CST ERP','CST ERP','CST ERP','CST ERP',1,'2019-09-02',1,'2019-09-02',NULL,'1'),(4776,30231,'CST ERP','CST ERP','CST ERP','CST ERP',1,'2019-09-02',1,'2019-09-02',NULL,'1'),(4777,31888,'CST ERP','CST ERP','CST ERP','CST ERP',1,'2019-09-03',1,'2019-09-03',NULL,'1'),(4778,30231,'CST ERP','CST ERP','CST Cloud','Testing',1,'2019-09-04',1,'2019-09-04',NULL,'1'),(4779,30231,'CST Cloud','Testing','CST LAB','CST LAB',1,'2019-09-04',1,'2019-09-04',NULL,'1'),(4780,30231,'CST ERP','CST ERP','CST ERP','CST ERP',1,'2019-09-04',1,'2019-09-04',NULL,'1'),(4781,30231,'CST ERP','CST ERP','CST ERP','CST ERP',1,'2019-09-04',1,'2019-09-04',NULL,'1'),(4782,30231,'CST ERP','CST ERP','CST ERP','CST ERP',1,'2019-09-04',1,'2019-09-04',NULL,'1');
/*!40000 ALTER TABLE `teamchangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verficode`
--

DROP TABLE IF EXISTS `verficode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verficode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `staffUsername` varchar(50) DEFAULT NULL COMMENT '账号',
  `Verficode` bigint(20) DEFAULT NULL COMMENT '验证码',
  `createTime` datetime DEFAULT NULL,
  `isDeleted` varchar(1) DEFAULT '0' COMMENT '删除标志位（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4847 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verficode`
--

LOCK TABLES `verficode` WRITE;
/*!40000 ALTER TABLE `verficode` DISABLE KEYS */;
INSERT INTO `verficode` VALUES (1,'jtp30231',641886,'2019-08-26 20:40:07','0'),(2,'jtp30231',933673,'2019-08-26 20:44:30','0'),(3,'jtp30231',460730,'2019-08-26 20:47:01','0'),(4684,'jtp30231',534327,'2019-08-26 20:49:12','0'),(4685,'jtp30231',229883,'2019-08-26 20:52:06','0'),(4686,'jtp30231',550790,'2019-08-26 20:52:46','0'),(4687,'jtp30231',603883,'2019-08-26 21:45:51','0'),(4688,'jtp30231',241733,'2019-08-26 21:50:31','0'),(4689,'jtp30231',364531,'2019-08-26 21:53:39','0'),(4690,'jtp30231',914049,'2019-08-26 22:10:21','0'),(4691,'jtp30231',477926,'2019-08-26 22:20:10','0'),(4692,'jtp30231',644542,'2019-08-26 22:25:46','0'),(4693,'jtp30231',736506,'2019-08-28 21:07:16','0'),(4694,'jtp30231',798819,'2019-08-28 23:08:09','0'),(4695,'jtp30231',786229,'2019-08-28 23:38:48','0'),(4696,'jtp30231',383344,'2019-08-28 23:42:58','0'),(4697,'jtp30231',511791,'2019-08-28 23:43:45','0'),(4698,'jtp30231',685977,'2019-08-28 23:48:10','0'),(4699,'jtp30231',261795,'2019-08-28 23:49:41','0'),(4700,'jtp30231',884110,'2019-08-28 23:53:47','0'),(4701,'jtp30231',482192,'2019-08-28 23:55:47','0'),(4702,'jtp30231',654047,'2019-08-29 00:03:59','0'),(4703,'jtp30231',103188,'2019-08-29 00:05:02','0'),(4704,'jtp30231',826459,'2019-08-29 00:06:57','0'),(4705,'jtp30231',312033,'2019-08-29 00:08:07','0'),(4706,'jtp30231',680905,'2019-08-29 00:11:13','0'),(4707,'jtp30231',507911,'2019-08-29 00:23:54','0'),(4708,'jtp30231',536159,'2019-08-29 00:26:36','0'),(4709,'jtp30231',404294,'2019-08-29 00:46:16','0'),(4710,'jtp30231',138240,'2019-08-29 01:15:14','0'),(4711,'jtp30231',413742,'2019-08-29 01:18:03','0'),(4712,NULL,834789,'2019-08-29 02:09:17','0'),(4713,NULL,854857,'2019-08-29 02:09:32','0'),(4714,NULL,804064,'2019-08-29 02:09:36','0'),(4715,'jtp30231',447697,'2019-08-29 02:15:12','0'),(4716,'jtp30231',733809,'2019-08-29 02:17:28','0'),(4717,'jtp30231',994509,'2019-08-29 02:17:44','0'),(4718,'jtp30231',652898,'2019-08-29 02:21:22','0'),(4719,'jtp30231',490628,'2019-08-29 02:21:39','0'),(4720,'jtp30231',457538,'2019-08-29 02:23:22','0'),(4721,'jtp30231',985813,'2019-08-29 02:23:38','0'),(4722,'jtp30231',551786,'2019-08-29 02:23:38','0'),(4723,'jtp30231',459665,'2019-08-29 02:23:38','0'),(4724,'jtp30231',968417,'2019-08-29 02:24:10','0'),(4725,'jtp30231',104945,'2019-08-29 02:24:25','0'),(4726,'jtp30231',134597,'2019-08-29 02:32:07','0'),(4727,'jtp30231',639565,'2019-08-29 02:32:12','0'),(4728,'jtp30231',226797,'2019-08-29 02:32:19','0'),(4729,'jtp30231',355505,'2019-08-29 02:32:20','0'),(4730,'jtp30231',908826,'2019-08-29 02:32:21','0'),(4731,'jtp30231',984757,'2019-08-29 02:33:36','0'),(4732,'jtp30231',662659,'2019-08-29 02:34:10','0'),(4733,'jtp30231',277297,'2019-08-29 02:35:59','0'),(4734,'jtp30231',549553,'2019-08-29 02:36:00','0'),(4735,'jtp30231',688695,'2019-08-29 02:36:01','0'),(4736,'jtp30231',536625,'2019-08-29 02:36:01','0'),(4737,'JTP30231',665708,'2019-08-29 02:36:50','0'),(4738,'JTP30231',847434,'2019-08-29 02:36:57','0'),(4739,'JTP30231',927732,'2019-08-29 02:45:53','0'),(4740,'JTP30231',586740,'2019-08-29 02:45:57','0'),(4741,'JTP30231',983884,'2019-08-29 02:45:58','0'),(4742,'',693971,'2019-08-29 02:49:09','0'),(4743,'jtp30231',812532,'2019-08-29 02:49:18','0'),(4744,'jtp30231',795825,'2019-08-29 02:49:20','0'),(4745,'jtp30231',229493,'2019-08-29 02:49:21','0'),(4746,'jtp30231',970047,'2019-08-29 02:49:21','0'),(4747,'jtp30231',374962,'2019-08-29 02:53:11','0'),(4748,'jtp30231',975040,'2019-08-29 02:53:16','0'),(4749,'jtp30231',571182,'2019-08-29 02:53:16','0'),(4750,'jtp30231',881012,'2019-08-29 02:53:29','0'),(4751,'jtp30231',137668,'2019-08-29 02:53:30','0'),(4752,'jtp30231',342571,'2019-08-29 02:55:26','0'),(4753,'jtp30231',664879,'2019-08-29 02:55:31','0'),(4754,'jtp30231',205616,'2019-08-29 02:55:31','0'),(4755,'jtp30231',160246,'2019-08-29 02:55:32','0'),(4756,'jtp30231',278172,'2019-08-29 02:57:48','0'),(4757,'jtp30231',999614,'2019-08-29 02:58:28','0'),(4758,'jtp30231',213714,'2019-08-29 02:59:23','0'),(4759,'jtp30231',126096,'2019-08-29 02:59:51','0'),(4760,'jtp30231',594182,'2019-08-29 02:59:52','0'),(4761,'jtp30231',534853,'2019-08-29 02:59:52','0'),(4762,'jtp30231',472425,'2019-08-29 02:59:53','0'),(4763,'jtp30231',103167,'2019-08-29 02:59:53','0'),(4764,'jtp30231',755980,'2019-08-29 02:59:53','0'),(4765,'jtp30231',371293,'2019-08-29 02:59:53','0'),(4766,'jtp30231',928127,'2019-08-29 03:00:16','0'),(4767,'jtp30231',740924,'2019-08-29 03:00:33','0'),(4768,'jtp30231',701990,'2019-08-29 03:00:33','0'),(4769,'jtp30231',438561,'2019-08-29 03:01:08','0'),(4770,'jtp30231',659400,'2019-08-29 03:01:43','0'),(4771,'jtp30231',133890,'2019-08-29 03:02:33','0'),(4772,'jtp30231',210341,'2019-08-29 03:02:36','0'),(4773,'jtp30231',215417,'2019-08-29 03:02:37','0'),(4774,'jtp30231',819549,'2019-08-29 03:03:43','0'),(4775,'jtp30231',403342,'2019-08-29 03:03:44','0'),(4776,'jtp30231',534379,'2019-08-29 03:03:45','0'),(4777,'jtp30231',633270,'2019-08-29 03:03:45','0'),(4778,'jtp30231',338984,'2019-08-29 03:03:45','0'),(4779,'jtp30231',923036,'2019-08-29 03:03:49','0'),(4780,'',182447,'2019-08-29 03:08:37','0'),(4781,'jtp30231',152240,'2019-08-29 03:08:50','0'),(4782,'jtp30231',837555,'2019-08-29 03:09:30','0'),(4783,'jtp30231',242290,'2019-08-29 03:09:55','0'),(4784,'jtp30231',448689,'2019-08-29 03:10:09','0'),(4785,'jtp30231',640466,'2019-08-29 03:10:25','0'),(4786,'jtp30231',304802,'2019-08-29 03:10:55','0'),(4787,'jtp30231',340308,'2019-08-29 03:11:20','0'),(4788,'jtp30231',211138,'2019-08-29 03:11:59','0'),(4789,'jtp30231',832011,'2019-08-29 03:12:13','0'),(4790,'jtp30231',142167,'2019-08-29 03:12:26','0'),(4791,'jtp30231',279338,'2019-08-29 03:12:26','0'),(4792,'jtp30231',400903,'2019-08-29 03:12:26','0'),(4793,'jtp30231',786470,'2019-08-29 03:12:26','0'),(4794,'jtp30231',725335,'2019-08-29 03:12:27','0'),(4795,'jtp30231',511038,'2019-08-29 03:12:30','0'),(4796,'jtp30231',712027,'2019-08-29 03:12:31','0'),(4797,'jtp30231',724246,'2019-08-29 03:12:31','0'),(4798,'jtp30231',337170,'2019-08-29 03:12:31','0'),(4799,'jtp30231',630423,'2019-08-29 03:12:31','0'),(4800,'jtp30231',365415,'2019-08-29 03:12:50','0'),(4801,'jtp30231',365481,'2019-08-29 03:12:59','0'),(4802,'jtp30231',742137,'2019-08-29 03:13:27','0'),(4803,'jtp30231',864768,'2019-08-29 03:15:11','0'),(4804,'jtp30231',362212,'2019-08-29 03:16:31','0'),(4805,'jtp30231',457756,'2019-08-29 03:16:43','0'),(4806,'jtp30231',321605,'2019-08-29 03:16:45','0'),(4807,'jtp30231',331075,'2019-08-29 03:20:19','0'),(4808,'jtp30231',662117,'2019-08-29 03:20:47','0'),(4809,'jtp30231',837919,'2019-08-29 03:20:47','0'),(4810,'jtp30231',866112,'2019-08-29 03:20:50','0'),(4811,'jtp30231',495029,'2019-08-29 03:20:50','0'),(4812,'jtp30231',200314,'2019-08-29 03:21:11','0'),(4813,'jtp30231',736788,'2019-08-29 03:23:06','0'),(4814,'jtp30231',325451,'2019-08-29 03:23:19','0'),(4815,'jtp30231',116772,'2019-08-29 03:23:21','0'),(4816,'jtp30231',113089,'2019-08-29 03:23:22','0'),(4817,'jtp30231',201975,'2019-08-29 03:23:58','0'),(4818,'jtp30231',750343,'2019-08-29 03:24:10','0'),(4819,'jtp30231',974442,'2019-08-29 03:38:01','0'),(4820,'jtp30231',855831,'2019-08-29 03:39:19','0'),(4821,'JTP30231',905632,'2019-08-29 03:54:59','0'),(4822,'jtp30231',985330,'2019-08-29 03:56:00','0'),(4823,'jtp30231',926065,'2019-08-29 09:24:21','0'),(4824,'jtp30231',752551,'2019-08-29 09:39:19','0'),(4825,'jtp30231',218665,'2019-08-29 15:54:41','0'),(4826,'jtp30231',596275,'2019-08-29 15:56:51','0'),(4827,'jtp30231',908007,'2019-08-29 17:03:21','0'),(4828,'jtp30231',658188,'2019-08-29 17:38:41','0'),(4829,'jtp30231',445117,'2019-08-29 21:16:16','0'),(4830,NULL,679374,'2019-09-02 14:34:31','0'),(4831,NULL,710265,'2019-09-02 15:30:02','0'),(4832,'jtp30231',542109,'2019-09-02 16:10:58','0'),(4833,NULL,575127,'2019-09-02 16:12:29','0'),(4834,NULL,188942,'2019-09-02 16:30:38','0'),(4835,NULL,918395,'2019-09-02 17:19:35','0'),(4836,NULL,800612,'2019-09-02 17:21:47','0'),(4837,NULL,827079,'2019-09-02 17:48:54','0'),(4838,NULL,605438,'2019-09-02 17:49:01','0'),(4839,'jtp30231',967100,'2019-09-03 23:54:17','0'),(4840,NULL,428937,'2019-09-04 00:00:06','0'),(4841,NULL,319141,'2019-09-04 00:03:20','0'),(4842,NULL,949154,'2019-09-04 00:05:25','0'),(4843,'jtp30231',277446,'2019-09-04 00:19:11','0'),(4844,NULL,616316,'2019-09-04 00:19:46','0'),(4845,NULL,650256,'2019-09-04 11:14:08','0'),(4846,'jtp30231',561278,'2019-09-04 11:24:30','0');
/*!40000 ALTER TABLE `verficode` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-04 13:45:54
