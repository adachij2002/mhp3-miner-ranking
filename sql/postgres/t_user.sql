DROP TABLE IF EXISTS t_user
;
CREATE TABLE t_user ( 
	id text NOT NULL,
	mh_name text,
	password text,
	comment text,
	publish boolean
)
;

ALTER TABLE t_user ADD CONSTRAINT PK_t_user 
	PRIMARY KEY (id)
;


