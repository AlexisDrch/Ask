--COPY category("name",description,tips)
--FROM '/Users/alexisdurocher/Docs/YouTheaSea/P18/cours/MobileApps/Ask/db-schema/csv/categories.csv' DELIMITER ',' CSV

COPY "user"(user_name,"password",email,"name",surname,description,ppicture_url,phone_num,address,age,sex,country,address_string)
FROM '/Users/alexisdurocher/Docs/YouTheaSea/P18/cours/MobileApps/Ask/db-schema/csv/users.csv' DELIMITER ',' CSV

--COPY "item"("name",category_id,avg_price,picture,description)
--FROM '/Users/alexisdurocher/Docs/YouTheaSea/P18/cours/MobileApps/Ask/db-schema/csv/items.csv' DELIMITER ',' CSV


