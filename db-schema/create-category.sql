CREATE TABLE IF NOT EXISTS "category" (
	category_id serial NOT NULL PRIMARY KEY,
	name VARCHAR(500) NOT NULL,
	description VARCHAR(500) NULL,
	tips VARCHAR(1000) NULL
);
