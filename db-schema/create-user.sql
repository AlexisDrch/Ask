CREATE TABLE IF NOT EXISTS "user" (
	user_id serial NOT NULL PRIMARY KEY,
	user_name varchar(500) NOT NULL,
	password varchar(500) NOT NULL,
	email varchar(500) NOT NULL,
	"name" varchar(500) NOT NULL,
	surname varchar(500) NOT NULL,
	description varchar(1000) DEFAULT NULL,
	ppicture_url varchar(1000) NOT NULL,
	phone_num varchar(500) DEFAULT NULL,
	age int DEFAULT NULL,
	sex int DEFAULT NULL,
	country int DEFAULT NULL,
	address varchar(1000) DEFAULT NULL,
	address_string varchar(1000) DEFAULT NULL,
	UNIQUE(user_name, password),
	UNIQUE(user_name, email)
 );
