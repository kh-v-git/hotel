SET NAMES 'utf8';
SET CHARACTER SET 'utf8';

DROP DATABASE IF EXISTS hotel_db;

CREATE DATABASE hotel_db;
USE hotel_db;

CREATE TABLE user
(
    user_id    INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(50) UNIQUE               NOT NULL,
    password   VARCHAR(128)                      NOT NULL,
    first_name VARCHAR(50)                      NOT NULL,
    last_name  VARCHAR(50)                      NOT NULL,
    phone      VARCHAR(12) UNIQUE               NOT NULL,
    role       ENUM ('user', 'manager','admin') NOT NULL DEFAULT 'user',
    status     ENUM ('active','deactivated')    NOT NULL DEFAULT 'active',
    about      TINYTEXT
);

CREATE TABLE room
(
    room_id        INT AUTO_INCREMENT PRIMARY KEY,
    number         INT UNIQUE,
    adult_capacity TINYINT UNSIGNED                               NOT NULL DEFAULT 0,
    child_capacity TINYINT UNSIGNED                               NOT NULL DEFAULT 0,
    price          DECIMAL(7, 2),
    bed_size       ENUM ('king', 'queen','twin', 'double', 'cot') NOT NULL DEFAULT 'cot',
    about          TINYTEXT
);

CREATE TABLE room_image
(
    room_image_id INT AUTO_INCREMENT PRIMARY KEY,
    room_id       INT,
    caption       VARCHAR(50) NOT NULL,
    image         LONGBLOB    NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_request
(
    user_request_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT,
    status          ENUM ('pending', 'preorder', 'approved', 'closed'),
    bed_size         ENUM ('king', 'queen','twin', 'double', 'cot') NOT NULL DEFAULT 'cot',
    adult_capacity  TINYINT UNSIGNED                               NOT NULL DEFAULT 0,
    child_capacity  TINYINT UNSIGNED                               NOT NULL DEFAULT 0,
    arrival_day     DATETIME,
    departure_day   DATETIME,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE room_order
(
    room_order_id    INT AUTO_INCREMENT PRIMARY KEY,
    user_id        INT,
    room_id       INT,
    user_request_id INT,
    order_status   ENUM ('free', 'reserved', 'booked', 'expired', 'ended', 'inaccessible'),
    order_date     DATETIME,
    arrival_day    DATETIME,
    departure_day  DATETIME,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_request_id) REFERENCES user_request (user_request_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE ON UPDATE CASCADE
);

