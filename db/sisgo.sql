-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.32-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.12.0.7122
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla sisgo.accion
CREATE TABLE IF NOT EXISTS `accion` (
  `idAccion` smallint(6) NOT NULL AUTO_INCREMENT,
  `nombreAccion` varchar(100) NOT NULL,
  PRIMARY KEY (`idAccion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `razonSocial` varchar(150) NOT NULL,
  `cuit` decimal(11,0) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `fechaAlta` date NOT NULL,
  `fechaBaja` date DEFAULT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `FK_CLIENTE_USUARIO` (`idUsuario`),
  CONSTRAINT `FK_CLIENTE_USUARIO` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.empleado
CREATE TABLE IF NOT EXISTS `empleado` (
  `idEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `legajo` tinyint(4) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `fechaAlta` date NOT NULL,
  `fechaBaja` date DEFAULT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  KEY `FK_EMPLEADO_USUARIO` (`idUsuario`),
  CONSTRAINT `FK_EMPLEADO_USUARIO` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.empleado_proyecto
CREATE TABLE IF NOT EXISTS `empleado_proyecto` (
  `idEmpleado` int(11) NOT NULL,
  `numeroProyecto` int(11) NOT NULL,
  PRIMARY KEY (`idEmpleado`,`numeroProyecto`),
  KEY `FK_EMPLEADO_PROYECTO_PROYECTO` (`numeroProyecto`),
  CONSTRAINT `FK_EMPLEADO_PROYECTO_EMPLEADO` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`idEmpleado`),
  CONSTRAINT `FK_EMPLEADO_PROYECTO_PROYECTO` FOREIGN KEY (`numeroProyecto`) REFERENCES `proyecto` (`numeroProyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.equipo
CREATE TABLE IF NOT EXISTS `equipo` (
  `idEquipo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcionEquipo` varchar(100) NOT NULL,
  `cantidadEquipos` smallint(6) NOT NULL,
  `fechaAlta` date NOT NULL,
  `fechaBaja` date DEFAULT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idEquipo`),
  KEY `FK_EQUIPO_USUARIO` (`idUsuario`),
  CONSTRAINT `FK_EQUIPO_USUARIO` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.equipo_proyecto
CREATE TABLE IF NOT EXISTS `equipo_proyecto` (
  `idEquipo` int(11) NOT NULL,
  `numeroProyecto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEquipo`,`numeroProyecto`),
  KEY `FK_EQUIPO_PROYECTO_PROYECTO` (`numeroProyecto`),
  CONSTRAINT `FK_EQUIPO_PROYECTO_EQUIPO` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`idEquipo`),
  CONSTRAINT `FK_EQUIPO_PROYECTO_PROYECTO` FOREIGN KEY (`numeroProyecto`) REFERENCES `proyecto` (`numeroProyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.insumo
CREATE TABLE IF NOT EXISTS `insumo` (
  `idInsumo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcionInsumo` varchar(100) NOT NULL,
  `cantidadStock` smallint(6) NOT NULL,
  `fechaAlta` date NOT NULL,
  `fechaBaja` date DEFAULT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idInsumo`),
  KEY `FK_INSUMO_USUARIO` (`idUsuario`),
  CONSTRAINT `FK_INSUMO_USUARIO` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.insumo_proyecto
CREATE TABLE IF NOT EXISTS `insumo_proyecto` (
  `idInsumo` int(11) NOT NULL,
  `numeroProyecto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idInsumo`,`numeroProyecto`),
  KEY `FK_INSUMO_PROYECTO_PROYECTO` (`numeroProyecto`),
  CONSTRAINT `FK_INSUMO_PROYECTO_INSUMO` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`),
  CONSTRAINT `FK_INSUMO_PROYECTO_PROYECTO` FOREIGN KEY (`numeroProyecto`) REFERENCES `proyecto` (`numeroProyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `numeroPedido` int(11) NOT NULL AUTO_INCREMENT,
  `fechaPedido` date NOT NULL,
  `numeroProyecto` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `detallePedido` varchar(1000) NOT NULL,
  `caracteristicasConstruccion` varchar(1000) NOT NULL,
  PRIMARY KEY (`numeroPedido`),
  KEY `FK_PEDIDO_CLIENTE` (`idCliente`),
  KEY `FK_PEDIDO_PROYECTO` (`numeroProyecto`),
  KEY `FK_PEDIDO_USUARIO` (`idUsuario`),
  CONSTRAINT `FK_PEDIDO_CLIENTE` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `FK_PEDIDO_PROYECTO` FOREIGN KEY (`numeroProyecto`) REFERENCES `proyecto` (`numeroProyecto`),
  CONSTRAINT `FK_PEDIDO_USUARIO` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.permiso
CREATE TABLE IF NOT EXISTS `permiso` (
  `idRol` smallint(6) NOT NULL,
  `idAccion` smallint(6) NOT NULL,
  PRIMARY KEY (`idRol`,`idAccion`),
  KEY `FK_PERMISO_ACCION` (`idAccion`),
  CONSTRAINT `FK_PERMISO_ACCION` FOREIGN KEY (`idAccion`) REFERENCES `accion` (`idAccion`),
  CONSTRAINT `FK_PERMISO_ROL` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.presupuesto
CREATE TABLE IF NOT EXISTS `presupuesto` (
  `numeroProyecto` int(11) NOT NULL,
  `numeroPresupuesto` smallint(6) NOT NULL,
  `fechaPresupuesto` date NOT NULL,
  `diasValidez` tinyint(4) NOT NULL,
  `importePresupuestado` float NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`numeroProyecto`,`numeroPresupuesto`),
  CONSTRAINT `FK_PRESUPUESTO_PROYECTO` FOREIGN KEY (`numeroProyecto`) REFERENCES `proyecto` (`numeroProyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.proyecto
CREATE TABLE IF NOT EXISTS `proyecto` (
  `numeroProyecto` int(11) NOT NULL AUTO_INCREMENT,
  `fechaCreacion` datetime NOT NULL,
  `nombreProyecto` varchar(50) NOT NULL,
  `fechaEstimadaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`numeroProyecto`),
  KEY `FK_PROYECTO_USUARIO` (`idUsuario`),
  CONSTRAINT `FK_PROYECTO_USUARIO` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.rol
CREATE TABLE IF NOT EXISTS `rol` (
  `idRol` smallint(6) NOT NULL AUTO_INCREMENT,
  `nombreRol` varchar(100) NOT NULL,
  `fechaAlta` date NOT NULL DEFAULT current_timestamp(),
  `fechaBaja` date DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sisgo.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(20) NOT NULL,
  `claveUsuario` varchar(64) NOT NULL,
  `descripcionUsuario` varchar(100) NOT NULL,
  `fechaAlta` date NOT NULL,
  `fechaBaja` date DEFAULT NULL,
  `idRol` smallint(6) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `FK_USUARIO_ROL` (`idRol`),
  CONSTRAINT `FK_USUARIO_ROL` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
