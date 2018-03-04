CREATE TABLE IF NOT EXISTS "picture" (
	picture_id serial NOT NULL PRIMARY KEY,
	url VARCHAR(5000) NOT NULL,
	category_id int REFERENCES category(category_id) ON DELETE CASCADE,
	item_id int REFERENCES item(item_id) ON DELETE CASCADE
);


# eventuall add a constraint on non-nullity of CATE_ID AND ITEM_ID