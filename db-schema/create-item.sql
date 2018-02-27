CREATE TABLE IF NOT EXISTS "item" (
	item_id serial NOT NULL PRIMARY KEY,
	name varchar(500) NOT NULL,
	avg_price double precision NOT NULL,
	description varchar(1000) NOT NULL
);

--items are stored by default in Ask database
--@todo : ON DELETE CASCADE necessary / dangerous ?
