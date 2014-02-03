-- Initalize database for MySQL

-- drop tables
DROP TABLE IF EXISTS t_status;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS m_skill;
DROP TABLE IF EXISTS m_mine;
DROP TABLE IF EXISTS m_amulet_type;
DROP TABLE IF EXISTS m_amulet_level;
DROP TABLE IF EXISTS m_amulet;

-- create tables
source mysql/m_amulet.sql
source mysql/m_amulet_level.sql
source mysql/m_amulet_type.sql
source mysql/m_mine.sql
source mysql/m_skill.sql
source mysql/t_user.sql
source mysql/t_status.sql

-- import data
SET character_set_database=utf8;
LOAD DATA LOCAL INFILE 'data/m_amulet.csv' INTO TABLE m_amulet FIELDS TERMINATED BY '\t' ENCLOSED BY '"' ESCAPED BY '\\' IGNORE 1 LINES;
LOAD DATA LOCAL INFILE 'data/m_amulet_level.csv' INTO TABLE m_amulet_level FIELDS TERMINATED BY '\t' ENCLOSED BY '"' ESCAPED BY '\\' IGNORE 1 LINES;
LOAD DATA LOCAL INFILE 'data/m_amulet_type.csv' INTO TABLE m_amulet_type FIELDS TERMINATED BY '\t' ENCLOSED BY '"' ESCAPED BY '\\' IGNORE 1 LINES;
LOAD DATA LOCAL INFILE 'data/m_mine.csv' INTO TABLE m_mine FIELDS TERMINATED BY '\t' ENCLOSED BY '"' ESCAPED BY '\\' IGNORE 1 LINES;
LOAD DATA LOCAL INFILE 'data/m_skill.csv' INTO TABLE m_skill FIELDS TERMINATED BY '\t' ENCLOSED BY '"' ESCAPED BY '\\' IGNORE 1 LINES;
LOAD DATA LOCAL INFILE 'data/t_user.csv' INTO TABLE t_user FIELDS TERMINATED BY '\t' ENCLOSED BY '"' ESCAPED BY '\\' IGNORE 1 LINES;
LOAD DATA LOCAL INFILE 'data/t_status.csv' INTO TABLE t_status FIELDS TERMINATED BY '\t' ENCLOSED BY '"' ESCAPED BY '\\' IGNORE 1 LINES;
