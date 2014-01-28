DROP TABLE IF EXISTS m_skill
;
CREATE TABLE m_skill ( 
	id text NOT NULL,
	name text,
	point integer
)
;

ALTER TABLE m_skill ADD CONSTRAINT PK_m_skill 
	PRIMARY KEY (id)
;


