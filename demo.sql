-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 29, 2017 at 08:47 AM
-- Server version: 5.7.17-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `tag` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `descri` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `tag`, `name`, `price`, `username`, `descri`) VALUES
(0, 0, 'testbook', 1000, 'tester', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` bigint(20) NOT NULL,
  `tag` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `teacher` varchar(8) NOT NULL DEFAULT '無',
  `score` int(11) NOT NULL,
  `difficulty` int(11) NOT NULL DEFAULT '0',
  `reward` int(11) NOT NULL DEFAULT '0',
  `student` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `tag`, `name`, `teacher`, `score`, `difficulty`, `reward`, `student`) VALUES
(1109, 0, '大學英文5F', '陳秋月', 0, 0, 0, 0),
(1111, 0, '大學英文5G', '張敦業', 0, 0, 0, 0),
(1113, 0, '大學英文5H', '陳秋月', 0, 0, 0, 0),
(1130, 0, '微積分 II', '白惠明', 0, 0, 0, 0),
(1135, 0, '大學英文：英語聽講練習5F', '彭怡婷', 0, 0, 0, 0),
(1136, 0, '大學英文：英語聽講練習5G', '彭怡婷', 0, 0, 0, 0),
(1138, 0, '大學英文：英語聽講練習5H', '張敦業', 0, 0, 0, 0),
(1194, 0, '線性代數', '吳信龍', 0, 0, 0, 0),
(1210, 0, '物件導向程式設計', '戴志華', 36, 36, 36, 12),
(1211, 0, '物理學', '林伯星', 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `course_appra`
--

CREATE TABLE `course_appra` (
  `id` bigint(20) NOT NULL,
  `username` varchar(32) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `course_appra`
--

INSERT INTO `course_appra` (`id`, `username`, `content`) VALUES
(1210, 'test', '測試'),
(1210, 'test', '測試'),
(1210, 'test', '測試'),
(1210, 'test', '測試'),
(1210, 'test', '測試'),
(1210, 'test', '測試'),
(1210, 'test', '測試'),
(1210, 'test', '測試'),
(1210, 'test', '測試');

-- --------------------------------------------------------

--
-- Table structure for table `gossip`
--

CREATE TABLE `gossip` (
  `id` bigint(20) NOT NULL,
  `reply` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `title` varchar(32) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gossip`
--

INSERT INTO `gossip` (`id`, `reply`, `username`, `title`, `content`) VALUES
(0, 4, 'tester', 'test!', 'yosoro');

-- --------------------------------------------------------

--
-- Table structure for table `gossip_reply`
--

CREATE TABLE `gossip_reply` (
  `id` bigint(20) NOT NULL,
  `username` varchar(32) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gossip_reply`
--

INSERT INTO `gossip_reply` (`id`, `username`, `content`) VALUES
(0, 'tester', 'haha'),
(0, 'tester', 'haha'),
(0, 'tester', 'haha'),
(0, 'tester', 'haha');

-- --------------------------------------------------------

--
-- Table structure for table `stuff`
--

CREATE TABLE `stuff` (
  `id` bigint(20) NOT NULL,
  `tag` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `descri` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stuff`
--

INSERT INTO `stuff` (`id`, `tag`, `name`, `price`, `username`, `descri`) VALUES
(0, 0, 'testsruff', 1000, 'tester', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(32) NOT NULL,
  `email` varchar(64) NOT NULL,
  `hash` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `email`, `hash`) VALUES
('tester', '123@456.789', 'pass');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gossip`
--
ALTER TABLE `gossip`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stuff`
--
ALTER TABLE `stuff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD UNIQUE KEY `username` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
