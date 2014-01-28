DROP TABLE IF EXISTS m_amulet_type
;
CREATE TABLE m_amulet_type ( 
	id text NOT NULL,
	name text
)
;

ALTER TABLE m_amulet_type ADD CONSTRAINT PK_m_mine 
	PRIMARY KEY (id)
;


