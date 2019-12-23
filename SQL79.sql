use DB79;

DROP TABLE Business;
DROP TABLE Private;
DROP TABLE Employee;

CREATE TABLE Employee 
	(idEmployee INT,
	fullname VARCHAR(50),
	department VARCHAR(80),
	leaves INT,
	overall DECIMAL(5),
	firstDate DATETIME,
	salary DECIMAL(5),
	email VARCHAR(40),
	username VARCHAR(40),
	password VARCHAR(40),
	PRIMARY KEY (idEmployee));

/*Pelates*/
CREATE TABLE Business
	(idBusiness INT NOT NULL,
	name VARCHAR(30),
	nmbrLoans INT,
	amount NUMERIC,
	type VARCHAR(30),
	idEmployee INT,
	PRIMARY KEY (idBusiness),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE Private
	(idPrivate INT,
	cards INT,
	name VARCHAR(30),
	nmbrLoans INT,
	amount NUMERIC,
	idEmployee INT,
	PRIMARY KEY (idPrivate),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (1, 'Konstantinos Alexopoulos', 'Manager', 23, 50, '2010-7-17', 2500, 'konstantinosAl@gmail.com', 'KA1', '1772019'); 
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (2, 'Alexandros Paulopoulos', 'Teller', 25, 70, '2007-5-19', 1300, 'alexandrosPa@gmail.com', 'AP2', '1952007');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (3, 'Leonidas Zafopoulos', 'Teller', 18, 80, '2009-3-24', 1300, 'leonidasZa@gmail.com', 'LZ3', '2432009');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (4, 'Rafail Iliopoulos', 'Private Customer Manager', 9, 45, '2012-7-22', 1800, 'rafailIl@gmail.com', 'RL4', '2272012');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (5, 'Panagiotis Euaggelou', 'Private Customer Manager Delays', 21, 90, '2017-10-25', 1500, 'panagiotisEu@gmail.com', 'PE5', '25102017');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (6, 'Spuros Maniatis', 'Private Customer Manager Vip', 5, 58, '2016-9-1', 1600, 'spurosMa@hotmail.com', 'SM6', '192016');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (7, 'Aggelos Papadakis', 'Business Service Manager', 27, 60, '2018-2-20', 2000, 'aggelosPa@hotmail.com', 'AP7', '2022018');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (8, 'Sotiris Kastrinos', 'Business Customer Manager', 19, 77, '2009-8-12', 1500, 'sotirisKa@hotmail.com', 'SK8', '1282009');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (9, 'Giorgos Mitropoulos', 'Business Customer Manager Vip', 14, 82, '2005-11-30', 1900, 'giorgosMi@gmail.com', 'GM9', '30112005');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (10, 'Ioannis Vasilopoulos', 'Business Customer Manager Delays', 20, 53, '2011-3-4', 1000, 'ioannisVa@gmail.com', 'IV10', '432011');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (11, 'Stratos Panopoulos', 'Loan Manager', 17, 38, '2009-4-23', 1200, 'stratosPa@gmail.com', 'SP11', '2342009');
INSERT INTO Employee(idEmployee, fullName, department, leaves, overall, firstDate, salary, email, username, password) VALUES (12, 'Dimitris Lamprou', 'Deposit Manager', 25, 74, '2007-9-7', 1800, 'dimitrisLa@hotmail.com', 'DL12', '792007');

INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES (1, 'Foodustry', 4, 25000, 'food company', 2);
INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES (2, 'tyres4you', 2, 20000, 'tyres company', 7);
INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES (3, 'GymLife', 1, 50000, 'gymnastics', 8);
INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES (4, 'Donmos Center', 7, 300000, 'Kaluntika', 9);
INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES (5, 'Blue Tiger', 3, 45000, 'indian food', 10);
INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES (6, 'Metaferw.gr', 9, 78000, 'metaforiki', 11);
INSERT INTO Business(idBusiness, name, nmbrLoans, amount, type, idEmployee) VALUES (7, 'Tsifoutis', 6, 99000, 'emporiko site', 12);

INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES (1, 3, 'Aggelos Giannakopoulos', 2, 20000, 3);
INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES (2, 7, 'Panagiotis Mpampis', 3, 17000, 4);
INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES (3, 1, 'Kyriakos Mourtos', 4, 30000, 5);
INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES (4, 2, 'Dionusis Karoulis', 1, 27000, 6);
INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES (5, 6, 'Vasilis Dominis', 3, 150000, 12);
INSERT INTO Private(idPrivate, cards, name, nmbrLoans, amount, idEmployee) VALUES (6, 1, 'Spuros Toutsikas', 9, 10000, 11);




/* Èá ìðïõí êáé åðéðëÝïí inserts, áðëÜ öñüíôéóá íá åéóÜãù ôá áðáñáßôçôá þóôå íá ëåéôïõñãåß ôï ðñüãñáììá*/
/* Äåí åßìáé óßãïõñïò ãéá ôçí áíôéóôïé÷ßá ôùí ôýðùí. Íá åëåã÷èïýí áðü ôçí áñ÷çãü ðïõ Ý÷åé ôï êïììÜôé êþäéêá ôçò óýíäåóçò*/
