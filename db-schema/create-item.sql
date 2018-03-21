CREATE TABLE IF NOT EXISTS "item" (
	item_id serial NOT NULL PRIMARY KEY,
	name varchar(500) NOT NULL,
	category_id int REFERENCES category(category_id) ON DELETE CASCADE,
	avg_price double precision NOT NULL,
	picture varchar(1000) NOT NULL,
	description varchar(1000)
);

--items are stored by default in Ask database
--@todo : ON DELETE CASCADE necessary / dangerous ?
