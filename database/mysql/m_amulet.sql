CREATE TABLE m_amulet ( 
	id varchar(255) NOT NULL,
	seed integer,
	name text,
	slot_num integer,
	note text,
	skill_id1 varchar(255),
	skill_id2 varchar(255),
	level_id varchar(255),
	type_id varchar(255),
	mine_id varchar(255),
	CONSTRAINT pk_m_amulet PRIMARY KEY (id)
);
