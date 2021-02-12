CREATE TABLE IF NOT EXISTS `regions`(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(64) unique NOT NULL,
    `shortName` varchar(64) NOT NULL
);
