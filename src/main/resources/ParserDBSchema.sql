
-- Dumping database structure for parser
CREATE DATABASE IF NOT EXISTS `parser` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `parser`;

-- Dumping structure for table parser.server_access_blocked
CREATE TABLE IF NOT EXISTS `server_access_blocked` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_start_time` datetime DEFAULT NULL,
  `blocking_comment` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DURATION` int(11) DEFAULT NULL,
  `IP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ip_blocked_time` datetime DEFAULT NULL,
  `THRESHOLD` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping structure for table parser.server_access_log
CREATE TABLE IF NOT EXISTS `server_access_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `REQUEST` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `server_access_time` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `user_agent` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
