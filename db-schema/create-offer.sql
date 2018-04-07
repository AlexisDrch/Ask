CREATE TABLE IF NOT EXISTS "offer" (
	belonging_id int NOT NULL REFERENCES item(item_id) ON DELETE CASCADE,
	request_id int NOT NULL REFERENCES request(request_id) ON DELETE CASCADE,
	requester_id int REFERENCES "user"(user_id) ON DELETE CASCADE,
	requester_name varchar(500),
	requester_surname varchar(500),
	requester_ppicture_url varchar(1000),
	provider_id int NOT NULL REFERENCES "user"(user_id) ON DELETE CASCADE,
	provider_name varchar(500),
	provider_surname varchar(500),
	provider_ppicture_url varchar(1000),
	begin_date date NOT NULL,
	end_date date NOT NULL,
	lon double precision NOT NULL,
	lat double precision NOT NULL,
	offer_price double precision NOT NULL,
	description varchar(1000),
	status int NOT NULL,
	message varchar(1000),
	PRIMARY KEY (request_id, provider_id),
    UNIQUE (request_id, provider_id)
);

--offers are made by providers. They correspond to one item requested.
--@todo : ON DELETE CASCADE necessary / dangerous ?

