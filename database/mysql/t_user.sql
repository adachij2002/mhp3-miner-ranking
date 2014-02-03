CREATE TABLE t_user ( 
	id varchar(255) NOT NULL,
	mh_name text,
	password text,
	comment text,
	publish boolean,
	CONSTRAINT pk_t_user PRIMARY KEY (id)
);
