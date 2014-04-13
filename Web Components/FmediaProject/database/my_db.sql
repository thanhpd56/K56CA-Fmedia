-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2014 at 04:12 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `my_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblcategory`
--

CREATE TABLE IF NOT EXISTS `tblcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(145) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=24 ;

--
-- Dumping data for table `tblcategory`
--

INSERT INTO `tblcategory` (`id`, `cat_name`) VALUES
(15, 'Films'),
(16, 'Sports'),
(18, 'Programming'),
(19, 'Learning English'),
(21, 'Vlogs'),
(22, 'Musics');

-- --------------------------------------------------------

--
-- Table structure for table `tblvideo`
--

CREATE TABLE IF NOT EXISTS `tblvideo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(145) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(145) COLLATE utf8_unicode_ci NOT NULL,
  `during_time` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `image_url` varchar(145) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(145) COLLATE utf8_unicode_ci NOT NULL,
  `rating` float NOT NULL,
  `create_time` int(88) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `create_time` (`create_time`),
  UNIQUE KEY `create_time_UNIQUE` (`create_time`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=97 ;

--
-- Dumping data for table `tblvideo`
--

INSERT INTO `tblvideo` (`id`, `title`, `url`, `during_time`, `image_url`, `description`, `rating`, `create_time`) VALUES
(77, 'phan thanh update', 'afdfasdfdf', '45 phut', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfadsdfdf                                                        ', 4, 1395816580),
(78, 'phan thanh', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfasfddfdf', 4, 1395816594),
(79, 'phan thanh', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfasdfdfdf', 4, 1395816634),
(80, 'phan thanh', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfasfdsfdf                            ', 4, 1395816689),
(81, 'for final text', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dasffasfsdf', 4, 1395816788),
(82, 'phan thanh', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dafsdfsdfdf                            ', 4, 1395816836),
(83, 'for final text', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfdadfsdfdsf                            ', 4, 1395816886),
(84, 'for final text', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfasfsdfdsf                            ', 4, 1395818637),
(85, 'update update', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfasfsdfdsf                                                        ', 4, 1395818683),
(86, 'phan thanh', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfafdfdf                            ', 4, 1395818739),
(87, 'phan thanh', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfafsdfsdf                            ', 4, 1395818868),
(88, 'phan thanh', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfasfsfdf                            ', 4, 1395818947),
(89, 'phan thanh', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfsafsfsfa', 4, 1395818986),
(90, 'dfadf', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfasdfdfdf', 4, 1395819113),
(91, 'Android - SlidingMenu Integration', 'OT76zDIeBe8', '56', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfsafsf', 4, 1395856640),
(92, 'Android - SlidingMenu Integration', 'afdfasdfdf', '2', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfafsfsf', 3, 1395856706),
(93, 'thanh', 'OT76zDIeBe8', '2', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfsfsfasfsdf', 5, 1395856737),
(94, 'Android - SlidingMenu Integration', 'afdfasdfdf', '3', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dsfasfsdff', 3, 1395856765),
(95, 'check for error', 'afdfasdfdf', '45', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dfasfsaddfsdfsdf', 3, 1395889036),
(96, 'Android - new check for during time', 'OT76zDIeBe8', '56 phut', 'http://ia.media-imdb.com/images/M/MV5BMTQxMDEzMTA2NF5BMl5BanBnXkFtZTgwMTUwMTkzMTE@._V1_SX640_SY720_.jpg', 'dsfasdfsdfsdf', 4, 1395976099);

-- --------------------------------------------------------

--
-- Table structure for table `tblvideo_cat`
--

CREATE TABLE IF NOT EXISTS `tblvideo_cat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` int(11) NOT NULL,
  `cat_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cat_for_idx` (`cat_id`),
  KEY `video_for_idx` (`video_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=49 ;

--
-- Dumping data for table `tblvideo_cat`
--

INSERT INTO `tblvideo_cat` (`id`, `video_id`, `cat_id`) VALUES
(6, 91, 15),
(7, 91, 16),
(8, 91, 18),
(9, 91, 19),
(10, 92, 15),
(11, 92, 16),
(13, 92, 18),
(14, 92, 19),
(15, 93, 15),
(16, 93, 18),
(17, 93, 16),
(18, 93, 19),
(21, 94, 15),
(22, 94, 18),
(23, 94, 19),
(28, 87, 15),
(29, 86, 15),
(30, 85, 15),
(31, 84, 15),
(32, 83, 15),
(33, 82, 15),
(35, 80, 15),
(36, 88, 15),
(37, 95, 15),
(38, 95, 16),
(40, 95, 18),
(41, 96, 15),
(42, 96, 16),
(43, 96, 18),
(44, 77, 15),
(45, 77, 16),
(46, 77, 22),
(47, 77, 21),
(48, 77, 18);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `name`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Thanh');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblvideo_cat`
--
ALTER TABLE `tblvideo_cat`
  ADD CONSTRAINT `cat_for` FOREIGN KEY (`cat_id`) REFERENCES `tblcategory` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `video_for` FOREIGN KEY (`video_id`) REFERENCES `tblvideo` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
