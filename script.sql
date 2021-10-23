
SET NAMES 'utf8';
SET CHARACTER SET 'utf8';

DROP DATABASE IF EXISTS hotel_db;

CREATE DATABASE hotel_db;
USE hotel_db;

CREATE TABLE user(
                     userID INT AUTO_INCREMENT PRIMARY KEY ,
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
                      roomID INT AUTO_INCREMENT PRIMARY KEY ,
                      number INT UNIQUE ,
                      adultCapacity TINYINT UNSIGNED NOT NULL DEFAULT 0,
                      childCapacity TINYINT UNSIGNED NOT NULL DEFAULT 0,
                      price DECIMAL(7,2) ,
                      level ENUM('lux', 'gold','standard') NOT NULL DEFAULT 'standard',
                      badSize ENUM('king', 'queen','twin', 'double', 'cot') NOT NULL DEFAULT 'cot'
);

CREATE TABLE room_image (
    roomImageID INT AUTO_INCREMENT PRIMARY KEY ,
    roomID INT,
    caption VARCHAR(45) NOT NULL,
    image LONGBLOB NOT NULL,
    FOREIGN KEY (roomID) REFERENCES room(roomID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_request (
                              userRequestID INT AUTO_INCREMENT PRIMARY KEY ,
                              userID INT,
                              status ENUM('pending', 'preorder', 'approved', 'closed'),
                              badSize ENUM('king', 'queen','twin', 'double', 'cot') NOT NULL DEFAULT 'cot',
                              level ENUM('lux', 'gold','standard') NOT NULL DEFAULT 'standard',
                              adultCapacity TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              childCapacity TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              arrivalDay DATETIME,
                              departureDay DATETIME,
                              FOREIGN KEY (userId) REFERENCES user(userID) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE room_order (
                            roomOrderID INT AUTO_INCREMENT PRIMARY KEY ,
                            userId INT,
                            roomID INT,
                            userRequestID INT,
                            orderStatus ENUM('free', 'reserved', 'booked', 'expired', 'ended', 'inaccessible'),
                            orderDate DATETIME,
                            arrivalDay DATETIME,
                            departureDay DATETIME,
                            FOREIGN KEY (userId) REFERENCES user(userID) ON DELETE CASCADE ON UPDATE CASCADE ,
                            FOREIGN KEY (userRequestID) REFERENCES user_request(userRequestID) ON DELETE CASCADE ON UPDATE CASCADE ,
                            FOREIGN KEY (roomID) REFERENCES room(roomID) ON DELETE CASCADE ON UPDATE CASCADE
);

