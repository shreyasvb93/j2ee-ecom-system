-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2017 at 01:50 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectv1`
--

-- --------------------------------------------------------

--
-- Table structure for table `account_info`
--

CREATE TABLE `account_info` (
  `userID` int(11) NOT NULL,
  `email` varchar(25) NOT NULL,
  `pw` varchar(100) NOT NULL,
  `AccountType` enum('A','C','P') NOT NULL,
  `lastLogin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `aid` int(10) UNSIGNED NOT NULL,
  `street` varchar(100) NOT NULL,
  `province` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `zip` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `bid` varchar(20) NOT NULL,
  `title` varchar(60) NOT NULL,
  `price` int(11) NOT NULL,
  `category` enum('Science','Fiction','Engineering','Art','Philosophy','Literature') NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `po`
--

CREATE TABLE `po` (
  `poID` int(10) UNSIGNED NOT NULL,
  `userID` int(11) NOT NULL,
  `order_status` enum('ORDERED','PROCESSED','DENIED') NOT NULL,
  `aid` int(10) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `poitem`
--

CREATE TABLE `poitem` (
  `poID` int(10) UNSIGNED NOT NULL,
  `bid` varchar(20) NOT NULL,
  `price` int(10) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `fName` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `memberSince` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `fName`, `lname`, `memberSince`) VALUES
(20000, 'Bruce', 'Wayne', '2017-04-01 20:00:46'),
(20001, 'Drake', 'Santana', '2017-04-01 20:00:46'),
(20002, 'Tony', 'Stark', '2017-04-01 20:00:46'),
(20003, 'Xavon', 'Charles', '2017-04-01 20:00:46'),
(20004, 'Rohit', 'Sattu', '2017-04-01 20:00:46'),
(20005, 'Shrey', 'Bhivandkar', '2017-04-01 20:00:46');

-- --------------------------------------------------------

--
-- Table structure for table `users_address`
--

CREATE TABLE `users_address` (
  `userID` int(11) NOT NULL,
  `aid` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `visitevent`
--

CREATE TABLE `visitevent` (
  `visit_date` varchar(8) NOT NULL,
  `bid` varchar(20) NOT NULL,
  `eventtype` enum('VIEW','CART','PURCHASE') NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`aid`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bid`);

--
-- Indexes for table `po`
--
ALTER TABLE `po`
  ADD PRIMARY KEY (`poID`),
  ADD KEY `aid` (`aid`),
  ADD KEY `pid` (`userID`);

--
-- Indexes for table `poitem`
--
ALTER TABLE `poitem`
  ADD PRIMARY KEY (`poID`,`bid`),
  ADD KEY `poID` (`poID`),
  ADD KEY `bid` (`bid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- Indexes for table `users_address`
--
ALTER TABLE `users_address`
  ADD PRIMARY KEY (`userID`,`aid`),
  ADD KEY `aid` (`aid`);

--
-- Indexes for table `visitevent`
--
ALTER TABLE `visitevent`
  ADD KEY `bid` (`bid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `aid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `po`
--
ALTER TABLE `po`
  MODIFY `poID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20019;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
