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
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`lesson_id`),
  KEY `lessons_modality_idx` (`modality_id`),
  KEY `lessons_category_idx` (`category_id`),
  CONSTRAINT `lessons_category` FOREIGN KEY (`category_id`) REFERENCES `lesson_category` (`lessons_category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lessons_modality` FOREIGN KEY (`modality_id`) REFERENCES `lesson_modality` (`lesson_modality_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,'Aprende matemáticas desde cero - Aritmética -','Números reales, sus propiedades y como operar con ellos. Un repaso de los números enteros. Teoría de números. Descomposición en números primos, Máximo Común Múltiplo, Mínimo Común Divisor y división entera. Fracciones. Operaciones y números decimales. Potencias y sus propiedades. El uso de la notación científica. Logaritmos y sus propiedades. Razones y proporciones. ','aritmetica.jpg',2,1,0,1),(2,'Trigonometría y Geometría Analítica','La Trigonometría es una rama de las matemáticas que dedica su estudio a la relación entre los lados y ángulos de un triángulo.\n\nLa Geometría Analítica es una rama de las matemáticas que estudia la relación entre las figuras geométricas y su representación mediante expresiones algebraicas.\n\nEste curso te proporciona el material necesario para el estudio de la Trigonometrí','trigonometria.jpg',3,0,0,1),(3,'Electricidad y Electrónica Automotriz Unidad 1','Funcionamiento del Motor de 4 Tiempos 1\nInspección de Motor 4 Cilindros en Vehículo\nDesmontaje de Motor 4 Cilindros\nEquipo de Taller, Inspección y Carga de Batería','electronicaauto1.jpg',2,1,1,1),(4,'Electricidad y Electrónica Automotriz Unidad 2','Funcionamiento del Motor de 4 Tiempos 2\nInspección y Rectificación de Piezas de Motor 4 Cilindros\nInstrumentos de Medición (Calibre, Comparador, Galgas, Micrómetro)','electronicaauto2.jpg',1,0,1,1),(5,'Electricidad y Electrónica Automotriz Unidad 10','Inyección Electrónica 2\nSistema de Inyección Electrónica EECIV, Componentes y Diagnóstico con Scanner y Multímetro\nLimpieza de Inyectores\nSistema de Encendido','electronicaauto3.jpg',1,1,1,1),(6,'Curso de Sushi','Este curso básico de sushi tiene como objetivo desarrollar o mejorar buenas prácticas para el manejo de técnicas de lavado, cocción, preparación y previo reposo de nuestro Shari (arroz de Sushi) como también tener una buena selección de productos a la hora de elegir pescados o verduras, manejo de temperaturas y mantenimiento de estas, siguiendo por las variadas formas de enrollar y emplatar tu sushi. Aprenderemos de los respectivos nombres y manejo de vocabulario o términos ligados a la cultura del sushi.','sushi.jpg',1,0,2,1),(10,'Curso de pizzas','El curso de pizza es de lo más exitosos de nuestra academia.\nRealizaremos dos tipos de pizzas diferentes en vivo:\nLa pizza americana clásica, de masa esponjosa y gruesa (con gran variedad de ingredientes). La verdadera pizza italiana, de masa delgada y crujiente. Profundizaremos sobre ingredientes esenciales, como la salsa de tomate (clave en el resultado), el aceite de oliva y el queso.\n\nTambién repasaremos variedades de queso muzzarella, como usarlo y su importancia en la pizza. Te enseñaremos técnicas de amasado, fermentado, formado y el secreto de la mejor salsa. Finalizando con el proceso de horneado de la pizza, para lucirte en tus propias preparaciones.','pizza.jpg',1,1,2,1);
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
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`lessons_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_category`
--

LOCK TABLES `lesson_category` WRITE;
/*!40000 ALTER TABLE `lesson_category` DISABLE KEYS */;
INSERT INTO `lesson_category` VALUES (0,'Cursos de Matemáticas','Aqui podrás encontrar todos nuestros cursos de Matemáticas','matematicasCurso.jpg',1),(1,'Cursos de Mecánica','Aqui podrás encontrar todos nuestros cursos de Mecánica','mecanicaCurso.jpg',1),(2,'Cursos de Cocina','Aqui podrás encontrar todos nuestros cursos de Cocina','cocinaCurso.jpg',1),(3,'Curso de Música','Variados cursos de música.','defecto.jpg',1);
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
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`lesson_comment_id`),
  KEY `user_comment_idx` (`user_id`),
  KEY `lessons_comment_idx` (`lesson_id`),
  CONSTRAINT `lessons_comment` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_comment` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_comment`
--

LOCK TABLES `lesson_comment` WRITE;
/*!40000 ALTER TABLE `lesson_comment` DISABLE KEYS */;
INSERT INTO `lesson_comment` VALUES (1,5,1,'2021-01-01 00:00:00','Buen curso',1),(3,10,3,'2021-01-08 23:35:18',' Curso recomendado para estudiantes universitarios.',1),(6,9,2,'2021-01-10 16:12:53',' Curso de mucha utilidad.',1);
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
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`lesson_modality_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_modality`
--

LOCK TABLES `lesson_modality` WRITE;
/*!40000 ALTER TABLE `lesson_modality` DISABLE KEYS */;
INSERT INTO `lesson_modality` VALUES (0,'Online',1),(1,'Presencial',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_schedule`
--

LOCK TABLES `lesson_schedule` WRITE;
/*!40000 ALTER TABLE `lesson_schedule` DISABLE KEYS */;
INSERT INTO `lesson_schedule` VALUES (1,2,5,12,'2020-12-25 13:24:00',3,3),(3,1,14,11,'2021-01-01 00:00:00',3,0),(4,4,14,9,'2020-12-30 08:00:00',2,0),(5,3,5,10,'2020-12-29 14:30:00',3,5),(6,5,15,13,'2020-12-29 10:00:00',3,4),(7,1,14,11,'2021-01-08 00:00:00',3,0),(8,6,15,2,'2021-01-15 00:00:00',2,0),(9,6,5,2,'2021-01-22 00:00:00',3,4),(10,10,14,2,'2020-12-29 10:00:00',3,0),(14,10,18,2,'2021-01-05 10:00:00',3,0),(15,10,18,2,'2021-01-08 11:00:00',3,0),(16,10,5,2,'2021-01-12 14:00:00',0,0),(17,10,5,2,'2021-01-14 15:00:00',7,0),(18,2,25,12,'2021-03-09 08:30:00',1,0),(19,2,25,12,'2021-04-06 19:00:00',0,0);
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
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`lesson_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_status`
--

LOCK TABLES `lesson_status` WRITE;
/*!40000 ALTER TABLE `lesson_status` DISABLE KEYS */;
INSERT INTO `lesson_status` VALUES (0,'Agendado Pendiente de Pago',1),(1,'Agendado Pagado',1),(2,'Pendiente',1),(3,'Finalizado',1),(4,'Cancelado',1),(5,'Atrasado',1),(7,'Curso Pagado a Docente',1);
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
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`lesson_teacher_id`),
  KEY `lessons_lessons_idx` (`lesson_id`),
  KEY `lessons_teacher_idx` (`user_id`),
  CONSTRAINT `lessons_lessons` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lessons_teacher` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_teacher`
--

LOCK TABLES `lesson_teacher` WRITE;
/*!40000 ALTER TABLE `lesson_teacher` DISABLE KEYS */;
INSERT INTO `lesson_teacher` VALUES (1,2,12,1),(5,1,11,1),(6,4,9,1),(10,3,10,1),(11,5,13,1),(12,10,2,1),(13,6,2,1);
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
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`specialty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty`
--

LOCK TABLES `specialty` WRITE;
/*!40000 ALTER TABLE `specialty` DISABLE KEYS */;
INSERT INTO `specialty` VALUES (0,'No aplica',1),(1,'Pizza Napolitana',1),(2,'Pizza bordes de pepperoni',1),(3,'Vehiculos Electricos',1),(4,'Vehiculos de carga',1),(5,'Sistema de ecuaciones',1),(6,'Trigonometria',1),(7,'Geometria',1),(8,'Handroll',1);
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
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`study_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (0,'No aplica',1),(1,'Básico',1),(2,'Intermedio',1),(3,'Universitario',1),(4,'prueba2',0);
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
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`teacher_schedule_id`),
  KEY `teacher_schedule_lessons_teacher_idx` (`lesson_teacher_id`),
  CONSTRAINT `teacher_schedule_lessons_teacher` FOREIGN KEY (`lesson_teacher_id`) REFERENCES `lesson_teacher` (`lesson_teacher_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_schedule`
--

LOCK TABLES `teacher_schedule` WRITE;
/*!40000 ALTER TABLE `teacher_schedule` DISABLE KEYS */;
INSERT INTO `teacher_schedule` VALUES (1,1,4,'00:00:00','01:00:00',1),(2,1,2,'08:00:00','12:00:00',1),(3,1,1,'08:30:00','12:00:00',1),(4,1,1,'13:00:00','20:00:00',1),(5,5,1,'08:00:00','13:00:00',1),(6,5,2,'14:00:00','21:00:00',1),(7,5,3,'15:00:00','21:00:00',1),(8,5,5,'11:00:00','20:00:00',1),(9,6,0,'09:00:02','12:00:02',1),(10,6,1,'14:00:00','20:00:00',1),(11,6,2,'14:00:00','20:00:00',1),(12,6,3,'09:00:00','18:00:00',1),(13,6,4,'10:00:00','19:00:00',1),(14,6,4,'13:00:00','18:00:00',1),(15,10,0,'08:12:12','12:10:12',1),(16,10,5,'14:00:00','20:00:00',1),(17,10,1,'08:00:00','12:00:00',1),(18,10,1,'14:00:00','14:00:00',1),(19,11,1,'09:00:00','13:00:00',1),(20,11,2,'09:00:00','13:00:00',1),(21,11,3,'09:00:00','13:00:00',1),(22,11,4,'09:00:00','13:00:00',1),(23,11,5,'09:00:00','13:00:00',1),(24,12,0,'11:30:00','18:30:00',1),(25,12,3,'10:00:00','18:00:00',1),(26,12,4,'08:00:00','18:00:00',1),(27,12,5,'08:00:00','18:00:00',1),(28,13,1,'08:00:00','12:00:00',1),(29,13,2,'14:00:00','18:00:00',1),(30,13,3,'15:00:00','21:00:00',1),(33,12,6,'10:30:30','12:30:30',0),(34,13,6,'09:30:30','12:30:30',1),(35,13,6,'15:30:30','18:30:30',1),(36,13,6,'09:00:00','12:00:00',1),(37,13,6,'08:00:00','13:00:00',1);
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
  `address` text,
  `phone` varchar(20) DEFAULT NULL,
  `user_type_id` int(11) NOT NULL DEFAULT '0',
  `study_level_id` int(11) NOT NULL DEFAULT '0',
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_user_type_idx` (`user_type_id`),
  KEY `user_study_level_idx` (`study_level_id`),
  CONSTRAINT `user_study_level` FOREIGN KEY (`study_level_id`) REFERENCES `study` (`study_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nicolas','nicolas.alexisvi96@gmail.com','$2a$10$ITBfo1PWhLPajFvMNxyjGOe4/ewxZrZox8kSv0Zy5fA0qKxs4BT3u','Collin #34','951753654',2,0,''),(2,'Juan Garrido','jgarrido@mailinator.com','$2a$10$ITBfo1PWhLPajFvMNxyjGOe4/ewxZrZox8kSv0Zy5fA0qKxs4BT3u','Collin #36','987654678',1,1,''),(5,'Javiera Duran','jangelica@mailinator.com','$2a$10$Kw9/jyJ.dqfyfLgRsMKP3uZi1XysUyHSP1mvnvvleEE3cCYJibXsu','villa padre hurtado  pasaje julio jarpa , sector parque lantaño','915431563',0,0,''),(8,'Nicolas','nicolas@mailinator.com','$2a$10$IkCJqx1J.1A0Suj2NZort.Ocm0KU4YKK1yuJQ.quX98SMkfvZmg6y','villa padre hurtado  pasaje julio jarpa , sector parque lantaño','934523453',1,2,''),(9,'Eduardo Garcia','egarcia@mailinator.com','$2a$10$IkCJqx1J.1A0Suj2NZort.Ocm0KU4YKK1yuJQ.quX98SMkfvZmg6y','Las acacias 987','986765465',1,2,''),(10,'Javier Gomez','jgomez@mailinator.com','$2a$10$IkCJqx1J.1A0Suj2NZort.Ocm0KU4YKK1yuJQ.quX98SMkfvZmg6y','Los pinos 56','976895674',1,2,''),(11,'Estefania Silva','esilva@mailinator.com','$2a$10$IkCJqx1J.1A0Suj2NZort.Ocm0KU4YKK1yuJQ.quX98SMkfvZmg6y','Arauco #887','945787766',1,1,''),(12,'Monica Rivas','mrivas@mailinator.com','$2a$10$IkCJqx1J.1A0Suj2NZort.Ocm0KU4YKK1yuJQ.quX98SMkfvZmg6y','Argentina #33','956443356',1,2,''),(13,'Marta Robles','mrobles@mailinator.com','$2a$10$IkCJqx1J.1A0Suj2NZort.Ocm0KU4YKK1yuJQ.quX98SMkfvZmg6y','Ecuador #99','967889443',1,1,''),(14,'Pablo Gomez','pgomez@mailinator.com','$2a$10$IkCJqx1J.1A0Suj2NZort.Ocm0KU4YKK1yuJQ.quX98SMkfvZmg6y','Argentina #667','965347877',0,0,''),(15,'Francico Pérez','fperez@mailinator.com','$2a$10$IkCJqx1J.1A0Suj2NZort.Ocm0KU4YKK1yuJQ.quX98SMkfvZmg6y','Arauco #12','967885544',0,0,''),(18,'Nicolas','nicolas.venegas1502@alumnos.ubiobio.cl','$2a$10$LRgqh5YIjy94fIVl4gH.zuxY1gpmBjdy1INN8WQ.ToX/O5I/UVHwO','Padre hurtado','915431563',0,0,''),(25,'Jorge Fernandez Lopez','jfernandez@mailinator.com','$2a$10$0.OBk7rC63O1COFPjHkDXONddz2Pm6l4NYlkb70/CX8M.eYthxdvq','Villa los espinos 600','987675665',0,0,'');
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
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_specialty_id`),
  KEY `user_user_idx` (`user_id`),
  KEY `user_specialty_idx` (`specialty_id`),
  CONSTRAINT `user_specialty` FOREIGN KEY (`specialty_id`) REFERENCES `specialty` (`specialty_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_specialty`
--

LOCK TABLES `user_specialty` WRITE;
/*!40000 ALTER TABLE `user_specialty` DISABLE KEYS */;
INSERT INTO `user_specialty` VALUES (1,2,1,''),(2,2,2,''),(3,1,0,''),(4,9,3,''),(5,10,3,''),(6,10,4,''),(7,11,5,''),(8,12,6,''),(9,12,7,''),(12,13,3,''),(13,13,4,''),(14,2,8,'');
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
INSERT INTO `user_type` VALUES (0,'STUDENT','Alumno'),(1,'TEACHER','Docente'),(2,'ADMIN','Coordinador/Admin');
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

-- Dump completed on 2021-03-04  0:11:43
