-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: oti
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `oti`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `oti` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oti`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeNO` varchar(255) NOT NULL,
  `RealName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Role` varchar(255) NOT NULL,
  `Department` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'9521','Ada','TIBDbyATMOw=','TM','HR'),(2,'9522','Ben','TIBDbyATMOw=','TL','HR'),(3,'9523','Cindy','TIBDbyATMOw=','ADMIN','HR'),(4,'9524','David','TIBDbyATMOw=','TM','IT'),(5,'9525','Ellen','TIBDbyATMOw=','TL','IT');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeID` int(11) NOT NULL,
  `CreationDate` date DEFAULT NULL,
  `Amount` int(10) NOT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  `LastModifierID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,1,'2018-08-08',1,'nothing',1),(2,1,'2018-08-08',2,'nothing',1),(3,2,'2018-08-08',3,NULL,2),(4,2,'2018-08-08',3,NULL,2),(5,3,'2018-08-08',3,NULL,3),(6,4,'2018-08-08',3,NULL,3),(7,1,'2018-01-01',11,'11',NULL),(8,2,'2019-01-01',23,'ff',NULL),(9,4,'2019-01-01',99,'dinner',NULL),(10,1,'2019-01-01',33,'nothing',NULL),(11,5,'2013-05-03',4,'project released',NULL);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_invoices`
--

DROP TABLE IF EXISTS `v_invoices`;
/*!50001 DROP VIEW IF EXISTS `v_invoices`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_invoices` AS SELECT 
 1 AS `Department`,
 1 AS `EmployeeNO`,
 1 AS `RealName`,
 1 AS `Role`,
 1 AS `ID`,
 1 AS `EmployeeID`,
 1 AS `CreationDate`,
 1 AS `Amount`,
 1 AS `Remark`,
 1 AS `LastModifierID`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `oti`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `oti` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oti`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeNO` varchar(255) NOT NULL,
  `RealName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Role` varchar(255) NOT NULL,
  `Department` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'9521','Ada','TIBDbyATMOw=','TM','HR'),(2,'9522','Ben','TIBDbyATMOw=','TL','HR'),(3,'9523','Cindy','TIBDbyATMOw=','ADMIN','HR'),(4,'9524','David','TIBDbyATMOw=','TM','IT'),(5,'9525','Ellen','TIBDbyATMOw=','TL','IT');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeID` int(11) NOT NULL,
  `CreationDate` date DEFAULT NULL,
  `Amount` int(10) NOT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  `LastModifierID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,1,'2018-08-08',1,'nothing',1),(2,1,'2018-08-08',2,'nothing',1),(3,2,'2018-08-08',3,NULL,2),(4,2,'2018-08-08',3,NULL,2),(5,3,'2018-08-08',3,NULL,3),(6,4,'2018-08-08',3,NULL,3),(7,1,'2018-01-01',11,'11',NULL),(8,2,'2019-01-01',23,'ff',NULL),(9,4,'2019-01-01',99,'dinner',NULL),(10,1,'2019-01-01',33,'nothing',NULL),(11,5,'2013-05-03',4,'project released',NULL);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_invoices`
--

DROP TABLE IF EXISTS `v_invoices`;
/*!50001 DROP VIEW IF EXISTS `v_invoices`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_invoices` AS SELECT 
 1 AS `Department`,
 1 AS `EmployeeNO`,
 1 AS `RealName`,
 1 AS `Role`,
 1 AS `ID`,
 1 AS `EmployeeID`,
 1 AS `CreationDate`,
 1 AS `Amount`,
 1 AS `Remark`,
 1 AS `LastModifierID`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `oti`
--

USE `oti`;

--
-- Final view structure for view `v_invoices`
--

/*!50001 DROP VIEW IF EXISTS `v_invoices`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_invoices` AS select `employee`.`Department` AS `Department`,`employee`.`EmployeeNO` AS `EmployeeNO`,`employee`.`RealName` AS `RealName`,`employee`.`Role` AS `Role`,`invoice`.`ID` AS `ID`,`invoice`.`EmployeeID` AS `EmployeeID`,`invoice`.`CreationDate` AS `CreationDate`,`invoice`.`Amount` AS `Amount`,`invoice`.`Remark` AS `Remark`,`invoice`.`LastModifierID` AS `LastModifierID` from (`employee` join `invoice`) where (`employee`.`ID` = `invoice`.`EmployeeID`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Current Database: `oti`
--

USE `oti`;

--
-- Final view structure for view `v_invoices`
--

/*!50001 DROP VIEW IF EXISTS `v_invoices`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_invoices` AS select `employee`.`Department` AS `Department`,`employee`.`EmployeeNO` AS `EmployeeNO`,`employee`.`RealName` AS `RealName`,`employee`.`Role` AS `Role`,`invoice`.`ID` AS `ID`,`invoice`.`EmployeeID` AS `EmployeeID`,`invoice`.`CreationDate` AS `CreationDate`,`invoice`.`Amount` AS `Amount`,`invoice`.`Remark` AS `Remark`,`invoice`.`LastModifierID` AS `LastModifierID` from (`employee` join `invoice`) where (`employee`.`ID` = `invoice`.`EmployeeID`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-08 18:10:39
