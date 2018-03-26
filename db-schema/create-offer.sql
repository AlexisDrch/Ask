CREATE TABLE IF NOT EXISTS "offer" (
<<<<<<< HEAD
	offer_id serial NOT NULL PRIMARY KEY,
=======
>>>>>>> 4137e7c2e446378b88aeb13060ca0829c0289fb4
	belonging_id int NOT NULL REFERENCES belonging(belonging_id) ON DELETE CASCADE,
	request_id int NOT NULL REFERENCES request(request_id) ON DELETE CASCADE,
	provider_id int NOT NULL REFERENCES "user"(user_id) ON DELETE CASCADE,
	begin_date date NOT NULL,
	end_date date NOT NULL,
	lon double precision NOT NULL,
	lat double precision NOT NULL,
<<<<<<< HEAD
	description varchar(1000) NOT NULL,
	message varchar(1000) NOT NULL
);

--offers are made by providers. They correspond to one item requested.
--@todo : ON DELETE CASCADE necessary / dangerous ?
=======
	description varchar(1000),
	message varchar(1000),
	PRIMARY KEY (request_id, provider_id),
    UNIQUE (request_id, provider_id)
);

--offers are made by providers. They correspond to one item requested.
--@todo : ON DELETE CASCADE necessary / dangerous ?

>>>>>>> 4137e7c2e446378b88aeb13060ca0829c0289fb4
