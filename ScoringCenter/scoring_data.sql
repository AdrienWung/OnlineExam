/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : scoring_data

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-12-12 21:16:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail` (
  `paperID` int(11) NOT NULL,
  `questionID` int(11) NOT NULL,
  `personalAnswer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paperID`,`questionID`),
  KEY `foreign2` (`questionID`),
  CONSTRAINT `foreign1` FOREIGN KEY (`paperID`) REFERENCES `paper` (`paperID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of detail
-- ----------------------------

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `paperID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `examID` int(255) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`paperID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('1', 'wangjc19960926@163.com', '102', '85');
INSERT INTO `paper` VALUES ('2', 'wangjc19960926@163.com', '103', '100');
