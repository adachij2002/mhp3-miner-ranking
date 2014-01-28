DROP TABLE IF EXISTS m_amulet_level
;
CREATE TABLE m_amulet_level ( 
	id text NOT NULL,
	name text
)
;

ALTER TABLE m_amulet_level ADD CONSTRAINT PK_m_mine 
	PRIMARY KEY (id)
;


