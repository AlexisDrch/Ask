CREATE TABLE IF NOT EXISTS "category_picture" (
	ca_picture_id serial NOT NULL PRIMARY KEY,
	url VARCHAR(2000) NOT NULL,
	category_id int NOT NULL REFERENCES category(category_id) ON DELETE CASCADE
);


