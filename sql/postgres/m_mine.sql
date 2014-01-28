DROP TABLE IF EXISTS m_mine
;
CREATE TABLE m_mine ( 
	id text NOT NULL,
	name text
)
;

ALTER TABLE m_mine ADD CONSTRAINT PK_m_mine 
	PRIMARY KEY (id)
;


