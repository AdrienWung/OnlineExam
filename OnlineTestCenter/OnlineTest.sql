/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : OnlineTest

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 12/16/2017 17:19:31 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `Exam`
-- ----------------------------
DROP TABLE IF EXISTS `Exam`;
CREATE TABLE `Exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `startTime` varchar(255) DEFAULT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `questionNum` int(11) DEFAULT NULL,
  `questionScore` int(11) DEFAULT NULL,
  `examName` varchar(255) DEFAULT NULL,
  `course` varchar(255) DEFAULT NULL,
  `groupBy` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `participants` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
