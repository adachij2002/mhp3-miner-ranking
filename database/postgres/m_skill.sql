DROP TABLE IF EXISTS m_skill;

CREATE TABLE m_skill ( 
	id text NOT NULL,
	name text,
	point integer,
	CONSTRAINT pk_m_skill PRIMARY KEY (id)
);
