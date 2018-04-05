DROP TABLE Tour CASCADE CONSTRAINTS;
DROP TABLE Booking CASCADE CONSTRAINTS;

DROP SEQUENCE hibernate_sequence;

CREATE sequence hibernate_sequence START WITH 4007 INCREMENT BY 1;

CREATE TABLE Tour (
tourId VARCHAR2(5) PRIMARY KEY,
city VARCHAR2(20) NOT NULL,
tourCost NUMBER(6,2) NOT NULL,
tourPackage VARCHAR2(20) NOT NULL,
availability VARCHAR2(4) NOT NULL
);


INSERT INTO Tour VALUES('T1001','Mysore',2500,'S','N');
INSERT INTO Tour VALUES('T1002','Ooty',3100,'S','Y');
INSERT INTO Tour VALUES('T1003','Bangalore',7000,'S','N');
INSERT INTO Tour VALUES('T1004','Mangalore',2500,'S','Y');
INSERT INTO Tour VALUES('T1005','Chennai',3000,'S','Y');
INSERT INTO Tour VALUES('T1006','Goa',5000,'S','Y');
INSERT INTO Tour VALUES('T1007','Coorg',4000,'S','N');
INSERT INTO Tour VALUES('T1008','mysore',3600,'P','Y');
INSERT INTO Tour VALUES('T1009','Bangalore',4000,'P','Y');
INSERT INTO Tour VALUES('T1010','Mysore',4500,'G','Y');
INSERT INTO Tour VALUES('T1011','Bangalore',3000,'G','Y');


CREATE TABLE Booking(
bookingId NUMBER(5) PRIMARY KEY, 
customerName VARCHAR2(30) NOT NULL, 
travelDate	DATE NOT NULL,
billAmount NUMBER(8,2) NOT NULL,
tourId VARCHAR2(5) REFERENCES Tour(tourId) NOT NULL
);

INSERT INTO Booking VALUES (4001,'Pragyan',sysdate+2, 2500,'T1001');
INSERT INTO Booking VALUES (4002,'Ankita',sysdate-3, 3000,'T1005');
INSERT INTO Booking VALUES (4003,'Debu',sysdate+3, 3000,'T1005');
INSERT INTO Booking VALUES (4004,'Jay',sysdate+3, 3000,'T1005');
INSERT INTO Booking VALUES (4005,'Jia',sysdate, 7000,'T1003');
INSERT INTO Booking VALUES (4006,'Parag',sysdate+4, 3600,'T1008');




select * from Tour;

select * from Booking