CREATE TABLE ROOT.AIRLINES (
	AIRLINES_ICAO_CODE VARCHAR(5) NOT NULL,
	AIRLINE_CALL_SIGN VARCHAR(30) NOT NULL,
	AIRLINE_COUNTRY VARCHAR(40) NOT NULL,
	AIRLINE_NAME VARCHAR(40) NOT NULL,
	PRIMARY KEY (AIRLINES_ICAO_CODE)
);	
CREATE TABLE ROOT.AIRPORTS (
	AIRPORT_CODE VARCHAR(10) NOT NULL,
	AIRPORT_LOCATION VARCHAR(60) NOT NULL,
	AIRPORT_NAME VARCHAR(50) NOT NULL,
	PRIMARY KEY (AIRPORT_CODE)
);	
CREATE TABLE ROOT.FLIGHTS (
	FLIGHT_CODE VARCHAR(30) NOT NULL,
	F_DATE TIMESTAMP NOT NULL,
	FLIGHT_DISTANCE BIGINT NOT NULL,
	NUM_OF_BUSINESS BIGINT NOT NULL,
	NUM_OF_ECONOMY BIGINT NOT NULL,
	NUM_OF_FIRST BIGINT NOT NULL,
	T_DATE TIMESTAMP NOT NULL,
	AIRLINE_CODE VARCHAR(5) NOT NULL,
	AIRPORT_FROM VARCHAR(10) NOT NULL,
	AIRPORT_TO VARCHAR(10) NOT NULL,
	COPILOT_ID DECIMAL(22),
	PILOT_ID DECIMAL(22) NOT NULL,
	PRIMARY KEY (FLIGHT_CODE)
)	
CREATE TABLE ROOT.PASSENGERS (
	PASS_ID DECIMAL(22) NOT NULL,
	COUNTRY VARCHAR(40) NOT NULL,
	FIRST_NAME VARCHAR(50) NOT NULL,
	LAST_NAME VARCHAR(50) NOT NULL,
	PASSPORT_NUM VARCHAR(10) NOT NULL,
	USERNAME VARCHAR(10) NOT NULL,
	PRIMARY KEY (PASS_ID)
);	
CREATE TABLE ROOT.PILOTS (
	PILOT_ID DECIMAL(22) NOT NULL,
	NAME VARCHAR(30) NOT NULL,
	SURNAME VARCHAR(30) NOT NULL,
	PRIMARY KEY (PILOT_ID)
);	
CREATE TABLE ROOT.PRICES (
	PRICE_ID DECIMAL(22) NOT NULL,
	TICKET_PRICE BIGINT NOT NULL,
	TRAVEL_CLASS VARCHAR(15) NOT NULL,
	FLIGHT_CODE VARCHAR(30) NOT NULL,
	PRIMARY KEY (PRICE_ID)
);	
CREATE TABLE ROOT.TICKETS (
	TICKET_CODE VARCHAR(30) NOT NULL,
	TICKET_STATUS VARCHAR(30) NOT NULL,
	PASS_ID DECIMAL(22) NOT NULL,
	PRICE_ID DECIMAL(22) NOT NULL,
	PRIMARY KEY (TICKET_CODE)
);	
CREATE TABLE ROOT.USERS (
	USERNAME VARCHAR(10) NOT NULL,
	PASSWORD VARCHAR(10) NOT NULL,
	USER_ROLE BIGINT NOT NULL,
	PRIMARY KEY (USERNAME)
);	
INSERT INTO ROOT.AIRLINES(AIRLINES_ICAO_CODE, AIRLINE_CALL_SIGN, AIRLINE_COUNTRY, AIRLINE_NAME) VALUES ('MBA', 'MAL', 'Беларусь', 'Минск АирЛайн');	
INSERT INTO ROOT.AIRLINES(AIRLINES_ICAO_CODE, AIRLINE_CALL_SIGN, AIRLINE_COUNTRY, AIRLINE_NAME) VALUES ('BTA1', 'RAL', 'Россия', 'РосЭйр');	
INSERT INTO ROOT.AIRLINES(AIRLINES_ICAO_CODE, AIRLINE_CALL_SIGN, AIRLINE_COUNTRY, AIRLINE_NAME) VALUES ('F12A', 'SA98', 'Россия', 'СуперАвиа');
INSERT INTO ROOT.AIRPORTS(AIRPORT_CODE, AIRPORT_LOCATION, AIRPORT_NAME) VALUES ('DomDed', 'Москва', 'Домодедово');	
INSERT INTO ROOT.AIRPORTS(AIRPORT_CODE, AIRPORT_LOCATION, AIRPORT_NAME) VALUES ('WEB', 'Беларусь', 'ВорлИнж Букинг');	
INSERT INTO ROOT.AIRPORTS(AIRPORT_CODE, AIRPORT_LOCATION, AIRPORT_NAME) VALUES ('SubMi', 'Россия', 'Шереметьево');	
INSERT INTO ROOT.AIRPORTS(AIRPORT_CODE, AIRPORT_LOCATION, AIRPORT_NAME) VALUES ('NutI', 'Минск', 'Минск Центр')	;
INSERT INTO ROOT.FLIGHTS(FLIGHT_CODE, F_DATE, FLIGHT_DISTANCE, NUM_OF_BUSINESS, NUM_OF_ECONOMY, NUM_OF_FIRST, T_DATE, AIRLINE_CODE, AIRPORT_FROM, AIRPORT_TO, COPILOT_ID, PILOT_ID) VALUES ('12BC', '2019-05-01 15:00:00.0', 1200, 66, 45, 76, '2019-05-02 01:00:00.0', 'MBA', 'WEB', 'DomDed', 1, 3);	
INSERT INTO ROOT.FLIGHTS(FLIGHT_CODE, F_DATE, FLIGHT_DISTANCE, NUM_OF_BUSINESS, NUM_OF_ECONOMY, NUM_OF_FIRST, T_DATE, AIRLINE_CODE, AIRPORT_FROM, AIRPORT_TO, COPILOT_ID, PILOT_ID) VALUES ('12', '2019-06-16 02:20:00.0', 2000, 78, 56, 66, '2019-06-16 09:20:00.0', 'MBA', 'DomDed', 'NutI', 1, 3);	
INSERT INTO ROOT.FLIGHTS(FLIGHT_CODE, F_DATE, FLIGHT_DISTANCE, NUM_OF_BUSINESS, NUM_OF_ECONOMY, NUM_OF_FIRST, T_DATE, AIRLINE_CODE, AIRPORT_FROM, AIRPORT_TO, COPILOT_ID, PILOT_ID) VALUES ('I2C', '2019-07-15 03:00:00.0', 1200, 55, 55, 55, '2019-07-16 15:33:00.0', 'MBA', 'DomDed', 'SubMi', 1, 2);	
INSERT INTO ROOT.FLIGHTS(FLIGHT_CODE, F_DATE, FLIGHT_DISTANCE, NUM_OF_BUSINESS, NUM_OF_ECONOMY, NUM_OF_FIRST, T_DATE, AIRLINE_CODE, AIRPORT_FROM, AIRPORT_TO, COPILOT_ID, PILOT_ID) VALUES ('676', '2012-02-02 04:00:00.0', 7800, 88, 56, 99, '2012-02-02 08:00:00.0', 'MBA', 'NutI', 'DomDed', 3, 1);	
INSERT INTO ROOT.PASSENGERS(PASS_ID, COUNTRY, FIRST_NAME, LAST_NAME, PASSPORT_NUM, USERNAME) VALUES (1288, 'Беларусь', 'Настя', 'Кравцова', 'BM44444', 'nastia');	
INSERT INTO ROOT.PASSENGERS(PASS_ID, COUNTRY, FIRST_NAME, LAST_NAME, PASSPORT_NUM, USERNAME) VALUES (1289, 'Беларусь', 'Ivan', 'Ivanov', 'BM45889', 'user');	
INSERT INTO ROOT.PILOTS(PILOT_ID, NAME, SURNAME) VALUES (1, 'Иван', 'Иванов');
INSERT INTO ROOT.PILOTS(PILOT_ID, NAME, SURNAME) VALUES (2, 'Петр', 'Петров');	
INSERT INTO ROOT.PILOTS(PILOT_ID, NAME, SURNAME) VALUES (3, 'Левонов', 'Леон');
INSERT INTO ROOT.PRICES(PRICE_ID, TICKET_PRICE, TRAVEL_CLASS, FLIGHT_CODE) VALUES (435, 300, 'BUSINESS', 'I2C');	
INSERT INTO ROOT.PRICES(PRICE_ID, TICKET_PRICE, TRAVEL_CLASS, FLIGHT_CODE) VALUES (12, 230, ' ECONOMY', 'I2C');	
INSERT INTO ROOT.PRICES(PRICE_ID, TICKET_PRICE, TRAVEL_CLASS, FLIGHT_CODE) VALUES (568, 8900, 'FIRST', '12BC');	
INSERT INTO ROOT.PRICES(PRICE_ID, TICKET_PRICE, TRAVEL_CLASS, FLIGHT_CODE) VALUES (789, 870, 'BUSINESS', '12BC');	
INSERT INTO ROOT.TICKETS(TICKET_CODE, TICKET_STATUS, PASS_ID, PRICE_ID) VALUES ('12', 'ACTIVE', 1288, 12);	
INSERT INTO ROOT.USERS(USERNAME, PASSWORD, USER_ROLE) VALUES ('nastia', 'nastia', 1);	
INSERT INTO ROOT.USERS(USERNAME, PASSWORD, USER_ROLE) VALUES ('login', 'password', 1);	
INSERT INTO ROOT.USERS(USERNAME, PASSWORD, USER_ROLE) VALUES ('user', 'user', 0);	
ALTER TABLE ROOT.FLIGHTS
	ADD FOREIGN KEY (AIRLINE_CODE) 
	REFERENCES ROOT.AIRLINES (AIRLINES_ICAO_CODE);	

