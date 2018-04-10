use coachsystem;
CREATE TABLE IF NOT EXISTS `course` (
  `courseid` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(50) NOT NULL DEFAULT '0',
  `place` varchar(50) NOT NULL DEFAULT '0',
  `content` varchar(50) NOT NULL DEFAULT '0',
  `pass` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程';

