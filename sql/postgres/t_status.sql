DROP TABLE IF EXISTS t_status;
DROP SEQUENCE IF EXISTS t_status_id_seq;

CREATE SEQUENCE t_status_id_seq INCREMENT 1 START 1;

CREATE TABLE t_status ( 
	id bigint DEFAULT NEXTVAL('t_status_id_seq'::TEXT) NOT NULL,
	checked boolean,
	user_id text,
	amulet_id text,
	CONSTRAINT pk_t_status PRIMARY KEY (id),
	CONSTRAINT fk_t_status_m_amulet FOREIGN KEY (amulet_id) REFERENCES m_amulet (id),
	CONSTRAINT fk_t_status_t_user FOREIGN KEY (user_id) REFERENCES t_user (id)
);
