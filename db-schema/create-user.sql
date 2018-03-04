CREATE TABLE IF NOT EXISTS "user" (
	user_id serial NOT NULL PRIMARY KEY,
	"name" varchar(500) NOT NULL,
	surname varchar(500) NOT NULL,
	age int NOT NULL,
	description varchar(1000) NOT NULL,
	ppicture_url varchar(1000) NOT NULL,
	phone_num varchar(500) NOT NULL,
	address varchar(500) DEFAULT NULL
 );
