/****** Object:  Table [dbo].[Booking]    Script Date: 4/5/2018 5:29:34 PM ******/

CREATE TABLE [dbo].[Tour](
	[tourId] [varchar](5) NOT NULL,
	[city] [varchar](20) NOT NULL,
	[tourCost] [numeric](6, 2) NOT NULL,
	[tourPackage] [varchar](20) NOT NULL,
	[availability] [varchar](4) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[tourId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Booking](
	[bookingId] [numeric](5, 0) NOT NULL,
	[customerName] [varchar](30) NOT NULL,
	[travelDate] [date] NOT NULL,
	[billAmount] [numeric](8, 2) NOT NULL,
	[tourId] [varchar](5) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[bookingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Booking]  WITH CHECK ADD FOREIGN KEY([tourId])
REFERENCES [dbo].[Tour] ([tourId])
GO

INSERT INTO Tour VALUES('T1001','Monterrey',2500,'S','N');
INSERT INTO Tour VALUES('T1002','Morelia',3100,'S','Y');
INSERT INTO Tour VALUES('T1003','CDMX',7000,'S','N');
INSERT INTO Tour VALUES('T1004','Guadalajara',2500,'S','Y');
INSERT INTO Tour VALUES('T1005','Tepic',3000,'S','Y');
INSERT INTO Tour VALUES('T1006','Reynosa',5000,'S','Y');
INSERT INTO Tour VALUES('T1007','Guadalajara',4000,'S','N');
INSERT INTO Tour VALUES('T1008','Cancun',3600,'P','Y');
INSERT INTO Tour VALUES('T1009','CDMX',4000,'P','Y');
INSERT INTO Tour VALUES('T1010','Cancun',4500,'G','Y');
INSERT INTO Tour VALUES('T1011','CDMX',3000,'G','Y');

INSERT INTO Booking VALUES (4001,'James',GETDATE()+2, 2500,'T1001');
INSERT INTO Booking VALUES (4002,'Jon',GETDATE()-3, 3000,'T1005');
INSERT INTO Booking VALUES (4003,'Debra',GETDATE()+3, 3000,'T1005');
INSERT INTO Booking VALUES (4004,'Jay',GETDATE()+3, 3000,'T1005');
INSERT INTO Booking VALUES (4005,'Charlie',GETDATE(), 7000,'T1003');
INSERT INTO Booking VALUES (4006,'Luis',GETDATE()+4, 3600,'T1008');