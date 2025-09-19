-- Create the database
CREATE DATABASE IF NOT EXISTS HotelManagementSystem;

-- Use the newly created database
USE HotelManagementSystem;

--
-- Table structure for `login`
-- This table stores user login credentials and their roles.
--
CREATE TABLE login (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    roles VARCHAR(20) NOT NULL
);

-- Insert initial data into the `login` table.
-- 'Tom' and 'Alice' are admins, while 'Jerry' and 'Bob' are regular users.
INSERT INTO login (username, password, roles) VALUES
('Tom', 'Tom', 'Admin'),
('Jerry', 'Jerry', 'User'),
('Bob', 'Bob', 'User'),
('Alice', 'Alice', 'Admin');

--
-- Table structure for `room`
-- This table stores information about each room in the hotel.
--
CREATE TABLE room (
    room_number VARCHAR(10) PRIMARY KEY,
    bed_type VARCHAR(20),
    cleaning_status VARCHAR(20),
    availability VARCHAR(20),
    price VARCHAR(10)
);

--
-- Table structure for `employee`
-- This table stores details of all hotel staff members.
--
CREATE TABLE employee (
    name VARCHAR(20),
    age VARCHAR(20),
    gender VARCHAR(20),
    job VARCHAR(20),
    salary VARCHAR(20),
    phone VARCHAR(20),
    aadhar VARCHAR(20),
    email VARCHAR(20)
);

--
-- Table structure for `driver`
-- This table stores information about available drivers.
--
CREATE TABLE driver (
    name VARCHAR(20),
    age VARCHAR(20),
    gender VARCHAR(20),
    car_company VARCHAR(20),
    car_name VARCHAR(20),
    available VARCHAR(20),
    location VARCHAR(20)
);

--
-- Table structure for `department`
-- This table holds information about hotel departments and their budgets.
--
CREATE TABLE department (
    dept_name VARCHAR(50),
    budget VARCHAR(50)
);

-- Insert initial data into the `department` table.
INSERT INTO department VALUES
("Office", "500000"),
("Security", "400000"),
("Food", "800000"),
("Kitchen", "600000"),
("House Keeping", "200000");

--
-- Table structure for `customer`
-- This table stores customer details, including their allocated room and transaction information.
--
CREATE TABLE customer (
    customer_name VARCHAR(50),
    id_type VARCHAR(50),
    id_no VARCHAR(50),
    gender VARCHAR(50),
    country VARCHAR(50),
    allocated_room VARCHAR(50),
    check_in VARCHAR(50),
    deposite VARCHAR(50),
    check_out VARCHAR(50)
);

-- Add a column to store the checkout time.
-- This is already handled by the Java code, but it's good to have in the script.
-- ALTER TABLE customer ADD COLUMN check_out VARCHAR(50);


--
-- Table structure for `extra_fees`
-- This table logs all additional fees charged to customers, providing a detailed history.
--
CREATE TABLE extra_fees (
    fee_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id_number VARCHAR(20),
    room_number VARCHAR(10),
    fee_type VARCHAR(50),
    description VARCHAR(255),
    amount VARCHAR(10),
    date_added TIMESTAMP
);
