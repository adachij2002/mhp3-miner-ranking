-- Initalize database for PostgreSQL

-- drop tables
DROP TABLE IF EXISTS t_status;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS m_skill;
DROP TABLE IF EXISTS m_mine;
DROP TABLE IF EXISTS m_amulet_type;
DROP TABLE IF EXISTS m_amulet_level;
DROP TABLE IF EXISTS m_amulet;

-- create tables
\i postgres/m_amulet.sql
\i postgres/m_amulet_level.sql
\i postgres/m_amulet_type.sql
\i postgres/m_mine.sql
\i postgres/m_skill.sql
\i postgres/t_user.sql
\i postgres/t_status.sql

-- import data
\copy m_amulet FROM 'data/m_amulet.csv' WITH CSV QUOTE E'\"' ESCAPE E'\\' DELIMITER E'\t' NULL 'NULL' HEADER ENCODING 'UTF8';
\copy m_amulet_level FROM 'data/m_amulet_level.csv' WITH CSV QUOTE E'\"' ESCAPE E'\\' DELIMITER E'\t' NULL 'NULL' HEADER ENCODING 'UTF8';
\copy m_amulet_type FROM 'data/m_amulet_type.csv' WITH CSV QUOTE E'\"' ESCAPE E'\\' DELIMITER E'\t' NULL 'NULL' HEADER ENCODING 'UTF8';
\copy m_mine FROM 'data/m_mine.csv' WITH CSV QUOTE E'\"' ESCAPE E'\\' DELIMITER E'\t' NULL 'NULL' HEADER ENCODING 'UTF8';
\copy m_skill FROM 'data/m_skill.csv' WITH CSV QUOTE E'\"' ESCAPE E'\\' DELIMITER E'\t' NULL 'NULL' HEADER ENCODING 'UTF8';
\copy t_user FROM 'data/t_user.csv' WITH CSV QUOTE E'\"' ESCAPE E'\\' DELIMITER E'\t' NULL 'NULL' HEADER ENCODING 'UTF8';
\copy t_status FROM 'data/t_status.csv' WITH CSV QUOTE E'\"' ESCAPE E'\\' DELIMITER E'\t' NULL 'NULL' HEADER ENCODING 'UTF8';
