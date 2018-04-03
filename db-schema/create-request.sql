CREATE TABLE IF NOT EXISTS "request" (
	request_id serial NOT NULL PRIMARY KEY,
	item_id int NOT NULL REFERENCES item(item_id) ON DELETE CASCADE,
	requester_id int NOT NULL REFERENCES "user"(user_id) ON DELETE CASCADE,
	requester_name varchar(500),
	requester_surname varchar(500),
	requester_ppicture_url varchar(1000),
	request_price double precision NOT NULL,
	status int NOT NULL,
	begin_date date NOT NULL,
	end_date date NOT NULL,
	lon double precision NOT NULL,
	lat double precision NOT NULL,
	description varchar(1000) NOT NULL
);

--requests are made by requesters. They correspond to one item requested.
--@todo : ON DELETE CASCADE necessary / dangerous ?
