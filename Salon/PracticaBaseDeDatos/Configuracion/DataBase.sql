-- Adminer 4.7.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';

DROP DATABASE IF EXISTS `DS3`;
CREATE DATABASE `DS3` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `DS3`;

DROP TABLE IF EXISTS `Estudiante`;
CREATE TABLE `Estudiante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expediente` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `activo` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2019-03-08 17:57:17
