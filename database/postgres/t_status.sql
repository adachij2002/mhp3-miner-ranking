DROP TABLE IF EXISTS t_status;

CREATE TABLE t_status ( 
	checked boolean,
	user_id text,
	amulet_id text,
	CONSTRAINT pk_t_status PRIMARY KEY (user_id, amulet_id),
	CONSTRAINT fk_t_status_m_amulet FOREIGN KEY (amulet_id) REFERENCES m_amulet (id),
	CONSTRAINT fk_t_status_t_user FOREIGN KEY (user_id) REFERENCES t_user (id)
);
