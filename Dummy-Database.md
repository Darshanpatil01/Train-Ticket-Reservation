-- Create Database
CREATE DATABASE reservation;
USE reservation;

-- CUSTOMER TABLE
CREATE TABLE customer (
    mailid VARCHAR(40) PRIMARY KEY,
    pword VARCHAR(20) NOT NULL,
    fname VARCHAR(20) NOT NULL,
    lname VARCHAR(20),
    addr VARCHAR(100),
    phno BIGINT NOT NULL
);

-- ADMIN TABLE
CREATE TABLE admin (
    mailid VARCHAR(40) PRIMARY KEY,
    pword VARCHAR(20) NOT NULL,
    fname VARCHAR(20) NOT NULL,
    lname VARCHAR(20),
    addr VARCHAR(100),
    phno BIGINT NOT NULL
);

-- TRAIN TABLE
CREATE TABLE train (
    tr_no INT PRIMARY KEY,
    tr_name VARCHAR(70) NOT NULL,
    from_stn VARCHAR(20) NOT NULL,
    to_stn VARCHAR(20) NOT NULL,
    seats INT NOT NULL,
    fare DECIMAL(10,2) NOT NULL
);

-- HISTORY TABLE
CREATE TABLE history (
    transid VARCHAR(36) PRIMARY KEY,
    mailid VARCHAR(40),
    tr_no INT,
    travel_date DATE,
    from_stn VARCHAR(20) NOT NULL,
    to_stn VARCHAR(20) NOT NULL,
    seats INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    
    FOREIGN KEY (mailid) REFERENCES customer(mailid)
);

-- INSERT DATA

INSERT INTO admin VALUES
('admin@demo.com','admin','System','Admin','Demo Address 123 colony','9874561230');

INSERT INTO customer VALUES
('shashi@demo.com','shashi','Shashi','Raj','Kolkata, West Bengal',954745222);

INSERT INTO train VALUES
(10001,'JODHPUR EXP','HOWRAH','JODHPUR',152,490.50),
(10002,'YAMUNA EXP','GAYA','DELHI',52,550.50),
(10003,'NILANCHAL EXP','GAYA','HOWRAH',92,451),
(10004,'JAN SATABDI EXP','RANCHI','PATNA',182,550),
(10005,'GANGE EXP','MUMBAI','KERALA',12,945),
(10006,'GARIB RATH EXP','PATNA','DELHI',1,1450.75),
(10007,'AJMER-SEALDAH EXP','SEALDAH','AJMER',120,1000.50),
(10008,'MUMBAI MAIL','HOWRAH','MUMBAI',100,2150.75);

INSERT INTO history VALUES
('BBC374-NSDF-4673','shashi@demo.com',10001,'2024-02-02','HOWRAH','JODHPUR',2,981),
('BBC375-NSDF-4675','shashi@demo.com',10004,'2024-01-12','RANCHI','PATNA',1,550),
('BBC373-NSDF-4674','shashi@demo.com',10006,'2024-07-22','PATNA','DELHI',3,4352.25);