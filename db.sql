--
-- Date:           26-09-2021 20:19:44
-- Server version: 5.5.50
-- Host:           localhost
-- Database:       roner
-- User:           root
--

SET NAMES 'latin1';

CREATE SCHEMA `roner` COLLATE latin1_swedish_ci;

USE `roner`;

CREATE TABLE `player`(
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tournamentId` BIGINT(20) NOT NULL,
  `player` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 7
COLLATE = latin1_swedish_ci
ROW_FORMAT = COMPACT;

CREATE TABLE `tournaments`(
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `reward` DOUBLE(10,2) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 11
COLLATE = latin1_swedish_ci
ROW_FORMAT = COMPACT;

ALTER TABLE `player` DISABLE KEYS;

INSERT INTO `player` VALUES
    (1, 1, 'LAVAN'),
    (2, 1, 'Giri'),
    (5, 4, 'ghjgjhgjh'),
    (6, 4, 'hjkghjghj');

ALTER TABLE `player` ENABLE KEYS;

ALTER TABLE `tournaments` DISABLE KEYS;

INSERT INTO `tournaments` VALUES
    (1, '80'),
    (3, '343'),
    (4, '343');

ALTER TABLE `tournaments` ENABLE KEYS;
