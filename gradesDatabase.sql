-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.30-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for college
CREATE DATABASE IF NOT EXISTS `college` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `college`;

-- Dumping structure for table college.grades
CREATE TABLE IF NOT EXISTS `grades` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GRADE` double NOT NULL,
  `WEIGHT` double NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `CLASS` varchar(6) NOT NULL,
  `SEMESTER` varchar(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;

-- Dumping data for table college.grades: ~34 rows (approximately)
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` (`ID`, `GRADE`, `WEIGHT`, `DESCRIPTION`, `CLASS`, `SEMESTER`) VALUES
	(24, 0.87, 14.6413, 'Test 1', 'BIO111', 'FALL 2017'),
	(25, 0.94, 14.6413, 'Test 2', 'BIO111', 'FALL 2017'),
	(26, 0.93, 14.6213, 'Test 3', 'BIO111', 'FALL 2017'),
	(27, 0.92, 14.6413, 'Test 4', 'BIO111', 'FALL 2017'),
	(28, 0.95, 14.6413, 'Homework', 'BIO111', 'FALL 2017'),
	(29, 0.889, 24.158, 'Lab', 'BIO111', 'FALL 2017'),
	(30, 1, 2.64, 'Bonus', 'BIO111', 'FALL 2017'),
	(31, 0.98, 15, 'Test 1', 'CSC191', 'FALL 2017'),
	(32, 0.84, 20, 'Test 2', 'CSC191', 'FALL 2017'),
	(33, 1, 10, 'Online Homework', 'CSC191', 'FALL 2017'),
	(34, 0.975, 10, 'Homework', 'CSC191', 'FALL 2017'),
	(35, 0.95, 15, 'Test 1', 'CSC195', 'FALL 2017'),
	(36, 0.83, 20, 'Test 2', 'CSC195', 'FALL 2017'),
	(37, 0.87, 25, 'Test 3', 'CSC195', 'FALL 2017'),
	(38, 0.9225, 40, 'Hw (estimated grade)', 'CSC195', 'FALL 2017'),
	(51, 0.865, 20, 'Test 1', 'CSC400', 'FALL 2018'),
	(52, 0.86, 20, 'Test 2', 'CSC400', 'FALL 2018'),
	(53, 0.96, 25, 'Assignments', 'CSC400', 'FALL 2018'),
	(54, 0.98, 20, 'Test 3', 'CSC400', 'FALL 2018'),
	(55, 0.8666666666666667, 15, 'Team Project', 'CSC400', 'FALL 2018'),
	(69, 1, 25, 'Test 1', 'MAT239', 'FALL 2018'),
	(70, 1, 20, 'Quizzes', 'MAT239', 'FALL 2018'),
	(71, 0.93, 25, 'Test 2', 'MAT239', 'FALL 2018'),
	(72, 0.97, 30, 'Final', 'MAT239', 'FALL 2018'),
	(124, 0.93, 30, 'Team project', 'CSC340', 'FALL 2018'),
	(125, 0.945, 15, 'Test 1', 'CSC340', 'FALL 2018'),
	(126, 0.94, 20, 'Individual Project', 'CSC340', 'FALL 2018'),
	(127, 0.97, 15, 'Final', 'CSC340', 'FALL 2018'),
	(128, 0.95, 20, 'Assignments', 'CSC340', 'FALL 2018'),
	(129, 1, 4, 'EXTRA CREDIT: notes @ ABM', 'CSC340', 'FALL 2018');
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
