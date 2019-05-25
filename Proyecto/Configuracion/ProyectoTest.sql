-- Adminer 4.7.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';

DROP DATABASE IF EXISTS `ProyectoTest`;
CREATE DATABASE `ProyectoTest` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ProyectoTest`;

DELIMITER ;;

CREATE PROCEDURE `SaveProductoDeVenta`(IN `IdProducto` int, IN `IdVenta` int, IN `precio` float, IN `cantidad` float)
BEGIN
  INSERT INTO ProductosDeVenta (IdProducto,IdVenta,precio,cantidad) VALUES (IdProducto,IdVenta,precio,cantidad);
END;;

CREATE PROCEDURE `SaveVenta`(OUT `idVenta` int, IN `idSucursal` int, IN `idEmpleado` int, IN `idCliente` int)
BEGIN
  INSERT INTO Venta (idCliente,idSucursal,idEmpleado) VALUES (idCliente,idSucursal,idEmpleado);

  SET idVenta = LAST_INSERT_ID();
END;;

DELIMITER ;

DROP TABLE IF EXISTS `Cliente`;
CREATE TABLE `Cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `Proveedor`;
CREATE TABLE `Proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `Sucursal`;
CREATE TABLE `Sucursal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `Empleado`;
CREATE TABLE `Empleado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `Producto`;
CREATE TABLE `Producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `precio` float NOT NULL,
  `perecedero` binary(1) NOT NULL,
  `idProveedor` int(11) NOT NULL,
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `Producto_fk0` (`idProveedor`),
  CONSTRAINT `Producto_fk0` FOREIGN KEY (`idProveedor`) REFERENCES `Proveedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `Venta`;
CREATE TABLE `Venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) NOT NULL,
  `idSucursal` int(11) NOT NULL,
  `idEmpleado` int(11) NOT NULL,
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `Venta_fk0` (`idCliente`),
  KEY `Venta_fk1` (`idSucursal`),
  KEY `Venta_fk2` (`idEmpleado`),
  CONSTRAINT `Venta_fk0` FOREIGN KEY (`idCliente`) REFERENCES `Cliente` (`id`),
  CONSTRAINT `Venta_fk1` FOREIGN KEY (`idSucursal`) REFERENCES `Sucursal` (`id`),
  CONSTRAINT `Venta_fk2` FOREIGN KEY (`idEmpleado`) REFERENCES `Empleado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ProductosDeVenta`;
CREATE TABLE `ProductosDeVenta` (
  `idProducto` int(11) NOT NULL,
  `idVenta` int(11) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` float NOT NULL,
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  KEY `ProductosDeVenta_fk0` (`idProducto`),
  KEY `ProductosDeVenta_fk1` (`idVenta`),
  CONSTRAINT `ProductosDeVenta_fk0` FOREIGN KEY (`idProducto`) REFERENCES `Producto` (`id`),
  CONSTRAINT `ProductosDeVenta_fk1` FOREIGN KEY (`idVenta`) REFERENCES `Venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP VIEW IF EXISTS `VistaVenta`;
CREATE TABLE `VistaVenta` (`idVenta` int(11), `producto` tinytext, `precio` float, `cantidad` float);


DROP VIEW IF EXISTS `VistaVentas`;
CREATE TABLE `VistaVentas` (`id` int(11), `empleado` varchar(250), `cliente` varchar(50), `sucursal` varchar(50), `fecha` datetime);


DROP TABLE IF EXISTS `VistaVenta`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `VistaVenta` AS select `v`.`id` AS `idVenta`,`p`.`name` AS `producto`,`pdv`.`precio` AS `precio`,`pdv`.`cantidad` AS `cantidad` from ((`ProductosDeVenta` `pdv` join `Producto` `p` on((`p`.`id` = `pdv`.`idProducto`))) join `Venta` `v` on((`v`.`id` = `pdv`.`idVenta`)));

DROP TABLE IF EXISTS `VistaVentas`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `VistaVentas` AS select `v`.`id` AS `id`,`e`.`name` AS `empleado`,`c`.`name` AS `cliente`,`s`.`name` AS `sucursal`,`v`.`createAt` AS `fecha` from (((`Venta` `v` join `Sucursal` `s` on((`s`.`id` = `v`.`idSucursal`))) join `Empleado` `e` on((`e`.`id` = `v`.`idEmpleado`))) join `Cliente` `c` on((`c`.`id` = `v`.`idCliente`)));

-- 2019-05-25 07:01:00