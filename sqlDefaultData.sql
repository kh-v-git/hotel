INSERT INTO hotel_db.user(email, password, first_name, last_name, phone, role, status, about)
VALUES ('root@root.com', '123', 'Admin', 'Nimda', '380939953400', 'admin', 'active',
        'Default admin user. Full access.'),
       ('user@user.com', '123', 'User', 'Resu', '380939953401', 'user', 'active', 'Default test user'),
       ('man@man.com', '123', 'Manager', 'Reganam', '380939953402', 'manager', 'active',
        'Manager user. Can contact with user'),
       ('usr@usr.com', '123', 'Тарас', 'Бульба', '380939953403', 'user', 'deactivated', 'Бот-коза Тарас.');

INSERT INTO hotel_db.room(number, adult_capacity, child_capacity, price, bed_size, about)
VALUES ('1', '1', '0', '10.55', 'cot', 'Room for one'),
       ('2', '1', '1', '12.55', 'cot', 'Room for one with children'),
       ('3', '2', '1', '15.75', 'double', 'Room for two with one children'),
       ('4', '2', '1', '17.75', 'double', 'Room for two with one children'),
       ('5', '2', '2', '18.10', 'double', 'Room for two with two children'),
       ('6', '3', '1', '25.25', 'twin', 'Room for three with one children'),
       ('7', '3', '2', '35.25', 'twin', 'Room for three with two children'),
       ('8', '4', '2', '70.85', 'queen', 'Room for four with two children'),
       ('9', '4', '2', '80.55', 'queen', 'Room for four with two children'),
       ('10', '2', '2', '100.00', 'king', 'Room for two with two children'),
       ('11', '2', '4', '125.55', 'king', 'Room for two with four children'),
       ('12', '1', '0', '5.55', 'cot', 'Room for one'),
       ('13', '1', '0', '8.45', 'cot', 'Room for one'),
       ('14', '1', '0', '8.45', 'cot', 'Room for one'),
       ('15', '2', '1', '17.75', 'double', 'Room for two with one children'),
       ('16', '2', '3', '20.75', 'double', 'Room for two with one children'),
       ('17', '3', '1', '20.25', 'twin', 'Room for three with one children'),
       ('18', '3', '2', '27.25', 'twin', 'Room for three with one children'),
       ('19', '3', '2', '28.25', 'twin', 'Room for three with one children'),
       ('20', '4', '1', '65.85', 'queen', 'Room for four with two children'),
       ('21', '4', '2', '75.85', 'queen', 'Room for four with two children'),
       ('22', '4', '2', '75.85', 'queen', 'Room for four with two children'),
       ('23', '4', '2', '70.85', 'queen', 'Room for four with two children'),
       ('24', '2', '2', '110.00', 'king', 'Room for two with two children'),
       ('25', '2', '2', '120.00', 'king', 'Room for two with two children'),
       ('26', '2', '2', '120.00', 'king', 'Room for two with two children');













