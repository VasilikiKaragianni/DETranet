use DB79;

DROP TABLE Business;
DROP TABLE Private;
DROP TABLE Customer;
DROP TABLE Teller;
DROP TABLE PrivateCustomerManagerVip;
DROP TABLE PrivateCustomerManagerDelays;
DROP TABLE PrivateCustomerManager;
DROP TABLE BusinessCustomerManagerVip;
DROP TABLE BusinessCustomerManagerDelays;
DROP TABLE BusinessCustomerManager;
DROP TABLE LoanManager;
DROP TABLE DepositManager;
DROP TABLE Manager;
DROP TABLE Employee;

CREATE TABLE Employee 
	(idEmployee INT,
	fullname VARCHAR(50),
	department VARCHAR(30),
	overall DECIMAL(5),
	firstdate DATETIME,
	salary DECIMAL(5),
	email VARCHAR(40),
	username VARCHAR(40),
	password VARCHAR(40),
	PRIMARY KEY (idEmployee));

CREATE TABLE Manager 
	(idEmployee INT,
	goalsLoanManager VARCHAR(400),
	goalsDepositManager VARCHAR(400),
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE DepositManager
	(idEmployee INT,
	pCMGoals VARCHAR(400),
	tellerGoals VARCHAR(400),
	pCMVipGoals VARCHAR(400),
	pCMDelaysGoals VARCHAR(400),
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE LoanManager
	(idEmployee INT,
	bSMGoals VARCHAR(400),
	cSMGoals VARCHAR(400),
	cSMVipGoals VARCHAR(400),
	cSMDelaysGoals VARCHAR(400),
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE BusinessCustomerManager 
	(idEmployee INT,
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE BusinessCustomerManagerDelays  
	(idEmployee INT,
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE BusinessCustomerManagerVip  
	(idEmployee INT,
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE PrivateCustomerManager  
	(idEmployee INT,
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE PrivateCustomerManagerDelays   
	(idEmployee INT,
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE PrivateCustomerManagerVip   
	(idEmployee INT,
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE Teller   
	(idEmployee INT,
	PRIMARY KEY (idEmployee),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE Customer
	(idCustomer INT,
	name VARCHAR(30),
	nmbrLoans INT,
	amount DECIMAL(3),
	idEmployee INT
	PRIMARY KEY (idCustomer),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE Business
	(idCustomer INT,
	type VARCHAR(30),
	idEmployee INT,
	PRIMARY KEY (idCustomer),
	FOREIGN KEY (idEmployee) REFERENCES Employee);

CREATE TABLE Private
	(idCustomer INT,
	cards INT,
	idEmployee INT,
	PRIMARY KEY (idCustomer),
	FOREIGN KEY (idEmployee) REFERENCES Employee);