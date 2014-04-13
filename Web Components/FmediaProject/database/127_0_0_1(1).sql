-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 01, 2014 at 08:02 PM
-- Server version: 5.5.36
-- PHP Version: 5.4.25

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=20 ;

--
-- Dumping data for table `tblcategory`
--

INSERT INTO `tblcategory` (`id`, `cat_name`) VALUES
(15, 'Film'),
(16, 'Sport'),
(17, 'Music'),
(18, 'Programming'),
(19, 'Learning English');

-- --------------------------------------------------------

--
-- Table structure for table `tblvideo`
--

CREATE TABLE IF NOT EXISTS `tblvideo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(145) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(145) COLLATE utf8_unicode_ci NOT NULL,
  `during_time` float NOT NULL,
  `image_url` varchar(145) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(145) COLLATE utf8_unicode_ci NOT NULL,
  `rating` float NOT NULL,
  `create_time` int(88) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `create_time` (`create_time`),
  UNIQUE KEY `create_time_UNIQUE` (`create_time`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=152 ;

--
-- Dumping data for table `tblvideo`
--

INSERT INTO `tblvideo` (`id`, `title`, `url`, `during_time`, `image_url`, `description`, `rating`, `create_time`) VALUES
(97, 'Buckys C++ Programming Tutorials - 2 - Understanding a Simple C++ Program', 'SWZfFNyUsxc', 9, 'https://i1.ytimg.com/vi/SWZfFNyUsxc/default.jpg', 'http://thenewboston.org/\r\n                            ', 9, 1396260113),
(98, 'Buckys C++ Programming Tutorials - 3 - More on Printing Text ', 'sPv0HQ8xOaU', 5, 'https://i1.ytimg.com/vi/sPv0HQ8xOaU/default.jpg', 'http://thenewboston.org/\r\n                            ', 9, 1396260408),
(99, 'Buckys C++ Programming Tutorials - 4 - Variables ', 'QwBSv4-_Lmk', 5, 'https://i1.ytimg.com/vi/QwBSv4-_Lmk/default.jpg', 'http://thenewboston.org/\r\n                                                                                    ', 9, 1396261424),
(100, 'Buckys C++ Programming Tutorials - 5 - Creating a Basic Calculator ', 'yjucJUsHSqg', 7, 'https://i1.ytimg.com/vi/yjucJUsHSqg/default.jpg', 'http://thenewboston.org/                            ', 9, 1396261522),
(101, 'Buckys C++ Programming Tutorials - 6 - Variables Memory Concepts', '3Iq_uFbc4L4', 3, 'https://i1.ytimg.com/vi/3Iq_uFbc4L4/default.jpg', 'http://thenewboston.org/\r\n                                                        ', 9, 1396261594),
(102, 'Buckys C++ Programming Tutorials - 7 - Basic Arithmetric', 'L1z2dpCosXU', 5, 'https://i1.ytimg.com/vi/L1z2dpCosXU/default.jpg', 'http://thenewboston.org/\r\n                                                        ', 9, 1396261817),
(103, 'Buckys C++ Programming Tutorials - 8 - if Statement', 'yEY8xlnarNo', 7, 'https://i1.ytimg.com/vi/yEY8xlnarNo/default.jpg', 'http://thenewboston.org/\r\n                            ', 9, 1396261965),
(104, 'Buckys C++ Programming Tutorials - 9 - Functions', 'bsWWHo4KDHE', 9, 'https://i1.ytimg.com/vi/bsWWHo4KDHE/default.jpg', 'http://thenewboston.org/                            ', 9, 1396262358),
(105, 'Buckys C++ Programming Tutorials - 10 - Creating Functions That Use Parameters ', '-87KQS-rZCA', 5, 'https://i1.ytimg.com/vi/-87KQS-rZCA/default.jpg', 'http://thenewboston.org/                            ', 9, 1396262362),
(111, 'Learning English - Lesson One (Introduction)', 'ohJCdihPWqc', 5, 'https://i1.ytimg.com/vi/ohJCdihPWqc/default.jpg', 'Speak English With Misterduncan', 9, 1396344305),
(112, 'Learning English - Lesson Two (Hello/Goodbye)', '6f_FtzgL9y4', 7, 'https://i1.ytimg.com/vi/6f_FtzgL9y4/default.jpg', 'Speak English With Misterduncan', 8, 1396344359),
(113, 'Learning English-Lesson Three (Please & Thank You)', 'K6m8_MRgfrs', 7, 'https://i1.ytimg.com/vi/K6m8_MRgfrs/default.jpg', 'Speak English With Misterduncan', 8, 1396344455),
(114, 'Learning English-Lesson Ten ( Saying Sorry )', 'u_BtrCuSOeE', 7, 'https://i1.ytimg.com/vi/u_BtrCuSOeE/default.jpg', 'Speak English With Misterduncan', 8, 1396344629),
(115, 'Learning English - Lesson Eleven (Irony and Coincidence)', '_roCWpvbhbg', 3, 'https://i1.ytimg.com/vi/_roCWpvbhbg/default.jpg', 'Speak English With Misterduncan', 8, 1396344707),
(116, 'Learning English -- Lesson Nine ( F A M E ) ', 'YXyH6_coLSs', 4, 'https://i1.ytimg.com/vi/YXyH6_coLSs/default.jpg', 'Speak English With Misterduncan\r\n', 9, 1396371528),
(117, 'Learning English-Lesson Eight-(Stress & Worry)', '-sZOc-jB2cw', 16, 'https://i1.ytimg.com/vi/-sZOc-jB2cw/default.jpg', 'Speak English With Misterduncan', 9, 1396371593),
(118, 'Learning English - Lesson Seven - (Health & Exercise) ', '7v4Vbhtx6TM', 15, 'https://i1.ytimg.com/vi/7v4Vbhtx6TM/default.jpg', 'Speak English With Misterduncan', 9, 1396371682),
(119, 'Learning English-Lesson Five (Good/Bad) ', 'v-elMi_t4Hc', 7, 'https://i1.ytimg.com/vi/v-elMi_t4Hc/default.jpg', 'Speak English With Misterduncan', 9, 1396371756),
(120, 'Learning English - Lesson Six - Happy & Sad', 'gwHIql9oql0', 14, 'https://i1.ytimg.com/vi/gwHIql9oql0/default.jpg', 'https://i1.ytimg.com/vi/gwHIql9oql0/default.jpg', 9, 1396371932),
(121, 'Buckys C++ Programming Tutorials - 1 - Installing CodeBlocks', 'tvC1WCdV1XU', 7, 'https://i1.ytimg.com/vi/tvC1WCdV1XU/default.jpg', 'thenewboston', 9, 1396372046),
(122, 'Adele - Someone Like You ', 'hLQl3WQQoQ0', 4, 'https://i1.ytimg.com/vi/hLQl3WQQoQ0/mqdefault.jpg', 'adele', 10, 1396372177),
(123, 'Adele - Rolling in the Deep ', 'rYEDA3JcQqw', 3, 'https://i1.ytimg.com/vi/rYEDA3JcQqw/mqdefault.jpg', 'adele', 10, 1396372240),
(124, 'Adele - Someone Like You (Live in Her Home)', 'NAc83CF8Ejk', 6, 'https://i1.ytimg.com/vi/NAc83CF8Ejk/mqdefault.jpg', 'adele', 8, 1396372353),
(125, 'Adele - Turning Tables (Live on Letterman) ', 'omkUG4kugvw', 4, 'https://i1.ytimg.com/vi/omkUG4kugvw/mqdefault.jpg', 'adele\r\n', 9, 1396372460),
(126, 'Adele - Chasing Pavements ', '08DjMT-qR9g', 3, 'https://i1.ytimg.com/vi/08DjMT-qR9g/mqdefault.jpg', 'adele 19                            ', 10, 1396372532),
(127, 'Adele - Hometown Glory ', 'BW9Fzwuf43c', 3, 'https://i1.ytimg.com/vi/BW9Fzwuf43c/mqdefault.jpg', 'adele 19', 9, 1396372629),
(128, 'Katy Perry - Dark Horse (Official) ft. Juicy J ', '0KSOMA3QBU0', 3, 'https://i1.ytimg.com/vi/0KSOMA3QBU0/mqdefault.jpg', 'katy perry', 9, 1396372902),
(129, 'Katy Perry - Unconditionally (Official', 'XjwZAa2EjKA', 3, 'https://i1.ytimg.com/vi/XjwZAa2EjKA/mqdefault.jpg', 'katy perry', 9, 1396372969),
(130, 'Katy Perry - Roar (Official) ', 'CevxZvSJLk8', 4, 'https://i1.ytimg.com/vi/CevxZvSJLk8/mqdefault.jpg', 'katy perry', 9, 1396373028),
(131, 'Katy Perry - Last Friday Night (T.G.I.F.) ', 'KlyXNRrsk4A', 8, 'https://i1.ytimg.com/vi/KlyXNRrsk4A/mqdefault.jpg', 'katy perry', 10, 1396373093),
(132, 'Finals - WS - Ratchanok Intanon vs Li Xuerui - 2013 BWF World Championships', 'bqUTCeVuaJA', 1, 'https://i1.ytimg.com/vi/bqUTCeVuaJA/mqdefault.jpg', 'badminton', 9, 1396373362),
(133, 'Finals - MS - Lin Dan vs Lee Chong Wei - 2013 BWF World Championships ', 'a64pWiUtgJQ', 2, 'https://i1.ytimg.com/vi/a64pWiUtgJQ/mqdefault.jpg', 'badminton', 10, 1396373428),
(134, 'The OSIM BWF World Superseries ', 'G251fSz6LD4', 0, 'https://i1.ytimg.com/vi/G251fSz6LD4/mqdefault.jpg', 'badminton                            ', 6, 1396373502),
(135, 'F - MS - Lee Chong Wei vs Lin Dan - 2012 All England ', 'TRNKfBmCa8M', 57, 'https://i1.ytimg.com/vi/TRNKfBmCa8M/mqdefault.jpg', 'badminton', 9, 1396373578),
(136, 'Phim HÃ i ÄÃ´Ì£i boÌng thiÃªÌu lÃ¢m full HD ChÃ¢u Tinh TriÌ€ ', 'TZ3DL3i61i4', 1, 'https://i1.ytimg.com/vi/TZ3DL3i61i4/mqdefault.jpg', 'chÃ¢u tinh trÃ¬                            ', 10, 1396373745),
(137, 'Finals - XD - T.Ahmad/L.Natsir vs Xu C./Ma J. - 2013 BWF World Championships ', 'p3k32Gxng3Q', 1, 'https://i1.ytimg.com/vi/p3k32Gxng3Q/mqdefault.jpg', 'badminton', 9, 1396373821),
(138, 'ChÃ¢u Tinh TrÃ¬ 2014 - Quan Xáº©m Lá»‘c Cá»‘c Lá»“ng Tiáº¿ng Full HD ', 'evt-Ic-w7CA', 1, 'https://i1.ytimg.com/vi/evt-Ic-w7CA/mqdefault.jpg', 'chÃ¢u tinh trÃ¬', 9, 1396373906),
(139, 'Finals - MD - M.Boe/C.Mogensen vs M.Ahsan/H.Setiawan - 2013 BWF World Championships ', 'V6ei7HEd85s', 1, 'https://i1.ytimg.com/vi/V6ei7HEd85s/mqdefault.jpg', 'badminton', 9, 1396373970),
(140, 'Finals - MS - Lee. C.W vs Lin D. - 2012 Victor Korea Open', '0RKlw33ZCSU', 1, 'https://i1.ytimg.com/vi/0RKlw33ZCSU/mqdefault.jpg', 'badminton', 9, 1396374027),
(141, 'QF - MS - Lin Dan vs Taufik Hidayat - 2012 All England ', 'Id1ljnbVUSE', 54, 'https://i1.ytimg.com/vi/Id1ljnbVUSE/mqdefault.jpg', 'badminton', 9, 1396374091),
(142, 'Finals - 2012 Yonex All England Open Badminton Championships ', 'RkHHP1eKhak', 5, 'https://i1.ytimg.com/vi/RkHHP1eKhak/mqdefault.jpg', 'badminton', 10, 1396374215),
(143, 'Finals - MS - Lee Chong Wei vs Chen Long - 2013 Yonex All England', 'RtHKe4e-vnQ', 1, 'https://i1.ytimg.com/vi/RtHKe4e-vnQ/mqdefault.jpg', 'badminton', 10, 1396374284),
(144, 'Lá»“ng Tiáº¿ng | TÃ¢n Lá»™c Äá»‰nh KÃ½ 1 - ChÃ¢u Tinh TrÃ¬ [Full HD] ', 'HFVCOPJ-zpg', 1, 'https://i1.ytimg.com/vi/HFVCOPJ-zpg/mqdefault.jpg', 'chÃ¢u tinh trÃ¬', 10, 1396374620),
(145, 'Lá»“ng Tiáº¿ng | Quá»‘c Sáº£n 007 - ChÃ¢u Tinh TrÃ¬, ViÃªn Vá»‹nh Nghi, La Gia Anh [Full HD] ', 'x0uVFR8O0BE', 1, 'https://i1.ytimg.com/vi/x0uVFR8O0BE/mqdefault.jpg', 'chÃ¢u tinh trÃ¬', 10, 1396374699),
(146, 'Lá»“ng Tiáº¿ng | Há»c Giáº£ CÆ°a GÃ¡i | ChÃ¢u Tinh TrÃ¬ | ÄÆ°á»ng BÃ¡ Há»• Äiá»ƒm Thu HÆ°Æ¡ng [Full HD] ', '-ohJv1lSgTk', 1, 'https://i1.ytimg.com/vi/-ohJv1lSgTk/mqdefault.jpg', 'chÃ¢u tinh trÃ¬', 9, 1396374765),
(147, 'Lá»“ng Tiáº¿ng | TrÆ°á»ng Há»c Uy Long 2 - ChÃ¢u Tinh TrÃ¬ [Full HD] ', '0fi0v6cLXl8', 1, 'https://i1.ytimg.com/vi/zN563AyjtUE/mqdefault.jpg', 'chÃ¢u tinh trÃ¬', 9, 1396374847),
(148, 'Lá»“ng Tiáº¿ng | TrÆ°á»ng Há»c Uy Long 1 - ChÃ¢u Tinh TrÃ¬ [Full HD] ', 'rcL8QTaB6Yc', 1, 'https://i1.ytimg.com/vi/rcL8QTaB6Yc/mqdefault.jpg', 'chÃ¢u tinh trÃ¬', 9, 1396374912),
(149, 'Lá»“ng Tiáº¿ng | TrÆ°á»ng Há»c Uy Long 3 - ChÃ¢u Tinh TrÃ¬, [Full HD] ', 'yeXbyn1ZZz8', 1, 'https://i1.ytimg.com/vi/yeXbyn1ZZz8/mqdefault.jpg', 'chÃ¢u tinh trÃ¬', 9, 1396374987),
(150, 'Lá»“ng Tiáº¿ng | TÃ¢n Lá»™c Äá»‰nh KÃ½ 2 - ChÃ¢u Tinh TrÃ¬ [Full HD]', '-s7H8wEF5XI', 1, 'https://i1.ytimg.com/vi/-s7H8wEF5XI/mqdefault.jpg', 'chÃ¢u tinh trÃ¬', 9, 1396375078),
(151, 'Lá»“ng Tiáº¿ng | ThÃ¡nh Tinh - Lá»«a Äáº£o Gáº·p Lá»«a Äáº£o | ChÃ¢u Tinh TrÃ¬ [Full HD] ', 'DXUlZQq5AU4', 1, 'https://i1.ytimg.com/vi/DXUlZQq5AU4/default.jpg', 'chÃ¢u tinh trÃ¬', 10, 1396375262);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=110 ;

--
-- Dumping data for table `tblvideo_cat`
--

INSERT INTO `tblvideo_cat` (`id`, `video_id`, `cat_id`) VALUES
(54, 111, 19),
(55, 112, 19),
(56, 113, 19),
(57, 114, 19),
(58, 115, 19),
(59, 97, 18),
(60, 98, 18),
(63, 100, 18),
(64, 101, 18),
(66, 102, 18),
(67, 103, 18),
(68, 104, 18),
(69, 105, 18),
(70, 99, 18),
(71, 116, 19),
(72, 117, 19),
(73, 118, 19),
(74, 119, 19),
(75, 120, 19),
(76, 121, 18),
(77, 122, 17),
(78, 123, 17),
(79, 124, 17),
(80, 125, 17),
(82, 126, 17),
(83, 127, 17),
(84, 128, 17),
(85, 129, 17),
(86, 130, 17),
(87, 131, 17),
(88, 132, 16),
(89, 133, 16),
(91, 134, 16),
(92, 135, 16),
(94, 137, 16),
(95, 136, 15),
(96, 138, 15),
(97, 139, 16),
(98, 140, 16),
(99, 141, 16),
(100, 142, 16),
(101, 143, 16),
(102, 144, 15),
(103, 145, 15),
(104, 146, 15),
(105, 147, 15),
(106, 148, 15),
(107, 149, 15),
(108, 150, 15),
(109, 151, 15);

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
