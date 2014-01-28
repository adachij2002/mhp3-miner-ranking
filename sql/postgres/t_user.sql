DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user ( 
	id text NOT NULL,
	mh_name text,
	password text,
	comment text,
	publish boolean,
	CONSTRAINT pk_t_user PRIMARY KEY (id)
);
