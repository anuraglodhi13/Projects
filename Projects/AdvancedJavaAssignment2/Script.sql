Drop database if exists tshirt_search;
create database if not exists tshirt_search;
use tshirt_search;

CREATE TABLE tshirt_data (
	tshirt_id VARCHAR(30),
	tshirt_name VARCHAR(100000),
	tshirt_color VARCHAR(30),
	tshirt_genderRecommendation VARCHAR(10),
	tshirt_size VARCHAR(30),
	tshirt_price VARCHAR(30),
	tshirt_rating VARCHAR(30),
	tshirt_availability VARCHAR(30),
	PRIMARY KEY (tshirt_id)
) ENGINE=InnoDB;
