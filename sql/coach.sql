use coachsystem;
CREATE TABLE IF NOT EXISTS `coach` (
  `coachid` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(30) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '0',
  `phone` varchar(50) NOT NULL DEFAULT '0',
  `idcard` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`coachid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教练';