ALTER TABLE ROOT.FLIGHTS
	ADD FOREIGN KEY (AIRPORT_TO) 
	REFERENCES ROOT.AIRPORTS (AIRPORT_CODE);	

ALTER TABLE ROOT.FLIGHTS
	ADD FOREIGN KEY (AIRPORT_FROM) 
	REFERENCES ROOT.AIRPORTS (AIRPORT_CODE);	

ALTER TABLE ROOT.FLIGHTS
	ADD FOREIGN KEY (COPILOT_ID) 
	REFERENCES ROOT.PILOTS (PILOT_ID);	

ALTER TABLE ROOT.FLIGHTS
	ADD FOREIGN KEY (PILOT_ID) 
	REFERENCES ROOT.PILOTS (PILOT_ID);	


ALTER TABLE ROOT.PASSENGERS
	ADD FOREIGN KEY (USERNAME) 
	REFERENCES ROOT.USERS (USERNAME);	


ALTER TABLE ROOT.PRICES
	ADD FOREIGN KEY (FLIGHT_CODE) 
	REFERENCES ROOT.FLIGHTS (FLIGHT_CODE);	


ALTER TABLE ROOT.TICKETS
	ADD FOREIGN KEY (PASS_ID) 
	REFERENCES ROOT.PASSENGERS (PASS_ID);	

ALTER TABLE ROOT.TICKETS
	ADD FOREIGN KEY (PRICE_ID) 
	REFERENCES ROOT.PRICES (PRICE_ID);	


