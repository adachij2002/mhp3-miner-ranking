DROP TABLE IF EXISTS m_amulet
;
CREATE TABLE m_amulet ( 
	id text NOT NULL,
	seed integer,
	name text,
	slot_num integer,
	note text,
	skill_id1 text,
	skill_id2 text,
	level_id text,
	type_id text,
	mine_id text
)
;

ALTER TABLE m_amulet ADD CONSTRAINT PK_m_amulet 
	PRIMARY KEY (id)
;

