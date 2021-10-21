INSERT INTO hotel_db.user(email, password, firstName, lastName, phone, role, status, about)
VALUES
       ('root@root.com', '123', 'Admin', 'Nimda', '380939953400', 'admin', 'active', 'Default admin user. Full access.'),
       ('user@user.com', '123', 'User', 'Resu', '380939953401', 'user', 'active', 'Default test user'),
       ('man@man.com', '123', 'Manager', 'Reganam', '380939953402', 'manager', 'active', 'Manager user. Can contact with user'),
       ('usr@usr.com', '123', 'Тарас', 'Бульба', '380939953403','user', 'deactivated', 'Бот-коза Тарас.');

INSERT INTO hotel_db.room(number, adultCapacity, childCapacity, price, level, badSize, imageMainID)
VALUES
('1', '1', '0', '10', 'standard', 'cot', '1'),
('2', '2', '1', '15', 'standard', 'double', '1'),
('3', '2', '2', '15', 'standard', 'double', '1'),
('4', '3', '2', '25', 'gold','twin', '1'),
('5', '4', '2', '25', 'gold','queen', '1'),
('6', '2', '0', '40', 'lux','king', '1'),
('7', '2', '2', '40', 'lux','king', '1');

