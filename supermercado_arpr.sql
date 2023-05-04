-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 04-05-2023 a las 17:52:24
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `supermercado_arpr`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `idcarrito` int(11) NOT NULL,
  `cuil` varchar(15) NOT NULL,
  `montototal` decimal(14,2) NOT NULL,
  `montocondesc` decimal(14,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`idcarrito`, `cuil`, `montototal`, `montocondesc`) VALUES
(1, '27360348439', 2340.00, 2350.00),
(2, '20342567896', 2560.00, 2065.00),
(3, '23256489552', 7350.00, 6300.00),
(4, '21254698528', 8500.00, 7550.00),
(5, '20256482365', 3650.00, 3050.00),
(6, '27454588569', 1600.00, 800.00),
(7, '27360348439', 900.00, 600.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `nombres` varchar(70) DEFAULT NULL,
  `cuil` varchar(15) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`nombres`, `cuil`, `telefono`, `email`) VALUES
('Juan Perez', '20342567896', '2644151099', 'juanpe23@gmail.com'),
('Maria Rojas', '21254698528', '2643200832', 'Rojassmari@gmail.com'),
('Tamara Avalos', '27360348439', '2645698989', 'tamara.avalos@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item`
--

CREATE TABLE `item` (
  `iditem` int(11) NOT NULL,
  `precio` decimal(14,2) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `item`
--

INSERT INTO `item` (`iditem`, `precio`, `nombre`) VALUES
(1, 250.00, 'Papas Fritas'),
(2, 450.00, 'Coca Cola'),
(3, 300.00, 'Cereales'),
(4, 800.00, 'Vino Tinto'),
(5, 850.00, 'Fernet Branca'),
(6, 256.00, 'Galletas Surtidas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `itemcarrito`
--

CREATE TABLE `itemcarrito` (
  `idcarrito` int(11) NOT NULL,
  `iditem` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `itemcarrito`
--

INSERT INTO `itemcarrito` (`idcarrito`, `iditem`, `cantidad`) VALUES
(1, 1, 2),
(4, 2, 6),
(1, 5, 3),
(3, 7, 1),
(5, 3, 2),
(2, 8, 1),
(7, 2, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`idcarrito`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cuil`);

--
-- Indices de la tabla `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`iditem`);

--
-- Indices de la tabla `itemcarrito`
--
ALTER TABLE `itemcarrito`
  ADD KEY `idcarrito` (`idcarrito`),
  ADD KEY `itemcarrito_ibfk_1` (`iditem`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `idcarrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `item`
--
ALTER TABLE `item`
  MODIFY `iditem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
