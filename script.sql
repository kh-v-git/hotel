
SET NAMES 'utf8';
SET CHARACTER SET 'utf8';

DROP DATABASE IF EXISTS hotel_db;

CREATE DATABASE hotel_db;
USE hotel_db;

CREATE TABLE user(
                     ID INT AUTO_INCREMENT PRIMARY KEY ,
                     email VARCHAR(50) UNIQUE NOT NULL ,
                     password VARCHAR(30) NOT NULL,
                     firstName VARCHAR(50) NOT NULL ,
                     lastName VARCHAR(50) NOT NULL,
                     phone VARCHAR(12) UNIQUE NOT NULL,
                     role ENUM('user', 'manager','admin') NOT NULL DEFAULT 'user',
                     status ENUM('active','deactivated') NOT NULL DEFAULT 'active',
                     about TINYTEXT
);

CREATE TABLE room (
                                   ID INT AUTO_INCREMENT PRIMARY KEY ,
                                   number INT UNIQUE ,
                                   adultCapacity TINYINT UNSIGNED NOT NULL DEFAULT 0,
                                   childCapacity TINYINT UNSIGNED NOT NULL DEFAULT 0,
                                   price DECIMAL(7,2) ,
                                   level ENUM('lux', 'gold','standard') NOT NULL DEFAULT 'standard',
                                   badSize ENUM('king', 'queen','twin', 'double', 'cot') NOT NULL DEFAULT 'cot',
                                   imageMainID INT DEFAULT 0,
                                   imageRoomID INT DEFAULT 0,
                                   imageBathID INT DEFAULT 0
);

CREATE TABLE room_services (
                               ID INT AUTO_INCREMENT PRIMARY KEY ,
                               roomID INT UNIQUE ,
                               service_0 VARCHAR(20),
                               service_1 VARCHAR(20),
                               service_2 VARCHAR(20),
                               service_3 VARCHAR(20),
                               service_4 VARCHAR(20),
                               service_5 VARCHAR(20),
                               FOREIGN KEY (roomID) REFERENCES room(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_request (
                        ID INT AUTO_INCREMENT PRIMARY KEY ,
                        userID INT,
                        status ENUM('pending', 'preorder', 'approved', 'closed'),
                        service ENUM('wifi', 'parking', 'spa', 'basin', 'gym', 'pets'),
                        badSize ENUM('king', 'queen','twin', 'double', 'cot') NOT NULL DEFAULT 'cot',
                        level ENUM('lux', 'gold','standard') NOT NULL DEFAULT 'standard',
                        adultCapacity TINYINT UNSIGNED NOT NULL DEFAULT 0,
                        childCapacity TINYINT UNSIGNED NOT NULL DEFAULT 0,
                        arrivalDay DATETIME,
                        departureDay DATETIME,
                        FOREIGN KEY (userId) REFERENCES user(ID) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE room_order (
                            ID INT AUTO_INCREMENT PRIMARY KEY ,
                            userId INT,
                            roomID INT,
                            userRequestID INT,
                            orderStatus ENUM('free', 'reserved', 'booked', 'expired', 'ended', 'inaccessible'),
                            orderDate DATETIME,
                            arrivalDay DATETIME,
                            departureDay DATETIME,
                            FOREIGN KEY (userId) REFERENCES user(ID) ON DELETE CASCADE ON UPDATE CASCADE ,
                            FOREIGN KEY (userRequestID) REFERENCES user_request(ID) ON DELETE CASCADE ON UPDATE CASCADE ,
                            FOREIGN KEY (roomID) REFERENCES room(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

