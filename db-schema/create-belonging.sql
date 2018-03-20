CREATE TABLE IF NOT EXISTS "belonging" (
	belonging_id serial PRIMARY KEY,
	name VARCHAR(500) NOT NULL,
	type VARCHAR(500) NOT NULL,
	price double precision NOT NULL,
	description varchar(1000) NOT NULL,
	max_rent_duration int NOT NULL,
	item_id int NOT NULL REFERENCES item(item_id) ON DELETE CASCADE,
	owner_id int NOT NULL REFERENCES "user"(user_id) ON DELETE CASCADE
);

--belongings are items owned by a user.
--@todo : ON DELETE CASCADE necessary / dangerous ?

