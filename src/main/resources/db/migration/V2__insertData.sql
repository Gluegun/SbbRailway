insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('19/02/1991', '%d/%m/%Y %H:%i:%s'), 'Ivan', 'Titov', '1', 'admin');
insert into roles (passenger_id, roles)
values (1, 'ADMIN');
insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('20/05/1949', '%d/%m/%Y %H:%i:%s'), 'Petr', 'Smirnov', '1', 'petrsmirnov');
insert into roles (passenger_id, roles)
values (2, 'ADMIN');
insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('07/04/1978', '%d/%m/%Y %H:%i:%s'), 'Maria', 'Veselova', '1', 'mariaveselova');
insert into roles (passenger_id, roles)
values (3, 'USER');
insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('14/10/2000', '%d/%m/%Y %H:%i:%s'), 'Alexandra', 'Mishina', '1', 'sashamishina');
insert into roles (passenger_id, roles)
values (4, 'USER');
insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('12/09/1987', '%d/%m/%Y %H:%i:%s'), 'Maxim', 'Ivanov', '1', 'maximivanov');
insert into roles (passenger_id, roles)
values (5, 'USER');

insert into stations (name)
values ('Яблочная');
insert into stations (name)
values ('Грушевая');
insert into stations (name)
values ('Виноградная');
insert into stations (name)
values ('Арбузная');
insert into stations (name)
values ('Черешневая');
insert into stations (name)
values ('Вишневая');

insert into trains(seats_amount, number)
values (1000, 'T001');
insert into trains(seats_amount, number)
values (980, 'T002');
insert into trains(seats_amount, number)
values (100, 'T003');
insert into trains(seats_amount, number)
values (10, 'T004');
insert into trains(seats_amount, number)
values (100, 'T005');

insert into schedule(departure_time, station_id, train_id)
values ('10:00', 1, 1);
insert into schedule(departure_time, station_id, train_id)
values ('11:00', 2, 1);
insert into schedule(departure_time, station_id, train_id)
values ('12:00', 3, 1);
insert into schedule(departure_time, station_id, train_id)
values ('13:00', 4, 1);
insert into schedule(departure_time, station_id, train_id)
values ('14:00', 5, 1);
insert into schedule(departure_time, station_id, train_id)
values ('15:00', 6, 1);

insert into schedule(departure_time, station_id, train_id)
values ('11:00', 1, 2);
insert into schedule(departure_time, station_id, train_id)
values ('12:00', 2, 2);
insert into schedule(departure_time, station_id, train_id)
values ('13:00', 3, 2);
insert into schedule(departure_time, station_id, train_id)
values ('14:00', 4, 2);
insert into schedule(departure_time, station_id, train_id)
values ('15:00', 5, 2);

insert into schedule(departure_time, station_id, train_id)
values ('13:00', 2, 3);
insert into schedule(departure_time, station_id, train_id)
values ('14:00', 3, 3);
insert into schedule(departure_time, station_id, train_id)
values ('15:00', 4, 3);
insert into schedule(departure_time, station_id, train_id)
values ('16:00', 5, 3);
insert into schedule(departure_time, station_id, train_id)
values ('17:00', 6, 3);


insert into schedule(departure_time, station_id, train_id)
values ('16:00', 6, 4);
insert into schedule(departure_time, station_id, train_id)
values ('17:00', 5, 4);
insert into schedule(departure_time, station_id, train_id)
values ('18:00', 4, 4);
insert into schedule(departure_time, station_id, train_id)
values ('19:00', 3, 4);
insert into schedule(departure_time, station_id, train_id)
values ('20:00', 2, 4);
insert into schedule(departure_time, station_id, train_id)
values ('21:00', 1, 4);

insert into schedule(departure_time, station_id, train_id)
values ('16:00', 5, 5);
insert into schedule(departure_time, station_id, train_id)
values ('17:00', 4, 5);
insert into schedule(departure_time, station_id, train_id)
values ('18:00', 3, 5);
insert into schedule(departure_time, station_id, train_id)
values ('19:00', 2, 5);
insert into schedule(departure_time, station_id, train_id)
values ('20:00', 1, 5);

INSERT INTO tickets(departureTime, passenger_id, train_id)
VALUES ('10:00', 2, 1);
INSERT INTO tickets(departureTime, passenger_id, train_id)
VALUES ('11:00', 1, 1);

INSERT INTO tickets(departureTime, passenger_id, train_id)
VALUES ('11:00', 4, 2);

INSERT INTO tickets(departureTime, passenger_id, train_id)
VALUES ('13:00', 3, 3);
INSERT INTO tickets(departureTime, passenger_id, train_id)
VALUES ('13:00', 5, 3);
