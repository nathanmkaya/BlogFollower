
DROP TABLE IF EXISTS `posts`;
		
CREATE TABLE `posts` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `title` MEDIUMTEXT NOT NULL DEFAULT 'NULL',
  `description` MEDIUMTEXT NOT NULL DEFAULT 'NULL',
  `pubDate` DATE NOT NULL DEFAULT 'NULL',
  `author` MEDIUMTEXT NOT NULL DEFAULT 'NULL',
  `link` MEDIUMTEXT NOT NULL DEFAULT 'NULL',
  `title_blogs` MEDIUMTEXT NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'blogs'
-- 
-- ---

DROP TABLE IF EXISTS `blogs`;
		
CREATE TABLE `blogs` (
  `title` MEDIUMTEXT NOT NULL DEFAULT 'NULL',
  `description` MEDIUMTEXT NOT NULL DEFAULT 'NULL',
  `link` MEDIUMTEXT NOT NULL DEFAULT 'NULL',
  `feed url` MEDIUMTEXT NOT NULL DEFAULT 'NULL',
  PRIMARY KEY (`title`, `Id`)
);

-- ---
-- Foreign Keys 
-- ---

ALTER TABLE `posts` ADD FOREIGN KEY (title_blogs) REFERENCES `blogs` (`title`);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE `posts` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `blogs` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `posts` (`id`,`title`,`description`,`pubDate`,`author`,`link`,`title_blogs`) VALUES
-- ('','','','','','','');
-- INSERT INTO `blogs` (`title`,`description`,`link`,`feed url`) VALUES
-- ('','','','');
