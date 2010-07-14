-- MySQL dump 10.13  Distrib 5.1.48, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: jcl
-- ------------------------------------------------------
-- Server version	5.1.48-1

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
-- Table structure for table `fichero`
--

DROP TABLE IF EXISTS `fichero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fichero` (
  `nombre_fisico` char(32) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`nombre_fisico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fichero`
--

LOCK TABLES `fichero` WRITE;
/*!40000 ALTER TABLE `fichero` DISABLE KEYS */;
/*!40000 ALTER TABLE `fichero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jcl`
--

DROP TABLE IF EXISTS `jcl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jcl` (
  `nombre` char(12) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jcl`
--

LOCK TABLES `jcl` WRITE;
/*!40000 ALTER TABLE `jcl` DISABLE KEYS */;
/*!40000 ALTER TABLE `jcl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jcl_has_programa`
--

DROP TABLE IF EXISTS `jcl_has_programa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jcl_has_programa` (
  `jcl_nombre` char(12) COLLATE utf8_spanish_ci NOT NULL,
  `programa_nombre` char(12) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`jcl_nombre`,`programa_nombre`),
  KEY `fk_jcl_has_programa_jcl` (`jcl_nombre`),
  KEY `fk_jcl_has_programa_programa1` (`programa_nombre`),
  CONSTRAINT `fk_jcl_has_programa_jcl` FOREIGN KEY (`jcl_nombre`) REFERENCES `jcl` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jcl_has_programa_programa1` FOREIGN KEY (`programa_nombre`) REFERENCES `programa` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jcl_has_programa`
--

LOCK TABLES `jcl_has_programa` WRITE;
/*!40000 ALTER TABLE `jcl_has_programa` DISABLE KEYS */;
/*!40000 ALTER TABLE `jcl_has_programa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paso`
--

DROP TABLE IF EXISTS `paso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paso` (
  `numero_paso` char(12) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `jcl_has_programa_jcl_nombre` char(12) COLLATE utf8_spanish_ci NOT NULL,
  `jcl_has_programa_programa_nombre` char(12) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`numero_paso`,`jcl_has_programa_jcl_nombre`,`jcl_has_programa_programa_nombre`),
  KEY `fk_paso_jcl_has_programa1` (`jcl_has_programa_jcl_nombre`,`jcl_has_programa_programa_nombre`),
  CONSTRAINT `fk_paso_jcl_has_programa1` FOREIGN KEY (`jcl_has_programa_jcl_nombre`, `jcl_has_programa_programa_nombre`) REFERENCES `jcl_has_programa` (`jcl_nombre`, `programa_nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paso`
--

LOCK TABLES `paso` WRITE;
/*!40000 ALTER TABLE `paso` DISABLE KEYS */;
/*!40000 ALTER TABLE `paso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paso_has_fichero`
--

DROP TABLE IF EXISTS `paso_has_fichero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paso_has_fichero` (
  `paso_numero_paso` char(12) COLLATE utf8_spanish_ci NOT NULL,
  `paso_jcl_has_programa_jcl_nombre` char(12) COLLATE utf8_spanish_ci NOT NULL,
  `paso_jcl_has_programa_programa_nombre` char(12) COLLATE utf8_spanish_ci NOT NULL,
  `fichero_nombre_fisico` char(32) COLLATE utf8_spanish_ci NOT NULL,
  `nombre_logico` char(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `disp` char(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`paso_numero_paso`,`paso_jcl_has_programa_jcl_nombre`,`paso_jcl_has_programa_programa_nombre`,`fichero_nombre_fisico`),
  KEY `fk_paso_has_fichero_paso1` (`paso_numero_paso`,`paso_jcl_has_programa_jcl_nombre`,`paso_jcl_has_programa_programa_nombre`),
  KEY `fk_paso_has_fichero_fichero1` (`fichero_nombre_fisico`),
  CONSTRAINT `fk_paso_has_fichero_paso1` FOREIGN KEY (`paso_numero_paso`, `paso_jcl_has_programa_jcl_nombre`, `paso_jcl_has_programa_programa_nombre`) REFERENCES `paso` (`numero_paso`, `jcl_has_programa_jcl_nombre`, `jcl_has_programa_programa_nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_paso_has_fichero_fichero1` FOREIGN KEY (`fichero_nombre_fisico`) REFERENCES `fichero` (`nombre_fisico`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paso_has_fichero`
--

LOCK TABLES `paso_has_fichero` WRITE;
/*!40000 ALTER TABLE `paso_has_fichero` DISABLE KEYS */;
/*!40000 ALTER TABLE `paso_has_fichero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programa`
--

DROP TABLE IF EXISTS `programa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programa` (
  `nombre` char(12) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programa`
--

LOCK TABLES `programa` WRITE;
/*!40000 ALTER TABLE `programa` DISABLE KEYS */;
/*!40000 ALTER TABLE `programa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-07-11 22:54:19
