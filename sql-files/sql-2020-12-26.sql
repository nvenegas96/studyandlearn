-- MySQL dump 10.16  Distrib 10.1.37-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: studyandlearn
-- ------------------------------------------------------
-- Server version	10.1.37-MariaDB

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
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson` (
                          `lesson_id` int(11) NOT NULL AUTO_INCREMENT,
                          `name` varchar(80) NOT NULL,
                          `description` text,
                          `image` varchar(255) DEFAULT NULL,
                          `hour_quantity` int(11) DEFAULT NULL,
                          `modality_id` int(11) NOT NULL,
                          `category_id` int(11) NOT NULL,
                          PRIMARY KEY (`lesson_id`),
                          KEY `lessons_modality_idx` (`modality_id`),
                          KEY `lessons_category_idx` (`category_id`),
                          CONSTRAINT `lessons_category` FOREIGN KEY (`category_id`) REFERENCES `lesson_category` (`lessons_category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                          CONSTRAINT `lessons_modality` FOREIGN KEY (`modality_id`) REFERENCES `lesson_modality` (`lesson_modality_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,'Aprende matemáticas desde cero - Aritmética - yoyo','Números reales, sus propiedades y como operar con ellos. Un repaso de los números enteros. Teoría de números. Descomposición en números primos, Máximo Común Múltiplo, Mínimo Común Divisor y división entera. Fracciones. Operaciones y números decimales. Potencias y sus propiedades. El uso de la notación científica. Logaritmos y sus propiedades. Razones y proporciones. Uso de la regla de tres, cálculo de porcentajes e intereses simples y compuestos. Diferentes sistemas de numeración, cómo pasar de uno a otro y operar en ellos. Sistema métrico decimal y equivalencias en las unidades de longitud, superficie, volumen y masa. Números denominados y cómo operar con ellos. Raíces y sus propiedades, Resolución de problemas con el uso de lógica aritmética','defecto.jpg',1,0,0),(2,'Prueba','bla bla bla','defecto.jpg',3,1,1),(3,'prueba1','prueba prueba prueba','defecto.jpg',1,1,1),(4,'prueba1','prueba prueba prueba','defecto.jpg',1,1,1),(5,'fokofk','fokofko','image.png',1,1,1),(6,'dfgfdgsdg','gfhgngfn','image.png',1,1,1),(10,'Yoyologia','curso sobre yoyos hhhh','image.png',2,1,2);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_category`
--

DROP TABLE IF EXISTS `lesson_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_category` (
                                   `lessons_category_id` int(11) NOT NULL AUTO_INCREMENT,
                                   `name` varchar(80) NOT NULL,
                                   `description` text,
                                   `image` varchar(255) DEFAULT NULL,
                                   `enable` tinyint(4) DEFAULT NULL,
                                   PRIMARY KEY (`lessons_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_category`
--

LOCK TABLES `lesson_category` WRITE;
/*!40000 ALTER TABLE `lesson_category` DISABLE KEYS */;
INSERT INTO `lesson_category` VALUES (0,'Cursos de Matemáticas','Aqui podrás encontrar todos nuestros cursos de Matemáticas','matematicasCurso.jpg',1),(1,'Cursos de Mecánica','Aqui podrás encontrar todos nuestros cursos de Mecánica','mecanicaCurso.jpg',1),(2,'Cursos de Cocina','Aqui podrás encontrar todos nuestros cursos de Cocina','cocinaCurso.jpg',1);
/*!40000 ALTER TABLE `lesson_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_comment`
--

DROP TABLE IF EXISTS `lesson_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_comment` (
                                  `lesson_comment_id` int(11) NOT NULL AUTO_INCREMENT,
                                  `user_id` int(11) NOT NULL,
                                  `lesson_id` int(11) NOT NULL,
                                  `date` datetime NOT NULL,
                                  `comment` text,
                                  PRIMARY KEY (`lesson_comment_id`),
                                  KEY `user_comment_idx` (`user_id`),
                                  KEY `lessons_comment_idx` (`lesson_id`),
                                  CONSTRAINT `lessons_comment` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                  CONSTRAINT `user_comment` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_comment`
--

LOCK TABLES `lesson_comment` WRITE;
/*!40000 ALTER TABLE `lesson_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `lesson_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_modality`
--

DROP TABLE IF EXISTS `lesson_modality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_modality` (
                                   `lesson_modality_id` int(11) NOT NULL AUTO_INCREMENT,
                                   `name` varchar(60) NOT NULL,
                                   PRIMARY KEY (`lesson_modality_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_modality`
--

LOCK TABLES `lesson_modality` WRITE;
/*!40000 ALTER TABLE `lesson_modality` DISABLE KEYS */;
INSERT INTO `lesson_modality` VALUES (0,'Online'),(1,'Presencial');
/*!40000 ALTER TABLE `lesson_modality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_schedule`
--

DROP TABLE IF EXISTS `lesson_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_schedule` (
                                   `lesson_schedule_id` int(11) NOT NULL AUTO_INCREMENT,
                                   `lesson_id` int(11) NOT NULL,
                                   `student_id` int(11) NOT NULL,
                                   `teacher_id` int(11) NOT NULL,
                                   `date` datetime NOT NULL,
                                   `status_id` int(11) NOT NULL,
                                   `assessment` tinyint(4) NOT NULL DEFAULT '0',
                                   PRIMARY KEY (`lesson_schedule_id`),
                                   KEY `lessons_lessons_id_idx` (`lesson_id`),
                                   KEY `lessons_student_idx` (`student_id`),
                                   KEY `lessons_teacher_idx` (`teacher_id`),
                                   KEY `lessons_status_idx` (`status_id`),
                                   CONSTRAINT `lessons_lessons_id` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                   CONSTRAINT `lessons_status` FOREIGN KEY (`status_id`) REFERENCES `lesson_status` (`lesson_status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                   CONSTRAINT `lessons_student_id` FOREIGN KEY (`student_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                   CONSTRAINT `lessons_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_schedule`
--

LOCK TABLES `lesson_schedule` WRITE;
/*!40000 ALTER TABLE `lesson_schedule` DISABLE KEYS */;
INSERT INTO `lesson_schedule` VALUES (1,1,2,2,'2020-12-25 13:24:00',0,1);
/*!40000 ALTER TABLE `lesson_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_status`
--

DROP TABLE IF EXISTS `lesson_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_status` (
                                 `lesson_status_id` int(11) NOT NULL AUTO_INCREMENT,
                                 `name` varchar(60) NOT NULL,
                                 PRIMARY KEY (`lesson_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_status`
--

LOCK TABLES `lesson_status` WRITE;
/*!40000 ALTER TABLE `lesson_status` DISABLE KEYS */;
INSERT INTO `lesson_status` VALUES (0,'Agendando'),(1,'Pendiente'),(2,'Rechazado'),(3,'Finalizada'),(4,'Atrasada');
/*!40000 ALTER TABLE `lesson_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_teacher`
--

DROP TABLE IF EXISTS `lesson_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_teacher` (
                                  `lesson_teacher_id` int(11) NOT NULL AUTO_INCREMENT,
                                  `lesson_id` int(11) NOT NULL,
                                  `user_id` int(11) NOT NULL,
                                  PRIMARY KEY (`lesson_teacher_id`),
                                  KEY `lessons_lessons_idx` (`lesson_id`),
                                  KEY `lessons_teacher_idx` (`user_id`),
                                  CONSTRAINT `lessons_lessons` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                  CONSTRAINT `lessons_teacher` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_teacher`
--

LOCK TABLES `lesson_teacher` WRITE;
/*!40000 ALTER TABLE `lesson_teacher` DISABLE KEYS */;
INSERT INTO `lesson_teacher` VALUES (1,1,2),(5,1,1);
/*!40000 ALTER TABLE `lesson_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialty`
--

DROP TABLE IF EXISTS `specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialty` (
                             `specialty_id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(80) NOT NULL,
                             PRIMARY KEY (`specialty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty`
--

LOCK TABLES `specialty` WRITE;
/*!40000 ALTER TABLE `specialty` DISABLE KEYS */;
INSERT INTO `specialty` VALUES (1,'Paella'),(2,'Aritmética'),(3,'Cocina Peruana'),(4,'Algebra Lineal'),(5,'Cinemática'),(6,'Sistema de ecuaciones'),(7,'Ley de Coulomb'),(8,'No aplica');
/*!40000 ALTER TABLE `specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `study` (
                         `study_id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(80) NOT NULL,
                         PRIMARY KEY (`study_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (0,'No aplica'),(1,'Básico'),(2,'Intermedio'),(3,'Universitario');
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_schedule`
--

DROP TABLE IF EXISTS `teacher_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_schedule` (
                                    `teacher_schedule_id` int(11) NOT NULL AUTO_INCREMENT,
                                    `lesson_teacher_id` int(11) NOT NULL,
                                    `day_of_the_week` tinyint(4) NOT NULL,
                                    `start_time` time NOT NULL,
                                    `end_time` time NOT NULL,
                                    PRIMARY KEY (`teacher_schedule_id`),
                                    KEY `teacher_schedule_lessons_teacher_idx` (`lesson_teacher_id`),
                                    CONSTRAINT `teacher_schedule_lessons_teacher` FOREIGN KEY (`lesson_teacher_id`) REFERENCES `lesson_teacher` (`lesson_teacher_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_schedule`
--

LOCK TABLES `teacher_schedule` WRITE;
/*!40000 ALTER TABLE `teacher_schedule` DISABLE KEYS */;
INSERT INTO `teacher_schedule` VALUES (1,1,4,'00:00:00','01:00:00'),(2,1,2,'08:00:00','12:00:00'),(3,1,1,'14:30:00','20:00:00'),(4,1,1,'10:00:00','13:00:00');
/*!40000 ALTER TABLE `teacher_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
                        `user_id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(100) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `enabled` bit(1) DEFAULT NULL,
                        `address` text,
                        `phone` varchar(20) DEFAULT NULL,
                        `user_type_id` int(11) NOT NULL DEFAULT '0',
                        `study_level_id` int(11) NOT NULL DEFAULT '0',
                        PRIMARY KEY (`user_id`),
                        KEY `user_user_type_idx` (`user_type_id`),
                        KEY `user_study_level_idx` (`study_level_id`),
                        CONSTRAINT `user_study_level` FOREIGN KEY (`study_level_id`) REFERENCES `study` (`study_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                        CONSTRAINT `user_user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nicolas','nicolas.alexisvi96@gmail.com','$2a$10$ITBfo1PWhLPajFvMNxyjGOe4/ewxZrZox8kSv0Zy5fA0qKxs4BT3u','','Collin #34','951753654',2,0),(2,'Angela Patricia','anpar@gmail.com','$2a$10$ITBfo1PWhLPajFvMNxyjGOe4/ewxZrZox8kSv0Zy5fA0qKxs4BT3u','','Collin #34','987654678',1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_specialty`
--

DROP TABLE IF EXISTS `user_specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_specialty` (
                                  `user_specialty_id` int(11) NOT NULL AUTO_INCREMENT,
                                  `user_id` int(11) NOT NULL,
                                  `specialty_id` int(11) NOT NULL,
                                  PRIMARY KEY (`user_specialty_id`),
                                  KEY `user_user_idx` (`user_id`),
                                  KEY `user_specialty_idx` (`specialty_id`),
                                  CONSTRAINT `user_specialty` FOREIGN KEY (`specialty_id`) REFERENCES `specialty` (`specialty_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                  CONSTRAINT `user_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_specialty`
--

LOCK TABLES `user_specialty` WRITE;
/*!40000 ALTER TABLE `user_specialty` DISABLE KEYS */;
INSERT INTO `user_specialty` VALUES (1,2,1),(2,2,5),(3,1,8);
/*!40000 ALTER TABLE `user_specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
                             `user_type_id` int(11) NOT NULL AUTO_INCREMENT,
                             `type_name` varchar(60) NOT NULL,
                             `type_translation` varchar(60) DEFAULT NULL,
                             PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (0,'student','Alumno'),(1,'teacher','Docente'),(2,'admin','Coordinador/Admin');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-26 13:06:12
