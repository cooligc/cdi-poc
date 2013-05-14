CREATE DATABASE IF NOT EXISTS test;

USE test;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `username`  varchar(255) default NULL,
  `email`  varchar(255) default NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO user VALUES (1, 'HIMANSHU', 'abc@xyz.com');
INSERT INTO user VALUES (2, 'TEST', 'pqr@xyz.com');
INSERT INTO user VALUES (3, 'SOME OTHER GUY', 'def@xyz.com');