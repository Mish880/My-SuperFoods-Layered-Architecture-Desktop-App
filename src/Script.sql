CREATE DATABASE SuperFoods;

DROP TABLE IF EXISTS Login;
CREATE TABLE IF NOT EXISTS Login(
 id VARCHAR (15),
 Password VARCHAR (15),
 role VARCHAR (45),
 CONSTRAINT PRIMARY KEY (id)
 );
INSERT INTO Login(id,Password,role) VALUES ('A001','A12','Adminstrator');
INSERT INTO Login(id,Password,role) VALUES ('C001','C12','Cashier');

Show tables;
desc Login;
#--------------------------------
DROP TABLE IF EXISTS Customer;
CREATE TABLE  IF NOT EXISTS Customer(
CustId VARCHAR (15),
CustTitle VARCHAR (10),
CustName VARCHAR (30),
CustAddress VARCHAR (30),
City VARCHAR (20),
Province VARCHAR(20),
PostalCode VARCHAR(9),
CONSTRAINT PRIMARY KEY (CustId)
);
Show tables;
desc Customer;
#-----------------------------------
DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
Code VARCHAR(15),
Description VARCHAR(50),
PackSize VARCHAR (20),
QtyOnHand INT DEFAULT 0,
UnitPrice DOUBLE DEFAULT 0.00,
CONSTRAINT PRIMARY KEY (Code)
);
Show tables;
desc Item;
#--------------------------------------
DROP TABLE IF EXISTS ROrder;
CREATE TABLE IF NOT EXISTS ROrder(
orderId VARCHAR(15),
custId VARCHAR(15),
orderDate DATE,
time VARCHAR(15),
cost DOUBLE,
CONSTRAINT PRIMARY KEY (orderId),
CONSTRAINT FOREIGN KEY (custId) REFERENCES Customer(CustId) ON DELETE CASCADE ON UPDATE CASCADE
);
Show tables;
desc ROrder;
#---------------------------------------
DROP TABLE IF EXISTS `Order Detail`;
CREATE TABLE IF NOT EXISTS `Order Detail`(
itemCode VARCHAR(15),
orderId VARCHAR(15),
qty INT,
price DOUBLE,
CONSTRAINT PRIMARY KEY (itemCode, orderId),
CONSTRAINT FOREIGN KEY (itemCode) REFERENCES Item(code) ON DELETE CASCADE ON UPDATE CASCADE ,
CONSTRAINT FOREIGN KEY (orderId) REFERENCES ROrder(orderId) ON DELETE CASCADE ON UPDATE CASCADE
);
Show tables;
desc `Order Detail`;
#----------------------------------------
