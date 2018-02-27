CREATE TABLE IF NOT EXISTS "item_picture" (
	it_picture_id serial NOT NULL PRIMARY KEY,
	url VARCHAR(2000) NOT NULL,
	item_id int NOT NULL REFERENCES item(item_id) ON DELETE CASCADE
);