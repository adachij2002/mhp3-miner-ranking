CREATE TABLE t_status ( 
	checked boolean,
	user_id varchar(255),
	amulet_id varchar(255),
	CONSTRAINT pk_t_status PRIMARY KEY (user_id, amulet_id),
	CONSTRAINT fk_t_status_m_amulet FOREIGN KEY (amulet_id) REFERENCES m_amulet (id),
	CONSTRAINT fk_t_status_t_user FOREIGN KEY (user_id) REFERENCES t_user (id)
);
