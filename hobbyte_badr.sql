-- phpMyAdmin SQL Dump
-- version 5.2.1-2.fc39
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 19-02-2024 a las 18:46:53
-- Versión del servidor: 10.5.23-MariaDB
-- Versión de PHP: 8.2.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hobbyte`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `heroes`
--

CREATE TABLE `heroes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `tipo_prueba` varchar(30) NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `heroes`
--

INSERT INTO `heroes` (`id`, `nombre`, `tipo_prueba`, `activo`) VALUES
(2, 'Gandalf', 'Magia', 1),
(3, 'Thorin', 'Fuerza', 1),
(4, 'Bilbo', 'Habilidad', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `heroe_partida`
--

CREATE TABLE `heroe_partida` (
  `id` int(11) NOT NULL,
  `id_heroe` int(11) NOT NULL,
  `id_partida` int(11) NOT NULL,
  `capacidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `heroe_partida`
--

INSERT INTO `heroe_partida` (`id`, `id_heroe`, `id_partida`, `capacidad`) VALUES
(19, 2, 43, 0),
(20, 3, 43, 50),
(21, 4, 43, 5),
(22, 2, 44, 25),
(23, 3, 44, 0),
(24, 4, 44, -20),
(25, 2, 45, 0),
(26, 3, 45, 0),
(27, 4, 45, 0),
(28, 2, 46, 10),
(29, 3, 46, 0),
(30, 4, 46, 0),
(31, 2, 47, 0),
(32, 3, 47, 50),
(33, 4, 47, 0),
(34, 2, 48, 5),
(35, 3, 48, 0),
(36, 4, 48, 0),
(37, 2, 49, 0),
(38, 3, 49, 0),
(39, 4, 49, 0),
(46, 2, 52, 0),
(47, 3, 52, 0),
(48, 4, 52, 5),
(49, 2, 53, 50),
(50, 3, 53, 45),
(51, 4, 53, 50),
(52, 2, 54, 25),
(53, 3, 54, 5),
(54, 4, 54, 0),
(55, 2, 55, 40),
(56, 3, 55, 50),
(57, 4, 55, 0),
(58, 2, 56, 0),
(59, 3, 56, 0),
(60, 4, 56, 50),
(61, 2, 57, 35),
(62, 3, 57, 30),
(63, 4, 57, 50),
(64, 2, 58, 20),
(65, 3, 58, 15),
(66, 4, 58, 0),
(67, 2, 59, 0),
(68, 3, 59, 40),
(69, 4, 59, 50),
(70, 2, 60, 50),
(71, 3, 60, 45),
(72, 4, 60, 45),
(73, 2, 61, 10),
(74, 3, 61, 0),
(75, 4, 61, 0),
(76, 2, 62, 15),
(77, 3, 62, 0),
(78, 4, 62, 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partida`
--

CREATE TABLE `partida` (
  `id` int(11) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT 0,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `partida`
--

INSERT INTO `partida` (`id`, `estado`, `id_usuario`) VALUES
(43, 1, 1),
(44, 1, 1),
(45, 1, 1),
(46, 1, 3),
(47, 1, 8),
(48, 1, 3),
(49, 0, 3),
(52, 1, 1),
(53, 3, 1),
(54, 3, 1),
(55, 3, 1),
(56, 1, 1),
(57, 2, 1),
(58, 1, 1),
(59, 1, 1),
(60, 2, 1),
(61, 1, 13),
(62, 2, 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pruebas`
--

CREATE TABLE `pruebas` (
  `id` int(11) NOT NULL,
  `destapada` tinyint(4) NOT NULL,
  `tipo_prueba` varchar(30) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `id_partida` int(11) NOT NULL,
  `orden` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `pruebas`
--

INSERT INTO `pruebas` (`id`, `destapada`, `tipo_prueba`, `capacidad`, `id_partida`, `orden`) VALUES
(389, 2, 'Magia', 35, 43, 8),
(390, 2, 'Habilidad', 10, 43, 9),
(391, 2, 'Habilidad', 35, 43, 10),
(392, 1, 'Magia', 20, 43, 11),
(393, 0, 'Magia', 45, 43, 0),
(394, 0, 'Habilidad', 40, 43, 0),
(395, 0, 'Magia', 30, 43, 0),
(396, 0, 'Habilidad', 40, 43, 0),
(397, 0, 'Habilidad', 10, 43, 0),
(398, 0, 'Fuerza', 5, 43, 0),
(399, 0, 'Habilidad', 30, 43, 0),
(400, 1, 'Fuerza', 15, 43, 1),
(401, 1, 'Fuerza', 25, 43, 2),
(402, 1, 'Habilidad', 30, 43, 3),
(403, 1, 'Fuerza', 50, 43, 4),
(404, 1, 'Habilidad', 25, 43, 5),
(405, 1, 'Fuerza', 50, 43, 6),
(406, 1, 'Fuerza', 30, 43, 7),
(407, 0, 'Magia', 20, 43, 0),
(408, 0, 'Magia', 15, 43, 0),
(409, 0, 'Habilidad', 40, 44, 0),
(410, 0, 'Fuerza', 35, 44, 0),
(411, 0, 'Fuerza', 30, 44, 0),
(412, 0, 'Habilidad', 30, 44, 0),
(413, 0, 'Habilidad', 35, 44, 0),
(414, 0, 'Magia', 50, 44, 0),
(415, 2, 'Habilidad', 30, 44, 1),
(416, 2, 'Fuerza', 20, 44, 2),
(417, 2, 'Fuerza', 30, 44, 3),
(418, 2, 'Magia', 25, 44, 4),
(419, 2, 'Habilidad', 40, 44, 5),
(420, 0, 'Habilidad', 10, 44, 0),
(421, 0, 'Magia', 5, 44, 0),
(422, 0, 'Habilidad', 25, 44, 0),
(423, 0, 'Magia', 5, 44, 0),
(424, 0, 'Magia', 35, 44, 0),
(425, 0, 'Fuerza', 25, 44, 0),
(426, 0, 'Fuerza', 45, 44, 0),
(427, 0, 'Fuerza', 15, 44, 0),
(428, 0, 'Habilidad', 35, 44, 0),
(429, 0, 'Fuerza', 5, 45, 0),
(430, 0, 'Magia', 15, 45, 0),
(431, 0, 'Magia', 30, 45, 0),
(432, 0, 'Habilidad', 5, 45, 0),
(433, 0, 'Magia', 15, 45, 0),
(434, 0, 'Habilidad', 25, 45, 0),
(435, 0, 'Magia', 25, 45, 0),
(436, 0, 'Fuerza', 40, 45, 0),
(437, 0, 'Fuerza', 5, 45, 0),
(438, 0, 'Fuerza', 35, 45, 0),
(439, 0, 'Habilidad', 45, 45, 0),
(440, 2, 'Fuerza', 40, 45, 1),
(441, 2, 'Fuerza', 25, 45, 2),
(442, 2, 'Habilidad', 40, 45, 3),
(443, 2, 'Habilidad', 20, 45, 4),
(444, 1, 'Magia', 40, 45, 5),
(445, 1, 'Magia', 35, 45, 6),
(446, 1, 'Habilidad', 20, 45, 7),
(447, 1, 'Fuerza', 20, 45, 8),
(448, 1, 'Magia', 50, 45, 9),
(449, 0, 'Magia', 35, 46, 0),
(450, 2, 'Habilidad', 45, 46, 1),
(451, 0, 'Fuerza', 20, 46, 0),
(452, 2, 'Fuerza', 15, 46, 2),
(453, 2, 'Fuerza', 30, 46, 3),
(454, 1, 'Habilidad', 30, 46, 4),
(455, 2, 'Magia', 40, 46, 5),
(456, 1, 'Fuerza', 20, 46, 6),
(457, 0, 'Fuerza', 15, 46, 0),
(458, 0, 'Magia', 20, 46, 0),
(459, 0, 'Fuerza', 5, 46, 0),
(460, 0, 'Habilidad', 15, 46, 0),
(461, 0, 'Habilidad', 15, 46, 0),
(462, 0, 'Fuerza', 50, 46, 0),
(463, 0, 'Magia', 45, 46, 0),
(464, 0, 'Magia', 5, 46, 0),
(465, 0, 'Habilidad', 5, 46, 0),
(466, 0, 'Magia', 45, 46, 0),
(467, 0, 'Fuerza', 5, 46, 0),
(468, 0, 'Habilidad', 25, 46, 0),
(469, 0, 'Fuerza', 35, 47, 0),
(470, 0, 'Habilidad', 15, 47, 0),
(471, 0, 'Magia', 10, 47, 0),
(472, 0, 'Magia', 50, 47, 0),
(473, 0, 'Fuerza', 25, 47, 0),
(474, 0, 'Habilidad', 15, 47, 0),
(475, 1, 'Magia', 5, 47, 4),
(476, 1, 'Habilidad', 15, 47, 5),
(477, 0, 'Habilidad', 35, 47, 0),
(478, 0, 'Fuerza', 45, 47, 0),
(479, 0, 'Habilidad', 45, 47, 0),
(480, 0, 'Habilidad', 50, 47, 0),
(481, 0, 'Habilidad', 35, 47, 0),
(482, 2, 'Habilidad', 5, 47, 3),
(483, 0, 'Magia', 35, 47, 0),
(484, 0, 'Habilidad', 45, 47, 0),
(485, 0, 'Habilidad', 20, 47, 0),
(486, 0, 'Magia', 50, 47, 0),
(487, 2, 'Habilidad', 15, 47, 2),
(488, 2, 'Magia', 25, 47, 1),
(489, 0, 'Fuerza', 30, 48, 0),
(490, 2, 'Magia', 10, 48, 1),
(491, 2, 'Fuerza', 50, 48, 2),
(492, 2, 'Habilidad', 45, 48, 3),
(493, 2, 'Magia', 10, 48, 4),
(494, 0, 'Magia', 40, 48, 0),
(495, 2, 'Magia', 5, 48, 5),
(496, 2, 'Magia', 20, 48, 6),
(497, 1, 'Habilidad', 40, 48, 7),
(498, 0, 'Fuerza', 5, 48, 0),
(499, 0, 'Magia', 45, 48, 0),
(500, 0, 'Magia', 30, 48, 0),
(501, 0, 'Magia', 15, 48, 0),
(502, 0, 'Fuerza', 35, 48, 0),
(503, 0, 'Magia', 35, 48, 0),
(504, 0, 'Habilidad', 25, 48, 0),
(505, 0, 'Habilidad', 10, 48, 0),
(506, 0, 'Magia', 30, 48, 0),
(507, 0, 'Magia', 35, 48, 0),
(508, 0, 'Fuerza', 10, 48, 0),
(509, 0, 'Magia', 40, 49, 0),
(510, 1, 'Fuerza', 25, 49, 5),
(511, 0, 'Habilidad', 5, 49, 0),
(512, 2, 'Magia', 25, 49, 1),
(513, 1, 'Magia', 50, 49, 2),
(514, 2, 'Habilidad', 30, 49, 3),
(515, 1, 'Habilidad', 5, 49, 4),
(516, 0, 'Magia', 15, 49, 0),
(517, 0, 'Fuerza', 10, 49, 0),
(518, 0, 'Magia', 15, 49, 0),
(519, 0, 'Magia', 45, 49, 0),
(520, 0, 'Fuerza', 10, 49, 0),
(521, 0, 'Fuerza', 30, 49, 0),
(522, 0, 'Magia', 50, 49, 0),
(523, 0, 'Habilidad', 40, 49, 0),
(524, 0, 'Habilidad', 20, 49, 0),
(525, 0, 'Magia', 30, 49, 0),
(526, 0, 'Magia', 40, 49, 0),
(527, 0, 'Habilidad', 45, 49, 0),
(528, 0, 'Magia', 10, 49, 0),
(569, 0, 'Fuerza', 50, 52, 0),
(570, 2, 'Fuerza', 5, 52, 1),
(571, 2, 'Fuerza', 15, 52, 6),
(572, 2, 'Habilidad', 5, 52, 2),
(573, 2, 'Habilidad', 20, 52, 9),
(574, 2, 'Fuerza', 10, 52, 4),
(575, 0, 'Habilidad', 30, 52, 0),
(576, 0, 'Magia', 35, 52, 0),
(577, 1, 'Magia', 10, 52, 5),
(578, 0, 'Fuerza', 25, 52, 0),
(579, 0, 'Fuerza', 35, 52, 0),
(580, 0, 'Fuerza', 30, 52, 0),
(581, 2, 'Fuerza', 15, 52, 7),
(582, 2, 'Habilidad', 20, 52, 8),
(583, 0, 'Habilidad', 50, 52, 0),
(584, 0, 'Magia', 25, 52, 0),
(585, 0, 'Fuerza', 35, 52, 0),
(586, 2, 'Fuerza', 5, 52, 3),
(587, 0, 'Habilidad', 35, 52, 0),
(588, 0, 'Magia', 15, 52, 0),
(589, 0, 'Habilidad', 15, 53, 0),
(590, 0, 'Magia', 40, 53, 0),
(591, 0, 'Magia', 40, 53, 0),
(592, 0, 'Fuerza', 35, 53, 0),
(593, 0, 'Habilidad', 50, 53, 0),
(594, 0, 'Fuerza', 25, 53, 0),
(595, 0, 'Fuerza', 15, 53, 0),
(596, 0, 'Habilidad', 40, 53, 0),
(597, 0, 'Magia', 25, 53, 0),
(598, 2, 'Fuerza', 5, 53, 1),
(599, 0, 'Fuerza', 25, 53, 0),
(600, 0, 'Magia', 45, 53, 0),
(601, 0, 'Habilidad', 15, 53, 0),
(602, 0, 'Magia', 25, 53, 0),
(603, 0, 'Habilidad', 20, 53, 0),
(604, 0, 'Fuerza', 35, 53, 0),
(605, 0, 'Magia', 20, 53, 0),
(606, 0, 'Magia', 35, 53, 0),
(607, 0, 'Magia', 40, 53, 0),
(608, 0, 'Fuerza', 40, 53, 0),
(609, 2, 'Magia', 25, 54, 1),
(610, 2, 'Habilidad', 15, 54, 2),
(611, 2, 'Fuerza', 45, 54, 3),
(612, 1, 'Habilidad', 35, 54, 4),
(613, 2, 'Habilidad', 10, 55, 1),
(614, 2, 'Magia', 10, 55, 2),
(615, 0, 'Fuerza', 40, 55, 0),
(616, 1, 'Habilidad', 35, 55, 3),
(617, 0, 'Magia', 50, 55, 0),
(618, 1, 'Magia', 30, 56, 2),
(619, 0, 'Fuerza', 50, 56, 0),
(620, 1, 'Fuerza', 10, 56, 3),
(621, 2, 'Magia', 5, 56, 1),
(622, 0, 'Magia', 40, 57, 0),
(623, 0, 'Magia', 30, 57, 0),
(624, 2, 'Fuerza', 20, 57, 2),
(625, 2, 'Magia', 15, 57, 1),
(626, 2, 'Habilidad', 15, 58, 3),
(627, 0, 'Habilidad', 40, 58, 0),
(628, 0, 'Magia', 30, 58, 0),
(629, 2, 'Habilidad', 5, 58, 2),
(630, 0, 'Magia', 35, 58, 0),
(631, 2, 'Habilidad', 10, 58, 5),
(632, 0, 'Habilidad', 30, 58, 0),
(633, 2, 'Magia', 15, 58, 9),
(634, 2, 'Fuerza', 10, 58, 4),
(635, 1, 'Habilidad', 15, 58, 7),
(636, 0, 'Habilidad', 45, 58, 0),
(637, 0, 'Habilidad', 25, 58, 0),
(638, 0, 'Habilidad', 25, 58, 0),
(639, 2, 'Magia', 15, 58, 8),
(640, 0, 'Habilidad', 20, 58, 0),
(641, 0, 'Magia', 40, 58, 0),
(642, 2, 'Habilidad', 15, 58, 6),
(643, 2, 'Fuerza', 20, 58, 10),
(644, 2, 'Fuerza', 5, 58, 1),
(645, 0, 'Habilidad', 30, 58, 0),
(646, 0, 'Habilidad', 30, 59, 0),
(647, 0, 'Magia', 20, 59, 0),
(648, 0, 'Magia', 20, 59, 0),
(649, 0, 'Habilidad', 45, 59, 0),
(650, 0, 'Fuerza', 30, 59, 0),
(651, 0, 'Fuerza', 25, 59, 0),
(652, 0, 'Magia', 10, 59, 0),
(653, 0, 'Habilidad', 15, 59, 0),
(654, 1, 'Magia', 5, 59, 1),
(655, 0, 'Fuerza', 15, 59, 0),
(656, 0, 'Magia', 40, 59, 0),
(657, 0, 'Habilidad', 15, 59, 0),
(658, 0, 'Fuerza', 35, 59, 0),
(659, 0, 'Fuerza', 20, 59, 0),
(660, 0, 'Habilidad', 10, 59, 0),
(661, 0, 'Habilidad', 50, 59, 0),
(662, 0, 'Habilidad', 10, 59, 0),
(663, 0, 'Fuerza', 50, 59, 0),
(664, 0, 'Fuerza', 15, 59, 0),
(665, 0, 'Habilidad', 40, 59, 0),
(666, 0, 'Fuerza', 35, 59, 0),
(667, 0, 'Magia', 30, 59, 0),
(668, 0, 'Habilidad', 50, 59, 0),
(669, 0, 'Habilidad', 50, 59, 0),
(670, 0, 'Fuerza', 45, 59, 0),
(671, 2, 'Fuerza', 5, 59, 2),
(672, 0, 'Magia', 20, 59, 0),
(673, 2, 'Fuerza', 5, 59, 3),
(674, 0, 'Habilidad', 20, 59, 0),
(675, 0, 'Habilidad', 25, 59, 0),
(676, 0, 'Habilidad', 35, 59, 0),
(677, 0, 'Habilidad', 30, 59, 0),
(678, 0, 'Fuerza', 50, 59, 0),
(679, 0, 'Fuerza', 15, 59, 0),
(680, 0, 'Magia', 35, 59, 0),
(681, 0, 'Habilidad', 20, 59, 0),
(682, 0, 'Habilidad', 30, 59, 0),
(683, 0, 'Fuerza', 50, 59, 0),
(684, 0, 'Magia', 30, 59, 0),
(685, 0, 'Habilidad', 50, 59, 0),
(686, 0, 'Magia', 15, 59, 0),
(687, 0, 'Fuerza', 25, 59, 0),
(688, 0, 'Magia', 45, 59, 0),
(689, 0, 'Magia', 20, 59, 0),
(690, 0, 'Habilidad', 35, 59, 0),
(691, 0, 'Habilidad', 15, 59, 0),
(692, 0, 'Habilidad', 45, 59, 0),
(693, 0, 'Fuerza', 30, 59, 0),
(694, 0, 'Magia', 15, 59, 0),
(695, 0, 'Fuerza', 15, 59, 0),
(696, 2, 'Habilidad', 5, 60, 1),
(697, 2, 'Fuerza', 5, 60, 2),
(698, 0, 'Fuerza', 20, 60, 0),
(699, 0, 'Fuerza', 30, 60, 0),
(700, 1, 'Fuerza', 40, 61, 7),
(701, 2, 'Magia', 35, 61, 6),
(702, 0, 'Fuerza', 45, 61, 0),
(703, 0, 'Habilidad', 30, 61, 0),
(704, 2, 'Habilidad', 30, 61, 8),
(705, 2, 'Magia', 5, 61, 1),
(706, 0, 'Fuerza', 50, 61, 0),
(707, 0, 'Habilidad', 45, 61, 0),
(708, 0, 'Fuerza', 40, 61, 0),
(709, 2, 'Habilidad', 20, 61, 3),
(710, 0, 'Magia', 40, 61, 0),
(711, 0, 'Habilidad', 35, 61, 0),
(712, 2, 'Fuerza', 25, 61, 5),
(713, 0, 'Magia', 50, 61, 0),
(714, 0, 'Magia', 35, 61, 0),
(715, 2, 'Fuerza', 10, 61, 2),
(716, 2, 'Habilidad', 20, 61, 4),
(717, 0, 'Fuerza', 45, 61, 0),
(718, 0, 'Habilidad', 25, 61, 0),
(719, 0, 'Fuerza', 30, 61, 0),
(720, 1, 'Fuerza', 20, 62, 2),
(721, 0, 'Magia', 45, 62, 0),
(722, 2, 'Habilidad', 20, 62, 1),
(723, 2, 'Magia', 35, 62, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `confirmation` varchar(15) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `confirmation`, `nombre`, `email`, `password`) VALUES
(1, '1', 'badr', 'badr@badr.com', '1234'),
(2, '0', 'prueba', 'prueba@prueba.com', '1234'),
(3, '1', 'test', 'test@test.com', '1234'),
(8, '1', 'badr', 'badr2hamidou2@gmail.com', '1234'),
(10, 'test', 'test', 'test', 'test'),
(13, '1', 'Francisco', 'alvarezbellonfrancisco@gmail.com', 'admin123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `heroes`
--
ALTER TABLE `heroes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `heroe_partida`
--
ALTER TABLE `heroe_partida`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_heroe` (`id_heroe`),
  ADD KEY `id_partida` (`id_partida`);

--
-- Indices de la tabla `partida`
--
ALTER TABLE `partida`
  ADD PRIMARY KEY (`id`),
  ADD KEY `partida_ibfk_2` (`id_usuario`);

--
-- Indices de la tabla `pruebas`
--
ALTER TABLE `pruebas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fb_2` (`id_partida`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `heroes`
--
ALTER TABLE `heroes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `heroe_partida`
--
ALTER TABLE `heroe_partida`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT de la tabla `partida`
--
ALTER TABLE `partida`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT de la tabla `pruebas`
--
ALTER TABLE `pruebas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=724;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `heroe_partida`
--
ALTER TABLE `heroe_partida`
  ADD CONSTRAINT `fb_1` FOREIGN KEY (`id_partida`) REFERENCES `partida` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `partida`
--
ALTER TABLE `partida`
  ADD CONSTRAINT `partida_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pruebas`
--
ALTER TABLE `pruebas`
  ADD CONSTRAINT `fb_2` FOREIGN KEY (`id_partida`) REFERENCES `partida` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
