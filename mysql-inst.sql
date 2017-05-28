
CREATE DATABASE `parking`;

 CREATE TABLE `charges` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `modified_at` datetime NOT NULL,
  `c_scheme` varchar(255) NOT NULL,
  `v_type` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `v_type` (`v_type`)
);

 CREATE TABLE `vehicle` (
  `v_num` varchar(255) NOT NULL,
  `v_type` varchar(255) NOT NULL,
  PRIMARY KEY (`v_num`)
);

CREATE TABLE `parking_slip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `v_num` varchar(255) NOT NULL,
  `check_in` datetime NOT NULL,
  `check_out` datetime DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vvv_num` (`v_num`),
  CONSTRAINT `fk_v` FOREIGN KEY (`v_num`) REFERENCES `vehicle` (`v_num`)
); 


